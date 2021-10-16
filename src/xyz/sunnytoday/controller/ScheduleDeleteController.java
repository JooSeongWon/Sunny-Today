package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/delete")
public class ScheduleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect("/schedule");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("scheduleCheckbox") != null) {
			
			List<Schedule> deleteScheduleList = scheduleService.getDeleteSchedule(req);
			
			scheduleService.deleteMaterial(deleteScheduleList);
			scheduleService.deleteFriend(deleteScheduleList);
			scheduleService.deleteSchedule(deleteScheduleList);
			
		}
		
		resp.sendRedirect("/schedule");
		
	}

}
