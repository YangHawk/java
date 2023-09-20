package xyz.itwill.dto;

import lombok.Data;

/*
이름             널?       유형           
-------------- -------- ------------ 
IDX            NOT NULL NUMBER       
FESTIVAL_IDX            NUMBER       
ACCOUNT_ID              VARCHAR2(50) 
MONEY                   NUMBER(20)   
DAY                     DATE         
STATE                   NUMBER(1)    
CANCLE_DAY              DATE         
PAY_DAY                 DATE         
PAY_TYPE                NUMBER(1)    
REFUND_ACCOUNT          VARCHAR2(30) 
REFUND_BANK             VARCHAR2(20) 
 */

@Data
public class Donation {
	private int idx;
	private int festivalIdx;
	private String accountId;
	private String money;
	private String day;
	private int state; // 0 = 결제 전 / 1 = 결제 완료 / 2 = 결제 취소
	private String cancelDay;
	private String payDay;
	private int payType;
	private String refundAccount;
	private String refundBank;
	private String subject;
	private String mainImg;
	private String impUid; //결제 관련 Open API에서 제공되는 고유값 
	private String merchantUid; //주문번호
}
