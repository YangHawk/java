package xyz.itwill10.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dto.RestBoard;
import xyz.itwill10.service.RestBoardService;

// Restful 기능을 제공하는 요청 처리 메소드가 정상적으로 실행되는지 확인하기 위하여 Advanced REST Client 크롬 App을 설치하여 사용 - Reestful API 테스트 프로그램
// @RestController: REST 기능을 제공하는 요청 처리 메소드(Restful API)만 선언된 Controller 클래스를 Spring Bean으로 등록하는 어노테이션
// ▶ 요청 처리 메소드에 @ResponseBody 어노테이션을 사용하지 않아도 문자열로 응답 처리 가능
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestBoardController {
	private final RestBoardService restBoardService;

	// 페이지 번호를 전달바당 RESTBOARD 테이블에 저장된 게시글 중 페이지 번호에 출력될 게시글 목록을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	// REST 기능을 제공하는 요청 처리 메소드는 @RequestMapping 어노테이션 대신 @GetMapping, @PostMapping, @PutMapping, @PatchMapping, @DeleteMapping 어노테이션을 사용하는 것을 권장
	// ▶ 요청 방식: GET(검색) / POST(삽입) / PUT(전체 변경) / PATC(부분 변경) / DELETE(삭제) 등
	// @RequestMapping(value = "/board_list", method = RequestMethod.GET)
	// @ResponseBody ◁ Controller 클래스를 @RestController 어노테이션을 사용하여 Spring Bean으로 등록할 경우 @ResponseBody 어노테이션을 생략해도 문자열로 응답 처리 가능
	@GetMapping("/board_list")
	public Map<String, Object> restBoardList(@RequestParam(defaultValue = "1") int pageNum) {
		// 자바스크립트의 Object 객체 형식의 문자열로 반환
		return restBoardService.getRestBoardList(pageNum);
	}
	
	// 게시글을 전달받아 RESTBOARD 테이블에 게시글을 삽입 처리하고 실행 결과를 문자열로 응답하는 요청 처리 메소드
	// ▶ [application/json] 형식의 문자열로 전달된 게시글을 Java 객체로 제공받아 매개 변수에 저장하기 위하여 @RequestBody 어노테이션을 사용
	// JSON 형식의 문자열로 전달하지 않을 경우, @ModelAttribute 사용하면 됨!
	@PostMapping("/board_add")
	public String restBoardAdd(@RequestBody RestBoard restBoard) {
		// HtmlUtils.htmlEscape(String str): 매개 변수로 전달받은 문자열에 저장된 HTML 태그 관련 문자를 회피 문자(Escape Character)로 변환하여 반환하는 메소드
		// ▶ XSS 공격에 대한 방어를 위한 사용 - HTML 태그 관련 문자열들이 회피 문자열로 바뀌어 출력할 때는 동작되지 않게 하는 것 - 저장할 때 사용
		restBoard.setContent(HtmlUtils.htmlEscape(restBoard.getContent()));
		restBoardService.addRestBoard(restBoard);
		return "success";
	}
	
	/*
	
	// 글번호를 전달받아 RESTBOARD 테이블에 저장된 게시글에서 해당 글번호의 게시글을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	// ▶ 질의문자열(QueryString)으로 전달된 글번호를 매개 변수로 제공받아 사용
	@GetMapping("/board_view")
	public RestBoard restBoardView(@RequestParam int idx) {
		return restBoardService.getRestBoard(idx);
	}
	
	 */
	
	// 글번호를 전달받아 RESTBOARD 테이블에 저장된 게시글에서 해당 글번호의 게시글을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	// ▶ 요청 URL 주소로 표현된 글번호를 매개 변수로 제공받아 사용
	// ▶ 요청 URL 주소로 값을 표현하여 전달하기 위하여 Mapping 어노테이션의 value 속성값에서 {이름} 형식으로 값을 제공받아 사용 가능
	// ▶ 요청 URL 주소로 표현된 값은 매개 변수에 @RequestParam 어노테이션 대신 @PathVariable 어노테이션을 사용하여 전달값을 저장 
	// @PathVariable: 요청 URL 주소로 표현된 값을 요청 처리 메소드의 매개 변수에 저장하기 위한 어노테이션
	// ▶ @GetMapping 어노테이션의 value 속성값으로 설정된 이름({idx}과 매개 변수의 이름(int idx)이 같도록 작성 
	// ▶ 전달값의 이름과 매개 변수의 이름이 다를 경우 @PathVariable 어노테이션에 value 속성을 사용하여 요청 URL 주소로 표현하여 전달된 값을 매개 변수에 저장 가능
	@GetMapping("/board_view/{num}")
	public RestBoard restBoardView(@PathVariable("num") int idx) {
		return restBoardService.getRestBoard(idx);
	}
	
	// 게시글을 전달받아 RESTBOARD 테이블에 저장된 게시글을 변경하고 실행 결과를 문자열로 응답하는 요청 처리 메소드
	// ▶ [application/json] 형식의 문자열로 전달된 게시글을 Java 객체로 제공받아 매개 변수에 저장하기 위하여 @RequestBody 어노테이션을 사용
	@PutMapping("/board_modify")
	public String restBoardModify(@RequestBody RestBoard restBoard) {
		restBoardService.modifyRestBoard(restBoard);
		return "success";
	}
	
	// 글번호를 전달받아 RESTBOARD 테이블에 저장된 게시글에서 해당 글번호의 게시글을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	@DeleteMapping("/board_remove/{idx}")
	public String restBoardRemove(@PathVariable int idx) {
		restBoardService.removeRestBoard(idx);
		return "success";
	}
}