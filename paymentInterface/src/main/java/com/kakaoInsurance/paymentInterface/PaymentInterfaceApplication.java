package com.kakaoInsurance.paymentInterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.kakaoInsurance.paymentInterface.**")
public class PaymentInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentInterfaceApplication.class, args);
	}

}
