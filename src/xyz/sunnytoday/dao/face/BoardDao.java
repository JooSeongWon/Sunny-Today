package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Post;

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
	public List<Post> selectMainListAll(Connection conn, Paging paging);

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
	public List<Map<String, Object>> selectMineListAll(Board board, Connection conn, Paging paging);

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
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 post_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextPost_no(Connection conn);

	/**
	 * 게시글 입력
	 * 
	 * @param post - 삽입될 게시글 내용
	 * @param board - board_no사용 위해 넣음
	 */
	public int insert(Connection conn, Post post, Board board);

	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param file - 첨부파일 정보
	 * @return 삽입 결과
	 */
	public int insertFile(Connection conn, File file);

	/**
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 board_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return board_no
	 */
	public int selectNextBaord_no(Connection conn);

	/**
	 * 게시글 테이블과 첨부파일 테이블에 입력될 공통 user_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return user_no
	 */
	public int selectNextUser_no(Connection conn);

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
	public Post selectBoardByPostno(Connection conn, Post post_no);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param detailBoard - 조회할 userno를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserno(Connection conn, Post detailBoard);

	
	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param detailBoard - 첨부파일을 조회할 게시글번호 객체
	 * @return File - 조회된 첨부파일
	 */
	public File selectFile(Connection conn, Post detailBoard);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param post - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, Post post);

	/**
	 * 게시글 삭제
	 * 
	 * @param post - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Post post);


}
