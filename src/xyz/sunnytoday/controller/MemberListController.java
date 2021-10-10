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
@WebServlet("/member/list")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging = memberService.getPaging(req);
		System.out.println("/member/list [GET]" + paging);
		
		List<Member> list = memberService.getMemberList(paging);
		
		//모델값 + 페이징 정보 전달
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/list.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging = memberService.getPaging(req);
		System.out.println("/member/list [POST]" + paging);
		String userid = req.getParameter("userid");
		String nick = req.getParameter("nick");
		Member param = new Member();
		List<Member> list =null;
		if(userid != null && "".equals(userid)){
			param.setUserid(req.getParameter("userid"));
			list = memberService.getSearchUserList(param, paging);
		}else if(nick != null && "".equals(nick)){
			param.setNick(req.getParameter("usernick"));			
		}else {
			resp.sendRedirect("/member/list");
		}
		

		
	}
}
