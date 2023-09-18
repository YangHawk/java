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
	private int state;
	private String cancelDay;
	private String payDay;
	private int payType;
	private String refundAccount;
	private String refundBank;
	private String subject;
	private String mainImg;
}
