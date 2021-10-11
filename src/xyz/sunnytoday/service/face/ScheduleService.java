package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Schedule;

public interface ScheduleService {
	
	/**
	 * USER_NO를 Schedule DTO에 저장
	 * 
	 * @param req - 요청 USER_NO 값
	 * @return Schedule - DTO
	 */
	public Schedule getSchedule(HttpServletRequest req);
	
	/**
	 * 
	 * 
	 * @param schedule
	 * @return List<Schedule>
	 */
	public List<Schedule> selectSchedule(Schedule schedule);
	
}
