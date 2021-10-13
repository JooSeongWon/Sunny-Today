package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface AdminMemberDao {
	
	/**
	 * 총 회원 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Member테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 특정 회원 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색한 회원의 id
	 * @return int - Member테이블 id = search 행 조회 결과
	 */
	public int selectCntId(Connection conn, String search);
	
	/**
	 * Member테이블 특정회원 조회
	 * 페이징 처리 추가
	 * 
	 * @param param - 검색요소
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Member> - Member테이블  id = search 조회 결과 리스트
	 */
	public List<Member> searchId(String param, Paging paging, Connection conn);
	

	/**
	 * Member테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Member> - Member테이블 전체 조회 결과 리스트
	 */
	public List<Member> All(Connection conn, Paging paging);
	
	/**
	 * 관리자권한 등록
	 * 
	 * @param userno - 등록할 유저의 번호
	 * @param conn - DB연결 객체
	 * @return int - 결과반환 1성공 0실패
	 */
	public int setAdmin(int userno, Connection conn);
	
	/**
	 * 관리자권한 삭제
	 * 
	 * @param userno - 삭제할 유저의 번호
	 * @param conn - DB연결 객체
	 * @return int - 결과반환 1성공 0실패
	 */
	public int delAdmin(int userno, Connection conn);
	
	/**
	 * 총 관리자 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Member테이블 관리자 전체 행 수 조회 결과
	 */
	public int selectCntAdmin(Connection conn);

	
	/**
	 * Member테이블 관리자 조회
	 * 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Member> - Member테이블  Admin = 'A' 조회 결과 리스트
	 */
	public List<Member> selectAdmin(Connection conn, Paging paging);




}
