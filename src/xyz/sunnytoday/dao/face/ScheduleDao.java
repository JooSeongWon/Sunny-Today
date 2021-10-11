package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Schedule;

public interface ScheduleDao {
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param schedule
	 * @return List<Schedule>
	 */
	public List<Schedule> selectScheduleByUser_No(Connection conn, Schedule schedule);

}
