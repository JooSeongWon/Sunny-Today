package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface AdminMemberService {

	public Paging getPaging(HttpServletRequest req);

	public List<Member> getlist(HttpServletRequest req, Paging paging);

	public Member getuserno(HttpServletRequest req);
	
	
}
