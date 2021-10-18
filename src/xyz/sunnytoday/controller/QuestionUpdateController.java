package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.QuestionService;
import xyz.sunnytoday.service.impl.QuestionServiceImpl;

@WebServlet("/question/update")
public class QuestionUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QuestionService questionService = new QuestionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/question/update [GET]");
		
		Question questionNo = questionService.getQuestionno(req);
		Question questionUpdate = questionService.detail(questionNo);
		
		System.out.println("questionUpdate : " + questionUpdate);
		
		req.setAttribute("questionUpdate", questionUpdate);
		req.setAttribute( "nick", questionService.getNick(questionUpdate) );
		
		req.getRequestDispatcher("/WEB-INF/views/user/question/questionUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		questionService.update(req);
		
		
		resp.sendRedirect("/question/list");
		
	}

}
