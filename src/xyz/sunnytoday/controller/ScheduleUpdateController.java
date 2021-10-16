package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/schedule/update")
public class ScheduleUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	ScheduleService scheduleService = new ScheduleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("scheduleCheckbox") != null) {
			
			Schedule schedule = scheduleService.selectSameSchedule(scheduleService.setSchedule(req));
			
			List<Material> material = scheduleService.getMaterial(schedule);
			List<Friend> friend = scheduleService.getFriend(schedule);
				
			req.setAttribute("schedule", schedule);
			req.setAttribute("material", material);
			req.setAttribute("friend", friend);
			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_update.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			//일정 삭제
			List<Schedule> deleteScheduleList = scheduleService.getDeleteSchedule(req);
			
			scheduleService.deleteMaterial(deleteScheduleList);
			scheduleService.deleteFriend(deleteScheduleList);
			scheduleService.deleteSchedule(deleteScheduleList);
			
			//일정 입력
			Schedule schedule = scheduleService.setSchedule(req);
			
			scheduleService.insertSchedule(schedule);
			
			Material material = scheduleService.setMaterial(scheduleService.selectSchedule(schedule));
			
			Friend friend = scheduleService.setFriend(scheduleService.selectSchedule(schedule));
			
			scheduleService.insertMaterial(req, material);
			scheduleService.insertFriend(req, friend);
			
			resp.sendRedirect("/schedule");

	}
	
}
