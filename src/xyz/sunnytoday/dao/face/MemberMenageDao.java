package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;

public interface MemberMenageDao {
	/**
	 * DB에서 회원의 리스트를 조회
	 * @param conn - DB연결
	 * @return - 조회된 결과 리스트 반환
	 */
	public List<Member> getMemberList(Connection conn);
	
	/**
	 * 총 회원의 수를 조회
	 * @param conn - DB연결 객체
	 * @return - 조회된 총 회원 수 반환
	 */
	public int selectCntAll(Connection conn);

}
