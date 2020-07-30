package kr.or.connect.guestbook.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.connect.guestbook.config.ApplicationConfig;
import kr.or.connect.guestbook.config.WebMvcContextConfiguration;
import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContextConfiguration.class, ApplicationConfig.class})
public class GuestBookApiControllerTest {
	@InjectMocks
	public GuestbookApiController guestbookApiController;
	// => InjectMocks 어노테이션이 붙어서 선언되면 mock 객체인 GuestbookService를 사용하게 됨 
	// 스프링에 의해 주입된 객체를 사용하는 것이 아니라, Mockito 프레임워크에 의해 생성된 가짜 객체가 주입되어
	// 객체가 생성됨 
	
	
	@Mock
	public GuestbookService guestbookService;
	// mock annotation을 붙여 선언된 guestbookService는 mockito에 의해 mock object로 생성됨
	// => 가짜 객체 
	
	private MockMvc mockMvc;
	
	// test method가 실행되기 전 @Before 가 붙은 메소드가 실행됨 
	@Before
	public void createController() {
		MockitoAnnotations.initMocks(this);
		// 현재 객체에서 @Mock이 붙은 필드를 mock 객체로 초기화
		
		mockMvc = MockMvcBuilders.standaloneSetup(guestbookApiController).build();
		// MockMVC 타입의 변수 mockMvc를 초기화. guestbookApiController를 테스트하기 위한 mockmvc 객체 생성 
	}
	
	
	@Test
	public void getGuestbooks() throws Exception {
		Guestbook guestbook1 = new Guestbook();
		guestbook1.setId(11L);
		guestbook1.setRegdate(new Date());
		guestbook1.setContent("Hello");
		guestbook1.setName("kim");
		
		
		List<Guestbook> list = Arrays.asList(guestbook1);
		
		// 방명록 한 건을 추가하고 Guestbook 타입의 리스트에 추가한 방명록을 넣음 
		
		
		when(guestbookService.getGuestbooks(0)).thenReturn(list);
		// when(mock 객체.mock 객체의 메소드 호출).thenReturn(메소드가 리턴할 값)
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/guestbooks").contentType(MediaType.APPLICATION_JSON);
		// mockmvcrequestbuilder를 이용해 MockMvc에게 호출할 url을 설정 
		// get 방식으로 /guestbooks 경로를 호출하라는 설정
		// contenttype = Application_json 으로 해서 application json으로 API를 호출함
		// 둘을 합쳐서 보면, application/json 형식으로 /guestbooks를 GET 방식으로 호출함. 
		// 그리고 그 URL 정보를 가진 reqBuilder를 생성
		
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		// mockMvc.perform(reqBuilder)는 해당하는 url에 대한 요청을 보냈다는 것을 의미
		// isOK는 상태코드값이 200이 나와야 한다는 것을 의미하며, andDo(print())는 처리 내용을 출력하게 됨
		
		verify(guestbookService).getGuestbooks(0);
		
		
		
	}
	
	@Test
	public void deleteGuestbook() throws Exception {
		Long id = 1L;
		when(guestbookService.deleteGuestbook(id, "127.0.0.1")).thenReturn(1);
		// 위의 getguestbook과 마찬가지로 임의로 id=1(long type)을 설정하고 deleteGuestbook 메소드를 실행하고,
		// 리턴할 값으로 1 지정
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/guestbooks/" + id).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(guestbookService).deleteGuestbook(id, "127.0.0.1");
		
	}
}
