package com.heegene.web.menu.dao;

import java.util.List;

import com.heegene.web.menu.dto.MenuDto;

public interface MenuDao {
	public List<MenuDto> getMenuList() throws Exception;
	
	public int saveMenu(MenuDto menuDto) throws Exception;
	
	public int updateMenu(MenuDto menuDto) throws Exception;
	
	public int deleteMenu(String code) throws Exception;
	
}
