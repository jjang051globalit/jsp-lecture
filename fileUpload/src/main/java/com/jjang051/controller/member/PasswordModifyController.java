package com.jjang051.controller.member;

import java.io.IOException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/modifyPassword")
public class PasswordModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PasswordModifyController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/modify-password.jsp");
		dispatcher.forward(request, response);
		
	}

}










