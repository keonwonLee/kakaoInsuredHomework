package com.kakaoInsurance.paymentInterface.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaoInsurance.paymentInterface.model.PaymentVO;
import com.kakaoInsurance.paymentInterface.security.AES256Util;

@Service
public class PaymentUtils {
	
	@Autowired
	private AES256Util AES256Util;
	
	public String raw(String value, int length, String position, String fillStr) {
	    StringBuffer padded = new StringBuffer(value);
	    StringBuffer resultStr = new StringBuffer(value);
	    String appendStr = "";
	    while (resultStr.toString().getBytes().length < length) {
	    	appendStr += fillStr;
	    	if("R".equals(position)) {
	    		resultStr = new StringBuffer(appendStr + padded.toString());
	    	}else if("L".equals(position)) {
	    		resultStr = new StringBuffer(padded.toString() + appendStr);
	    	}
	    }
	    return resultStr.toString();
	}
	
	public String makeUniqueId() {
		String uniqueId = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar dateTime = Calendar.getInstance();
        uniqueId = sdf.format(dateTime.getTime());
        uniqueId = ((int)(Math.random()*999)+100) + uniqueId;
        System.out.println("manageNum : [" + uniqueId + "]");
        return uniqueId;
	}
	
	public String makeFixedLengthData(PaymentVO paymentVO) {
		String sndStr = "";
		try {
			sndStr += raw(paymentVO.getReqDiv(), 10, "L", " ");
	    	sndStr += raw(paymentVO.getManageNum(), 20, "L", " ");
	    	sndStr += raw(AES256Util.decrypt(paymentVO.getCardNum()), 20, "L", " ");
	    	sndStr += raw(paymentVO.getInstMth()+"", 2, "R", "0");
	    	sndStr += raw(AES256Util.decrypt(paymentVO.getExpDt()), 4, "L", " ");
	    	sndStr += raw(AES256Util.decrypt(paymentVO.getCvc()), 3, "L", " ");
	    	sndStr += raw(paymentVO.getPayAmt().toString(), 10, "R", " ");
	        sndStr += raw(paymentVO.getVat().toString(), 10, "R", "0");
	        sndStr += raw("", 20, "L", " ");
	        String encData = AES256Util.decrypt(paymentVO.getCardNum()) + "|" + AES256Util.decrypt(paymentVO.getExpDt()) + "|" + AES256Util.decrypt(paymentVO.getCvc());
	        sndStr += raw(AES256Util.encrypt(encData), 300, "L", " ");
	        sndStr += raw("", 47, "L", " ");
	        
	        sndStr = raw(sndStr.getBytes().length+"", 4, "L", " ") + sndStr;
	        System.out.println("sndStr : [" + sndStr + "]");
	        System.out.println("sndStr.length : [" + sndStr.getBytes().length + "]");
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sndStr;
	}
	
	public String maskingCardNum(String cardNum) {
		if(cardNum != null && !"".equals(cardNum)){
		    String middleMask = cardNum.substring(6, cardNum.length()-3);
		    String masking = "";
		    
		    for(int i = 0; i < middleMask.length(); i++){
		    	masking += "*";
		    }
		    
		    cardNum = cardNum.replace(middleMask, masking);
		}

		return cardNum;
	}
}
