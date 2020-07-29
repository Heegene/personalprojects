package kr.or.connect.guestbook.config;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.connect.guestbook"} )
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
		
		// css, img, js ~ 로 시작하는 요청은 resource location css/ img/ js/ 에서 찾도록 지정 
	}
	
	// default servlet handler를 사용하게 합니다. 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		// mapping 정보가 없는 요청은 스프링의 defaultservlethttprequesthandler가 처리하게 함 
		// WAS의 default servlet이 static한 자원을 읽어서 보여주도록 함
	}
	
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		System.out.println("addViewContollers가 호출됩니다. ");
		registry.addViewController("/").setViewName("index");
		
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//어떤 뷰인지 정보를 찾아내도록 하는 설정
		// index가 들어왔다면 web-inf/views 아래의 index.jsp를 반환하도록 함 
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
		
	}
	
	
}
