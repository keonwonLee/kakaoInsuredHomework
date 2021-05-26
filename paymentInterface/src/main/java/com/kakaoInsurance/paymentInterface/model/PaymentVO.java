package com.kakaoInsurance.paymentInterface.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT")
/*
@Table( name="PAYMENT", uniqueConstraints={
	 @UniqueConstraint( 
			 columnNames={"MANAGE_NUM","MANAGE_SEQ"}
		 ) 
	 } 
)
*/
@IdClass(PaymentVO.class)
public class PaymentVO implements Serializable{
	
	@Id 
	@Column(name = "MANAGE_NUM")
	public String manageNum;	//관리번호(unique id, 20자리)
	
	@Id 
	@Column(name = "MANAGE_SEQ")
	public int manageSeq;	//관리 순번
	
	@Column(name = "ORIGIN_MANAGE_NUM")
	public String originManageNum;	//관리번호(unique id, 20자리)
	
	@Column(name = "SND_DATA", length = 450)
	public String sndData;	//카드사에 전달한 string 데이터 : "공통 헤더부문" + "데이터 부문"
	
	@Column(name = "CARD_NUM", nullable = false)
	public String cardNum;	//카드번호(10 ~ 16자리 숫자)
	
	@Column(name = "EXP_DT", nullable = false)
	public String expDt;	//유효기간(4자리 숫자, mmyy)
	
	@Column(name = "CVC", nullable = false)
	public String cvc;	//cvc(3자리 숫자)
	
	@Column(name = "INST_MTH", nullable = false)
	public int instMth;	//할부개월수 : 0(일시불), 1 ~ 12
	
	@Column(name = "PAY_AMT", nullable = false)
	public Long payAmt;	//결제금액(100원 이상, 10억원 이하, 숫자)

	@Column(name = "VAT")
	public Long vat;	//부가가치세
	
	@Column(name = "CAN_VAT")
	public Long canVat;	//취소부가가치세

	@Column(name = "CAN_AMT")
	public Long canAmt;	//취소금액
	
	@Column(name = "REQ_DIV")
	public String reqDiv;	//요청구분

	public String getOriginManageNum() {
		return originManageNum;
	}

	public void setOriginManageNum(String originManageNum) {
		this.originManageNum = originManageNum;
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

	public String getManageNum() {
		return manageNum;
	}

	public Long getCanAmt() {
		return canAmt;
	}

	public void setCanAmt(Long canAmt) {
		this.canAmt = canAmt;
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

	public int getInstMth() {
		return instMth;
	}

	public void setInstMth(int instMth) {
		this.instMth = instMth;
	}

	public Long getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}

	public Long getVat() {
		return vat;
	}

	public void setVat(Long vat) {
		this.vat = vat;
	}
	
	public int getManageSeq() {
		return manageSeq;
	}

	public void setManageSeq(int manageSeq) {
		this.manageSeq = manageSeq;
	}

}
