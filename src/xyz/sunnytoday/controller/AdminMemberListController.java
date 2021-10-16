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
import xyz.sunnytoday.util.Paging;

/**
 * Servlet implementation class AdminMemberMenageController
 */
@WebServlet("/admin/member/list")
public class AdminMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/list [GET]");
		String location = "member";
		Member param = new Member();
		String option = req.getParameter("select_option");
		String search = req.getParameter("search");
		System.out.println("location : " + location);
		List<Member> list = null;
		Paging paging = null;
		
		if(search != null && !"".equals(search) || search != null && !"".equals(search)){
			if("userid".equals(option)) {
				param.setUserid(search);
			}else {
				param.setNick(search);
			}
		}
		
		paging = memberService.getPaging(req, param, location);
		list = memberService.getUserList(param, paging);			
		
		//모델값 + 페이징 정보 전달
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/member_list.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/list [POST]");
		 
		resp.sendRedirect("/admin/member/list");
		
	}
}
