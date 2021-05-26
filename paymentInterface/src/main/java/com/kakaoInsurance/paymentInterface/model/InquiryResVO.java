package com.kakaoInsurance.paymentInterface.model;

import java.io.Serializable;

public class InquiryResVO implements Serializable{

	public String manageNum;	//관리번호(unique id, 20자리)
	
	public String cardNum;	//카드번호(10 ~ 16자리 숫자)

	public String expDt;	//유효기간(4자리 숫자, mmyy)
	
	public String cvc;	//cvc(3자리 숫자)
	
	public Long payAmt;	//결제금액(100원 이상, 10억원 이하, 숫자)

	public Long canAmt;	//취소금액

	public Long vat;	//부가가치세
	
	public Long canVat;	//취소부가가치세

	public String reqDiv;	//요청구분
	
	public String getManageNum() {
		return manageNum;
	}
	
	public void setManageNum(String manageNum) {
		this.manageNum = manageNum;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getExpDt() {
		return expDt;
	}

	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Long getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}

	public Long getCanAmt() {
		return canAmt;
	}

	public void setCanAmt(Long canAmt) {
		this.canAmt = canAmt;
	}

	public Long getVat() {
		return vat;
	}

	public void setVat(Long vat) {
		this.vat = vat;
	}

	public Long getCanVat() {
		return canVat;
	}

	public void setCanVat(Long canVat) {
		this.canVat = canVat;
	}

	public String getReqDiv() {
		return reqDiv;
	}

	public void setReqDiv(String reqDiv) {
		this.reqDiv = reqDiv;
	}

	
}
