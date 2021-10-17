package xyz.sunnytoday.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Question;

public interface QuestionService {

	/**
	 * 게시글 전체 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 전체 목록을 리스트로 반환
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public List<Question> getList(Paging paging);

	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void insert(HttpServletRequest req);

}
