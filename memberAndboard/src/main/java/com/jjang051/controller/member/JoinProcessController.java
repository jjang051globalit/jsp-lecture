package com.jjang051.controller.member;

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


@WebServlet("/member/joinProcess")
public class JoinProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("왜 안넘어오니...");
		
		
		request.setCharacterEncoding("utf-8"); 
		String userId = request.getParameter("userId"); 
		String userPw = request.getParameter("userPw"); 
		String userName = request.getParameter("userName"); 
		String userEmail = request.getParameter("userEmail"); 
		int zonecode = Integer.parseInt(request.getParameter("zonecode")); 
		String userAddress = request.getParameter("userAddress"); 
		String detailAddress = request.getParameter("detailAddress"); 
		String extraAddress = request.getParameter("extraAddress"); 
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		memberDto.setId(userId);
		memberDto.setPassword(userPw);
		memberDto.setName(userName);
		memberDto.setZonecode(zonecode);
		memberDto.setAddress(userAddress);
		memberDto.setExtraAddress(extraAddress);
		memberDto.setDetailAddress(detailAddress);
		memberDto.setEmail(userEmail);
		int result = memberDao.insertMember(memberDto);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원가입 되었습니다.", "../member/login");
		} else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류가 발생 되었습니다. 다시 시도해 주세요");
		}
	}
}




















