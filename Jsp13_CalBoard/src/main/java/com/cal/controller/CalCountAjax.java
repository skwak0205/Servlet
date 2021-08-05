package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;

import net.sf.json.JSONObject;

@WebServlet("/count.do")
public class CalCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String yyyyMMdd = request.getParameter("yyyyMMdd");
		System.out.println("param : " + id + ", " + yyyyMMdd);

		CalDao dao = new CalDao();
		int count = dao.calendarViewCount(id, yyyyMMdd);
		System.out.println("일정 갯수 : " + count);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);

		// json-lib 가지고 map(java object) -> json
		JSONObject obj = JSONObject.fromObject(map);

		PrintWriter out = response.getWriter();
		// json-lib에서 이렇게 만듦. 보통은 out.println()
		obj.write(out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
