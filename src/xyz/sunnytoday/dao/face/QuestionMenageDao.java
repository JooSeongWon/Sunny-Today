package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Question;
import xyz.sunnytoday.util.Paging;

public interface QuestionMenageDao {

	/**
	 * 모든 질문 / 검색된 질문의 총 행의 수 조회
	 * @param conn - DB연결 객체
	 * @param param - 검색조건
	 * @return - 조회된 총원 수 반환
	 */
	public int selectCnt(Connection conn, Member param);
	
	/**
	 * 모든 리스트 / 검색된 문의 리스트 조회
	 * @param param - Member DTO 객체
	 * @param paging - 페이징 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 문의 리스트 반환
	 */
	public List<Map<String, Object>> searchQuestion(Member param, Paging paging, Connection conn);

	/**
	 * 조회된 문의 사항의 세부 정보를 조회
	 * @param conn - DB 연결 객체
	 * @param param - Question dto 객체
	 * @return - 조회된 문의사항의 세부 정보 반환
	 */
	public Question getQuestionDatil(Connection conn, Question param);

	/**
	 * 입력된 답변을 DB에 저장
	 * @param conn - DB연결 객체
	 * @param param - Question dto 객체
	 * @return - 답변을 DB에 저장여부를 반환
	 */
	public int setUpdateAnswer(Connection conn, Question param);

	/**
	 * 선택된 번호가 들어간 모든 행을 삭제
	 * @param conn - DB 연결 객체
	 * @param param - Question dto 객체 -> 삭제할 번호가 들어가있음
	 * @return - 삭제 성공 여부를 반환
	 */
	public int deleteQuestion(Connection conn, Question param);


}
