package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.util.Paging;

public interface QuestionMenageDao {
	
	/**
	 * id로 검색된 총 페이지 수 조회
	 * @param conn - DB 연결 객체
	 * @param param - member dto 객체
	 * @return - id로 검색된 총 페이지 수 반환
	 */
	public int selectIdCntAll(Connection conn, Member param);

	/**
	 * nick로 검색된 총 페이지 수 조회
	 * @param conn - DB 연결 객체
	 * @param param - member dto 객체
	 * @return - id로 검색된 총 페이지 수 반환
	 */
	public int selectNickCntAll(Connection conn, Member param);
	
	/**
	 * question의 모든 데이터(행)의 수를 조회
	 * @param conn - DB 연결객체
	 * @return - 조회된 총 페이지의 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * id로 검색된 문의 리스트 조회
	 * @param param - Member DTO 객체
	 * @param paging - 페이징 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 문의 리스트 반환
	 */
	public List<Question> searchUserId(Member param, Paging paging, Connection conn);

	/**
	 * nick으로 검색된 문의 리스트 조회
	 * @param param - Member DTO 객체
	 * @param paging - 페이징 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 문의 리스트 반환 	
	 */
	public List<Question> searchUserNick(Member param, Paging paging, Connection conn);

	/**
	 * 모든 문의 리스트 조회
	 * @param param - Member DTO 객체
	 * @param paging - 페이징 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 문의 리스트 반환 	
	 */
	public List<Question> getMemberList(Connection conn, Paging paging);

}
