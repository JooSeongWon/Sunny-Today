package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.ScheduleDao;
import xyz.sunnytoday.dto.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public List<Schedule> selectScheduleByUser_No(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT * FROM schedule";
		sql += " WHERE user_no = ?";
		
		//결과 저장할 List
		List<Schedule> scheduleList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, schedule.getUser_no());
			
			System.out.println(schedule.getUser_no());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			System.out.println(rs);
			
			//조회 결과 처리
			while(rs.next()) {
				Schedule s = new Schedule(); //결과값 저장 객체
				
				//결과값 한 행 처리
				s.setSchedule_no( rs.getInt("schedule_no") );
				s.setCreate_date( rs.getDate("create_date") );
				s.setUser_no( rs.getInt("user_no") );
				s.setSchedule_date( rs.getDate("schedule_date") );
				s.setTitle( rs.getString("title") );
				s.setContent( rs.getString("content") );
				s.setMemo( rs.getString("memo") );
				s.setLatitude( rs.getDouble("latitude") );
				s.setLongitude( rs.getDouble("longitude") );
				
				//리스트에 결과값 저장
				scheduleList.add(s);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return scheduleList;
		
	}

}
