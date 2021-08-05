package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// web.xml의 servlet과 servlet-mapping 의미
// 객체 하나 생성하는 것
// HelloServlet helloServlet = new HelloServlet();
@WebServlet("/controller.do")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 직렬화

	private String initParam;

	public HelloServlet() {
		System.out.println("HelloServlet 생성!");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		initParam = config.getInitParameter("actor");
		String contextParam = config.getServletContext().getInitParameter("singer");

		System.out.println("initParam : " + initParam); // post에서 initParam이 null인 이유는 hello servlet에서만 사용 가능한 변수이기 때문
		System.out.println("contextParam : " + contextParam);

		System.out.println("HelloServlet init!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// controller에서 무조건 써주기!!
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("get 방식으로 들어옴!!");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		// server가 client에 응답 (response) - output
		PrintWriter out = response.getWriter();
		out.print("<h1 style='color : red;'>Hello Servlet</h1>"); // html 형태의 문자열
		out.print("<h2>계층구조/LifeCycle/url-mapping</h2>");
		out.print("<a href='home.html'>home...</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println("post 방식으로 들어옴!!");

		String command = request.getParameter("command");
		System.out.println("{" + command + "}");

		String result = "<h1 style='color : blue;'>Hello Servlet</h1>" + "<h2>계층구조/LifeCycle/url-mapping</h2>"
				+ "<a href='home.html'>home...</a>";

		response.getWriter().append(result);
	}

	@Override
	public void destroy() {
		System.out.println("HelloServlet destory!");
	}

}
