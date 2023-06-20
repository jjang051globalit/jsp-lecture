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


@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
       

    public LoginFilter() {
        super();

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession(false);
		System.out.println(session);
//		MemberDto loggedMember = (MemberDto)session.getAttribute("loggedMember");
//		if(loggedMember==null) {
//			ScriptWriter.alertAndNext(httpResponse, "로그인", "../member/login");
//		} else {
//			chain.doFilter(request, response);
//		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
