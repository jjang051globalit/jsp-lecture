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
		String strStart = request.getParameter("start");
		String strEnd = request.getParameter("end");
		double pagePerList = 10; // 한번에 보여줄 게시물 갯수
		double total = boardDao.getTotal(); // 전체 페이지 갯수
		
		int pageTotal = (int) (Math.ceil(total / pagePerList));
		System.out.println("pageTotal==="+  pageTotal);
		//만약에 전체 글 갯수가 108라면 108 
		int start = strStart==null ? 1 : Integer.parseInt( strStart );
		int end = strEnd==null ? (int) pagePerList: Integer.parseInt( strEnd );
		
		
		ArrayList<BoardDto> boardList = boardDao.getList(start,end);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageTotal", pageTotal);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/list.jsp");
		dispatcher.forward(request, response);
	}
}











