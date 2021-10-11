package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;

public interface MypageService {

	public Member selectMember(String loginUserId);

	public int nickCheck(String nick);

	public int phoneOpen(String phone, String loginUserId);

	public Member getchangeMember(HttpServletRequest req);

	public void change(Member param);


}
