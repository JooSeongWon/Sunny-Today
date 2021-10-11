package xyz.sunnytoday.dao.face;

import java.sql.Connection;

import xyz.sunnytoday.dto.Member;

public interface MypageDao {

	public Member selectMemberById(Connection conn, String loginUserId);

	public int nickCheck(Connection conn, String nick);

	public int selectPhoneOpen(Connection conn, String phone, String loginUserId);

}
