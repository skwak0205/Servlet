package com.answer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;

@WebServlet("/answer.do")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		AnswerBiz biz = new AnswerBizImpl();

		String command = request.getParameter("command");
		System.out.println("command : " + command);

		if (command.equals("list")) {
			List<AnswerDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");

		} else if (command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "boardselect.jsp");

		} else if (command.equals("insertform")) {
			response.sendRedirect("boardinsert.jsp");

		} else if (command.equals("insertres")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");

			AnswerDto dto = new AnswerDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setWriter(writer);

			boolean res = biz.boardInsert(dto);
			if (res) {
				jsResponse(response, "글 작성 성공", "answer.do?command=list");
			} else {
				jsResponse(response, "글 작성 실패", "answer.do?command=inserform");
			}
		} else if (command.equals("updateform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "boardupdate.jsp");

		} else if (command.equals("updateres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			AnswerDto dto = new AnswerDto();
			dto.setBoardno(boardno);
			dto.setTitle(title);
			dto.setContent(content);

			boolean res = biz.boardUpdate(dto);
			if (res) {
				jsResponse(response, "글 수정 성공", "answer.do?command=detail&boardno=" + boardno);
			} else {
				jsResponse(response, "글 수정 실패", "answer.do?command=updateform&boardno=" + boardno);
			}
		} else if (command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			boolean res = biz.boardDelete(boardno);
			if (res) {
				jsResponse(response, "글 삭제 성공", "answer.do?command=list");
			} else {
				jsResponse(response, "글 삭제 실패", "answer.do?command=detail&boardno" + boardno);
			}
		} else if (command.equals("answerform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "boardanswer.jsp");

		} else if (command.equals("answerproc")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");

			AnswerDto dto = new AnswerDto();
			dto.setBoardno(parentboardno);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setWriter(writer);

			boolean res = biz.answerProc(dto);
			if (res) {
				jsResponse(response, "답변 성공", "answer.do?command=list");
			} else {
				jsResponse(response, "답변 실패", "answer.do?command=answerform&boardno=" + parentboardno);
			}
		}

	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script>" + "alert('" + msg + "');" + "location.href='" + url + "';" + "</script>";

		response.getWriter().append(responseText);
	}

}
