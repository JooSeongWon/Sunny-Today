package xyz.sunnytoday.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;

/**
 * Servlet implementation class MypagePasswordCheckController
 */
@WebServlet("/mypage/password/check")
public class MypagePasswordCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member member = mypageService.selectMember(userno);
	
		//유저정보 전달
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/pw_check.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/password/check");
		
		boolean user = mypageService.checkPassword(req);
		
		if( user == true ) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user );
			session.setMaxInactiveInterval(15 * 60);
			
			resp.sendRedirect("/leaveid");
		} else {
			resp.sendRedirect("/mypage/password/check");
		}
		
        
	}
}
