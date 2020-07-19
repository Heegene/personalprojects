package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {
	
	
	public static void main(String[] args) {
		// spring 이 가진 공장 생성
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// 공장을 생성하고 해당 공장에서 생성할 정보(빈)의 위치를 지정해 줌
		
		System.out.println("초기화 완료!!");
		
		UserBean userBean = (UserBean) ac.getBean("userBean");
		// 무조건 오브젝트타입으로 반환하므로 형변환 해줘야함 
		
		userBean.setName("이콩이");
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean)ac.getBean("userBean");
		
		if(userBean == userBean2) {
			System.out.println("빈 1과 빈 2 인스턴스는  같습니다. ");
			// singleton 패턴을 이용하므로 getbean 을 계속 한다 하더라도
			// 한번 만들어둔 하나의 bean을 이용하는 것
		}
		
		userBean2.setName("이콩아지");
		
		System.out.println(userBean.getName());
		System.out.println(userBean2.getName());
		// userbean2로 바꿔도 userbean에서도 바뀜 왜냐면 인스턴스가 하나니까 !! 
	}

}
