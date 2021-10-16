package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Report;
import xyz.sunnytoday.util.Paging;

public interface MemberMenageDao {
	
	/**
	 * DB에서 검색될 리스트 / 전체 리스트의 수를 구해 페이징에 저장
	 * @param conn - DB연결 객체
	 * @param param - member dto 객체
	 * @return - 페이징 객체
	 */
	public int searchCnt(Connection conn, Member param, String location);

	/**
	 * DB에서 회원의 리스트를 조회
	 * @param conn - DB연결
	 * @param paging - paging 정보 객체
	 * @param param - member dto 객체
	 * @return - 조회된 결과 리스트 반환
	 */
	public List<Member> getMemberList(Connection conn, Paging paging, Member param);
	

	/**
	 * 유저의 세부 정보를 조회
	 * @param param - 유저 정보 DTO 객체
	 * @param conn - DB연결 객체
	 * @return - 조회된 유저의 세부정보
	 */
	public Member selectUserDatail(Member param, Connection conn);
	
	/**
	 * DB에서 검색될 리스트 / 전체 리스트의 수를 구해 페이징에 저장
	 * @param conn - DB연결 객체
	 * @param param1 - Member dto 객체
	 * @param param2 - Report dto 객체 
	 * @return - 페이징 객체
	 */
	public int searchReportCnt(Connection conn, Member param1, Report param2);

	/**
	 * DB에서 제재목록 리스트를 요청
	 * @param param - 검색 조건 DTO 객체
	 * @param paging - 페이징 객체
	 * @param conn - DB연결 객체
	 * @return - 조회된 리스트를 반환
	 */
	public List<Map<String, Object>> searchPurnishList(Member param, Paging paging, Connection conn);

	/**
	 * 선택된 번호가 들어간 모든 행을 삭제
	 * @param conn - DB 연결 객체
	 * @param param - Ban dto 객체 -> 삭제할 번호가 들어가있음
	 * @return - 삭제 성공 여부를 반환
	 */
	public int deletePurnish(Connection conn, Ban param);

	/**
	 * DB에서 회원의 세부 정보를 조회
	 * @param req - 조회할 ban테이블의 pk
	 * @param conn - DB 연결 객체
	 * @return - 조회된 리스트 반환
	 */
	public List<Map<String, Object>> getPurnishDetailList(HttpServletRequest req, Connection conn);
	


}
