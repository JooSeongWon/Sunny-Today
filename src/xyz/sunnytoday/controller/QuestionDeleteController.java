package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.QuestionService;
import xyz.sunnytoday.service.impl.QuestionServiceImpl;

@WebServlet("/question/delete")
public class QuestionDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QuestionService questionService = new QuestionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/question/delete [GET]");
		
		Question questionNo = questionService.getQuestionno(req);
		
		questionService.delete(questionNo);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/question/list");
	}

}
