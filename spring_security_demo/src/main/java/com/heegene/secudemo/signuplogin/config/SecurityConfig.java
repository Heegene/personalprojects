package com.heegene.secudemo.signuplogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.heegene.secudemo.signuplogin.service.MemberService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// enablewebsecurity는 Spring security를 설정할 클래스라고 정의하는 어노테이션
	// 설정은 websecuconfigadapter 를 상속받아 메서드를 구현하는 것이 일반적 
	// websecuconfigadapter는 websecurityconfigurer 인스턴스를 편리하게 생성하기 위한 클래스 
	private MemberService memberService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// passencoder는 BCryptPasswordEncoder는 spring security에서 제공하는 비밀번호 암호화 객체
		// Service에서 비밀번호를 암호화할 수 있도록 Bean 으로 등록 
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉토리 하위파일목록은 인증 무시(= 항상 통과함)
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
		// 해당 경로의 파일들은 spring security가 무시
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// 페이지 권한 설정
				.antMatchers("/admin/**").hasRole("ADMIN") // admin 페이지는 ADMIN 롤이 있는 경우만
				.antMatchers("user/myinfo").hasRole("MEMBER") // user/myinfo는 MEMBER 롤이 있는 경우만
				.antMatchers("/**").permitAll() // /로 시작하는 건 전체 허용
			.and() // 로그인 설정
				.formLogin()
				.loginPage("/user/login") // 로그인 페이지
				.defaultSuccessUrl("/user/login/result") // 성공 시 페이지
				.permitAll()
			.and() // 로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 페이지
				.logoutSuccessUrl("/user/logout/result") // 로그아웃 성공 시 페이지 
				.invalidateHttpSession(true) // session invalidate 
			.and()
				// 403 예외처리 핸들링
				.exceptionHandling().accessDeniedPage("/user/denied");
		
		// httpsecurity를 통해 http 요청에 대한 웹 기반 보안을 구성
		// authrizerequest() => httpservletrequest에 따라 접근(access)를 제한
		// antMatchers() 메서드로 특정 경로를 지정, permitAll(), hasRole()로 역할에 따른 접근설정을 잡아줌
		// permitall은 권한없이 접근이 가능함
		// anyRequest().authenricated()는 모든 요청에 대해 인증된 사용자만 접근하도록 설정(여기에선 설정 안했음)
		
		// 로그인 설정
		// form 기반으로 인증하도록 하며, 로그인 정보는 기본적으로 httpSession을 이용함
		// login 경로로 접근하면 Spring security에서 제공하는 로그인 form을 사용하도록 함 
		// .loginpage => 기본 제공되는 form 말고 커스텀 로그인 폼을 사용하고 싶으면 loginpage()메서드 사용
		// 이때 커스텀 로그인 form의 action 경로와 loginpage()의 parameter 경로가 일치해야 인증 처리 가능(login.html)
		// defaultSuccessurl => 성공했을 때 이동되는 페이지로, 컨트롤러에서 URL 매핑이 되어 있어야 함
		// .usernameParameter => 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데,
		// usernameparameter를 통해 파라미터명을 변경할 수 있음 (여기에서는 변경하지 않았음) 
		
		// 로그아웃 설정
		// 로그아웃을 지원하는 메서드로, websecurityconfigureradapter를 사용할 때 자동적용
		// 기본적으로는 /logout에 접근하면 http session을 제거함
		// logoutrequestmatcher 를 사용하면 기본 url(/logout)이 아닌 다른 url로 로그아웃 url을 재정의함
		// invalidate -> http 세션 초기화하는 작업
		// deleteCookies("KEY명") 로그아웃 시 특정 쿠키를 제거하고 싶을 때 사용
		
		// exceptionhandlign().accessdeniedpage -> 예외가 발생했을 때 exceptionhandling으로 핸들링 가능
		// 접근 권한 없을 때 로그인 페이지로 이동하도록 명시 
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	// spring security에서 모든 인증은 authenticationmanager를 통해 이뤄지고, 이를 생성하기 위해 builder 사용
	// 로그인 처리, 즉 인증을 위해서는 Userdetailservice를 통해 필요한 정보를 가져오는데,
	// 여기에서는 memberserivice에서 이를 처리함
	// 서비스 클래스에서는 userdetailservice 인터페이스를 implement했고, loaduserbyusername() 메서드를 구현
	// 비밀번호 암호화를 위해 passwordencoder를 사용함
	
	
	
	
	
	
}
