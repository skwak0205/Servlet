package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

@WebServlet("/MVCController")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("{" + command + "}");

		MVCBiz biz = new MVCBizImpl();

		try {

			if (command.equals("list")) {
				// 1.
				// 2.
				List<MVCDto> list = biz.selectList();

				// 3.
				request.setAttribute("list", list);

				// 4.
				dispatch(request, response, "mvclist.jsp");

			} else if (command.equals("insertform")) {
				response.sendRedirect("mvcinsert.jsp");

			} else if (command.equals("insertres")) {
				// 1.
				String writer = request.getParameter("writer");
				String title = request.getParameter("title");
				String content = request.getParameter("content");

				// 2.
				MVCDto dto = new MVCDto(0, writer, title, content, null);

				int res = biz.insert(dto);
				if (res > 0) {
					jsResponse(response, "글 작성 성공", "mvc.do?command=list");
				} else {
					jsResponse(response, "글 작성 실패", "mvc.do?command=insertform");
				}
			} else if (command.equals("detail")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				MVCDto dto = biz.selectOne(seq);
				request.setAttribute("dto", dto);
				dispatch(request, response, "mvcselect.jsp");

			} else if (command.equals("updateform")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				MVCDto dto = biz.selectOne(seq);
				request.setAttribute("dto", dto);
				dispatch(request, response, "mvcupdate.jsp");

			} else if (command.equals("updateres")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");

				MVCDto dto = new MVCDto(seq, null, title, content, null);
				int res = biz.update(dto);
				if (res > 0) {
					dispatch(request, response, "mvc.do?command=list");
				} else {
					dispatch(request, response, "mvc.do?command=updateform&seq=" + seq);
				}
			} else if (command.equals("delete")) {
				int seq = Integer.parseInt(request.getParameter("seq"));

				int res = biz.delete(seq);
				if (res > 0) {
					dispatch(request, response, "mvc.do?command=list");
				} else {
					MVCDto dto = biz.selectOne(seq);
					request.setAttribute("dto", dto);
					dispatch(request, response, "mvc.do?command=select&seq=" + seq);
				}
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			dispatch(request, response, "error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		// forward or include 때문에 사용
		// 요청을 받아 해당 요청을 전달해 주거나 추가하는 역할
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url
				+ "';" + "</script>";

		response.getWriter().print(responseText);
	}

}
