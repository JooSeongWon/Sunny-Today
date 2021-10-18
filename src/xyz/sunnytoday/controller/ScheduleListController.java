package xyz.sunnytoday.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.sunnytoday.common.repository.Forecast;
import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;
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

@WebServlet("/schedule")
public class ScheduleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체 생성
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private ForecastService forecastService = new ForecastServiceImpl();
	private CostumeService costumeService = new CostumeServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	
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
        	
        	//6개월이 지난 일정 삭제
        	scheduleService.deleteSchedule6M(scheduleList);
        	
        	List<String> tenDay = new ArrayList<>();
        	List<Schedule> underSchedule = new ArrayList<>();
        	List<Forecast> forecast = new ArrayList<>();
        	
			//결과 저장
			List<File> fileList = new ArrayList<>();
			List<Forecast> resultForecast = new ArrayList<>();
			List<Integer> resultFileCnt = new ArrayList<>();

			int listNum = -1;

			for(int i=0; i<10; i++) {
        		
        		Calendar toDayCal = Calendar.getInstance();
        		
        		toDayCal.add(Calendar.DATE, +i);
        		
        		//현재 날짜/시간
        		Date dateToday = toDayCal.getTime();
        		
        		//포맷팅 정의
        		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        		
        		//포맷팅 적용
        		String formatedNow = formatter.format(dateToday);
        		try {
        			Date eqDateToday = formatter.parse(formatedNow);
        			
        			//오늘부터 10일 중 일치하는 일정이 있을 경우
        			for(int k=0; k<scheduleList.size(); k++) {
        				if(scheduleList.get(k).getSchedule_date().equals(eqDateToday)) {
        					underSchedule.add(scheduleList.get(k));
        					
        					String city = "";
        					
        					//도시 이름 설정
        					if(scheduleList.get(k).getR2() != null) {
        						city = scheduleList.get(k).getR1() + scheduleList.get(k).getR2();
        					} else {
        						city = scheduleList.get(k).getR1();
        					}
        					
        					//불러올 날씨 설정 (단기 / 중기)
        					if(i <= 2) {
        						//단기 (조회한 날짜가 단기로 조회해야할 때) 
        						forecast = forecastService.getShortTermForecast(city);
        						System.out.println("단기");
        					} else if(i >= 3) {
        						//중기 (조회한 날짜가 중기로 조회해야할 때)
        						forecast = forecastService.getMediumTermForecast(city);
        						System.out.println("중기");
        					}
        					
        					//형태 설정
        					SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
        					
        					//일정 date -> String
        					String strSchedule_date = transFormat.format(scheduleList.get(k).getSchedule_date());
        					
        					
        					for(int j=0; j<forecast.size(); j++) {
        						
        						//일정 날짜와 가져온 날씨 데이터의 날짜가 일치할 경우
        						if(forecast.get(j).getBaseDate().equals(strSchedule_date)) {
        							
        							resultForecast.add(forecast.get(j));
        							listNum++;
        							break;
        							
        						}
        					}
        					
        					int fileCnt = 0;
        					
        					Member member = memberService.getMemberByUserNoOrNull(scheduleList.get(0).getUser_no());
        					
        					//온도에 맞는 Costume 가져오기
        					List<Costume> costumeList = costumeService.selectCostumeList(resultForecast.get(listNum).getTemperature());
        					
        					//일정을 조회한 회원의 성별에 따라 File 조회
        					for(int j=0; j<costumeList.size(); j++) {
        						
        						if(costumeList.get(j).getGender().equals(member.getGender())) {
        							
        							File addFile = costumeService.selectCostume_File(costumeList.get(j).getFile_no());
        							
        							fileList.add(addFile);
        							fileCnt++;
        							
        						}
        					}
        					
        					//file이 들어가는 만큼 카운트
        					resultFileCnt.add(fileCnt);
        					
        				}
        			}
        		} catch (ParseException e) {
        			e.printStackTrace();
        		}
        		
        		tenDay.add(formatedNow);
        		
        	} //10번 반복 종료
			
			
			
        	//조회결과 전달
        	req.setAttribute("scheduleList", scheduleList);
        	req.setAttribute("tenDay", tenDay);
        	req.setAttribute("underSchedule", underSchedule);
			req.setAttribute("resultForecast", resultForecast);
			req.setAttribute("fileList", fileList);
			req.setAttribute("fileCnt", resultFileCnt);
        	
        	req.getRequestDispatcher("/WEB-INF/views/user/schedule/schedule.jsp").forward(req, resp);
        }
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}

}
