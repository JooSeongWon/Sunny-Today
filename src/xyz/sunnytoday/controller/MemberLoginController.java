package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//MemberService 객체 생성
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [GET]");
		
		//VIEW 지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/user/login/loginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - 로그인 정보
		Member member = memberService.getLoginMember(req);
		
		//로그인 인증
		boolean login = memberService.login(member);
		
		if(login) {
			//로그인 사용자의 정보 얻어오기
			member = memberService.info(member);
			
			//세션정보 저장하기
			HttpSession session = req.getSession(); 
			session.setAttribute("userno", member.getUserno());
		} else {
			System.out.println("로그인 실패");//여기에 실패시 어떻게 할건지 만들면되요
		}
		
		//메인페이지로 리다이렉트
		resp.sendRedirect("/main");
	}
}
