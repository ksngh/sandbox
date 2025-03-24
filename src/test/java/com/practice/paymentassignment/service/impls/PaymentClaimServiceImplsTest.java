package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.PaymentClaimService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("결제 요청 서비스 테스트")
class PaymentClaimServiceImplsTest {

    @Autowired
    private PaymentClaimService paymentClaimService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @BeforeEach
    void setUp() {
        User user = User.of("김성호");
        userRepository.save(user);

        Franchise franchise = Franchise.of("스마트핀테크 가맹점");
        franchiseRepository.save(franchise);
    }

    @Test
    @DisplayName("결제 요청 생성 테스트, 성공 시 response 반환")
    void createPaymentClaim() {
        //given
        Long userId = 1L;
        Long franchiseId = 1L;
        PaymentClaimCreate.Request paymentClaimCreateRequest = new PaymentClaimCreate.Request(userId, 30000L, franchiseId);

        //when
        PaymentClaimCreate.Response paymentClaimCreateResponse = paymentClaimService.createPaymentClaim(paymentClaimCreateRequest);

        //then
        //todo: 수정할 것
        assertNotNull(paymentClaimCreateResponse);

    }
}