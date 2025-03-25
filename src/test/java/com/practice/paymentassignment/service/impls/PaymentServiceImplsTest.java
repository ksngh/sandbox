package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.repository.PaymentRepository;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.PaymentClaimService;
import com.practice.paymentassignment.service.PaymentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("결제 서비스 테스트")
class PaymentServiceImplsTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentClaimRepository paymentClaimRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    private static final int THREAD_COUNT = 100;
    private static final int EXECUTIONS_PER_THREAD = 10;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger abortCount = new AtomicInteger(0);
    private PaymentClaim savedPaymentClaim;
    private User savedUser;
    private Franchise savedFranchise;
    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        User user = User.of("김성호");
        user.charge(100000);
        savedUser = userRepository.save(user);

        Franchise franchise = Franchise.of("스마트핀테크 가맹점");
        savedFranchise = franchiseRepository.save(franchise);

        PaymentClaim paymentClaim = PaymentClaim.of(user,franchise,30000L);
        savedPaymentClaim = paymentClaimRepository.save(paymentClaim);
    }

    @Test
    @DisplayName("결제 생성 테스트, 성공 시 결과 반환")
    void create() {
        //given
        Long paymentClaimId = 1L;

        //when
        PaymentCreate.Response paymentCreateResponse = paymentService.create(paymentClaimId);

        //then
        System.out.println(paymentCreateResponse.getId());
        assertNotNull(paymentCreateResponse);

    }

    @Test
    @DisplayName("동시에 결제 요청을 여러번 할 시, 하나만 성공하고 나머진 실패 또는 충돌")
    void testSerializableTransactionConcurrency() throws InterruptedException {
        List<Long> paymentClaimIds = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            PaymentClaim paymentClaim = PaymentClaim.of(savedUser, savedFranchise, 30000L);
            PaymentClaim saved = paymentClaimRepository.save(paymentClaim);
            paymentClaimIds.add(saved.getId());
        }
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT * EXECUTIONS_PER_THREAD);
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < EXECUTIONS_PER_THREAD; j++) {
                    Long claimId = paymentClaimIds.get(random.nextInt(paymentClaimIds.size()));
                    try {
                        PaymentCreate.Response response = paymentService.create(claimId);
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        if (isSerializationFailure(e)) {
                            abortCount.incrementAndGet();
                        } else if (e instanceof IllegalStateException) {
                            // 중복 결제 시도
                        } else {
                            e.printStackTrace(); // 알 수 없는 예외
                        }
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        latch.await(); // 모든 스레드 작업 완료 대기
        long elapsed = System.nanoTime() - startTime;

        System.out.println("✅ 성공 횟수: " + successCount.get());
        System.out.println("❌ Abort 횟수 (충돌 감지): " + abortCount.get());
        System.out.println("⏱️ 걸린 시간 (초): " + elapsed / 1_000_000_000.0);
    }

    private boolean isSerializationFailure(Throwable e) {
        while (e != null) {
            if (e instanceof org.postgresql.util.PSQLException &&
                    e.getMessage().contains("could not serialize access")) {
                return true;
            }
            e = e.getCause();
        }
        return false;
    }

}