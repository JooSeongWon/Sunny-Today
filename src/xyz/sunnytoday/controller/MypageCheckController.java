package xyz.sunnytoday.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import xyz.sunnytoday.dto.Member;
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
		
		//로그인 유저 세션의 유저넘버 얻기
		Object param = req.getSession().getAttribute("userno");
		int userno = (int) param;
		
		//유저넘버로 유저정보 얻기 - member
		Member member = mypageService.selectMember(userno);
		
		if(req.getParameter("nick") != null && !"".equals("nick")) {
			//변경할 닉네임 얻기
			String nick = req.getParameter("nick");
			System.out.println(nick);
		
			//닉네임 중복 체크
			int result = mypageService.nickCheck(nick);
		
			//json 형식으로 변환
			Gson gson = new Gson();
			String rs = gson.toJson(result);
		
			// 전송이 되는 부분
			resp.getWriter().write(rs);
		
		}
		
		if(req.getParameter("phone") != null && !"".equals("phone")) {
			//변경할 상태 얻기
			String phone = req.getParameter("phone");

			//상태 업데이트
			mypageService.phoneOpen(phone, member);
			
			String phoneOpen = member.getBirth_open();
			//json 형식으로 변환
			Gson gson = new Gson();
			String rs = gson.toJson(phoneOpen);
		
			// 전송이 되는 부분
			resp.getWriter().write(rs);
			
		}

		if(req.getParameter("birth") != null && !"".equals("birth")) {
			//변경할 상태 얻기
			String birth = req.getParameter("birth");
			
			//상태 업데이트
			mypageService.birthOpen(birth, member);
			
			String birthOpen = member.getBirth_open();
			//json 형식으로 변환
			Gson gson = new Gson();
			String rs = gson.toJson(birthOpen);
		
			// 전송이 되는 부분
			resp.getWriter().write(rs);
			
		}
		
				
		
	}
}
