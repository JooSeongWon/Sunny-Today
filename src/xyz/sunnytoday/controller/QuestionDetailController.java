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


@WebServlet("/question/detail")
public class QuestionDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QuestionService questionService = new QuestionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/question/detail [GET]");
		
		Question questionNo = questionService.getQuestionno(req);
		Question questionDetail = questionService.detail(questionNo);
		
		System.out.println("questionDetail : " + questionDetail);
		
		req.setAttribute("questionDetail", questionDetail);
		req.setAttribute( "nick", questionService.getNick(questionDetail) );
		
		req.getRequestDispatcher("/WEB-INF/views/user/question/questionDetail.jsp").forward(req, resp);
		
	}
	
	

}
