package xyz.sunnytoday.service.face;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.util.Paging;
import xyz.sunnytoday.dto.Board;

public interface AdminBoardService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Board> - 게시글 전체 조회 결과 리스트
	 */
	public List<Board> getList();

	/**
	 * 게시글 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 게시글 전체 조회 결과 리스트
	 */
	public List<Board> getList(Paging paging);
	
	
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
	
	public int getCount(HttpServletRequest req);
	
	public int getTitleCount(HttpServletRequest req);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Board - 전달파라미터 boardno를 포함한 객체
	 */
	public Board getBoardno(HttpServletRequest req);
	
	/**
	 * 주어진 boardno를 이용하여 게시글을 조회한다
	 * 
	 * @param boardno - boardno를 가지고 있는 객체
	 * @return Board - 조회된 게시글
	 */
	public Board view(Board board_no);
	
	public void write(HttpServletRequest req);
	
	public void deleteByAdBoard(Board board);
	
	public void updateByAdBoard(HttpServletRequest req);	
}
