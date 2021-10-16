package xyz.sunnytoday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/view")
public class ScheduleViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private ForecastService forecastService = new ForecastServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(scheduleService.checkDate(req.getParameter("date")));
		
		if(scheduleService.checkDate(req.getParameter("date"))) {
			
			//user_no와 schedule_no가 일치하는 DB 조회
			Schedule schedule = scheduleService.selectSameSchedule(scheduleService.setSchedule(req));
			
			//schedule_no와 일치하는 준비물, 친구 List
			List<Material> material = scheduleService.getMaterial(schedule);
			List<Friend> friend = scheduleService.getFriend(schedule);
			
        	List<Forecast> shortForecast = forecastService.getShortTermForecast(schedule.getR1() + schedule.getR2());
        	
			req.setAttribute("schedule", schedule);
			req.setAttribute("material", material);
			req.setAttribute("friend", friend);
			req.setAttribute("weather", shortForecast.get(0).getWeather());
			req.setAttribute("temperature", shortForecast.get(0).getTemperature());
			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_view.jsp").forward(req, resp);
		
	}

}
