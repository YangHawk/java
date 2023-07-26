package xyz.itwill04.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;

//Spring 프레임워크에서는 BeanFactory 객체 또는 ApplicationContext 객체로 String 컨테이너(Spring Container) 기능을 제공
//▶ 스프링 컨테이너는 환경 설정 파일(Spring Bean Configuration File - XM<L) 파일을 제공받아 Spring Bean(객체) 관리
public class CreateBeanApp {
	public static void main(String[] args) {
		System.out.println("1.BeanFactory 객체를 생성하여 스프링 컨테이너로 사용하는 방법");
		System.out.println("=============== Spring Container 초기화 전 ===============");
		BeanFactory factory = new XmlBeanFactory(null);
		System.out.println("=============== Spring Container 초기화 전 ===============");
	}
}
