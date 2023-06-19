package com.jjang051.controller.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.model.MemberDto;
import com.jjang051.utils.ScriptWriter;

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto loggedMember = (MemberDto) session.getAttribute("loggedMember");
		String loggedMemberName = loggedMember.getName();
		session.invalidate();
		ScriptWriter.alertAndNext(response, loggedMemberName+"님 안녕히 가세요.", "../index/index");
		//정형돈님 로그아웃도었습니다. index
	}
}








