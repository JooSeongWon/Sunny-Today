package xyz.sunnytoday.service.face;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.AdminBoard;

public interface AdminBoardService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Board> - 게시글 전체 조회 결과 리스트
	 */
	public List<AdminBoard> getList();

	/**
	 * 게시글 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 게시글 전체 조회 결과 리스트
	 */
	public List<AdminBoard> getList(Paging paging);
	
	
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
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Board - 전달파라미터 boardno를 포함한 객체
	 */
	public AdminBoard getBoardno(HttpServletRequest req);
	
}
