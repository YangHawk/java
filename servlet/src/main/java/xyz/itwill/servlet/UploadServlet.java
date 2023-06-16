package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//[multipart/form-data]로 전달될 경우 request 객체로부터 입력 스트림(ServletInputStream 객체)을 제공받아 임시 파일에 저장한 후 전달값과 전달 파일을 구분하여 처리
//→ [multipart/form-data]로 전달된 경우 [multipart/form-data]를 처리하는 클래스를 사용하는 것을 권장

//[multipart/form-data]를 처리하는 클래스가 포함된 라이브러리를 다운로드 받아 프로젝트에 Build 처리
//→ 1. Apache 그룹에서 배포한 commons-fileupload 라이브러리의 클래스 사용 - 선택적 파일 업로드 가능
//→ 2. Oreilly 그룹에서 배포한 cos 라이브러리의 클래스 사용 - 검증 없이 무조건적인 파일 업로드

//Oreilly 그룹에서 배포한 cos 라이브러리를 다운로드하여 프로젝트 빌드 처리
//http://www.servlets.com 사이트 접속 → COS File Upload Library → cos-22.05.zip 다운로드 → cos-22.05\lib\cos.jar 복사 → 프로젝트 내 src\main\webapp\WEB-INF\lib 에 붙여넣기

//입력 페이지(fileupload.html)에서 전달된 값과 파일명을 클라이언트에게 전달하여 응답하는 서블릿
//→ 전달 파일은 서버 디렉토리에 저장될 것 - 파일 업로드(Upload) 처리
@WebServlet("/upload.itwill")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (request.getMethod().equals("GET")) { // 비정상적인 접근일 경우(입력값 검증)
			response.sendRedirect("fileupload.html");
			return;
		}

		// request.setCharacterEncoding("utf-8");

		// form 태그의 [multipart/form-data]로 전달된 입력값은 절대 HttpServletRequest 객체의 getParameter() 메소드를 호출하여 반환할 수 없음
		// String uploader = request.getParameter("uploader");

		// 전달 파일을 저장하기 위한 서버 디렉토리의 시스템 파일 경로를 반환받아 저장
		// → /upload에 웹 자원을 저장하기 위한 폴더 생성(클라이언트가 접근을 못하게 하려면 WEB-INF 안에 생성해주면 됨!)
		// → 이클립스의 작업 디렉토리(WorkSpace)가 아닌 WAS 프로그램(여기서는 Apache Tomcat)이 사용하는 웹 디렉토리의(WebApps)의 파일 시스템 경로를 반환
		// → WAS 프로그램이 실행(Start)될 때 이클립스의 작업 디렉토리의 파일을 이용하여 웹 디렉토리의 자원(Context)으로 변환하여 저장 - 동기화 처리
		// → 작업 디렉토리에 업로드 파일이 없으므로 동기화 처리될 경우 웹 디렉토리에 업로드된 파일이 소멸
		String saveDirectory = request.getServletContext().getRealPath("/upload");
		// System.out.println("서버 콘솔에다가 saveDirectory를 출력 = " + saveDirectory);
		// 서버 콘솔에다가 saveDirectory를 출력 = C:\Temp\apache-tomcat-9.0.75\webapps\servlet\\upload

		// cos 라이브러리의 MultipartRequest 클래스로 객체 생성
		// → 모든 전달 파일을 제공받아 서버 디렉토리에 저장 - 자동 업로드 처리
		// MultipartRequest 객체: [multipart/form-data]로 전달받은 값과 파일을 처리하기 위한 객체
		// → MultipartRequest(HttpServletRequest request, String saveDirectory[, int maxPostSize][, String encoding][, FileRenamePolicy policy]) 생성자를 이용하여 객체 생성
		// → request: 요청 정보가 저장된 HttpServletRequest 객체 전달
		// → saveDirectory: 전달 파일이 저장될 서버 디렉토리의 파일 시스템 경로 전달
		// → maxPostSize: 전달 파일의 용량을 제한하기 위한 크기 전달 - 단위: Byte / 생략 시 무제한 기본값으로 사용
		// → encoding: 전달값에 제공받기 위한 문자 형태 전달 - 생략 시 서유럽어를 기본값으로 사용
		// → policy: FileRenamePolicy 객체 전달 - 생략 시 전달 파일로 기존 파일을 덮어씌우기(Overwrite)
		// ※FileRenamePolicy 객체: 업로드될 파일의 이름과 같은 이름의 파일이 서버 디렉토리에 이미 존재할 경우 업로드될 파일의 이름을 변경하는 기능을 제공하는 객체
		// → FileRenamePolicy 인터페이스를 상속받은 자식클래스로 객체 생성(ex: DefaultFileRenamePolicy)

		MultipartRequest mr = new MultipartRequest(request, saveDirectory, 20 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
		// 이 순간 업로드는 이미 끝난 것!
		// → 20MB를 넘어가는 파일 처리 불가하게 설정! / 문자 형태 UTF-8로 설정! / 파일1 형식 설정!
		// MultipartRequest 객체를 이용하여 전달값을 반환받아 저장
		// → getParameter(String name) 또는 getParameterValues(String name) 메소드 호출
		String uploader = mr.getParameter("uploader");

		// MultipartRequest 객체를 이용하여 전달 파일명을 반환받아 저장
		// MultipartRequest.getOriginalFileName(String name): 매개변수로 전달된 이름의 파일명(입력 파일명)을 반환하는 메소드
		// String uploadFileOne = mr.getOriginalFileName("uploadfileone");
		// String uploadFileTwo = mr.getOriginalFileName("uploadfiletwo");
		// MultipartRequest.getFilesystemName(String name): 매개변수로 전달된 이름의 파일명(업로드 처리된 파일명)을 반환하는 메소드
		// → FileRenamePolicy 객체를 사용한 경우 호출하는 메소드
		String uploadFileOne = mr.getFilesystemName("uploadfileone");
		String uploadFileTwo = mr.getFilesystemName("uploadfiletwo");

		// ※ WAS를 Start 되는 순간 웹 자원으로 바뀔 떄 파일이 싹 소멸?
		// 파일을 계속 보고 싶으면 실제 apache tomcat webapp 가서 복사해서 이클립스 upload에 붙여넣기 하던가!

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>File Upload</h1>");
		out.println("<hr>");
		out.println("<p>올린이 = " + uploader + "</p>");
		out.println("<p>파일 = " + uploadFileOne + "</p>");
		out.println("<p>진짜 파일 = " + uploadFileTwo + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
