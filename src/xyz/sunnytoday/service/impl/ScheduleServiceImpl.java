package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.ScheduleDao;
import xyz.sunnytoday.dao.impl.ScheduleDaoImpl;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	
	//DAO 객체 생성
	private final ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public Schedule getSchedule(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		Schedule schedule = new Schedule();
		
		Member member = (Member) req.getSession().getAttribute("member");
		
		schedule.setUser_no(member.getUserno());
		
		return schedule;
	}

	@Override
	public List<Schedule> selectSchedule(Schedule schedule) {
		
		List<Schedule> result = null;
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.selectScheduleByUser_No(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		return result;
	}

}
