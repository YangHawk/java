package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

// @Controller: 요청 처리 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// ▶ 클래스의 이름을 Spring Bean의 식별자(beanName)로 자동 설정 - 첫 문자는 소문자로 변환
// ▶ value 속성을 사용하여 Spring Bean의 식별자(beanName) 변경 가능
// @Component 는 일반적인 Java 클래스! || DAO 클래스(Repository)를 Spring Bean 할때는 @Repository || 서비스 클래스 = @Service
// 요청 처리 클래스 = Controller야! implements Controller 안해도 됨!

@Slf4j
// @Controller 어노테이션을 사용하면 Controller 인터페이스를 상속받지 않아도 요청 처리 클래스로 작성 가능(자동으로 상속)
// ▶ @RequestMapping이라는 어노테이션을 사용하여 메소드가 요청 처리 메소드로 처리되도록 작성
// ▶ @RequestMapping이라는 어노테이션을 사용하여 요청 처리 메소드를 여러 개 선언 가능(handleRequest 를 여러 개!)
@Controller
public class HelloController {
	
	// 요청 처리 메소드는 응답 처리를 위하여 Front Controller에게 반드시 뷰의 이름(ViewName)을 제공해야 함
	// ▶ Front Controller는 제공받은 뷰의 이름을 이용하여 ViewResolver 객체로 응답 처리되도록 변환
	// 1. 요청 처리 메소드의 반환형을 [void]로 작성한 경우 Front Controller에게 메소드의 이름을 뷰의 이름으로 제공 - hello가 뷰이름이 되는거야!
	// 2. 요청 처리 메소드의 반환형을 [String]로 작성한 경우 Front Controller에게 메소드의 반환값(문자열)을 뷰의 이름으로 제공
	// 3. 요청 처리 메소드의 반환형을 [ModelAndView]로 작성한 경우 반환된 ModelAndView 객체에 뷰의 이름을 저장하여 제공

	// Front Controller가 InternalResourceViewResolver를 사용한 경우 뷰의 이름으로 생성된 JSP 문서로 응답 처리
	// 그런데, Front Controller가 BeanNameViewResolver를 사용한 경우 Spring Bean으로 응답 처리
	// ▶ 요청 처리 메소드의 반환형을 Map 인터페이스 또는 Model 인터페이스로 설정 가능
	
	// @RequestMapping: 클라이언트 요청을 처리하기 위한 메소드를 선언하기 위한 어노테이션
	// ▶ 기본적으로 클라이언트의 모든 요청 방식(Method - GET / POST / PUT / PATCH / DELETE등)에 의해 호출되는
	// 요청 처리 메소드를 작성할 경우 사용하는 어노테이션
	// ▶ 클라이언트의 요청 방식을 구분하여 요청 처리 메소드를 호출하고자 할 경우 @GetMapping / @PostMapping /
	// @PutMapping / @PatchMapping / @DeleteMapping 등의 어노테이션을 사용할 수 있음
	// REST 프로그램을 만들 때 사용 - RESTful API
	// Value 속성: 클라이언트의 요청 정보(URL 주소)를 속성값으로 설정
	// ▶ 클라이언트의 요청 URL 주소로 Front Controller에 의하여 요청 처리 메소드가 자동으로 호출됨
	// ▶ 다른 속성이 없을 경우 속성값만 설정 가능
	// ▶ 다른 요청 처리 메소드의 value 속성값과 중복될 경우 WAS 프로그램 시작 시 에러 발생
	@RequestMapping(value = "/hello")
	public void hello() {
		// 요청 처리 명령 작성 - Service 클래스의 메소드 호출
		log.info("[/hello] 페이지 요청 : HelloController 클래스의 hello() 메소드 호출");
	}

	@RequestMapping("/helloViewName")
	public String helloViewName() {
		log.info("[/helloViewName] 페이지 요청 : HelloController 클래스의 helloViewName() 메소드 호출");
		return "hello";
	}

	@RequestMapping("/helloMav")
	public ModelAndView helloModelAndView() {
		log.info("[/helloMav] 페이지 요청 : HelloController 클래스의 helloModelAndView() 메소드 호출");

		// ModelAndView: 처리 결과를 속성값으로 저장하고 뷰의 이름(ViewName)을 저장하기 위한 객체
		// 1. Setter 메소드로
		// ModelAndView modelAndView = new ModelAndView();
		// modelAndView.setViewName("hello"); // Setter 메소드 뷰의 이름 변경
		// return modelAndView;

		// 2. 생성자
		ModelAndView modelAndView = new ModelAndView("hello"); // 생성자로 뷰의 이름 변경
		return modelAndView;
	}
}
