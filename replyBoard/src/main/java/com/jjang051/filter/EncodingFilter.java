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

@WebFilter("/EncodingFilter")
public class EncodingFilter extends HttpFilter implements Filter {
	private String encoding;
	public EncodingFilter() {
		super();

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("EncodingFilter doFilter() 동작중...");
		// 1. 만일 요청 인코딩이 설정되지 않았다면
		if (request.getCharacterEncoding() == null) {
			// 인코딩 설정하기
			request.setCharacterEncoding(encoding);
			// request.setCharacterEncoding("utf-8");

		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
