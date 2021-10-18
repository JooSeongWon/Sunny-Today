package xyz.sunnytoday.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.Report;

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
	public void setThumFile(List<Map<String, Object>> list);

	/**
	 * 사용자가 입력한 값에 따라서 검색된 리스트 조회
	 * @param req 입력한 값
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> getSearchList(HttpServletRequest req, Paging paging);

	/**
	 * 로그인 되어있는 닉네임 세션에서 가져오기
	 * @param req 
	 * @return
	 */
	public String loginNick(HttpServletRequest req);

	/**
	 * postno에 맞는 댓글 리스트 가져오기
	 * @param post_no - 게시글 번호
	 * @return List<Comments> - 댓글 리스트
	 */
	public List<Comments> selectCommentPost(Post post_no);

	/** 
	 * 댓글 내용 가져오기
	 * @param req - 댓글 내용이 든 req
	 * @return
	 */
	public String getComments(HttpServletRequest req);

	/**
	 * 댓글 추가요청
	 * @param post_no - 댓글 추가할 postno
	 * @param comments - 댓글 내용
	 * @param userno - 댓글 작성자
	 */
	public void insertComment(Post post_no, String comments, int userno);

	/**
	 * 보드의 세부정보를 요청
	 * @param param - 요청할 보드의 정보객체 post
	 * @param param2 - 요청할 보드의 정보객체 comments
	 * @return - 조회된 보드의 세부 정보 리스트
	 */
	public List<Map<String, Object>> boardDetail(Post param, Comments param2);

	/**
	 * 신고테이블에 해당 신고글 등록
	 * @param param - report dto 객체 
	 */
	public void insertReport(Report param);




}
