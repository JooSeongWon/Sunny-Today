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
	
	/**
	 * 유저의 id로 검색 조회
	 * @param param - 유저 정보 DTO 객체
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결객체
	 * @return - 조회된 유저의 조회 리스트
	 */
	public List<Member> searchUserId(Member param, Paging paging, Connection conn);
	
	/**
	 * 유저의 Nick으로 검색 조회
	 * @param param - 유저 정보 DTO 객체
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결객체
	 * @return - 조회된 유저의 조회 리스트
	 */
	public List<Member> searchUserNick(Member param, Paging paging, Connection conn);
	
	/**
	 * DB에서 id로 검색될 회원의 수를 구해 페이징에 저장
	 * @param conn - DB연결 객체
	 * @param param - member dto 객체
	 * @return - 페이징 객체
	 */
	public int selectIdCntAll(Connection conn, Member param);
	
	/**
	 * DB에서 nick으로 검색될 회원의 수를 구해 페이징에 저장
	 * @param conn - DB연결 객체
	 * @param param - member dto 객체
	 * @return - 페이징 객체
	 */
	public int selectNickCntAll(Connection conn, Member param);

}
