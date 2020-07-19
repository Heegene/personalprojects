package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam02 {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Car car = (Car) ac.getBean("c");
		car.run();
		// 실행 결과
		// Engine 생성자 호출
		// Car 생성자 호출
		// Car.run 호출됨(그리고 그 안에있던 engine의 exec 도 호출됨)
		// 이렇게 객체를 주입하는 DI의 장점
		// 사용자는 실행 클래스만 알고 사용하면 됨(Engine을 몰라도 됨)
		// 설정만 바꾸면(문서xml) 실행코드 변경 없이 설정 가능 
		
		
	}
}
