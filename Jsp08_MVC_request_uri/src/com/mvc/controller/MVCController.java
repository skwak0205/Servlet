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

@WebServlet(urlPatterns = { "/mylist", "/myselect", "/myinsertForm", "/myinsertRes", "/myupdateForm", "/myupdateRes",
		"/mydelete" })
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MVCBiz biz = new MVCBizImpl();

	private void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getRequestURI();
		System.out.println("[" + command + "]");

		if (command.endsWith("/mylist")) {
			doSelectList(request, response);

		} else if (command.endsWith("/myselect")) {
			doSelectOne(request, response);

		} else if (command.endsWith("/myinsertForm")) {
			doInsertForm(request, response);

		} else if (command.endsWith("/myinsertRes")) {
			doInsertRes(request, response);

		} else if (command.endsWith("/myupdateForm")) {
			doUpdateForm(request, response);

		} else if (command.endsWith("/myupdateRes")) {
			doUpdateRes(request, response);

		} else if (command.endsWith("/mydelete")) {
			doDeleteOne(request, response);
		}
	}

	private void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));

		int res = biz.delete(seq);
		if (res > 0) {
			jsResponse(response, "글 삭제 성공", "./mylist");
		} else {
			jsResponse(response, "글 삭제 실패", "./myselect?seq=" + seq);
		}
	}

	private void doUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);

		int res = biz.update(dto);
		if (res > 0) {
			jsResponse(response, "글 수정 성공", "./myselect?seq=" + seq);
		} else {
			jsResponse(response, "글 수정 실패", "./myupdateForm");
		}
	}

	private void doUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch(request, response, "mvcupdate.jsp");
	}

	private void doInsertRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		MVCDto dto = new MVCDto(0, writer, title, content, null);
		int res = biz.insert(dto);
		if (res > 0) {
			jsResponse(response, "글 작성 성공", "./mylist");
		} else {
			jsResponse(response, "글 작성 실패", "./myinsertForm");
		}
	}

	private void doInsertForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("mvcinsert.jsp");
	}

	private void doSelectOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch(request, response, "mvcselect.jsp");
	}

	private void doSelectList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch(request, response, "mvclist.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getRequest(request, response);
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
