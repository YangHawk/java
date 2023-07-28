package xyz.itwill05.lombok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@AllArgsConstructor: 모든 필드에 대한 초기화 설정이 가능한 생성자를 생성하여  
//@RequiredArgsConstructor: final 제한자를 사용하여 작성된 필드에 대한 초기화 작업
//▶ fianl 제한자 대신 @NonNull 어노테이션을 사용한 필드에 대한 생성자 생성 
@RequiredArgsConstructor
@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	// 필드에 @Autowired 어노테이션을 사용하여 의존성 주입 - 필드 레벨의 의존성 주입
	// ▶ 필드에 Setter 메소드가 없어도 필드에 의존성 주입 가능
	// ▶ 가장 보편적인 의존석 주입 방법이지만 순환 참조 시 에러가 발생되지 않아 StackOverflow 발생
	// 스택 오버플로우!
	// @Autowired
	
	//@RequiredArgsConstructor 어노테이션으로 생성된 생성자로 초기화 처리하기 위하여 
	// private final MemberDAO memberDAO;
	@NonNull
	private MemberDAO memberDAO;

	// 필드에 대한 Setter 메소드에 @Autowired 어노테이션을 사용하여 의존성 주입 - Setter 레벨의 의존성 주입
	// ▶ Setter 메소드의 접근 제한자가 [public]인 경우 Setter 메소드들 직접 호출하여 의존 관계가 변경 가능성이 있어 별로!
	/*
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	*/

	/*
	public MemberServiceImpl() {
		log.info("MemberServiceImpl 클래스의 기본 생성자 호출");
	}
	*/

	// 매개 변수로 전달받은 객체로 필드에 의존성을 주입하는 생성자에 @Autowired 어노테이션을 사용하여 의존성 주입 - Constructor 레벨의 의존성 주입 
	// ▶ Spring 프레임 워크에서는 순환 참조 방지를 위하여 생성자를 이용한 의존성 주입 권장
	// Setter Injection(필드 / 세터) vs. Constructor Injection(생성자) = 이게 더 좋다!
	// ▶ 매개 변수가 선언된 생성자를 하나만 작성한 경우(기본 생성자 없이) @Autowired 어노테이션 생략 가능 - 무조건 매개 변수로 전달받아야 하니까!
	/*
	//@Autowired
	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
		log.info("MemberServiceImpl 클래스의 매개 변수가 선언된 생성자 호출");
	}
	*/
	// 근데 @AllArgsConstructor를 사용하면 @Autowired 어노테이션 사용할 필요 없이 필드에 대한 초기화 작업 매개 변수가
	// 선언된 생성자를 생성하기 때문!
	// 근데 또 필드가 두 개 이상이면 안 돼! - 무엇을 전달받아야 하는지 모르니까!
	// 그래서 모든 필드 말고 내가 필요한 것만! - @RequiredArgsConstructor

	@Override
	public void addMember(Member member) {
		log.info("MemberServiceImpl 클래스의 addMember(Member member) 메소드 호출");
		memberDAO.insertMember(member);
	}

	@Override
	public void modifyMember(Member member) {
		log.info("MemberServiceImpl 클래스의 modifyMember(Member member) 메소드 호출");
		memberDAO.updateMember(member);
	}

	@Override
	public void removeMember(String id) {
		log.info("MemberServiceImpl 클래스의 removeMember(String id) 메소드 호출");
		memberDAO.deleteMember(id);
	}

	@Override
	public Member getMember(String id) {
		log.info("MemberServiceImpl 클래스의 getMember(String id) 메소드 호출");
		return memberDAO.selectMember(id);
	}

	@Override
	public List<Member> getMemberList() {
		log.info("MemberServiceImpl 클래스의 getMemberList() 메소드 호출");
		return memberDAO.selectMemberList();
	}
}
