package xyz.sunnytoday.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule")
public class ScheduleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private ForecastService forecastService = new ForecastServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 확인
		if(req.getSession().getAttribute("userno") == null) {
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
        } else {
        	//USER_NO를 schedule DTO에 저장
        	Schedule schedule = scheduleService.setSchedule(req);
        	
        	//USER_NO와 일치하는 일정 조회
        	List<Schedule> scheduleList = scheduleService.selectScheduleList(schedule);
        	
        	//현재 날짜 구하기
        	LocalDate now = LocalDate.now();
        	
        	//포맷 정의
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        	
        	//포맷 적용
        	String formatedNow = now.format(formatter);
        	
        	//6개월이 지난 일정 삭제
        	scheduleService.deleteSchedule6M(scheduleList);
        	
//        	List<Forecast> mediumForecast = forecastService.getMediumTermForecast(scheduleList.get(1).getR1() + scheduleList.get(1).getR2());
//        	List<Forecast> shortForecast = forecastService.getShortTermForecast(scheduleList.get(0).getR1());
        	
//        	System.out.println(mediumForecast.get(0).getWeather());
//        	System.out.println(shortForecast.get(0).getWeather());
        	
        	//조회결과 전달
        	req.setAttribute("scheduleList", scheduleList);
        	
        	//오늘 날짜 전달
        	req.setAttribute("now", formatedNow);
        	
        	req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
        }
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}

}
