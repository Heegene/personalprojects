package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Car {
	private Engine v8;
	
	public Car() {
		System.out.println("Car 생성자");
	}
	
	public void setEngine(Engine e) {
		this.v8 = e;
		
	}
	
	public void run() {
		System.out.println("엔진을 이용하여 달립니다. ");
		v8.exec();
	}
	
	public static void main(String[] args) {
		//Engine e = new Engine(); Car는 엔진이 필요하므로 엔진 생성
		// Car c = new Car(); // Car 생성자 실행됨
		
		//c.setEngine(e);
		// 위의 내용을 applicationContext.xml 에서 Spring에게 시켰으므로 주석처리 
		// c.run();
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Car car = (Car)ac.getBean("c");
		// 
		
		car.run();
	}
}
