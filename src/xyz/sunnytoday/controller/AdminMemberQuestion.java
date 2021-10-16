package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.service.face.MemberMenageService;
import xyz.sunnytoday.service.impl.MemberMenageServiceImpl;
import xyz.sunnytoday.util.Paging;

/**
 * Servlet implementation class AdminMemberQuestion
 */
@WebServlet("/admin/member/question")
public class AdminMemberQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberMenageService memberService = new MemberMenageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/admin/member/question [GET]");
		Member param = new Member();
		String option = req.getParameter("select_option");
		String search = req.getParameter("search");
		String location = "question";
		List<Question> list = null;
		Paging paging = null;
		if(search != null) {
			if("userid".equals(option)) {
				param.setUserid(search);
			}else {
				param.setNick(search);
			}
		}
		
		paging = memberService.getPaging(req, param, location);
		list = memberService.getQuestionList(param, paging);
		
		req.setAttribute("paging", paging);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/admin/member/question.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/question [POST]");
		req.setCharacterEncoding("UTF-8");
		String chNum = null; // 받아올 파라미터의 이름 설정
		int count = 0;
		//선택한 항목의 갯 수 확인
		for(int i=0; i < 10; i++) {
			chNum = "ch" + i; // 파라미터의 뒷번호를 for문으로 자동 생성
			if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum)))
				  count++; //선택된 항목이 있다면 카운트 증가
		}
		
		System.out.println("count : " + count);
		
		//항목의 갯수가 0이 아니면 실행
		if(count != 0) {
			//항목을 저장할 배열 생성
			int[] question_no = new int[count];//카운트 값을 넣어 에러가 나지 않도록 처리
			int cnt = 0;
			//요청 객체의 모든 값을 조회해야 선택된 값인지 판별이 가능합니다.
			// -> 참고로 저는 한페이지당 10개씩 출력해서 10으로 잡았습니다. -> 바꾸셔도 됩니다.
			for(int i=0; i < 10; i++) {
				chNum = "ch" + i;
				
				if(req.getParameter(chNum) != null && !"".equals(req.getParameter(chNum))) {
					question_no[cnt] = Integer.parseInt(req.getParameter(chNum));  
					cnt++;
				}
			}
			
			cnt = 0;
			Question param = new Question();
			
			for(int i=0; i < question_no.length; i++) {
				param.setQuestion_no(question_no[i]);
			}
			memberService.deleteQuestion(param);
			
		}
		
		resp.sendRedirect("/admin/member/question");
	}
}
