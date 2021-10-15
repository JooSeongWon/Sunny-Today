package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Report;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;
import xyz.sunnytoday.util.Paging;


/**
 * Servlet implementation class AdminReportMenageController
 */
@WebServlet("/admin/member/report")
public class AdminReportMenageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("/admin/member/report [GET]");
    	Member param1 = new Member();
    	Report param2 = new Report();
    	String category_option = req.getParameter("select_category");
		String search_option = req.getParameter("select_search");
		String search = req.getParameter("search");
		
		List<Map<String, Object>> list = null;
		
		Paging paging = null;
		if(search != null) {
			if("post".equals(category_option)) {
				param2.setReport_type("P");
			}else if("comment".equals(category_option)){
				param2.setReport_type("C");
			}else {
				param2.setReport_type(null);
			}
			
			if("userid".equals(search_option)) {
				param1.setUserid(search);
			}else {
				param1.setNick(search);
			}	
		}
		paging = memberService.getReportPaging(req, param1, param2);
		list = memberService.getReportList(param1, param2, paging);
		
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
    	req.getRequestDispatcher("/WEB-INF/views/admin/member/report_list.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("/admin/member/report [POST]");
    	
    	req.setCharacterEncoding("UTF-8");
		String chNum = null; // 받아올 파라미터의 이름 설정
		int count = 0;
		String location = "report";
		Member param = new Member();
		
		int cntRow = memberService.cntList(req, param, location);
		
		//선택한 항목의 갯 수 확인
		for(int i=0; i < cntRow; i++) {
			chNum = "ch" + i; // 파라미터의 뒷번호를 for문으로 자동 생성
			if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
				  count++; //선택된 항목이 있다면 카운트 증가
				  System.out.println("req.getParameter(chNum) : " + req.getParameter(chNum));
			}
		}
		
		System.out.println("count : " + count);
		
		//항목의 갯수가 0이 아니면 실행
		if(count != 0) {
			//항목을 저장할 배열 생성
			int[] report_no = new int[count];//카운트 값을 넣어 에러가 나지 않도록 처리
			int cnt = 0;
			//요청 객체의 모든 값을 조회해야 선택된 값인지 판별이 가능합니다.
			// -> 참고로 저는 한페이지당 10개씩 출력해서 10으로 잡았습니다. -> 바꾸셔도 됩니다.
			for(int i=0; i < cntRow; i++) {
				chNum = "ch" + i;
				
				if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
					report_no[cnt] = Integer.parseInt(req.getParameter(chNum));  
					cnt++;
				}
			}
			
			cnt = 0;
			Report report = new Report();
			
			for(int i=0; i < report_no.length; i++) {
				report.setReport_no(report_no[i]);
				memberService.deleteReport(report);
			}

			
		}
		
    	resp.sendRedirect("/admin/member/report");
    }
}
