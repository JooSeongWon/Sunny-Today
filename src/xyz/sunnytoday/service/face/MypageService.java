package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;

public interface MypageService {
	
	/**
	 * 로그인 유저 정보 얻기
	 * 
	 * @param userno - 유저번호
	 */
	public Member selectMember(int userno);

	/**
	 * 닉네임 중복체크
	 * 
	 * @param nick - 입력받은 닉네임
	 */
	public int nickCheck(String nick);
	
	/**
	 * 전화번호 공개/비공개 
	 * 
	 * @param phone - 체크박스 value
	 * @param member - 변경할 유저
	 */
	public int phoneOpen(String phone, Member member);
	
	/**
	 * 핸드폰 공개/비공개 
	 * 
	 * @param birth - 체크박스 value
	 * @param member - 변경할 유저
	 */
	public int birthOpen(String birth, Member member);
	
	/**
	 * 마이페이지 프로필사진 업로드
	 * 
	 * @param req - 저장할 사진
	 */
	public void update(HttpServletRequest req);
	
	/**
	 * 마이페이지 비밀번호 체크
	 * 
	 * @param req - 요청정보객체
	 */
	public int checkPassword(HttpServletRequest req);
	
	/**
	 * 유저 프로필 사진
	 * 
	 * @param userno - 유저정보
	 */
	public File selectProfile(Member member);
	
	/**
	 * 유저 비밀번호 업데이트
	 * 
	 * @param req - 요청정보객체
	 * @param userno - 유저번호
	 */
	public int updatePw(HttpServletRequest req, int userno);
	
	/**
	 * 회원 탈퇴
	 * 
	 * @param userno - 요청정보객체
	 */
	public void delMember(int userno);
	

	
	

	



}
