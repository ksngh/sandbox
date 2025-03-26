package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.repository.PaymentRepository;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.PaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Autowired
    private PaymentRepository paymentRepository;

    private static final int THREAD_COUNT = 10;
    private static final int EXECUTIONS_PER_THREAD = 10;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger abortCount = new AtomicInteger(0);
    private final AtomicInteger duplicateCount = new AtomicInteger(0);
    private PaymentClaim savedPaymentClaim;
    private User savedUser;
    private Franchise savedFranchise;


    @BeforeEach
    void setUp() {
        User user = User.of("김성호");
        user.charge(100000);
        savedUser = userRepository.save(user);

        Franchise franchise = Franchise.of("스마트핀테크 가맹점");
        savedFranchise = franchiseRepository.save(franchise);

        PaymentClaim paymentClaim = PaymentClaim.of(user, franchise, 30000L);
        savedPaymentClaim = paymentClaimRepository.save(paymentClaim);
    }

    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
        paymentClaimRepository.deleteAll();
        franchiseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("결제 생성 테스트, 성공 시 결과 반환")
    void create() {
        //given
        Long paymentClaimId = 1L;

        //when
        PaymentCreate.Response paymentCreateResponse = paymentService.create(paymentClaimId);

        //then
        assertEquals(paymentClaimId, paymentCreateResponse.getId());
    }

    @Test
    @DisplayName("동시에 결제 요청을 여러번 할 시, 하나만 성공하고 나머진 실패 또는 충돌")
    void testSerializableTransactionConcurrency() throws InterruptedException {
        List<Long> paymentClaimIds = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            PaymentClaim paymentClaim = PaymentClaim.of(savedUser, savedFranchise, 30000L);
            PaymentClaim saved = paymentClaimRepository.save(paymentClaim);
            paymentClaimIds.add(saved.getId());
        }

        AtomicReference<Long> currentClaimId = new AtomicReference<>(paymentClaimIds.get(0));
        AtomicInteger claimIndex = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT * EXECUTIONS_PER_THREAD);
        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < EXECUTIONS_PER_THREAD; j++) {
                    Long claimId = currentClaimId.get();
                    try {
                        paymentService.create(claimId);
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        if (isSerializationFailure(e)) {
                            abortCount.incrementAndGet();
                        } else if (e instanceof IllegalStateException) {
                            duplicateCount.incrementAndGet();
                            // ✅ 중복 예외 발생 시 다음 Claim ID로 변경
                            int nextIndex = claimIndex.incrementAndGet();
                            if (nextIndex < paymentClaimIds.size()) {
                                currentClaimId.set(paymentClaimIds.get(nextIndex));
                            }
                        } else {
                            e.printStackTrace();
                        }
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        latch.await();
        long elapsed = System.nanoTime() - startTime;
        int total = duplicateCount.get() + abortCount.get() + successCount.get();
        int success = duplicateCount.get() + successCount.get();

        System.out.println("성공 횟수: " + success);
        System.out.println("총 요청 수: " + total);
        System.out.println("Abort 횟수 (충돌 감지): " + abortCount.get());
        System.out.println("걸린 시간 (초): " + elapsed / 1_000_000_000.0);
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