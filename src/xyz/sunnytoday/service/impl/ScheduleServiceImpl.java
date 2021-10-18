package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.ScheduleDao;
import xyz.sunnytoday.dao.impl.ScheduleDaoImpl;
import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;
import xyz.sunnytoday.service.face.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	
	//DAO 객체 생성
	private final ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public Schedule setSchedule(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		Schedule schedule = new Schedule();
		
		//세션에서 user_no 가져오기
		Integer userno = (Integer) session.getAttribute("userno");
		
		schedule.setUser_no(userno);
		
		//일정 상세보기를 했을 경우
		if(req.getParameter("date") != null) {
			
			//String 문자열 -> Date
			java.sql.Date schedule_dateView = java.sql.Date.valueOf(req.getParameter("date"));
			
			schedule.setSchedule_date(schedule_dateView);
			
		}
		
		//일정 수정을 했을 경우
		if(req.getParameter("scheduleCheckbox") != null) {
			
			//String 문자열 -> Date
			java.sql.Date schedule_dateView = java.sql.Date.valueOf(req.getParameter("scheduleCheckbox"));
			
			schedule.setSchedule_date(schedule_dateView);
			
		}
		
		//일정을 입력했을 경우
		if(req.getParameter("schedule_title") != null) {
			
			//String 문자열 -> Date
			java.sql.Date scheduleDate = java.sql.Date.valueOf(req.getParameter("schedule_date"));
			
			//String 문자열 -> double
			double latitude = Double.parseDouble(req.getParameter("latitude"));
			double longitude = Double.parseDouble(req.getParameter("longitude"));
			
			//r1, r2 분리
			String r1r2 = req.getParameter("r1r2");
			
			String[] array = r1r2.split(" ");
			
			//DTO에 데이터 저장
			schedule.setSchedule_date(scheduleDate);
			schedule.setTitle(req.getParameter("schedule_title"));
			schedule.setContent(req.getParameter("schedule_content"));
			schedule.setMemo(req.getParameter("schedule_memo"));
			schedule.setLatitude(latitude);
			schedule.setLongitude(longitude);
			schedule.setR1(array[0]);
			
			if(!(array[0].equals("서울특별시") || array[0].equals("인천광역시") || array[0].equals("부산광역시")
					|| array[0].equals("울산광역시") || array[0].equals("대구광역시")
					|| array[0].equals("광주광역시") || array[0].equals("대전광역시")
					|| array[0].equals("세종특별자치시") )) {
				
				schedule.setR2(array[1]);
				
			} else {
				schedule.setR2(null);
			}
			
		}
		
		return schedule;
	}

	@Override
	public List<Schedule> selectScheduleList(Schedule schedule) {
		
		List<Schedule> result = null;
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.selectScheduleList(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Schedule selectSameSchedule(Schedule schedule) {
		
		Schedule result = new Schedule();
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.selectSameSchedule(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Material setMaterial(Schedule schedule) {
		
		Material material = new Material();
		
		Schedule resultSchedule = new Schedule();
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			resultSchedule = scheduleDao.selectSchedule_no(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		material.setSchedule_no(resultSchedule.getSchedule_no());
		
		return material;
	}

	@Override
	public Friend setFriend(Schedule schedule) {
		
		Friend friend = new Friend();
		
		Schedule resultSchedule = new Schedule();
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			resultSchedule = scheduleDao.selectSchedule_no(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		friend.setSchedule_no(resultSchedule.getSchedule_no());
		
		return friend;
		
	}

	@Override
	public void insertSchedule(Schedule schedule) {
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			int res = scheduleDao.insertSchedule(conn, schedule);
			
			if(res == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertMaterial(HttpServletRequest req, Material material) {
		
		Material resultMaterial = new Material();
		
		String[] materialArray = req.getParameterValues("material");
		
		for(int i=0; i < materialArray.length; i++) {
			
			resultMaterial.setSchedule_no(material.getSchedule_no());
			resultMaterial.setName(materialArray[i]);
			
			try (Connection conn = JDBCTemplate.getConnection()) {
				int res = scheduleDao.insertMaterial(conn, resultMaterial);
				
				if(res == 1) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void insertFriend(HttpServletRequest req, Friend friend) {
		
		Friend resultFriend = new Friend();
		
		String[] friendArray = req.getParameterValues("friend");
		
		for(int i=0; i < friendArray.length; i++) {
			
			resultFriend.setSchedule_no(friend.getSchedule_no());
			resultFriend.setName(friendArray[i]);
			
			try (Connection conn = JDBCTemplate.getConnection()) {
				int res = scheduleDao.insertFriend(conn, resultFriend);
				
				if(res == 1) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public Schedule selectSchedule(Schedule schedule) {
		
		Schedule result = new Schedule();
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.selectSchedule(conn, schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public List<Schedule> getDeleteSchedule(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		List<Schedule> result = new ArrayList<Schedule>();
		
		Schedule schedule = new Schedule();
		
		//세션에서 user_no 가져오기
		Integer userno = (Integer) session.getAttribute("userno");
		
		schedule.setUser_no(userno);
		
		String[] checkboxArray = req.getParameterValues("scheduleCheckbox");
			
			//DB에서 user_id와 schedule_date가 일치하는 데이터를 List로 가져옴
			try (Connection conn = JDBCTemplate.getConnection()) {
				
				for(int i=0; i < checkboxArray.length; i++) {
					
					//String 문자열 -> Date
					java.sql.Date scheduleDate = java.sql.Date.valueOf(checkboxArray[i]);
					
					schedule.setSchedule_date(scheduleDate);
				
					result.add(scheduleDao.selectSameSchedule(conn, schedule));
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
		
	}

	@Override
	public void deleteMaterial(List<Schedule> deleteScheduleList) {
		
		try (Connection conn = JDBCTemplate.getConnection()) {
		
			for(int i=0; i < deleteScheduleList.size(); i++) {
			
				int res = scheduleDao.deleteMaterial(conn, deleteScheduleList.get(i));
				
				if(res == 1) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFriend(List<Schedule> deleteScheduleList) {

		try (Connection conn = JDBCTemplate.getConnection()) {
			
			for(int i=0; i < deleteScheduleList.size(); i++) {
			
				int res = scheduleDao.deleteFriend(conn, deleteScheduleList.get(i));
				
				if(res == 1) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteSchedule(List<Schedule> deleteScheduleList) {
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			
			for(int i=0; i < deleteScheduleList.size(); i++) {
			
				int res = scheduleDao.deleteSchedule(conn, deleteScheduleList.get(i));
				
				if(res == 1) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Material> getMaterial(Schedule schedule) {
		
		List<Material> result = null;
		
		Material material = setMaterial(schedule);
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.getMaterial(conn, material);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Friend> getFriend(Schedule schedule) {
		
		List<Friend> result = null;
		
		Friend friend = setFriend(schedule);
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = scheduleDao.getFriend(conn, friend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean checkDate(String checkDate) {
		
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd"); //검증할 날짜 포맷 설정
            dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
            dateFormatParser.parse(checkDate); //대상 값 포맷에 적용되는지 확인
            return true;
        } catch (Exception e) {
            return false;
        }
		
	}

	@Override
	public void deleteSchedule6M(List<Schedule> scheduleList) {
		
		Calendar getToday = Calendar.getInstance();
		getToday.setTime(new Date()); //금일 날짜
		
		Calendar cmpDate = Calendar.getInstance();
		
		for(int i=0; i < scheduleList.size(); i++) {
			
			Schedule schedule = new Schedule();
			
			schedule.setSchedule_date(scheduleList.get(i).getSchedule_date());
			schedule.setSchedule_no(scheduleList.get(i).getSchedule_no());
			
			cmpDate.setTime(schedule.getSchedule_date());
			
			long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
			long diffDays = diffSec / (24*60*60); //일자수 차이
			
			//6개월 이상 지났을 경우
			if(diffDays > 180) {
				
				try (Connection conn = JDBCTemplate.getConnection()) {
					int res1 = scheduleDao.deleteFriend(conn, schedule);
					if(res1 == 1) {
						JDBCTemplate.commit(conn);
					} else {
						JDBCTemplate.rollback(conn);
					}
					
					int res2 = scheduleDao.deleteMaterial(conn, schedule);
					
					if(res2 == 1) {
						JDBCTemplate.commit(conn);
					} else {
						JDBCTemplate.rollback(conn);
					}
					
					int res3 = scheduleDao.deleteSchedule(conn, schedule);
					
					if(res3 == 1) {
						JDBCTemplate.commit(conn);
					} else {
						JDBCTemplate.rollback(conn);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}




}
