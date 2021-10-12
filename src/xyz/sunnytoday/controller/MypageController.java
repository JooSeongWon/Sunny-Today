package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.xml.ws.Response;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;


@WebServlet("/mypage")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mypage [GET]");
		
//		//로그인 되어있지 않으면 리다이렉트 
//		if( req.getsession().getattribute("login") == null
//				|| !(boolean)req.getsession().getattribute("login") ) {
//			
//			resp.sendredirect("/");
//			
//			return;
//		}
//		
		//로그인 유저 세션의 유저넘버 얻기
		Member loginUser = mypageService.getUser(req);
		
		//유저넘버로 유저정보 얻기 - member
		Member loginmember = mypageService.selectMember(loginUser);
	
		//유저정보 전달
		req.setAttribute("loginmember", loginmember);
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/mypage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mypage [POST]");
		
		//업데이트
		mypageService.update(req);
		
		resp.sendRedirect("/mypage");
	}
}
