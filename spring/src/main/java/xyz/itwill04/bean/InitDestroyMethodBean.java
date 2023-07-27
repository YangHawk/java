package xyz.itwill04.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class InitDestroyMethodBean implements ApplicationContextAware {
	//Aware 인터페이스를 상속받아 초기화 작업 - 예시!(낭중에는 Annotation 써!)
	private ApplicationContext context;

	public InitDestroyMethodBean() {
		System.out.println("※※※ InitDestroyMethodBean 클래스의 기본 생성자 호출 ※※※");
	}

	public void init() {
		System.out.println("※※※ InitDestroyMethodBean 클래스의 init() 메소드 호출 ※※※");
	}

	public void destroy() {
		System.out.println("※※※ InitDestroyMethodBean 클래스의 destroy() 메소드 호출 ※※※");
	}

	public void display() {
		System.out.println("※※※ InitDestroyMethodBean 클래스의 display() 메소드 호출 ※※※");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
