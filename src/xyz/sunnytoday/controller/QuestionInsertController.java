package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.QuestionService;
import xyz.sunnytoday.service.impl.QuestionServiceImpl;

@WebServlet("/question/insert")
public class QuestionInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QuestionService questionService = new QuestionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/question/insert [GET]");
		
		if( req.getSession().getAttribute("userno") != null ) {
			req.setAttribute( "loginMember", questionService.loginMember(req));
		}
		
		req.getRequestDispatcher("/WEB-INF/views/user/question/questionInsert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		questionService.insert(req);
		
		resp.sendRedirect("/question/list");
	}
	
	
}
