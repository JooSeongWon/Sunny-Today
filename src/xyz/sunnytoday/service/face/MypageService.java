package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;

public interface MypageService {
	
<<<<<<< HEAD
	public Member getUser(HttpServletRequest req);

	public Member selectMember(Member loginUserId);

	public void update(HttpServletRequest req);
	
	public int nickCheck(String nick);
	
	
	
	
	public Member getchangeMember(HttpServletRequest req);

	public int phoneOpen(String phone, Member loginUser);

	

=======
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
	 * @return
	 */
	public boolean checkPassword(HttpServletRequest req);
	
	

	

>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8


}
