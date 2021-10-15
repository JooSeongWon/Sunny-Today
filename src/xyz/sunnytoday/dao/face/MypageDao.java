package xyz.sunnytoday.dao.face;

import java.sql.Connection;

import xyz.sunnytoday.dto.Member;

public interface MypageDao {

	public Member selectMemberByUserno(Connection conn, Member loginUser);

	public int nickCheck(Connection conn, String nick);

	public int selectPhoneOpen(Connection conn, String phone, Member loginUserId);

	int updateMember(Connection conn, Member param, Member loginUserId);


}
