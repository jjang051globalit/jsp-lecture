package com.jjang051.controller.board;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;


@WebServlet("/board/view")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		BoardDao boardDao = new BoardDao();
		BoardDto boardDto = boardDao.getView(id);
		request.setAttribute("boardDto", boardDto);
		RequestDispatcher dispatcher =   
				request.getRequestDispatcher("/WEB-INF/board/view.jsp");
		dispatcher.forward(request, response);
	}
}









