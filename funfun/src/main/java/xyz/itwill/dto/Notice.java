package xyz.itwill.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
이름        널?       유형                  
--------- -------- ------------------- 
IDX       NOT NULL NUMBER              
TITLE     NOT NULL VARCHAR2(100 CHAR)  
CONTENT   NOT NULL VARCHAR2(1000 CHAR) 
DAY                DATE                
FILE_DATA          VARCHAR2(100)       
COUNT              NUMBER              
STATUS             NUMBER(1)
 */
@Data
public class Notice {
	private int idx;
	private String title;
	private String content;
	private String day;
	private String fileData;
	private String newFileData;
	private int count;
	private MultipartFile uploadFile;
	private int status;
	private int prevnum; //이전글의 글번호
	private int nextnum; //다음글의 글번호
}
