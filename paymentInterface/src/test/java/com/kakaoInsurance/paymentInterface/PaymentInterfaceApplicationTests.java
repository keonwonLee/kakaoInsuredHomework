package com.kakaoInsurance.paymentInterface;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.kakaoInsurance.paymentInterface.controller.PaymentController;
import com.kakaoInsurance.paymentInterface.model.CancelResVO;
import com.kakaoInsurance.paymentInterface.model.InquiryResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentVO;


@SpringBootTest
class PaymentInterfaceApplicationTests {

	@Autowired
	PaymentController paymentController;
	
	@Test
	public void 결재취소조회() {
		PaymentVO paymentVO = new PaymentVO();
		try {
			String manageNum = "";
			
			paymentVO.setCardNum("43920210524191553527");
			paymentVO.setExpDt("0226");
			paymentVO.setCvc("123");
			paymentVO.setInstMth(3);
			paymentVO.setPayAmt((long) 10000);
			paymentVO.setVat((long) 1000);
			ResponseEntity<PaymentResVO> paymentResult = paymentController.payment(paymentVO);
			manageNum = paymentResult.getBody().getManageNum();
			assertNotNull(paymentResult);
			paymentVO = new PaymentVO();
			
			paymentVO.setManageNum(manageNum);
			paymentVO.setCanAmt((long) 100);
			paymentVO.setCanVat((long) 0);
			ResponseEntity<CancelResVO> cancelResult = paymentController.cancel(paymentVO);
			manageNum = cancelResult.getBody().getManageNum();
			assertNotNull(cancelResult);
			paymentVO = new PaymentVO();
			
			paymentVO.setManageNum(manageNum);
			ResponseEntity<InquiryResVO> inquiryResult = paymentController.inquiry(paymentVO);
			assertNotNull(inquiryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}