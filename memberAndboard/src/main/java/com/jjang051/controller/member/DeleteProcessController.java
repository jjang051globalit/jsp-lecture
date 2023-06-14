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
import com.jjang051.model.MemberDto;
import com.jjang051.utils.ScriptWriter;

@WebServlet("/member/deleteProcess")
public class DeleteProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		memberDto.setId(userId);
		memberDto.setPassword(userPw);
		
		int result = memberDao.deleteMember(memberDto);
		if(result>0) {
			HttpSession session = request.getSession();
			session.invalidate();
			ScriptWriter.alertAndNext(response, "회원탈퇴되었습니다.", "../index/index");
		} else {
			ScriptWriter.alertAndBack(response, "비밀번호 확인해 주세요.");
		}
	}
}



















