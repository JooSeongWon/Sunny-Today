package xyz.sunnytoday.service.impl;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dao.face.ScheduleDao;
import xyz.sunnytoday.dao.impl.ScheduleDaoImpl;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	
	//DAO 객체 생성
	ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public Schedule getSchedule(HttpServletRequest req) {
		
		Schedule schedule = new Schedule();
		
//		schedule.set req.getAttribute("id");
		
		return schedule;
	}

}
