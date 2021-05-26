package com.kakaoInsurance.paymentInterface.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaoInsurance.paymentInterface.model.CancelResVO;
import com.kakaoInsurance.paymentInterface.model.InquiryResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentResVO;
import com.kakaoInsurance.paymentInterface.model.PaymentVO;
import com.kakaoInsurance.paymentInterface.repositories.PaymentRepository;
import com.kakaoInsurance.paymentInterface.security.AES256Util;
import com.kakaoInsurance.paymentInterface.utils.PaymentUtils;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private AES256Util AES256Util;
	
	@Autowired
	private PaymentUtils paymentUtils;
	
	@Override
	public PaymentResVO payment(PaymentVO paramPaymentVO) {
		PaymentVO paymentVO = new PaymentVO();
		try {
			String uniqueId = paymentUtils.makeUniqueId();
			paymentVO.setManageNum(uniqueId);
	        paymentVO.setOriginManageNum(uniqueId);
	        paymentVO.setManageSeq(0);
	        paymentVO.setReqDiv("PAYMENT");
			paymentVO.setCardNum(AES256Util.encrypt(paramPaymentVO.getCardNum()));
			paymentVO.setExpDt(AES256Util.encrypt(paramPaymentVO.getExpDt()));
			paymentVO.setCvc(AES256Util.encrypt(paramPaymentVO.getCvc()));
			paymentVO.setInstMth(paramPaymentVO.getInstMth());
			paymentVO.setPayAmt(paramPaymentVO.getPayAmt());
			paymentVO.setCanAmt((long) 0);
			if(paramPaymentVO.getVat() == null) {
				paymentVO.setVat((long) 0);
			}else {
				paymentVO.setVat(paramPaymentVO.getVat());
			}
			paymentVO.setCanVat((long) 0);
		} catch (UnsupportedEncodingException | GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		
        String sndStr = paymentUtils.makeFixedLengthData(paymentVO);
        paymentVO.setSndData(sndStr);
        
		paymentRepository.save(paymentVO);
		
		PaymentResVO paymentResVO = new PaymentResVO();
		paymentResVO.setManageNum(paymentVO.getManageNum());
		paymentResVO.setSndData(paymentVO.getSndData());
		return paymentResVO;
	}


	@Override
	public CancelResVO cancel(PaymentVO pramPaymentVO) {
		String manageNum = pramPaymentVO.getManageNum();
		Long canAmt = pramPaymentVO.getCanAmt();
		Long canVat = pramPaymentVO.getCanVat();
		
		PaymentVO paymentVO = paymentRepository.findByPaymentId(manageNum);

        
		PaymentVO cancelPaymentVO = new PaymentVO();
		String uniqueId = paymentUtils.makeUniqueId();
		cancelPaymentVO.setManageNum(uniqueId);
		cancelPaymentVO.setOriginManageNum(manageNum);
		cancelPaymentVO.setManageSeq(paymentVO.getManageSeq()+1);
		cancelPaymentVO.setSndData(paymentVO.getSndData());
		cancelPaymentVO.setCardNum(paymentVO.getCardNum());
		cancelPaymentVO.setExpDt(paymentVO.getExpDt());
		cancelPaymentVO.setCvc(paymentVO.getCvc());
		cancelPaymentVO.setInstMth(paymentVO.getInstMth());
		cancelPaymentVO.setPayAmt(paymentVO.getPayAmt()-canAmt);
		cancelPaymentVO.setCanAmt(canAmt);
		cancelPaymentVO.setVat(paymentVO.getVat()-canVat);
		cancelPaymentVO.setCanVat(canVat);
		cancelPaymentVO.setReqDiv("CANCEL");
		
		String sndStr = paymentUtils.makeFixedLengthData(cancelPaymentVO);
        cancelPaymentVO.setSndData(sndStr);
		
		paymentRepository.save(cancelPaymentVO);
		CancelResVO cancelResVO = new CancelResVO();
		cancelResVO.setManageNum(cancelPaymentVO.getManageNum());
		cancelResVO.setSndData(cancelPaymentVO.getSndData());
		return cancelResVO;
	}


	@Override
	public InquiryResVO inquiry(PaymentVO paymentVO) {
		paymentVO = paymentRepository.findByPaymentId(paymentVO.getManageNum());
		
		InquiryResVO inquiryResVO = new InquiryResVO();
		try {
			inquiryResVO.setManageNum(paymentVO.getManageNum());
			inquiryResVO.setCardNum(paymentUtils.maskingCardNum(AES256Util.decrypt(paymentVO.getCardNum())));
			inquiryResVO.setExpDt(AES256Util.decrypt(paymentVO.getExpDt()));
			inquiryResVO.setCvc(AES256Util.decrypt(paymentVO.getCvc()));
			inquiryResVO.setReqDiv(paymentVO.getReqDiv());
			inquiryResVO.setPayAmt(paymentVO.getPayAmt());
			inquiryResVO.setCanAmt(paymentVO.getCanAmt());
			inquiryResVO.setVat(paymentVO.getVat());
			inquiryResVO.setCanVat(paymentVO.getCanVat());
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return inquiryResVO;
	}
}
