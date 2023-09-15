package xyz.itwill.dto;

import lombok.Data;

/*
이름           널?       유형           
------------ -------- ------------ 
IDX          NOT NULL NUMBER       
FESTIVAL_IDX          NUMBER       
ACCOUNT_ID            VARCHAR2(50) 
 */

@Data
public class Wish {
	private int idx;
	private int festivalIdx;
	private String accountId;
	private int wishCount;
	private String subject;
 	private String mainImg;
}
