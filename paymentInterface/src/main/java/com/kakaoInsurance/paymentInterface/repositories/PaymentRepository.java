package com.kakaoInsurance.paymentInterface.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kakaoInsurance.paymentInterface.model.PaymentVO;

@Repository
@EnableJpaRepositories
public interface PaymentRepository extends JpaRepository<PaymentVO, String> {
	@Query(value="SELECT p.* from PAYMENT p WHERE p.MANAGE_NUM = :manageNum", nativeQuery=true)
	PaymentVO findByPaymentId( @Param("manageNum") String manageNum);
}
