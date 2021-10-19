package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.AdminMessageDao;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.util.Paging;

public class AdminMessageDaoImpl implements AdminMessageDao {

	@Override
	public int selectCntAll(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT count(*) FROM member";

		int count = 0;
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return count;
	}

	@Override
	public int selectCntByEmail(Connection conn, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT count(*) FROM member" 
				+ " WHERE email LIKE ? ";

		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCntById(Connection conn, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT count(*) FROM member"
				+ " WHERE id LIKE ? ";

		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCntByNick(Connection conn, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT count(*) FROM member" 
				+ " WHERE nick LIKE ? ";

		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public List<Member> searchAll(Connection conn, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT * FROM(" 
				+ " SELECT rownum rnum, B.* FROM("
				+ "    SELECT user_no, id, nick, email, create_date, admin" 
				+ "   FROM member"
				+ "   ORDER BY user_no DESC" 
				+ " )B " 
				+ ") MEMBER_BOARD" 
				+ " WHERE rnum BETWEEN ? AND ?";

		List<Member> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				member.setAdmin(rs.getString("admin"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;
	}

	@Override
	public List<Member> searchId(Connection conn, String search, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT * FROM(" 
				+ " SELECT rownum rnum, B.* FROM("
				+ "    SELECT user_no, id, nick, email, create_date, admin" 
				+ "   FROM member" 
				+ "   	WHERE id LIKE ?"
				+ "   ORDER BY user_no DESC" 
				+ " )B " 
				+ ") MEMBER_BOARD" 
				+ " WHERE rnum BETWEEN ? AND ?";

		List<Member> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				member.setAdmin(rs.getString("admin"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;
	}

	@Override
	public List<Member> searchEmail(Connection conn, String search, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT * FROM(" 
				+ " SELECT rownum rnum, B.* FROM("
				+ "    SELECT user_no, id, nick, email, create_date, admin" 
				+ "   FROM member"
				+ "   	WHERE email LIKE ?"
				+ "   ORDER BY user_no DESC" 
				+ " )B " 
				+ ") MEMBER_BOARD"
				+ " WHERE rnum BETWEEN ? AND ?";

		List<Member> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				member.setAdmin(rs.getString("admin"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;
	}

	@Override
	public List<Member> searchNick(Connection conn, String search, Paging paging) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "" 
				+ "SELECT * FROM(" 
				+ " SELECT rownum rnum, B.* FROM("
				+ "    SELECT user_no, id, nick, email, create_date, admin" 
				+ "   FROM member"
				+ "   	WHERE nick LIKE ?" 
				+ "   ORDER BY user_no DESC" 
				+ " )B " 
				+ ") MEMBER_BOARD"
				+ " WHERE rnum BETWEEN ? AND ?";

		List<Member> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + search + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setUserno(rs.getInt("user_no"));
				member.setUserid(rs.getString("id"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setCreate_date(rs.getDate("create_date"));
				member.setAdmin(rs.getString("admin"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;
	}

	@Override
	public List<Member> selectByUserno(int[] userno, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();

		for (int i = 0; i < userno.length; i++) {
			int j = userno[i];

			String sql = "" 
					+ "SELECT user_no, id, nick, email, create_date, admin FROM MEMBER" 
					+ " WHERE user_no = ? ";

			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, j);

				rs = ps.executeQuery();

				while (rs.next()) {
					Member member = new Member();

					member.setUserno(rs.getInt("user_no"));
					member.setUserid(rs.getString("id"));
					member.setNick(rs.getString("nick"));
					member.setEmail(rs.getString("email"));
					member.setCreate_date(rs.getDate("create_date"));
					member.setAdmin(rs.getString("admin"));

					list.add(member);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
		}
		return list;
	}

	@Override
	public int send(Connection conn, Message message, int userno, int to) {
		PreparedStatement ps = null;

		int result = 0;

		String sql = "" 
				+ "INSERT INTO MESSAGE (MESSAGE_NO ,TOO ,FROMM ,TITLE ,CONTENT )"
				+ " VALUES (message_seq.nextval, ? , ? , ? , ? )";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			ps.setInt(2, to);
			ps.setString(3, message.getTitle());
			ps.setString(4, message.getContent());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;
	}
	
	@Override
	public int[] getuserno(Connection conn, int totalcount) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int user[] = new int[totalcount];
		
		String sql = "" 
				+ "SELECT user_no FROM MEMBER";
		
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()) {
				user[i] = rs.getInt("user_no");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return user;
	}
	
}
