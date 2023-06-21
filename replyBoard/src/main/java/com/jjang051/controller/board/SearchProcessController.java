package com.jjang051.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;


@WebServlet("/board/searchProcess")
public class SearchProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchProcessController() {
        super();

    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String searchWord = request.getParameter("searchWord");
		ArrayList<BoardDto> searchList = null;
		BoardDao boardDao = new BoardDao();
		searchList = (ArrayList<BoardDto>)boardDao.search(category,searchWord);
		request.setAttribute("searchList", searchList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/search-list.jsp");
		dispatcher.forward(request, response);
		//System.out.println(category);
		//System.out.println(searchWord);
		
	}

}











