package xyz.sunnytoday.service.face;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;

public interface MypageService {

	public Member selectMember(String loginUserId);
	


}
