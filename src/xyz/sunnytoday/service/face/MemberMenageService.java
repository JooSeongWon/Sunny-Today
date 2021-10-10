package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface MemberMenageService {
	/**
	 * 등록된 회원의 리스트를 조회 요청
	 * @return - 조회된 회원 리스트
	 */
	public List<Member> getMemberList();
	/**
	 * 리스트의 페이징 처리
	 * @param req - Http 요청 객체
	 * @return - 페이징 결과 반환
	 */
	public Paging getPaging(HttpServletRequest req);

}
