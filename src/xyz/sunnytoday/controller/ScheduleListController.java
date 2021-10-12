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
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 확인
		if(req.getSession().getAttribute("member") == null) {
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
        } else {
        	//USER_NO를 schedule DTO에 저장
        	Schedule schedule = scheduleService.getSchedule(req);
        	
        	//USER_NO와 일치하는 일정 조회
        	List<Schedule> scheduleList = scheduleService.selectSchedule(schedule);
        	
        	//조회결과 전달
        	req.setAttribute("scheduleList", scheduleList);
        	
        	req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
        }
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}

}
