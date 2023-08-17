package xyz.itwill10.dto;

import lombok.Data;

/*

CREATE TABLE RESTBOARD(IDX NUMBER PRIMARY KEY, WRITER VARCHAR2(50), CONTENT VARCHAR2(100), REGDATE DATE);
CREATE SEQUENCE RESTBOARD_SEQ;

이름      널?       유형            
------- -------- ------------- 
IDX     NOT NULL NUMBER        
WRITER           VARCHAR2(50)  
CONTENT          VARCHAR2(100) 
REGDATE          DATE          

*/

@Data
public class RestBoard {
	private int idx;
	private String writer;
	private String content;
	private String regdate;
}
