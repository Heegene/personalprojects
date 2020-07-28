package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
        // 코드를 작성하세요.
    	GuestbookDao dao = new GuestbookDao();
    	// request.setCharacterEncoding("utf-8");
=======
    	GuestbookDao dao = new GuestbookDao();
>>>>>>> 6a8e7501abac37ad1a372ba49caabe1142482439
    	List<Guestbook> li = dao.getGuestbooks();
    	
    	request.setAttribute("list", li);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/guestbook/guestbooks.jsp");
    	rd.forward(request, response);
    	
    }

}
