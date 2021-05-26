# 카드결제 / 결제취소 / 결제정보 조회 REST API

## 1. 개발 프레임워크
- IDE : eclipse
- OS : windows 10
- framework : spring boot
- DB : H2 
- 그 외 : JPA

## 2. 테이블 설계
![ERD](https://user-images.githubusercontent.com/84861378/119690201-70102980-be84-11eb-8e07-5704db009f44.PNG)

- 필드
(PK)MANAGE_NUM : 요청별 채번된 관리번호 
(PK)MANAGE_SEQ : 원관리번호(최초 관리번호)에 대한 요청순번
REQ_DIV : 요청구분 (PAYMENT/CANCEL)
ORIGIN_MANAGE_NUM : 원관리번호(최초 관리번호)
CARD_NUM : 카드번호, 암호화
EXP_DT : 카드유효기간, 암호화
CVC : 카드 CVC, 암호화
INST_MTH : 할부개월수 (0~12)
PAY_AMT : 결재 요청/잔여 금액
CAN_AMT : 취소 요청 금액
VAT : 요청/잔여 부가세 금액
CAN_VAT : 취소 부가세 금액
SND_DATA : 카드사 전송 전문데이터

## 3. 문제해결 전략
- 암호화 : AES256 방식
- 전문구성 : fixed length 방식의 전문 구성
- 결재 취소 요청 시 파라미터인 관리번호의 원관리번호로 조회하여 최종 순번의 데이터 조회 및 차감처리

## 4. API
- 결재요청 : /api/payments/payment
```
{
  "cardNum": "43920210524191553527",
  "expDt" : "0226",
  "cvc" : "123",
  "instMth" : 3,
  "payAmt" : 10000,
  "vat" : 1000
}
```
> 응답
```
{"manageNum":"86920210527010553091","sndData":"446 PAYMENT   8692021052701055309143920210524191553527030226123     100000000001000                    zPQ+MoMLAnT9epevru4alaO99BMBnVUHi01DV9teKrk=                                                                                                                                                                                                                                                                                                               "}
```

- 취소요청 : /api/payments/cancel
```
{
  "manageNum": "86920210527010553091",
  "canAmt" : 100,
  "canVat" : 0
}
```
> 응답
```
{"manageNum":"41620210527010648211","sndData":"446 CANCEL    4162021052701064821143920210524191553527030226123      99000000001000                    zPQ+MoMLAnT9epevru4alaO99BMBnVUHi01DV9teKrk=                                                                                                                                                                                                                                                                                                               "}
```

- 조회요청 : /api/payment/inquiry
```
{
  "manageNum": "41620210527010648211"
}
```
> 응답
```
{"manageNum":"41620210527010648211","cardNum":"439202***********527","expDt":"0226","cvc":"123","payAmt":9900,"canAmt":100,"vat":1000,"canVat":0,"reqDiv":"CANCEL"}
```

![캡처2](https://user-images.githubusercontent.com/84861378/119694392-24f81580-be88-11eb-81ff-f97287329254.PNG)


## 4. 빌드 및 실행방법
