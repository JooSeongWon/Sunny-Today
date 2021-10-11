package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;


@WebServlet("/mypage/check")
public class MypageCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 유저 세션의 아이디 얻기
		String loginUserId = (String) req.getSession().getAttribute("id");
		
//		//아이디로 유저정보 얻기 - member
//		Member loginmember = mypageservice.selectMember(loginUserId);
		
		//변경할 닉네임 얻기
		String nick = req.getParameter("nick");
		
		//닉네임 중복 체크
		int result = mypageService.nickCheck(nick);
		
		//보내주기
		resp.getWriter().print(result);
		
		//변경할 상태 얻기
		String phone = req.getParameter("phone");
		
		//상태 업데이트
		int phoneOpen = mypageService.phoneOpen(phone, loginUserId);
				
		resp.getWriter().print(phoneOpen);
		
	}
}
