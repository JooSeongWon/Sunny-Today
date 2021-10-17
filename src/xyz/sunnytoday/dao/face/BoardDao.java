package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;

public interface BoardDao {

	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return int - Board테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 게시판 글 전체 조회
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 게시판 전체를 List로 반환
	 */
	public List<Map<String, Object>> selectMainListAll(Connection conn, Paging paging);

	/**
	 * 질문응답만 전체 조회
	 * @param board 
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 질문응답게시판만 List로 반환
	 */
	public List<Map<String, Object>> selectAskingListAll(Board board, Connection conn, Paging paging);

	/**
	 * 지름 게시판만 전체 조회
	 * @param board 
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 지름 게시판만 List로 반환
	 */
	public List<Map<String, Object>> selectBuyListAll(Board board, Connection conn, Paging paging);

	/**
	 * 내가 쓴 글만 전체 조회
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 내가 쓴 글만 List로 반환
	 */
	public List<Map<String, Object>> selectMineListAll(int userno, Connection conn, Paging paging);

	/**
	 * 정보공유만 전체 조회
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 정보공유만 List로 반환
	 */
	public List<Map<String, Object>> selectShareListAll(Board board, Connection conn, Paging paging);

	/**
	 * 일상룩만 전체 조회
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 일상룩만 List로 반환
	 */
	public List<Map<String, Object>> selectDailyListAll(Board board, Connection conn, Paging paging);

	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블, postFile테이블에 입력될 공통 post_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextPost_no(Connection conn);

	/**
	 * 다음 파일 번호 조회
	 * 
	 * 	첨부파일 테이블, postFile테이블에 입력될 공통 file_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextFile_no(Connection conn);
	
	/**
	 * 카테고리 이름으로 boardno 서치
	 * @param conn 
	 * 
	 * @param value - title이 들어있는 변수
	 * @return 해당하는 boardno
	 */
	public int changeBoardno(Connection conn, String value);

	/**
	 * 게시글 입력
	 * 
	 * @param post - 삽입될 게시글 내용
	 * @param board - board_no사용 위해 넣음
	 * @param member 
	 */
	public int insert(Connection conn, Post post);

	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param file - 첨부파일 정보
	 * @param member 
	 * @return 삽입 결과
	 */
	public int insertFile(Connection conn, File file);

	/**
	 * 파일과 postno 정보 입력
	 * @param conn 
	 * @param postFile
	 * @return
	 */
	public int insertFileInfo(Connection conn, PostFile postFile);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param post_no - 조회된 게시글 번호를 가진 객체
	 */
	public int updateHit(Connection conn, Post post_no);

	/**
	 * 특정 게시글 조회
	 * 
	 * @param post_no - 조회할 post_no를 가진 객체
	 * @return Post - 조회된 결과 객체
	 */
	public Post selectPostByPostno(Connection conn, Post post_no);

	/**
	 * userno를 이용해 nick을 조회한다
	 * 
	 * @param detailBoard - 조회할 userno를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserno(Connection conn, Post detailBoard);
	
	/**
	 * userno를 이용해 nick을 조회한다
	 * 
	 * @param detailBoard - 조회할 userno를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserno(Connection conn, Comments comments);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param fileno - 첨부파일을 조회할 게시글 번호의 fileno
	 * @return File - 조회된 첨부파일
	 */
	public File selectFile(Connection conn, int fileno);

	/**
	 * postno에 해당하는 fileno 확인
	 * @param conn
	 * @param post_no postno이 있는 객체
	 * @return fileno 
	 */
	public int changeFileno(Connection conn, Post post_no);
	
	/**
	 * 게시글 수정 
	 * 
	 * @param board - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, Post post);
	
	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param file_no - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, int file_no);

	/**
	 * 게시글 삭제
	 * 
	 * @param post - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Post post);

	/**
	 * postno에 맞는 file을 선택하여 삭제
	 * 
	 * @param conn
	 * @param post_no - post no
	 * @return
	 */
	public PostFile selectFileByPostno(Connection conn, Post post_no);

	/**
	 * postno에 맞는 file을 검색
	 * @param conn 
	 * @param file_no - postno이 있는 객체
	 * @return
	 */
	public File selectThum(Connection conn, Post post);

	/**
	 * 서치된 리스트 조회
	 * @param conn
	 * @param paging
	 * @param keyword - 검색 키워드
	 * @param select - 검색하는 분류 (제목(title), 본문(content), 작성자(nick))
	 * @param boardTitle - 카테고리 분류
	 * @return
	 */
	public List<Map<String, Object>> selectSearchList(Connection conn, Paging paging, String boardTitle, String select, String keyword);

	/**
	 * post_no을 이용해서 댓글 리스트 조회
	 * @param conn
	 * @param post_no
	 * @return
	 */
	public List<Map<String, Object>> selectCommentPost(Connection conn, Post post_no);

	/**
	 * 댓글 추가
	 * @param conn
	 * @param post_no - 댓글 추가할 post_no
	 * @param comments - 댓글 내용
	 * @param userno - 댓글 작성한 사람
	 * @return
	 */
	public int insertComment(Connection conn, Post post_no, String content, int userno);

	public List<Map<String, Object>> selectDetail(Connection conn, Post param);










}
