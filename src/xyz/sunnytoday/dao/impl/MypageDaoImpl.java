package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.MypageDao;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

public class MypageDaoImpl implements MypageDao {
	
	@Override
	public Member selectMemberByUserno(Connection conn, int userno) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = ""
			+ "SELECT NICK, EMAIL, PHONE, BIRTH, "
			+ " PICTURE_NO, BIRTH_OPEN, PHONE_OPEN, user_no, id"
			+ " FROM MEMBER"
			+ " WHERE USER_NO = ?";
		
		Member member = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, userno ); //유저 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				member = new Member(); //결과값 저장 객체
				
				//결과값 한 행 처리
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setBirth(rs.getDate("birth"));
				member.setPictureno(rs.getInt("picture_no"));
				member.setBirth_open(rs.getString("birth_open"));
				member.setPhone_open(rs.getString("phone_open"));
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return member;
		
	}
	
	
	public int nickCheck(Connection conn, String nick) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = -1; //에러
		
		String sql = ""
			+ "SELECT COUNT(*) FROM MEMBER"
			+ " WHERE NICK = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, nick);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				//-1 에러
				// 0 중복없음
				// 1 중복있음
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	public int updatePhoneOpen(Connection conn, String phone, Member member) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = -1;
		
		String sql = ""
			+ "UPDATE MEMBER SET PHONE_OPEN = ? "
			+ " WHERE user_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, phone);
			ps.setInt(2, member.getUserno());
			
			//-1 에러 , 1 성공
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public int updateBirthOpen(Connection conn, String birth, Member member) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = -1;
		
		String sql = ""
			+ "UPDATE MEMBER SET BIRTH_OPEN = ? "
			+ " WHERE user_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, birth);
			ps.setInt(2, member.getUserno());
			//-1 에러 , 1 성공
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	
	
	@Override
	public int update(Connection conn, Member member) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//java utill Date 타입을 sql에 적용하기 위해 java.sql.date로 변화 
		java.sql.Date date = (java.sql.Date) member.getBirth(); 
		
		String sql = ""
			+"UPDATE MEMBER"
			+ " SET nick = ? ,"
			+ "		phone = ? ,"
			+ "		birth = ? "
			+ "	WHERE user_no = ? ";
		
		int res = -1;
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getNick());
			ps.setString(2, member.getPhone());
			ps.setDate(3, date);
			ps.setInt(4, member.getUserno());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertFile(Connection conn, File file) {
		PreparedStatement ps = null;

		String sql = ""
			+ "INSERT INTO \"FILE\"( file_no, user_no, url, thumbnail_url, origin_name )"
			+ " VALUES( ?, ?, ?, ?, ? )";
		
			
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, file.getFile_no());
			ps.setInt(2, file.getUser_no());
			ps.setString(3, file.getUrl());
			ps.setString(4, file.getThumbnail_url());
			ps.setString(5, file.getOrigin_name());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int insertPicture(Connection conn, Member member) {
		PreparedStatement ps = null;
		
		String sql = ""
				+ "UPDATE MEMBER "
				+ " SET Picture_no = ? "
				+ " WHERE user_no = ? ";
			
				
			int res = 0;
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, member.getPictureno());
				ps.setInt(2, member.getUserno());
				
				res = ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			
			return res;
	}
	
	
	@Override
	public Member getsalt(String userId, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
			+ "SELECT salt, password "
			+ " FROM MEMBER"
			+ " WHERE Id = ?";
		
		Member member = null;
		
		try {
			ps = conn.prepareStatement(sql); 
			
			ps.setString(1, userId ); 
			
			rs = ps.executeQuery(); 
			
			//조회 결과 처리
			while(rs.next()) {
				member = new Member();
				
				member.setSalt(rs.getString("salt"));
				member.setUserpw(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return member;
	}
	
	@Override
	public Member getsalt(int userno, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
			+ "SELECT salt, password "
			+ " FROM MEMBER"
			+ " WHERE user_no = ?";
		
		Member member = null;
		
		try {
			ps = conn.prepareStatement(sql); 
			
			ps.setInt(1, userno ); 
			
			rs = ps.executeQuery(); 
			
			//조회 결과 처리
			while(rs.next()) {
				member = new Member();
				
				member.setSalt(rs.getString("salt"));
				member.setUserpw(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return member;
	}
	
	@Override
	public int selectNextFile_no(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+"SELECT file_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextFile_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextFile_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextFile_no;
	}
	
	@Override
	public File selectFile(Connection conn, Member member) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+ "SELECT f.file_no, thumbnail_url, url, origin_name , f.user_no"
				+ " FROM \"FILE\" F "
				+ " INNER JOIN MEMBER M "
				+ " ON f.file_no = m.picture_no"
				+ " WHERE m.user_no = ? ";
		
		File profile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getUserno());

			rs = ps.executeQuery();
			while(rs.next()) {
				profile = new File();
				
				profile.setFile_no( rs.getInt("file_no") );
				profile.setThumbnail_url(rs.getString("thumbnail_url"));
				profile.setUrl( rs.getString("url") );
				profile.setOrigin_name( rs.getString("origin_name") );
				profile.setUser_no( rs.getInt("user_no") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return profile;
	}
	
	@Override
	public int insertPw(int userno, Connection conn, String newpw) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = ""
				+ "UPDATE MEMBER "
				+ " SET password= ? "
				+ " WHERE USER_NO = ? ";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newpw );
			ps.setInt(2, userno );

			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
}
