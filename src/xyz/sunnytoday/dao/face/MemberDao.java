package xyz.sunnytoday.dao.face;

import java.sql.Connection;

import xyz.sunnytoday.dto.Member;

public interface MemberDao {

	/**
	 * 
	 * @param connection
	 * @param member
	 * @return
	 */
	public int selectCntMemberByUseridUserpw(Connection connection, Member member);

	/**
	 * 
	 * @param connection
	 * @param member
	 * @return
	 */
	public Member selectMemberByUserid(Connection connection, Member member);

	/**
	 * 
	 * @param conn
	 * @param member
	 * @return
	 */
	public int insert(Connection conn, Member member);


}
