package com.jjang051.controller.board;

import java.io.IOException;
import java.util.ArrayList;



import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/board/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// board 테이블의 row값을 가지고 오기....
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> boardList = boardDao.getList();
		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/list.jsp");
		dispatcher.forward(request, response);
	}
}











