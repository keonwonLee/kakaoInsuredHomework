package com.kakaoInsurance.paymentInterface.service;

import org.springframework.stereotype.Service;

import com.kakaoInsurance.paymentInterface.model.CancelResVO;
import com.kakaoInsurance.paymentInterface.model.InquiryResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentVO;

@Service
public interface PaymentService {
	PaymentResVO payment(PaymentVO paramPaymentVO);
	
	CancelResVO cancel(PaymentVO paymentVO);
	
	InquiryResVO inquiry(PaymentVO paymentVO);
}
