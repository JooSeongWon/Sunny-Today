package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.AdminMemberService;
import xyz.sunnytoday.service.impl.AdminMemberServiceImpl;
import xyz.sunnytoday.util.Paging;

@WebServlet("/admin/set")
public class AdminSetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminMemberService memberService = new AdminMemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/set [get]");
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = memberService.getPaging(req);
		
		//아이디 전체 조회
		List<Member> list = memberService.getlist(req, paging);
		
		
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/views/admin/management/admin_set.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/set [Post]");
		
		Member userno = memberService.getuserno(req);
		
		resp.sendRedirect("/admin/set");
	}
}
