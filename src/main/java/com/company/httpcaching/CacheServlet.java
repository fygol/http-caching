package com.company.httpcaching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.time.Time;

@WebServlet (urlPatterns="/cache")
public class CacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET method invoked: " + new Date());
		
		String ifModifiedSince = request.getHeader("If-Modified-Since");
		System.out.println("If-Modified-Since:" + ifModifiedSince);
		
		response.setContentType("text/plain");
		response.setHeader("Date", Time.formatToHttpDate(new Date()));
//		response.setHeader("Expires", Time.formatToHttpDate(Time.afterSeconds(30)));
//		response.setHeader("Cache-Control", "private, max-age=15");
		response.setHeader("Last-Modified", Time.formatToHttpDate(new Date()));
		
//		try {
//			Thread.sleep(3000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		PrintWriter out = response.getWriter();
		out.write( "Time:" +  getTestMessage());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(new Date());
	}
	
	private String getTestMessage() {
		return new Date().toString();
	}
}
