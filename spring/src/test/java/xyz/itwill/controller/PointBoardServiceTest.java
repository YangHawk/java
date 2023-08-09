package xyz.itwill.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import xyz.itwill10.dto.PointBoard;
import xyz.itwill10.dto.PointUser;
import xyz.itwill10.service.PointBoardService;

// Spring 프레임워크의 TransactionManager 관련 클래스를 이용하여 트랜잭션 처리하는 방법
// 1. spring-tx 라이브러리를 프로젝트에 빌드 처리 - 메이븐: pom.xml
// ▶ spring-jdbc 라이브러리를 빌드 처리하면, 라이브러리 의존 관계에 의하여 자동 빌드 처리 되어있음!
// 2. Spring Bean Configuration File(root-context.xml: 모두 다 쓰게 해야 함)에 TransactionManager 클래스를 Spring Bean으로 등록
// ▶ beanName을 TransactionManager
// 3. Spring Bean Configuration File(servlet-context.xml)에 트랜잭션 처리를 위한 AOP 설정 
// ▶ Spring Bean Configuration File의 AOP 설정을 이용하여 TransactionManager 객체를 사용할 수 있도록 설정하는 것 대신!
// @Transactional 어노테이션을 사용하여 트랜잭션 처리가 가능 - 더 간편!
// TransactionManager 객체를 이용하여 트랜잭션 처리 기능을 제공받을 메소드에 @Transactional 어노테이션 사용
// ▶ 해당 메소드의 명령 실행 시 예외(Exception - 모든 예외)가 발생된 경우 롤백 처리
// ▶ @Transactional 어노테이션을 사용하기 위하여 Spring Bean Configuration File(root-context.xml)에 tx 네임스페이스를 추가하여
// spring-tx.xsd 파일을 제공받아 annotation-driven 엘리먼트를 반드시 사용해야 함
// ▶ 테스트 클래스의 테스트 메소드에 @Transactional 어노테이션을 사용하면 테스트 메소드의 명령을 실행 후 무조건 롤백 처리 (REAL TEST)

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class PointBoardServiceTest {
	@Autowired
	private PointBoardService pointBoardService;

	@Transactional // 굳이 테이블에 진짜 저장할 필요가 없다! 이걸 쓰면 ㄹㅇ
	@Test
	public void test1() throws Exception {
		// 게시글(PointBoard 객체) 생성
		PointBoard board = PointBoard.builder().writer("abc123").subject("테스트").build();

		// PointBoardService 클래스의 addPointBoard() 메소드를 호출하여 POINTBOARD 테이블에 게시글 삽입
		// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자에 대한 회원 정보의 포인트를 증가
		// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자의 회원 정보를 검색하여 반환
		// ▶ 매개 변수로 전달받은 게시글에서 게시글 작성자가 없는 경우 예외 발생
		// ※문제점: 예외 발생 전에 실행된 게시글 삽입에 대한 SQL 명령은 이미 DBMS 서버에 전달되어 실행된 상태로 POINTBOARD 테이블에는 비정상적인 게시글에 저장
		// ▶ POINTBOARD 테이블에 게시글 작성자가 존재하지 않는 게시글이 저장되어 있음 - 게시글을 검색하여 출력할 경우 비정상적인 결과를 제공
		// ※해결법: 예외가 발생되기 전에 실행된 SQL 명령에 대하여 모두 ROLLBACK 처리되도록 설정
		// ▶ Spring 프레임워크에서 제공하는 트랜잭션 관리 기능을 사용하여 트랜잭션 처리
		// ▶ TransactionManager 관련 클래스를 이용하면 일관성 있는 트랜잭션 처리 기능 제공
		// 게시글 삽입 처리
		PointUser user = pointBoardService.addPointBoard(board);

		// 게시글 작성자에 대한 회원 정보를 반환받아 기록
		log.info(user.toString());

		// PointBoardService 클래스의 getPointBoardList() 메소드를 호출하여 게시글 목록을 반환받아 기록
		// 게시글 목록을 반환받아 기록
		log.info(pointBoardService.getPointBoardList().toString());
	}

	/*
	
	@Test
	public void test2() throws Exception {
		// PointBoardService 클래스의 removePointBoard() 메소드를 호출하여 POINTBOARD 테이블에 게시글 삭제
		// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자에 대한 회원 정보의 포인트를 감소
		// ▶ POINTUSER 테이블에 저장된 회원 정보 중 게시글 작성자의 회원 정보를 검색하여 반환
		// 1번 게시글(test1() 메소드에서 삽입된 게시글) 삭제 처리
		PointUser user = pointBoardService.removePointBoard(1);

		// 게시글 작성자에 대한 회원 정보를 반환받아 기록
		log.info(user.toString());

		// PointBoardService 클래스의 getPointBoardList() 메소드를 호출하여 게시글 목록을 반환받아 기록
		// 게시글 목록을 반환받아 기록
		log.info(pointBoardService.getPointBoardList().toString());
	}
	
	 */
}