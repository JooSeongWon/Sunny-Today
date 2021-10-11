package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Post;

public interface BoardService {

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 게시글 전체 목록 조회
	 * @param paging - 페이징 정보 객체
	 * @return 전체 목록을 리스트로 반환
	 */
	public List<Post> getList(Paging paging);

}
