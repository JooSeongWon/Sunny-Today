package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;

@WebServlet("/password/check")
public class PasswordCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 유저 세션의 아이디 얻기
		Member loginUser = mypageService.getUser(req); 
		
		//아이디로 유저정보 얻기 - member
		Member loginmember = mypageService.selectMember(loginUser);
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/pw_check.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		
		
		
//		resp.sendRedirect(location);
	}
}
