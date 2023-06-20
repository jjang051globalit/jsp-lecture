package com.jjang051.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.BoardDao;
import com.jjang051.model.BoardDto;
import com.jjang051.utils.ScriptWriter;

@WebServlet("/board/replyProcess")
public class ReplyProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int regroup = Integer.parseInt( request.getParameter("regroup"));
		int relevel = Integer.parseInt(request.getParameter("relevel"));
		int restep = Integer.parseInt(request.getParameter("restep"));
		
		BoardDto boardDto = new BoardDto();
		boardDto.setRegroup(regroup);
		boardDto.setRelevel(relevel);
		boardDto.setRestep(restep);
		
		
		boardDao.updateRelevel(boardDto);
		
		boardDto.setName(userName);
		boardDto.setUserId(userId);
		boardDto.setTitle(title);
		boardDto.setContents(contents);
		boardDto.setRegroup(regroup);
		boardDto.setRelevel(relevel+1);
		boardDto.setRestep(restep+1);
		
		int result = boardDao.replyBoard(boardDto);
		if(result>0) {
			response.sendRedirect("../board/list");
		} else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류로 글이 입력되지 않았습니다. 다시 시도해 주세요");
		}
	}
}
