package kr.or.connect.guestbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.guestbook.dao", "kr.or.connect.guestbook.service"})
// base pkg 선언하고 이것들이 실행될 때 사용할 DBConfig를 import하도록 지정 
@Import({DBConfig.class})
public class ApplicationConfig {
	
	
	
}
