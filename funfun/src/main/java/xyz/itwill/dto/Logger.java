package xyz.itwill.dto;

/*
이름         널?       유형            
---------- -------- ------------- 
IDX        NOT NULL NUMBER        
ACCOUNT_ID          VARCHAR2(50)  
LOGIN_DAY           DATE          
IP                  VARCHAR2(100) 
LOGOUT_DAY          DATE          
INFO                VARCHAR2(200) 
SUCCESS             CHAR(8)       
 */
public class Logger {
	private int idx;
	private String accountId;
	private String loginDay;
	private String ip;
	private String logoutDay;
	private String info;
	private String success;
}
