package xyz.sunnytoday.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.CostumeService;
import xyz.sunnytoday.service.face.ForecastService;
import xyz.sunnytoday.service.face.MemberService;
import xyz.sunnytoday.service.face.ScheduleService;
import xyz.sunnytoday.service.impl.CostumeServiceImpl;
import xyz.sunnytoday.service.impl.ForecastServiceImpl;
import xyz.sunnytoday.service.impl.MemberServiceImpl;
import xyz.sunnytoday.service.impl.ScheduleServiceImpl;

@WebServlet("/schedule/view")
public class ScheduleViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private ForecastService forecastService = new ForecastServiceImpl();
	private CostumeService costumeService = new CostumeServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(scheduleService.checkDate(req.getParameter("date"))) {
			
			//user_no와 schedule_no가 일치하는 DB 조회
			Schedule schedule = scheduleService.selectSameSchedule(scheduleService.setSchedule(req));
			
			//schedule_no와 일치하는 준비물, 친구 List
			List<Material> material = scheduleService.getMaterial(schedule);
			List<Friend> friend = scheduleService.getFriend(schedule);
			
			
			Calendar cal = Calendar.getInstance();
			Calendar scheduleCal = Calendar.getInstance();
			
			
			//오늘 날짜
			cal.setTime(new Date());
			
			//일정 날짜
			scheduleCal.setTime(schedule.getSchedule_date());
			
			//오늘 날짜에 +2일
			cal.add(Calendar.DATE, +2);
			
			String city = "";
			List<Forecast> forecast = null;
			
			//도시 이름 설정
			if(schedule.getR2() != null) {
				city = schedule.getR1() + schedule.getR2();
			} else {
				city = schedule.getR1();
			}
			
			//불러올 날씨 설정 (단기 / 중기)
			if(cal.after(scheduleCal)) {
				//단기 (조회한 날짜가 단기로 조회해야할 때) 
				forecast = forecastService.getShortTermForecast(city);
				System.out.println("단기");
			} else {
				//중기 (조회한 날짜가 중기로 조회해야할 때)
				forecast = forecastService.getMediumTermForecast(city);
				System.out.println("중기");
			}
			
			//형태 설정
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			
			//일정 date -> String
			String strSchedule_date = transFormat.format(schedule.getSchedule_date());
			
			int listNum = 0;
			
			for(int i=0; i<forecast.size(); i++) {
				
				//일정 날짜와 가져온 날씨 데이터의 날짜가 일치할 경우
				if(forecast.get(i).getBaseDate().equals(strSchedule_date)) {
					
					listNum = i;
					break;
					
				}
			}
			
			Member member = memberService.getMemberByUserNoOrNull(schedule.getUser_no());
			
			//온도에 맞는 Costume 가져오기
			List<Costume> costumeList = costumeService.selectCostumeList(forecast.get(listNum).getTemperature());
			
			//결과 저장
			List<File> fileList = new ArrayList<>();
			
			//일정을 조회한 회원의 성별에 따라 File 조회
			for(int i=0; i<costumeList.size(); i++) {
				
				if(costumeList.get(i).getGender().equals(member.getGender())) {
					
					File addFile = costumeService.selectCostume_File(costumeList.get(i).getFile_no());
					
					fileList.add(addFile);
					
				}
			}
						
			Random random = new Random();
			
			//랜덤으로 fileList Thumbnail_url을 보내기 위한 설정
			int ranInt = random.nextInt(fileList.size());
			
			//조회한 일정 날짜가 오늘 이전이라면 날씨와 스타일을 조회할 수 없도록 설정
			LocalDate todaysDate = LocalDate.now();
			
			System.out.println(todaysDate);
			System.out.println(schedule.getSchedule_date());
			
			LocalDate localDate = new java.sql.Date(schedule.getSchedule_date().getTime()).toLocalDate();
			
			req.setAttribute("schedule", schedule);
			req.setAttribute("material", material);
			req.setAttribute("friend", friend);
			
			req.setAttribute("isAfter", localDate.isAfter(todaysDate));
			req.setAttribute("equalsDate", localDate.equals(todaysDate));
			req.setAttribute("weather", forecast.get(listNum).getWeather());
			req.setAttribute("rain", forecast.get(listNum).getChanceOfRain());
			req.setAttribute("temperature", forecast.get(listNum).getTemperature());
			req.setAttribute("thumbnail", fileList.get(ranInt).getThumbnail_url());
			
			req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule_view.jsp").forward(req, resp);
			
		} else {
			resp.sendRedirect("/schedule");
		}
		
		
	}

}
