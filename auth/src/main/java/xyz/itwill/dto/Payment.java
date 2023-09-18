package xyz.itwill.dto;

import java.sql.Date;

import lombok.Data;

/*

이름           널?       유형            
------------ -------- ------------- 
IMP_UID      NOT NULL VARCHAR2(100) 
MERCHANT_UID          VARCHAR2(100) 
PAY_DATE              DATE          
USERID                VARCHAR2(100) 
AMOUNT                NUMBER        
ORDER_IDX             NUMBER        
STATUS                VARCHAR2(20)  

create table payments(imp_uid varchar2(100) primary key, merchant_uid
varchar2(100), pay_date date, userid varchar2(100), amount number,
order_idx number, status varchar2(20));

*/

// 결제 관련 정보를 저장하기 위한 클래스
@Data
public class Payment {
	private String impUid;//결제 관련 Open API에서 제공되는 고유값 
	private String merchantUid;//주문번호
	private long amount;//결제금액
	private Date payDate;//결제일 
	private String userid;//결제 사용자 아이디
	private String status;//결제 상태 
}
