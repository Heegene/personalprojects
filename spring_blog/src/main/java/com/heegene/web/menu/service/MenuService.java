package com.heegene.web.menu.service;

import java.util.List;

import com.heegene.web.menu.dto.MenuDto;

public interface MenuService {
	
	public List<MenuDto> getMenuList() throws Exception;
	
	public void saveMenu(MenuDto menuDto) throws Exception;
	
	public void updateMenu(MenuDto menuDto) throws Exception;
	
	public void deleteMenu(String code) throws Exception;
	

}
