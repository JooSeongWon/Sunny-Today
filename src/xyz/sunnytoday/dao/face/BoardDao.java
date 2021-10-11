package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Post;

public interface BoardDao {

	/**
	 * 게시판 글 전체 조회
	 * @param conn - DB접속
	 * @param paging - 페이징 정보 객체
	 * @return 게시판 전체를 List로 반환
	 */
	public List<Post> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return int - Board테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

}
