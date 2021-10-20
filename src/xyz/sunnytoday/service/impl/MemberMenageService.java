package xyz.sunnytoday.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Ban;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.dto.Report;
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
	 * 리스트의 페이징 처리
	 * @param req - Http 요청 객체
	 * @param param1 - member DTO 객체
	 * @param param2 - report DTO 객체
	 * @return - 페이징 결과 반환
	 */	
	public Paging getReportPaging(HttpServletRequest req, Member param1, Report param2);
	
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
	public List<Map<String, Object>> getQuestionList(Member param, Paging paging);

	/**
	 * 문의의 세부 사항을 요청
	 * @param req - Http 요청 객체
	 * @param param - Question dto 객체
	 * @return - 조회된 Question 정보 객체를 반환 
	 */
	public Question getQuestionDetail(HttpServletRequest req, Question param);
	
	/**
	 * 문의에 대한 답변을 적고 답변 일자를 DB에 기록을 요청
	 * @param param - 답변을 담은 Question DTO객체
	 */
	public void updateAnswer(Question param);
	
	/**
	 * 선택된 문의글을 모두 삭제
	 * @param param - 삭제할 문의글의 번호를 담은 DTO 객체
	 */
	public void deleteQuestion(Question param);
	
	/**
	 * 선택된 신고글을 모두 삭제
	 * @param param - 삭제할 문의글의 번호를 담은 DTO 객체
	 */
	public void deleteReport(Report param);

	/**
	 * 신고글의 세부사항을 조회
	 * @param req - 클릭된 신고글의 요청 정보
	 * @return - 반환된 세부 리스트
	 */
	public List<Map<String, Object>> getReportDatil(HttpServletRequest req);
	
	/**
	 * 신고된 회원의 리스트를 요청
	 * @param param1 - 검색 정보를 담고 있는 Member dto 객체
	 * @param param2 - 검색 정보를 담고 있는 Report dto 객체
	 * @param paging - 페이징 정보 객체
	 * @return - 조회된 신고자 리스트
	 */
	public List<Map<String, Object>> getReportList(Member param1, Report param2, Paging paging);

	/**
	 * 신고결과를 테이블에 저장
	 * @param req - 저장할 결과 정보를 가진 요청 객체
	 */
	public void updateExecuteResult(HttpServletRequest req);

	/**
	 * 제재된 회왼의 목록을 리스트로 요청
	 * @param param - 검색 파라미터를 담은 DTO 객체
	 * @param paging - 페이징 객체
	 * @return - 조회된 리스트를 반환
	 */
	public List<Map<String, Object>> getPurnishList(Member param, Paging paging);

	/**
	 * 선택된 회원의 정보를 제재 리스트에서 삭제하는 것으로 복구
	 * @param param - 복구할 회원 정보
	 */
	public void deletePurnish(Ban param);

	/**
	 * 리스트의 갯수를 반환
	 * @param req - Http 요청 객체
	 * @param param - member DTO 객체
	 * @param location - 페이징 호출 위치
	 * @return - 게시글의 총 갯수
	 */
	public int cntList(HttpServletRequest req, Member param, String location);

	/**
	 * 제재된 회원의 세부 정보를 조회
	 * @param req - 조회할 회원의 정보를 담은 요청 객체
	 * @return - 조회된 리스트 반환
	 */
	public List<Map<String, Object>> getPurnishDatailList(HttpServletRequest req);

	/**
	 * 제재목록에 추가 
	 * @param member - 제재할 회원
	 * @param ban - 제재 정보
	 * @param date - 제재 일수
	 */
	public void insertBan(Member member, Ban ban, int date);


	

}
