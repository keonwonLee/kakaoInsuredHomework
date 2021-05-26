package com.kakaoInsurance.paymentInterface.model;

import java.io.Serializable;

public class PaymentResVO implements Serializable{
	
	public String manageNum;	//관리번호(unique id, 20자리)
	
	public String sndData;	//카드사에 전달한 string 데이터 : "공통 헤더부문" + "데이터 부문"

	public String getManageNum() {
		return manageNum;
	}

	public void setManageNum(String manageNum) {
		this.manageNum = manageNum;
	}

	public String getSndData() {
		return sndData;
	}

	public void setSndData(String sndData) {
		this.sndData = sndData;
	}

	
}
