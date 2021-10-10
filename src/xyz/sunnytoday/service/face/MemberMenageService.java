package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface MemberMenageService {
	/**
	 * 등록된 회원의 리스트를 조회 요청
	 * @param paging - paging 정보 객체
	 * @return - 조회된 회원 리스트
	 */
	public List<Member> getMemberList(Paging paging);
	
	/**
	 * 리스트의 페이징 처리
	 * @param req - Http 요청 객체
	 * @return - 페이징 결과 반환
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 회원의 세부 정보 조회
	 * @param req - Http 요청 객체
	 * @return - 조회된 회원의 세부 정보 DTO객체 반환
	 */
	public Member getMemberDetailList(HttpServletRequest req);
	
	/**
	 * 전달받은 회원 검색 조건으로 리스트를 요청
	 * @param param - DTO 객체
	 * @param paging - paging객체
	 * @return - 조회된 회원의 리스트 반환
	 */
	public List<Member> getSearchUserList(Member param, Paging paging);

}
