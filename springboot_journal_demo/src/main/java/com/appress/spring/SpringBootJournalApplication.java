package com.appress.spring;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appress.spring.domain.Journal;
import com.appress.spring.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication {
	
	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return() -> {
			repo.save(new Journal("스프링 부트 저널 초기화용 데이터", "스프링 부트 공부 시작", new Date()));
			repo.save(new Journal("제목입니다", "날짜입니다", new Date()));
			repo.save(new Journal("제목2", "내용2", new Date()));
			repo.save(new Journal("테스트 제목", "내용입니다.내용내용내용내용! 내용내용..내용내용?? 내용... 내용....!내용!", new Date()));
		};
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}

}
