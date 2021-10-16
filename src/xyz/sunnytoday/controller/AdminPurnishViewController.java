package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;

/**
 * Servlet implementation class AdminPurnishViewController
 */
@WebServlet("/admin/purnish/view")
public class AdminPurnishViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/purnish/view [GET]");
		List<Map<String, Object>> list = memberService.getPurnishDatailList(req);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/member/purnish_view.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/purnish/view [POST]");
		if(req.getParameter("ban_no") != null && !"".equals(req.getParameter("ban_no"))) {
			Ban param = new Ban();
			param.setBan_no(Integer.parseInt(req.getParameter("ban_no")));
			memberService.deletePurnish(param);
		}
		resp.sendRedirect("/admin/purnish/list");
	}
}
