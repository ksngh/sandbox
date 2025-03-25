package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.PaymentClaimService;
import com.practice.paymentassignment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    private static final int THREAD_COUNT = 10;
    private static final int EXECUTIONS_PER_THREAD = 100;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger abortCount = new AtomicInteger(0);
    private PaymentClaim savedPaymentClaim;

    @BeforeEach
    void setUp() {
        User user = User.of("김성호");
        userRepository.save(user);

        Franchise franchise = Franchise.of("스마트핀테크 가맹점");
        franchiseRepository.save(franchise);

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
        assertNotNull(paymentCreateResponse);

    }

    @Test
    @DisplayName("동시에 결제 요청을 여러번 할 시, 하나만 성공")
    void testSerializableTransactionConcurrency() throws InterruptedException {
        //given
        Long paymentClaimId = 1L;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.nanoTime();
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT * EXECUTIONS_PER_THREAD);

        //when
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < EXECUTIONS_PER_THREAD; j++) {
                    try {
                        paymentService.create(paymentClaimId);
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        if (isSerializationFailure(e)) {
                            abortCount.incrementAndGet();
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

        //then
        System.out.println("성공 횟수: " + successCount.get());
        System.out.println("Abort 횟수 (충돌 감지): " + abortCount.get());
        System.out.println("걸린 시간 (초): " + elapsed / 1_000_000_000.0);
    }

    private boolean isSerializationFailure(Throwable e) {
        while (e != null) {
            if (e.getMessage().contains("could not serialize access")) {
                return true;
            }
            e = e.getCause();
        }
        return false;
    }

}