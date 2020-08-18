package com.heegene.blog;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA auditing 활성화를 위해 enablejpaauditing 어노테이션 추가 
@SpringBootApplication
public class SpringbootBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}

}
