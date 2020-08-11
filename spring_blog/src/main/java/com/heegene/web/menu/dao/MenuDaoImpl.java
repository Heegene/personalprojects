package com.heegene.web.menu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.heegene.web.menu.dto.MenuDto;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MenuDto> getMenuList() throws Exception {
		return sqlSession.selectList("com.heegene.web.menu.menuMapper.getMenuList");
	}
	
	@Override
	public int saveMenu(MenuDto menuDto) throws Exception {
		return sqlSession.insert("com.heegene.web.menu.menuMapper.insertMenu", menuDto);
	}
	
	@Override
	public int updateMenu(MenuDto menuDto) throws Exception {
		return sqlSession.update("com.heegene.web.menu.menuMapper.updateMenu", menuDto);
	}
	
	@Override
	public int deleteMenu(String code) throws Exception {
		return sqlSession.delete("com.heegene.web.menu.menuMapper.deleteMenu", code);
	}

}
