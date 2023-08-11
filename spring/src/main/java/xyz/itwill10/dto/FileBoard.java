package xyz.itwill10.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*

이름      널?       유형            
------- -------- ------------- 
IDX     NOT NULL NUMBER        - 글번호
WRITER           VARCHAR2(20)  - 작성자
SUBJECT          VARCHAR2(100) - 제목
ORGIN            VARCHAR2(100) - 사용자로부터 입력받은 파일명
UPLOAD           VARCHAR2(100) - 서버에 저장된 파일명

*/

// DAO 클래스의 메소드에서 사용하기 위한 객체를 표현하기 위한 클래스 - DTO 클래스
// 전달값들이 저장된 Command 객체를 표현하기 위한 클래스의 기능
@Data
public class FileBoard {
	private int idx;
	private String writer;
	private String subject;
	private String origin;
	private String upload;

	// 사용자로부터 입력되어 전달된 파일 정보를 저장하기 위한 필드 - TABLE로 사용하진 않음!
	private MultipartFile multipartFile;
}
