package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;

public interface ScheduleService {
	
	/**
	 * 전달받은 schedule 데이터를 Schedule DTO에 저장
	 * 
	 * @param req - 요청
	 * @return Schedule - DTO
	 */
	public Schedule setSchedule(HttpServletRequest req);
	
	/**
	 * 유저 ID에 맞는 일정 리스트 가져오기
	 * 
	 * @param schedule
	 * @return List<Schedule>
	 */
	public List<Schedule> selectScheduleList(Schedule schedule);
	
	/**
	 * user_no와 schedule_date를 통해 일치하는 일정 조회
	 * 
	 * @param schedule
	 * @return
	 */
	public Schedule selectSameSchedule(Schedule schedule);
	
	/**
	 * 저장된 일정 중 가장 최신 일정 가져오기
	 * 
	 * @param schedule
	 * @return
	 */
	public Schedule selectSchedule(Schedule schedule);
	
	/**
	 * schedule_no를 Material DTO에 저장
	 * 
	 * @param req - 요청
	 * @param schedule - schedule DTO
	 * @return Material - DTO
	 */
	public Material setMaterial(Schedule schedule);
	
	/**
	 * schedule_no를 Friend DTO에 저장
	 * 
	 * @param req
	 * @return
	 */
	public Friend setFriend(Schedule schedule);
	
	/**
	 * 입력한 일정을 DB에 insert
	 * 
	 * @param schedule - 입력한 schedule DTO
	 */
	public void insertSchedule(Schedule schedule);
	
	/**
	 * 입력한 준비물을 DB에 insert
	 * 
	 * @param req
	 * @param material - 입력한 material DTO
	 */
	public void insertMaterial(HttpServletRequest req, Material material);
	
	/**
	 * 입력한 친구를 DB에 insert
	 * 
	 * @param req
	 * @param friend - 입력한 friend DTO
	 */
	public void insertFriend(HttpServletRequest req, Friend friend);
	
	/**
	 * 삭제할 일정 DTO를 user_no과 schedule_date를 통해 리스트로 저장
	 * 
	 * @param req
	 */
	public List<Schedule> getDeleteSchedule(HttpServletRequest req);
	
	/**
	 * 선택한 준비물 테이블 삭제
	 * 
	 * @param list
	 */
	public void deleteMaterial(List<Schedule> deleteScheduleList);
	
	/**
	 * 선택한 친구 테이블 삭제
	 * 
	 * @param deleteScheduleList
	 */
	public void deleteFriend(List<Schedule> deleteScheduleList);
	
	/**
	 * 선택한 일정 테이블 삭제
	 * 
	 * @param deleteScheduleList
	 */
	public void deleteSchedule(List<Schedule> deleteScheduleList);

	/**
	 * schedule_no와 일치하는 material 테이블 조회 List
	 * 
	 * @param schedule
	 * @return
	 */
	public List<Material> getMaterial(Schedule schedule);
	
	/**
	 * schedule_no와 일치하는 friend 테이블 조회 List
	 * 
	 * @param schedule
	 * @return
	 */
	public List<Friend> getFriend(Schedule schedule);
	
	/**
	 * 유효한 날짜인지 체크
	 * 
	 * @param checkDate
	 * @return true / false
	 */
	public boolean checkDate(String checkDate);
	
	/**
	 * 지난 일정 중 6개월이 지난 일정 삭제
	 * 
	 * @param scheduleList
	 */
	public void deleteSchedule6M(List<Schedule> scheduleList);
	
	
	
		
	
}
