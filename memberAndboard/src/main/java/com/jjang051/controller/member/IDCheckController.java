package com.jjang051.controller.member;

import java.io.IOException;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.jjang051.model.MemberDao;

@WebServlet("/member/idCheck")
public class IDCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IDCheckController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDao memberDao = new MemberDao();
		String userId = request.getParameter("userId");
		int result = memberDao.idCheck(userId);
		request.setAttribute("count", result);
		HttpSession session = request.getSession();
		//session.setAttribute("count", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/idCheck.jsp");
		dispatcher.forward(request, response);
	}
}











