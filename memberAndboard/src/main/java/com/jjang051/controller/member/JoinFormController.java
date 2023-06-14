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

@WebServlet("/member/join")
public class JoinFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JoinFormController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/member/join.jsp");
		dispatcher.forward(request, response);
	}

}











