package kr.or.connect.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 이 annotation으로 config 파일이라는 것을 인식하게 됨
// 설정 클래스로 인식하게 됨(컴파일러가)

// config 어노테이션이 부으면 Spring 설정 클래스라는 것을 의미하며,
// AnnotationConfigApplicationContext 는 나중에 이런 자바 config 클래스를 읽어들여
// IOC(inversion of control)과 DI(dependency injection)를 적용하게 됨
// 이때 bean 어노테이션이 붙어 있는 메소드들을 자동으로 실행해서
// 그 결과를 리턴하는 객체들을 기본적으로 싱글턴으로 관리하게 됨 
public class ApplicationConfig {
	@Bean
	public Car car(Engine e) {
		// bean 선언도 자바 메서드 생성하듯이 선언하도록 함 
		Car c = new Car();
		c.setEngine(e);
		return c;
	} 
	
	@Bean
	public Engine engine() {
		// annotation은 jdk 5부터 지원
		// engine bean도 선언
		
		return new Engine();
	}
	
}
