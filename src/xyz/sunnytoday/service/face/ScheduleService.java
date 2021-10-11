package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Schedule;

public interface ScheduleService {
	
	/**
	 * 일정 ID를 Schedule DTO에 저장
	 * 
	 * @param req - 요청 ID 값
	 * @return Schedule - DTO
	 */
	public Schedule getSchedule(HttpServletRequest req);

}
