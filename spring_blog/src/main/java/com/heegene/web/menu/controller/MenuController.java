package com.heegene.web.menu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heegene.web.menu.dto.MenuDto;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public String getMenuList(Model model) throws Exception {
		model.addAttribute("menuDto", new MenuDto());
		
		return "menu/menu";
	}
	// url로 접근시 views/menu 아래의 menu.jsp로 연결시켜주는 역할
	// 거기에 menu.jsp의 입력폼에서 사용할 menuDto 객체 생성하여 보내주는 역할 

}
