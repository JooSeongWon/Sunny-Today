package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.ScheduleDao;
import xyz.sunnytoday.dto.Friend;
import xyz.sunnytoday.dto.Material;
import xyz.sunnytoday.dto.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public List<Schedule> selectScheduleList(Connection conn, Schedule schedule) {
		
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
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
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
				s.setR1( rs.getString("r1"));
				s.setR2( rs.getString("r2"));
				
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
	
	@Override
	public Schedule selectSameSchedule(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT * FROM schedule";
		sql += " WHERE user_no = ? and schedule_date = ?";
		
		//결과 저장
		Schedule resultSchedule = new Schedule();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, schedule.getUser_no());
			ps.setDate(2, (Date) schedule.getSchedule_date());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				
				//결과값 처리
				resultSchedule.setSchedule_no( rs.getInt("schedule_no") );
				resultSchedule.setCreate_date( rs.getDate("create_date") );
				resultSchedule.setUser_no( rs.getInt("user_no") );
				resultSchedule.setSchedule_date( rs.getDate("schedule_date") );
				resultSchedule.setTitle( rs.getString("title") );
				resultSchedule.setContent( rs.getString("content") );
				resultSchedule.setMemo( rs.getString("memo") );
				resultSchedule.setLatitude( rs.getDouble("latitude") );
				resultSchedule.setLongitude( rs.getDouble("longitude") );
				resultSchedule.setR1( rs.getString("r1"));
				resultSchedule.setR2( rs.getString("r2"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return resultSchedule;
		
	}

	@Override
	public Schedule selectSchedule_no(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT * FROM schedule";
		sql += " WHERE user_no = ? and schedule_date = ?";
		
		
		//결과 저장할 Schedule
		Schedule resultSchedule = new Schedule();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, schedule.getUser_no());
			ps.setDate(2, (Date) schedule.getSchedule_date());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				
				//결과값 한 행 처리
				resultSchedule.setSchedule_no( rs.getInt("schedule_no") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return resultSchedule;
		
	}
	
	@Override
	public Schedule selectSchedule(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT s.schedule_no, s.USER_NO, s.CREATE_DATE, s.SCHEDULE_DATE, s.TITLE, s.CONTENT, s.MEMO, s.LATITUDE, s.LONGITUDE, s.R1, s.R2";
		sql += " FROM schedule s";
		sql += " WHERE user_no = ? AND s.schedule_no = (SELECT MAX(schedule_no) FROM schedule)";
		
		//결과 저장
		Schedule resultSchedule = new Schedule();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, schedule.getUser_no());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				
				//결과값 처리
				resultSchedule.setSchedule_no( rs.getInt("schedule_no") );
				resultSchedule.setCreate_date( rs.getDate("create_date") );
				resultSchedule.setUser_no( rs.getInt("user_no") );
				resultSchedule.setSchedule_date( rs.getDate("schedule_date") );
				resultSchedule.setTitle( rs.getString("title") );
				resultSchedule.setContent( rs.getString("content") );
				resultSchedule.setMemo( rs.getString("memo") );
				resultSchedule.setLatitude( rs.getDouble("latitude") );
				resultSchedule.setLongitude( rs.getDouble("longitude") );
				resultSchedule.setR1( rs.getString("r1"));
				resultSchedule.setR2( rs.getString("r2"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return resultSchedule;
		
	}

	@Override
	public int insertSchedule(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "INSERT INTO schedule( schedule_no, user_no, create_date, schedule_date, title, content, memo, latitude, longitude, r1, r2 )";
		sql += " VALUES( schedule_seq.nextval, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, schedule.getUser_no());
			ps.setDate(2, (Date) schedule.getSchedule_date());
			ps.setString(3, schedule.getTitle());
			ps.setString(4, schedule.getContent());
			ps.setString(5, schedule.getMemo());
			ps.setDouble(6, schedule.getLatitude());
			ps.setDouble(7, schedule.getLongitude());
			ps.setString(8, schedule.getR1());
			ps.setString(9, schedule.getR2());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int insertMaterial(Connection conn, Material material) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "INSERT INTO material( material_no, schedule_no, name )";
		sql += " VALUES( material_seq.nextval, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, material.getSchedule_no());
			ps.setString(2, material.getName());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int insertFriend(Connection conn, Friend friend) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "INSERT INTO friend( friend_no, schedule_no, name )";
		sql += " VALUES( friend_seq.nextval, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, friend.getSchedule_no());
			ps.setString(2, friend.getName());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int deleteMaterial(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "DELETE FROM material";
		sql += " WHERE schedule_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, schedule.getSchedule_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int deleteFriend(Connection conn, Schedule schedule) {
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "DELETE FROM friend";
		sql += " WHERE schedule_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, schedule.getSchedule_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public int deleteSchedule(Connection conn, Schedule schedule) {

		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "DELETE FROM schedule";
		sql += " WHERE schedule_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, schedule.getSchedule_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}

	@Override
	public List<Material> getMaterial(Connection conn, Material material) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT * FROM material";
		sql += " WHERE schedule_no = ?";
		
		//결과 저장할 List
		List<Material> materialList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, material.getSchedule_no());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				Material m = new Material(); //결과값 저장 객체
				
				//결과값 한 행 처리
				m.setMaterial_no( rs.getInt("material_no") );
				m.setSchedule_no( rs.getInt("schedule_no") );
				m.setName( rs.getString("name") );
				
				//리스트에 결과값 저장
				materialList.add(m);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return materialList;
		
	}

	@Override
	public List<Friend> getFriend(Connection conn, Friend friend) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT * FROM friend";
		sql += " WHERE schedule_no = ?";
		
		//결과 저장할 List
		List<Friend> friendList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, friend.getSchedule_no());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				Friend f = new Friend(); //결과값 저장 객체
				
				//결과값 한 행 처리
				f.setFriend_no( rs.getInt("friend_no") );
				f.setSchedule_no( rs.getInt("schedule_no") );
				f.setName( rs.getString("name") );
				
				//리스트에 결과값 저장
				friendList.add(f);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return friendList;
		
	}

}
