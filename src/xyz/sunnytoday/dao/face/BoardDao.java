package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Board;
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

}
