package com.jjang051.controller.member;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.model.MemberDao;
import com.jjang051.model.PasswordDto;
import com.jjang051.utils.ScriptWriter;

@WebServlet("/member/modifyPasswordProcess")
public class PasswordModifyProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PasswordModifyProcessController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPw = request.getParameter("userPw");
		String newUserPw = request.getParameter("newUserPw");
		String userId = request.getParameter("userId");
		PasswordDto passwordDto = new PasswordDto();
		passwordDto.setUserId(userId);
		passwordDto.setUserPw(userPw);
		passwordDto.setNewUserPw(newUserPw);
		
		MemberDao memberDao = new MemberDao();
		int result = memberDao.modifyPassword(passwordDto);
		if(result>0) {
			HttpSession session = request.getSession();
			session.invalidate();
			ScriptWriter.alertAndNext(response, 
					"비밀번호 변경 되었습니다. 다시 로그인 해주세요.", "../member/login");
		} else {
			ScriptWriter.alertAndBack(response, 
					"알 수 없는 오류로 비밀번호 변경에 실패하였습니다. 잠시 후 다시 시도해 주세요.");
		}
	}
}



















