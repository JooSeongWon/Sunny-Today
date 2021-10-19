package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MypageService;
import xyz.sunnytoday.service.impl.MypageServiceImpl;

/**
 * Servlet implementation class AdminCheckController
 */
@WebServlet("/admin/check")
public class AdminCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MypageService mypageService = new MypageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/admin/check [get]");
		
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member member = mypageService.selectMember(userno);
	
		//유저정보 전달
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mypage/admin/check [post]");
		
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member member = mypageService.selectMember(userno);
		
		//입력한 비밀번호가 없을 경우
		int res = 1;
		
		//해당유저의 비밀번호가 있을 경우
		if( member.getUserpw() != null && !"".equals(member.getUserpw())) {
			// 입력한 비밀번호가 있을 경우
			if (req.getParameter("userpw") != null && !"".equals(req.getParameter("userpw"))) {
				res = mypageService.checkPassword(req);
			} 
		} else { //해당유저의 비밀번호가 없을 경우
			res = 2;
		}
		// json 형식으로 변환
		Gson gson = new Gson();
		String rs = gson.toJson(res);

		// 전송이 되는 부분
		resp.getWriter().write(rs);
		
		
	}
}
