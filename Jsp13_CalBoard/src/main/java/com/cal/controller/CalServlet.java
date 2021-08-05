package com.cal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dto.CalDto;

@WebServlet("/CalServlet")
public class CalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		CalDao dao = new CalDao();

		String command = request.getParameter("command");
		System.out.printf("[%s]\n", command);

		if (command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");

		} else if (command.equals("calendarlist")) {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");

			String yyyyMMdd = year + Util.isTwo(month) + Util.isTwo(date);

			List<CalDto> list = dao.calendarList("kh", yyyyMMdd);

			request.setAttribute("list", list);
			dispatch(request, response, "calendarlist.jsp");

		} else if (command.equals("insertcalendar")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");

			String mdate = year + Util.isTwo(month) + Util.isTwo(date) + Util.isTwo(hour) + Util.isTwo(min);

			int res = dao.insertCalendar(new CalDto(0, id, title, content, mdate, null));
			if (res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				request.setAttribute("msg", "일정 추가 실패");
				dispatch(request, response, "error.jsp");
			}

		} else if (command.equals("detail")) {

		}

		else if (command.equals("muldel")) {
			String[] seqs = request.getParameterValues("ckh");

			if (seqs == null || seqs.length == 0) {

			} else {
				int res = dao.multiDelete(seqs);
				if (res == seqs.length) {

				} else {

				}
			}
		}

	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
