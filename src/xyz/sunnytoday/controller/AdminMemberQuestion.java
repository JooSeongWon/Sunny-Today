package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;
import xyz.sunnytoday.util.Paging;

/**
 * Servlet implementation class AdminMemberQuestion
 */
@WebServlet("/admin/member/question")
public class AdminMemberQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/question [GET]");
		Member param = new Member();
		String option = req.getParameter("select_option");
		String search = req.getParameter("search");
		String location = "question";
		List<Question> list = null;
		Paging paging = null;
		if(search != null) {
			if("userid".equals(option)) {
				param.setUserid(search);
			}else {
				param.setNick(search);
			}
			paging = memberService.getPaging(req, param, location);
			list = memberService.getQuestionList(param, paging);
		}else {
			paging = memberService.getPaging(req, param, location);
			list = memberService.getQuestionList(param, paging);
		}
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/question.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/question [POST]");
		resp.sendRedirect("/admin/member/question");
	}
}
