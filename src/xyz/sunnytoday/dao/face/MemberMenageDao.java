package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface MemberMenageDao {
	/**
	 * DB에서 회원의 리스트를 조회
	 * @param conn - DB연결
	 * @param paging - paging 정보 객체
	 * @return - 조회된 결과 리스트 반환
	 */
	public List<Member> getMemberList(Connection conn, Paging paging);
	
	/**
	 * 총 회원의 수를 조회
	 * @param conn - DB연결 객체
	 * @return - 조회된 총 회원 수 반환
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 유저의 세부 정보를 조회
	 * @param param - 유저 정보 DTO 객체
	 * @param conn - DB연결 객체
	 * @return - 조회된 유저의 세부정보
	 */
	public Member selectUserDatail(Member param, Connection conn);

}
