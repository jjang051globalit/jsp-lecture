package com.jjang051.controller.board;

import java.io.IOException;
import java.util.ArrayList;



import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;
import com.jjang051.model.PageDto;

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
		//String strStart = request.getParameter("start");
		//String strEnd = request.getParameter("end");
		int clickPage = 0;
		String tempClickPage = request.getParameter("clickPage");
		if(tempClickPage==null) {
			clickPage = 1;
		} else {
			clickPage = Integer.parseInt(tempClickPage);
		}
		
		double total = boardDao.getTotal(); // 전체 페이지 갯수
		
		double pagePerList = 10; // 위에서 한번에 보여줄 게시물 갯수
		int pageBlock = 4; // 아래쪽에 pagination의 한번에 보여지는 갯수
		
		
		// 1~5   6~10  11~15 
		int pageTotal = (int) (Math.ceil(total / pagePerList)); // 아래쪽 페이지 출력 갯수
		int pageStart=(clickPage-1)/pageBlock*pageBlock+1;  //1 ~ 4 / 5 ~ 8 / 9 ~ 12 
		int pageEnd=pageStart+pageBlock-1;
		if(pageEnd>pageTotal) pageEnd = pageTotal;  
		// 마지막 페이지
		
		
		
		System.out.println("pageTotal==="+  pageTotal);
		//만약에 전체 글 갯수가 108라면 108 
		//int start = strStart==null ? 1 : Integer.parseInt( strStart );
		//int end = strEnd==null ? (int) pagePerList: Integer.parseInt( strEnd );
		
		//clickPage = 1; //1 == 1~10; 2 == 11~20 
		int start =  (clickPage - 1)*(int)pagePerList+1; //1 , 11, 21
		int end = 	 start+(int)pagePerList-1;	         //10, 21, 30
		
		
		PageDto pageDto = new PageDto();
		pageDto.setPageTotal(pageTotal);
		pageDto.setTotal(total);
		pageDto.setPageBlock(pageBlock);
		pageDto.setPageStart(pageStart);
		pageDto.setPageEnd(pageEnd);
		pageDto.setPagePerList(pagePerList);
		
		ArrayList<BoardDto> boardList = boardDao.getList(start,end);
		request.setAttribute("clickPage", clickPage);
		request.setAttribute("boardList", boardList);
//		request.setAttribute("pageTotal", pageTotal);
//		request.setAttribute("total", (int)total);
//		request.setAttribute("pageBlock", pageBlock);
//		request.setAttribute("pageStart", pageStart);
//		request.setAttribute("pageEnd", pageEnd);
//		request.setAttribute("pagePerList", (int)pagePerList);
		request.setAttribute("pageDto", pageDto);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/board/list.jsp");
		dispatcher.forward(request, response);
	}
}











