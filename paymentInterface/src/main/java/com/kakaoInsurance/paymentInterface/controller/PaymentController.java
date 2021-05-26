package com.kakaoInsurance.paymentInterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakaoInsurance.paymentInterface.model.CancelResVO;
import com.kakaoInsurance.paymentInterface.model.InquiryResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentVO;
import com.kakaoInsurance.paymentInterface.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	/** 
	 * * 결제 요청
	 * * @param post 
	 * * @return 
	 * * @throws Exception 
	 * */ 
	@PostMapping("/payment") 
	public ResponseEntity<PaymentResVO> payment(@RequestBody PaymentVO paymentVO) throws Exception {
		
		/*
		{
		  "cardNum": "43920210524191553527",
		  "expDt" : "0226",
		  "cvc" : "123",
		  "instMth" : 3,
		  "payAmt" : 10000,
		  "vat" : 1000
		}
		*/
		PaymentResVO paymentResVO = paymentService.payment(paymentVO);
		return new ResponseEntity<>(paymentResVO, HttpStatus.OK); 
	}
	
	/** 
	 * * 결제 취소
	 * * @param post 
	 * * @return 
	 * * @throws Exception 
	 * */ 
	@PostMapping("/cancel") 
	public ResponseEntity<CancelResVO> cancel(@RequestBody PaymentVO pramPaymentVO) throws Exception {
		
		/*
		{
		  "manageNum": "91320210526231103523",
		  "canAmt" : 100,
		  "canVat" : 0
		}
		 */
		CancelResVO cancelResVO = paymentService.cancel(pramPaymentVO);
		return new ResponseEntity<>(cancelResVO, HttpStatus.OK); 
	}
	
	/** 
	 * * 결제 조회
	 * * @param post 
	 * * @return 
	 * * @throws Exception 
	 * */ 
	@PostMapping("/inquiry") 
	public ResponseEntity<InquiryResVO> inquiry(@RequestBody PaymentVO paymentVO) throws Exception {
		
		/*
		{
		  "manageNum": "18820210526233120099"
		}
		 */
		InquiryResVO inquiryResVO = paymentService.inquiry(paymentVO);
		return new ResponseEntity<>(inquiryResVO, HttpStatus.OK); 
	}
}
