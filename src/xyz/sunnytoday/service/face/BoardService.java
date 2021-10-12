package xyz.sunnytoday.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Post;

public interface BoardService {

	/**
	 * 게시글 전체 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 전체 목록을 리스트로 반환
	 */
	public List<Post> getList(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);


	/**
	 * 질문 응답 목록 조회
	 * @param req 
	 * @param paging - 페이징 정보 객체
	 * @return 질문 응답 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getAskingList(HttpServletRequest req, Paging paging);

	/**
	 * 지름게시판 목록 조회
	 * @param req 
	 * @param paging - 페이징 정보 객체
	 * @return 지름게시판 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getBuyList(HttpServletRequest req, Paging paging);

	/**
	 * 내가 쓴 글 목록 조회
	 * @param req 
	 * @param paging - 페이징 정보 객체
	 * @return 내가 쓴 글 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getMineList(HttpServletRequest req, Paging paging);

	/**
	 * 정보공유 목록 조회
	 * @param req 
	 * @param paging - 페이징 정보 객체
	 * @return 정보공유 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getShareList(HttpServletRequest req, Paging paging);

	/**
	 * 일상룩 목록 조회
	 * @param req 
	 * @param paging - 페이징 정보 객체
	 * @return 일상룩 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getDailyList(HttpServletRequest req, Paging paging);

}
