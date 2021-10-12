package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface AdminMemberDao {

	public int selectCntAll(Connection conn);

	public int selectCntId(Connection conn, String search);

	public List<Member> searchId(String param, Paging paging, Connection conn);

	public List<Member> All(Connection conn, Paging paging);

	public Member setAdmin(String userno, Connection conn);

	public Member delAdmin(String userno, Connection conn);



}
