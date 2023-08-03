package xyz.itwill10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 요청 처리 메소드에 의하여 처리될 결과를 뷰(JSP 문서)에게 제공하는 방법
// 1. ModelAndView 객체의 addObject 메소드를 호출하여 처리 결과를 속성값으로 저장하여 제공
@Controller
public class ResultController {
	@RequestMapping("/resultMav")
	public ModelAndView modelAndViewResult() {
		ModelAndView modelAndView = new ModelAndView("result_display");

		// ModelAndView.addObject(String attributeName, Object attributeValue)
		// ▶ ModelAndView 객체에 처리 결과를 속성값으로 저장하는 메소드 - Request Scope
		// ▶ 뷰(JSP 문서)에서는 EL 또는 JSTL을 사용하여 속성명으로 속성값을 제공받아 사용
		modelAndView.addObject("mavName", "홍길동");
		return modelAndView;
	}

	// 요청 처리 메소드는 Front Controller에 의하여 자동으로 호출되는 메소드
	// ▶ 요청 처리 메소드에 매개 변수를 작성하면 Front Controller는 스프링 컨테이너(WebApplicationContext)에게
	// 매개 변수에 저장 가능한 객체(Spring Bean)를 제공받아 저장 가능
	@RequestMapping("/resultMav2")
	public ModelAndView modelAndViewResult2(ModelAndView modelAndView) {

		modelAndView.setViewName("result_display");
		modelAndView.addObject("mavName2", "홍길똥");
		return modelAndView;
	}

	@RequestMapping("/resultRequest")
	public String requestResult(HttpServletRequest request) {
		// HttpServletRequest.setAttribute(String attributeName, Object attributeValue)
		// ▶ HttpServletRequest 객체에 처리 결과를 속성값으로 저장하는 메소드 - Request Scope
		request.setAttribute("requestName", "임꺽정");
		return "result_display";
	}

	@RequestMapping("/resultModel")
	public String modelResult(Model model) {
		// Model 객체: 처리 결과를 속성값으로 저장하여 뷰에게 제공하기 위한 객체
		// Model.addAttribute(String attributeName, Object attributeValue)
		// ▶ Model 객체에 처리 결과를 속성값으로 저장하는 메소드 - Request Scope
		model.addAttribute("modelName", "전좌치");
		return "result_display";
	}
}
