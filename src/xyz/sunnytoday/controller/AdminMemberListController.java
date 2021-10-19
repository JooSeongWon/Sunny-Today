package xyz.sunnytoday.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;
import xyz.sunnytoday.util.Paging;

/**
 * Servlet implementation class AdminMemberMenageController
 */
@WebServlet("/admin/member/list")
public class AdminMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/list [GET]");
		String location = "member";
		Member param = new Member();
		String option = req.getParameter("select_option");
		String search = req.getParameter("search");
		System.out.println("location : " + location);
		List<Member> list = null;
		Paging paging = null;
		
		if(search != null && !"".equals(search) || search != null && !"".equals(search)){
			if("userid".equals(option)) {
				param.setUserid(search);
			}else {
				param.setNick(search);
			}
		}
		
		paging = memberService.getPaging(req, param, location);
		list = memberService.getUserList(param, paging);			
		
		//모델값 + 페이징 정보 전달
		req.setAttribute("search", search);
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/member_list.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/list [POST]");
		
		req.setCharacterEncoding("UTF-8");
		String chNum = null; // 받아올 파라미터의 이름 설정
		String location = "member";
		Member param = new Member();
		
		//해당 테이블 행의 갯수를 반환
		int cntRow = memberService.cntList(req, param, location);
		
		int count = 0;
		//선택한 항목의 갯수 확인
		for(int i=0; i < cntRow; i++) {
			chNum = "cb" + i; // 파라미터의 뒷번호를 for문으로 자동 생성
			if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
				  count++; //선택된 항목이 있다면 카운트 증가 -> 테이블에 있는 모든 행의 수에서 선택된 항목의 갯수 파악
				  System.out.println("req.getParameter(chNum) : " + req.getParameter(chNum));
			}
		}
		
		System.out.println("count : " + count);
		
		//항목의 갯수가 0이 아니면 실행
		if(count != 0) {
			//항목을 저장할 배열 생성
			int[] user_no = new int[count];//카운트 값을 넣어 에러가 나지 않도록 처리
			int cnt = 0;
			
			//요청 객체의 모든 값을 조회해야 선택된 값인지 판별이 가능합니다.
			for(int i=0; i < cntRow; i++) {
				chNum = "cb" + i;
				
				if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
					user_no[cnt] = Integer.parseInt(req.getParameter(chNum));  
					cnt++;
				}
			}
			
			cnt = 0;
			Member member = new Member();
			Ban ban = new Ban();
			
			
			if(req.getParameter("Ban_type") == "login") {
				ban.setBan_type("L");
			}else {
				ban.setBan_type("W");
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("curent : " + df.format(cal.getTime()));
			if(req.getParameter("Ban_date") == "1week") {
				cal.add(Calendar.DATE, 7);
			}else if(req.getParameter("Ban_date") == "1month") {
				cal.add(Calendar.MONTH, 1);
			}else if(req.getParameter("Ban_date") == "3month") {
				cal.add(Calendar.MONTH, 3);
			}else if(req.getParameter("Ban_date") == "1year") {
				cal.add(Calendar.YEAR, 1);
			}else{
				cal.add(Calendar.YEAR, 9999);
			}
			
			System.out.println("after : " + df.format(cal.getTime()));
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date to = fm.parse(df.format(cal.getTime()));
				ban.setExpiry_date(to);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			for(int i=0; i < user_no.length; i++) {
				member.setUserno(user_no[i]);
				memberService.insertBan(member, ban);
			}

			
		}
		resp.sendRedirect("/admin/member/list");
		
	}
}
