package com.jjang051.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.model.MemberDto;
import com.jjang051.utils.ScriptWriter;


@WebFilter("/board/*")
public class LoginFilter extends HttpFilter implements Filter {
       

    public LoginFilter() {
        super();

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req=(HttpServletRequest)request;
	      HttpSession session=req.getSession();
	      MemberDto loggedMember=(MemberDto)session.getAttribute("loggedMember");
	      
	      //2. 만일 로그인된 상태라면 관여하지 않고 요청의 흐름을 이어가면 되고
	      if(loggedMember != null) {
	         chain.doFilter(request, response);
	      }else {
	         //3. 로그인된 상태가 아니라면 로그인 폼으로 리다일렉트 이동시킨다. ( HttpServletResponse 필요)
	         HttpServletResponse res=(HttpServletResponse)response;
	         String cPath=req.getContextPath();
	         res.sendRedirect(cPath+"/member/login");
	      }
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
