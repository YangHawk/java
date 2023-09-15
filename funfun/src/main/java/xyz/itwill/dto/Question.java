package xyz.itwill.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
이름             널?       유형                  
-------------- -------- ------------------- 
IDX            NOT NULL NUMBER              
ACCOUNT_ID              VARCHAR2(50)        
TITLE                   VARCHAR2(150 CHAR)  
CONTENT                 VARCHAR2(1000 CHAR) 
DAY                     DATE                
SECRET                  NUMBER(1)           
FILE_DATA               VARCHAR2(100)       
ANSWER_CONTENT          VARCHAR2(1000 CHAR) 
ANSWER_DAY              DATE                
COUNT                   NUMBER              
STATUS                  NUMBER              
PROCESS                 NUMBER             
 */

@Data
public class Question {
	private int idx;
	private String accountId;
	private String title;
	private String content;
	private String day;
	private int secret;
	private String fileData;
	private String answerContent;
	private String answerDay;
	private int count;
	private MultipartFile uploadFile;
	private int prevnum; //이전글의 글번호
	private int nextnum; //다음글의 글번호
}
