package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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


@WebServlet("/admin/purnish/list")
public class AdminPurnishMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberMenageService memberService = new MemberMenageServiceImpl();
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/purnish/list [GET]");
		Member param = new Member();
    	String search_option = req.getParameter("select_option");
		String search = req.getParameter("search");
		String location = "purnish";
		List<Map<String, Object>> list = null;
		
		Paging paging = null;
		if(search != null) {
			
			if("userid".equals(search_option)) {
				param.setUserid(search);
			}else {
				param.setNick(search);
			}	
		}
		paging = memberService.getPaging(req, param, location);
		list = memberService.getPurnishList(param, paging);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/purnish_list.jsp").forward(req, resp);
	}
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/purnish/list [POST]");
    	
    	req.setCharacterEncoding("UTF-8");
		String chNum = null; // 받아올 파라미터의 이름 설정
		String location = "purnish";
		Member param = new Member();
		
		int cntRow = memberService.cntList(req, param, location);
		int count = 0;
		//선택한 항목의 갯 수 확인
		for(int i=0; i < cntRow; i++) {
			chNum = "cb" + i; // 파라미터의 뒷번호를 for문으로 자동 생성
			if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
				  count++; //선택된 항목이 있다면 카운트 증가
				  System.out.println("req.getParameter(chNum) : " + req.getParameter(chNum));
			}
		}
		
		System.out.println("count : " + count);
		
		//항목의 갯수가 0이 아니면 실행
		if(count != 0) {
			//항목을 저장할 배열 생성
			int[] ban_no = new int[count];//카운트 값을 넣어 에러가 나지 않도록 처리
			int cnt = 0;
			
			//요청 객체의 모든 값을 조회해야 선택된 값인지 판별이 가능합니다.
			for(int i=0; i < cntRow; i++) {
				chNum = "cb" + i;
				
				if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
					ban_no[cnt] = Integer.parseInt(req.getParameter(chNum));  
					cnt++;
				}
			}
			
			cnt = 0;
			Ban ban = new Ban();
			
			for(int i=0; i < ban_no.length; i++) {
				ban.setBan_no(ban_no[i]);
				memberService.deletePurnish(ban);
			}

			
		}
		
    	resp.sendRedirect("/admin/purnish/list");
	}

}
