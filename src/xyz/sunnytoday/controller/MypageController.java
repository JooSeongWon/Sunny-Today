package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
=======
import javax.websocket.Session;
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8

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
		
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		
//		//로그인 되어있지 않으면 리다이렉트 
<<<<<<< HEAD
//		if( req.getsession().getattribute("login") == null
//				|| !(boolean)req.getsession().getattribute("login") ) {
=======
//		if( req.getSession().getAttribute("nick") == null
//				|| !(boolean)req.getSession().getAttribute("nick") ) {
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
//			
//			resp.sendredirect("/");
//			
//			return;
//		}
//		
		//로그인 유저 세션의 유저넘버 얻기
		Member loginUser = mypageService.getUser(req);
		
		//유저넘버로 유저정보 얻기 - member
<<<<<<< HEAD
		Member loginmember = mypageService.selectMember(loginUser);
	
		//유저정보 전달
		req.setAttribute("loginmember", loginmember);
=======
		Member member = mypageService.selectMember(userno);
	
		//유저정보 전달
		req.setAttribute("member", member);
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/mypage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mypage [POST]");
		
<<<<<<< HEAD
=======
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member loginmember = mypageService.selectMember(userno);
		
>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
		//업데이트
		mypageService.update(req);
		
		resp.sendRedirect("/mypage");
	}
}
