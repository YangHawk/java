package xyz.itwill.dto;

import lombok.Data;

/*
이름           널?       유형            
------------ -------- ------------- 
IDX          NOT NULL NUMBER        
FESTIVAL_IDX          NUMBER        
ACCOUNT_ID            VARCHAR2(50)  
STAR                  NUMBER(1)     
CONTENT               VARCHAR2(200) 
DAY                   DATE          
STATUS                NUMBER(1)     
 */

@Data
public class Expect {
	private int idx;
	private int festivalIdx;
	private String accountId;
	private int star;
	private String content;
	private String day;
	private int status;
	//메인화면에 기대평 3개를 출력할 때 같이 출력되도록 추가한 필드
	private String subject; 
	private String mainImg; 
}
