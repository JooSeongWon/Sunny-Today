package xyz.sunnytoday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/write")
public class ScheduleWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("userno") == null) {
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_write.jsp").forward(req, resp);
		}
        
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Schedule schedule = scheduleService.setSchedule(req);
		
		Schedule savedSchedule = scheduleService.selectSameSchedule(schedule);
		
		if(schedule.getSchedule_date().equals(savedSchedule.getSchedule_date())) {
			//일정이 입력된 날짜에 중복 입력
			req.setAttribute("msg", "n");
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_write.jsp").forward(req, resp);
		} else {
			scheduleService.insertSchedule(schedule);
			
			//친구를 입력했을 경우
			if(req.getParameter("friend") != null) {
				Friend friend = scheduleService.setFriend(scheduleService.selectSchedule(schedule));
				
				scheduleService.insertFriend(req, friend);
			}
			
			//준비물을 입력했을 경우
			if(req.getParameter("material") != null) {
				Material material = scheduleService.setMaterial(scheduleService.selectSchedule(schedule));
				
				scheduleService.insertMaterial(req, material);
			}
			
			resp.sendRedirect("/schedule");
			
		}
		
	}

}
