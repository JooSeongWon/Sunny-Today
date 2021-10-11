package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;

@WebServlet("/admin/member/view")
public class AdminMemberViewController extends HttpServlet {
	MemberMenageService memberService = new MemberMenageServiceImpl();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/view [GET]");
		Member param = memberService.getMemberDetailList(req);
		req.setAttribute("member", param);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/view.jsp").forward(req, resp);
	}
}
