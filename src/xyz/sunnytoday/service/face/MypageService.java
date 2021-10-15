package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;

public interface MypageService {
	
	public Member getUser(HttpServletRequest req);

	public Member selectMember(Member loginUserId);

	public void update(HttpServletRequest req);
	
	public int nickCheck(String nick);
	
	
	
	
	public Member getchangeMember(HttpServletRequest req);

	public int phoneOpen(String phone, Member loginUser);

	



}
