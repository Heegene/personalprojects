package com.heegene.web.menu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.heegene.web.menu.dao.MenuDao;
import com.heegene.web.menu.dto.MenuDto;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Inject
	private MenuDao menuDao;
	
	@Override
	public List<MenuDto> getMenuList() throws Exception {
		return menuDao.getMenuList();
	}
	
	@Override
	public void saveMenu(MenuDto menuDto) throws Exception {
		menuDao.saveMenu(menuDto);
	}
	
	@Override
	public void updateMenu(MenuDto menuDto) throws Exception {
		menuDao.updateMenu(menuDto);
	}
	
	@Override
	public void deleteMenu(String code) throws Exception {
		menuDao.deleteMenu(code);
	}
}
