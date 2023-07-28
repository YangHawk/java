package xyz.itwill05.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// ※클래스 작성에 필요한 메소드를 자동으로 생성해주는 Lomobob 라이브러리를 사용하는 방법※
// 1. Lombok 라이브러리를 프로젝트 빌드 처리 - Maven 사용: pom.xml
// 2. 이클립스(STS)를 종료하고 콘솔(명령 프롬프트 - cmd)를 관리자 권한으로 실행한 후 Lombok 라이브리가 저장된 로컬 저장소(Local Repository - 사용자 폴더의 .m2 폴더)의 라이브러리 폴더로 이동
// 3. 콘솔에서 Jar 프로그램을 실행하여 Lombok 라이브러리를 이클립스에 사용할 수 있도록 설정
// ▶ Jar 프로그램 실행 방법: java -jar lombok-1.18.28.jar
// ▶ 혹은 탐색기에서 로컬 저장소의 라이브러리 폴더로 가서 Jar 파일 실행
// 4. Jar 프로그램을 실행하여 제공된 설치 창(Installer)에서 Lombok 라이브러리를 사용할 이클립스(STS)를 선택하여 Lombok 설치
// ▶ 이클립스를 자동으로 찾을 수 없는 경우 이클립스가 설치된 폴더를 선택하여 Lombok 설치
// 5. 이클립스를 실행하여 클래스를 작성할 때 Lombok 라이브러리가 제공하는 어노테이션 사용
// ▶ @Setter / @Getter / @ToString / @Data / @AllArgsConstructor / @RequiredArgsConstructor 등!
// ▶ 이클립스(STS)가 실행되지 않는 경우 이클립스(STS) 설치 폴더로 이동하여 eclipse.ini 파일 변경
// ▶-javaagent 속성의 lombok 경로를 절대 경로에서 상대 경로로 수정
// -javaagent:C:\Temp\sts-bundle\sts-3.9.18.RELEASE\lombok.jar ▷ -javaagent:lombok.jar

// @NoArgsConstructor: 매개 변수가 없는 생성자를 자동 생성하여 제공하는 어노테이션 - Member()

// @AllArgsConstructor: 모든 필드에 초기값을 저장할 수 있는 매개 변수가 있는 생성자를 자동 생성하여 제공하는 어노테이션 - Member(String, String, String)
// ▶ 모든 필드에 초기값을 저장할 수 있는 매개 변수가 있는 생성자 생성

// @RequiredArgsConstructor: 특정 필드에 초기값을 저장할 수 있는 매개 변수가 있는 생성자를 자동 생성하여 제공하는 어노테이션
// ▶ final 제한자를 사용하여 선언된 필드에 초기값 저장할 수 있는 매개 변수가 있는 생성자 생성
// ▶ 필드를 final 선언하지 않으면 기본 생성자로 취급???
// ▶ @NoArgsConstructor 어노테이션과 중복 사용 불가

// @Setter: 클래스에 선언된 모든 필드에 대한 Setter() 메소드를 자동 생성하여 제공하는 어노테이션
// ▶ 클래스 레벨이 아닌 필드 레벨로 @Setter 어노테이션을 사용하면 그 필드에 대한 Setter() 메소드를 자동 생성하여 제공

// @Getter: 클래스에 선언된 모든 필드에 대한 Getter() 메소드를 자동 생성하여 제공하는 어노테이션
// ▶ 클래스 레벨이 아닌 필드 레벨로 @Getter 어노테이션을 사용하면 그 필드에 대한 Getter() 메소드를 자동 생성하여 제공

// @ToString: 클래스에 toString() 메소드를 오버라이드 자동 생성하여 제공하는 어노테이션
// ▶ 클래스에 선언된 모든 필드의 값을 문자열로 반환하는 기능을 제공

// @Data: 기본 생성자 / Setter() 메소드 / Getter() 메소드 / toString() 메소드 / equals() / hashCode() 메소드 등을 자동생성하여 제공하는 어노테이션

// @Builder: 클래스 내부에 Builder 클래스를 생성하여 제공하는 어노테이션 - MemberBuilder
// ▶ Builder 클래스: 객체 생성 시 객체 필드에 필요한 값을 저장하기 위한 메소드를 제공
// ▶ 생성자보다 가독성이 좋고 필드의 순서에 상관 없이 초기화 작업 편리
// ▶ 매개 변수에 생성자의 @Builder 어노테이션을 사용하면 생성사 매개 변수로 초기화 처리하기 위한 필드에만 값을 저장할 수 있는 메소드 제공

// @Slf4j: 로그 이벤트를 발생하는 Logger 객체가 저장된 필드(log)를 자동 생성하여 제공하는 어노테이션

@AllArgsConstructor
@Builder
@Data
public class Member {
	private String id;
	private String name;
	private String eamil;
}
