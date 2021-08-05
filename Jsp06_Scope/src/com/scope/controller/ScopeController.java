package com.scope.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// index의 request에 값을 담았기 때문
		String requestScope = (String) request.getAttribute("requestScope");

		HttpSession session = request.getSession();
		String sessionScope = (String) session.getAttribute("sessionScope");

		ServletContext application = getServletContext();
		String applicationScope = (String) application.getAttribute("applicationScope");

		System.out.println("request : " + requestScope); // result : null
		System.out.println("session : " + sessionScope);
		System.out.println("application : " + applicationScope);

		// PrintWriter out = response.getWriter();

		String responseText = "<h1>RESULT</h1>" + "<table border=1>" + "  <tr>" + "    <th>request</th>" + "    <td>"
				+ requestScope + "</td>" + "  </tr>" + "  <tr>" + "    <th>session</th>" + "    <td>" + sessionScope
				+ "</td>" + "  </tr>" + "  <tr>" + "    <th>application</th>" + "    <td>" + applicationScope + "</td>"
				+ "  </tr>" + "</table>";

		// out.print(responseText);

		String requestval = request.getParameter("requestVal");
		System.out.println("requestVal : " + requestval);

		// index의 request에 값을 담음
		request.setAttribute("requestScope", "request forward value");

		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		// forward로 result.jsp로 위임했기 때문에 index의 request가 result.jsp까지 간 것
		dispatch.forward(request, response);
	}

}
