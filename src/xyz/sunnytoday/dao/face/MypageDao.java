package xyz.sunnytoday.dao.face;

import java.sql.Connection;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

public interface MypageDao {
	
	/**
	 * 유저번호로 유저정보 검색하기
	 * 
	 * @param conn -DB연결 객체
	 * @param userno - 유저번호
	 * @return member - 유저정보
	 */
	public Member selectMemberByUserno(Connection conn, int userno);
	
	/**
	 * 회원테이블의 닉네임 중복 확인하기
	 * 
	 * @param conn - DB연결 객체
	 * @param nick - 확인할 닉네임
	 * @return 1중복 0중복없음 -1에러
	 */
	public int nickCheck(Connection conn, String nick);

	/**
	 * 유저정보, 공개 키를 통해 유저상태 업데이트
	 * 
	 * @param conn -DB연결 객체
	 * @param phone - 공개 키
	 * @param member - 유저 정보
	 */
	public int updatePhoneOpen(Connection conn, String phone, Member member);
	
	/**
	 * 유저정보, 공개 키를 통해 유저상태 업데이트
	 * 
	 * @param conn -DB연결 객체
	 * @param phone - 공개 키
	 * @param member - 유저 정보
	 */
	public int updateBirthOpen(Connection conn, String birth, Member member);

	/**
	 * 회원정보 업데이트
	 * 
	 * @param conn - DB연결 객체
	 * @param member - update될 회원
	 */
	public int update(Connection conn, Member member);
	
	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param file - 첨부파일 정보
	 * @return 삽입 결과
	 */
	public int insertFile(Connection conn, File file);
	
	/**
	 * 비밀번호 확인을 위해 id로 회원조회
	 * 
	 * @param userId
	 * @param conn 
	 * @return
	 */
	public Member getsalt(String userId, Connection conn);
	


}
