package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.util.Paging;

public interface MemberMenageService {
	
	/**
	 * 리스트의 페이징 처리
	 * @param req - Http 요청 객체
	 * @param param - member DTO 객체
	 * @param location - 페이징 호출 위치
	 * @return - 페이징 결과 반환
	 */
	public Paging getPaging(HttpServletRequest req, Member param, String location);
	
	/**
	 * 회원의 세부 정보 조회
	 * @param req - Http 요청 객체
	 * @return - 조회된 회원의 세부 정보 DTO객체 반환
	 */
	public Member getMemberDetailList(HttpServletRequest req);
	
	/**
	 * 검색 조건이 있다면 조건에 맞는 리스트 / 없다면 모든 회원의 리스트를 요청
	 * @param param - member DTO 객체
	 * @param paging - paging객체
	 * @return - 조회된 회원의 리스트 반환
	 */
	public List<Member> getUserList(Member param, Paging paging);

	/**
	 * 검색 조건이 있다면 조건에 맞는 리스트 / 없다면 모든 문의 리스트를 요청
	 * @param param - member DTO 객체
	 * @param paging - 페이징 객체
	 * @return - 조회된 문의 리스트 반환
	 */
	public List<Question> getQuestionList(Member param, Paging paging);
	

}
