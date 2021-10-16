package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.util.Paging;

public interface AdminMemberService {
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Board테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 회원 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Member> - 회원 조회 결과 리스트
	 */
	public List<Member> getlist(HttpServletRequest req, Paging paging);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 */
	public void getuserno(HttpServletRequest req);
	
	
}
