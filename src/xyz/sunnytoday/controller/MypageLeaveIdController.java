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


@WebServlet("/leaveid")
public class MypageLeaveIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/leaveid [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/leave_Id.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/leaveid [POST]");
		
		// 로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		mypageService.delMember(userno);
		
		//세션 객체
		HttpSession session = req.getSession();
		
		//세션 정보(객체) 삭제
		session.invalidate();

		resp.sendRedirect("/main");
		
		
	}
}
