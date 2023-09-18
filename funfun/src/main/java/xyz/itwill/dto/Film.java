package xyz.itwill.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
이름           널?       유형                 
------------ -------- ------------------ 
IDX          NOT NULL NUMBER             
FESTIVAL_IDX          NUMBER             
TITLE                 VARCHAR2(50)       
DIRECTOR              VARCHAR2(20)       
STORY                 VARCHAR2(500 CHAR) 
DAY                   DATE               
TIME                  VARCHAR2(50)       
IMG                   VARCHAR2(100)      
VIDEO                 VARCHAR2(100)   
 */

@Data
public class Film {
   private int idx;
   private int festivalIdx;
   private String title;
   private String director;
   private String story;
   private String day;
   private String time;
   private String img;
   private String video;
   private String accountId;
   private MultipartFile multipartFile;
}