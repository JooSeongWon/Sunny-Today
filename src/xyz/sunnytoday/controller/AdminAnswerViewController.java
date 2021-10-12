package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;

/**
 * Servlet implementation class AdminAnswerViewController
 */
@WebServlet("/admin/answer/view")
public class AdminAnswerViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/answer/view [GET]");
		Question param = new Question();
		System.out.println("question_no : " + req.getParameter("question_no"));
		Question question = memberService.getQuestionDetail(req, param);
		req.setAttribute("question", question);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/question_answer_view.jsp").forward(req, resp);
	}
}
