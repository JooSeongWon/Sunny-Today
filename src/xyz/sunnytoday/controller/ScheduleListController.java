package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule")
public class ScheduleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//세션 객체
		HttpSession session = req.getSession();
		
		//임시 아이디
		session.setAttribute("user_no", 2);
		
		//회원번호가 세션에 없을 시 접속불가 설정
		if(session.getAttribute("user_no") == null) {
			req.setAttribute("user_no_ok", "N");
		}
		
		//USER_ID를 schedule DTO에 저장
		Schedule schedule = scheduleService.getSchedule(req);
		
		//USER_ID와 일치하는 일정 조회
		List<Schedule> scheduleList = scheduleService.selectSchedule(schedule);
		
		//조회결과 전달
		req.setAttribute("scheduleList", scheduleList);
		
		System.out.println(scheduleList);
		
		req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}

}
