package xyz.sunnytoday.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;

public interface BoardService {

	/**
	 * 게시글 전체 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 전체 목록을 리스트로 반환
	 */
	public List<Map<String, Object>> getList(Paging paging);
	
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

	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Post - 전달파라미터 Postno를 포함한 객체
	 */
	public Post getPostno(HttpServletRequest req);

	/**
	 * 주어진 Postno를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param Postno - Postno를 가지고 있는 객체
	 * @return Post - 조회된 게시글
	 */
	public Post detail(Post post_no);
	
	/**
	 * 주어진 postno을 이용하여 usernick을 조회한다
	 * @param post_no - postno 를 가지고 있는 객체
	 * @return string - usernick
	 */
	public String SearchNick(Post post_no);

	/**
	 * Post 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param detailBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Post detailBoard);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param post_no - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return File - 첨부파일 정보 DTO객체
	 */
	public File detailFile(Post post_no);

	/**
	 * 게시글 삭제
	 * 
	 * @param post - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Post post_no);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * list에서 post_no을 추출하여 file객체 얻어온 후 썸네일 조회 
	 * @param list - post값이 들어있음
	 * @return file객체
	 */
	public File thumFileShow(List<Map<String, Object>> list);



}
