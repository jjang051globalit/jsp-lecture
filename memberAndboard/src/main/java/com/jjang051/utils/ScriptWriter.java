package com.jjang051.utils;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;



public class ScriptWriter {
	public static void alert(HttpServletResponse response,String alertMsg) throws IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"');</script>");
	}
	public static void alertAndBack(HttpServletResponse response,String alertMsg) throws IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"');history.back();</script>");
	}
	public static void alertAndNext(HttpServletResponse response,String alertMsg,String next) throws IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertMsg+"');location.href='"+next+"';</script>");
	}
}








