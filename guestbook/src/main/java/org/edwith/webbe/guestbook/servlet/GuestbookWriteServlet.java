package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	// form 에서 제출된 정보를 바탕으로 GuestBook 객체를 완성시키고, addguestbook 메소드를 실행함
    	
    	
    	// 정보 받아오기
    	request.setCharacterEncoding("utf-8");
    	
    	String name = request.getParameter("name");
    	String content = request.getParameter("content");
    	Guestbook guestbook = new Guestbook(name, content);
    	
    	GuestbookDao dao = new GuestbookDao();
    	
    	
    	try {
			dao.addGuestbook(guestbook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    	response.sendRedirect("http://localhost:8085/guestbook/guestbooks");
    
    	
    }

}
