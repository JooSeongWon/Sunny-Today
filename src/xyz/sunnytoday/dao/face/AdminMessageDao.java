package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Message;
import xyz.sunnytoday.util.Paging;

public interface AdminMessageDao {
	
	/**
	 * 총 회원 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Member테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 특정 회원 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색한 회원의 id
	 * @return int - Member테이블 id = search 행 조회 결과
	 */
	public int selectCntById(Connection conn, String search);
	
	/**
	 * 특정 회원 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색한 회원의 id
	 * @return int - Member테이블 nick = search 행 조회 결과
	 */
	public int selectCntByNick(Connection conn, String search);

	/**
	 * 특정 회원 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색한 회원의 id
	 * @return int - Member테이블 id = search 행 조회 결과
	 */
	public int selectCntByEmail(Connection conn, String search);
	
	/**
	 * Member테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Member> - Member테이블 전체 조회 결과 리스트
	 */
	public List<Member> searchAll(Connection conn, Paging paging);
	
	/**
	 * Member테이블 특정회원 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색요소
	 * @param paging - 페이징 정보객체
	 * @return List<Member> - Member테이블 id = search 조회결과 리스트
	 */
	public List<Member> searchId(Connection conn, String search, Paging paging);
	
	/**
	 * Member테이블 특정회원 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색요소
	 * @param paging - 페이징 정보객체
	 * @return List<Member> - Member테이블 nick = search 조회결과 리스트
	 */
	public List<Member> searchNick(Connection conn, String search, Paging paging);

	/**
	 * Member테이블 특정회원 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색요소
	 * @param paging - 페이징 정보객체
	 * @return List<Member> - Member테이블 email = search 조회결과 리스트
	 */
	public List<Member> searchEmail(Connection conn, String search, Paging paging);
	
	/**
	 * Member테이블 선택회원 조회
	 * 
	 * @param userno - 유저 넘버
	 * @param conn - DB연결 객체
	 * @return List<Member> - Member테이블 조회결과 리스트
	 */
	public List<Member> selectByUserno(int[] userno, Connection conn);
	
	/**
	 * 메세지 정보 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param message - 메세지 정보객체
	 * @param to - 보내는 유저정보
	 * @param userno - 받을 유저정보
	 */
	public int send(Connection conn, Message message, int userno, int to);
	
	/**
	 * 전체회원의 유저번호 받기
	 * 
	 * @param conn
	 * @param totalcnt 
	 * @return
	 */
	public int[] getuserno(Connection conn, int totalcnt);
	
	


}
