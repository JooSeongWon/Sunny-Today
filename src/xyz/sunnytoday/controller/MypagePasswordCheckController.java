package xyz.sunnytoday.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import xyz.sunnytoday.dto.File;
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
		
		//유저 썸네일 전달
		File profile = mypageService.selectProfile(member);
		
		//썸네일 전달
		req.setAttribute("profile", profile);
	
		//유저정보 전달
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/user/mypage/pw_check.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/password/check");
		
		int res = 0;
		
		if( req.getParameter("userid") != null && !"".equals(req.getParameter("userid") )) {
			res = mypageService.checkPassword(req);
		}
		// json 형식으로 변환
		Gson gson = new Gson();
		String rs = gson.toJson(res);

		// 전송이 되는 부분
		resp.getWriter().write(rs);
		
        
	}
}
