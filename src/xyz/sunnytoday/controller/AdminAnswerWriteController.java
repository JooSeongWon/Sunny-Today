package xyz.sunnytoday.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;

/**
 * Servlet implementation class AdminAnswerWriteController
 */
@WebServlet("/admin/answer/write")
public class AdminAnswerWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/answer/write [GET]");
		System.out.println(req.getParameter("question_no"));
		Question param = new Question();
		param.setAdmin_no(Integer.parseInt(req.getParameter("admin_no")));
		param = memberService.getQuestionDetail(req, param);
		req.setAttribute("question", param);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/question_answer_write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/answer/write [POST]");
		req.setCharacterEncoding("UTF-8");
		Question param = new Question();
		
		param.setAnswer(req.getParameter("answer"));
		param.setQuestion_no(Integer.parseInt(req.getParameter("question_no")));
		param.setAdmin_no(Integer.parseInt(req.getParameter("Admin_no")));
		
		if(param.getAnswer() != null && !"".equals(param.getAnswer())) {
			memberService.updateAnswer(param);
			resp.sendRedirect("/admin/member/question");
		}else {
			System.out.println("답변이 null이거나 없음");
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = resp.getWriter(); 
			writer.println("<script>alert('답변을 입력하세요');</script>"); 
			writer.close();

		}
	}
}
