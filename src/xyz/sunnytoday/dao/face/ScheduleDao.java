package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;

public interface ScheduleDao {
	
	/**
	 * user_no로 schedule 리스트 검색
	 * 
	 * @param conn
	 * @param schedule
	 * @return List<Schedule>
	 */
	public List<Schedule> selectScheduleList(Connection conn, Schedule schedule);
	
	/**
	 * user_no, schedule_date로 schedule 검색
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public Schedule selectSameSchedule(Connection conn, Schedule schedule);
	
	/**
	 * 입력한 일정의 일정번호를 검색
	 * 
	 * @param schedule - 입력한 일정의 DTO
	 * @return 입력한 일정번호 (schedule_no)
	 */
	public Schedule selectSchedule_no(Connection conn, Schedule schedule);
	
	/**
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public int insertSchedule(Connection conn, Schedule schedule);
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param material
	 * @return
	 */
	public int insertMaterial(Connection conn, Material material);
	
	/**
	 * 유저 ID를 통해 저장된 일정 중 가장 최신 일정 가져오기
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public Schedule selectSchedule(Connection conn, Schedule schedule);
	
	/**
	 * 
	 * 
	 * @param conn
	 * @param resultFriend
	 * @return
	 */
	public int insertFriend(Connection conn, Friend friend);
	
	/**
	 * schedule_no와 일치하는 material 테이블 삭제
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public int deleteMaterial(Connection conn, Schedule schedule);
	
	/**
	 * schedule_no와 일치하는 friend 테이블 삭제
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public int deleteFriend(Connection conn, Schedule schedule);
	
	/**
	 * schedule_no와 일치하는 schedule 테이블 삭제
	 * 
	 * @param conn
	 * @param schedule
	 * @return
	 */
	public int deleteSchedule(Connection conn, Schedule schedule);
	
	/**
	 * schedule_no와 일치하는 material 테이블 조회 List
	 * 
	 * @param conn
	 * @param material
	 * @return
	 */
	public List<Material> getMaterial(Connection conn, Material material);
	
	/**
	 * schedule_no와 일치하는 friend 테이블 조회 List
	 * 
	 * @param conn
	 * @param friend
	 * @return
	 */
	public List<Friend> getFriend(Connection conn, Friend friend);
	
}
