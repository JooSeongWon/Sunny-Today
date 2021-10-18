package xyz.sunnytoday.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Member;
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
	public List<Map<String, Object>> getList(Paging paging);

	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void insert(HttpServletRequest req);

	/**
	 * 게시글 마다 userno가져오기
	 * @param req
	 * @return
	 */
	public Question getUserno(HttpServletRequest req);

	/**
	 * 게시글 마다 questionno가져오기
	 * @param req
	 * @return
	 */
	public Question getQuestionno(HttpServletRequest req);
	
	/**
	 * 게시글마다 nick 가져오기
	 * @param userno - userno을 이용해서 구함
	 * @return
	 */
	public String getNick(Question userno);


	/**
	 * 로그인 한 멤버 정보
	 * @param req
	 * @return
	 */
	public Member loginMember(HttpServletRequest req);

	/**
	 * 문의 상세 확인
	 * @param questionNo
	 * @return
	 */
	public Question detail(Question questionNo);

	/**
	 * 문의 게시글 수정
	 * @param req - 요청정보객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 문의 게시글 삭제
	 * @param questionNo
	 */
	public void delete(Question questionNo);


}
