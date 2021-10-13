package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.util.Paging;

public interface MessageEventDao {
	
	/**
	 * 검색 이벤트 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 검색한 Event 테이블 전체 행 수 조회 결과
	 */
	public int selectCntEvent(Connection conn, String search);
	
	/**
	 * 총 이벤트 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - MessageEvent 테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * MessageEvent테이블 특정이벤트 조회
	 * 페이징 처리 추가
	 * 
	 * @param param - 검색요소
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<MessageEvent> - Event테이블  title 조회 결과 리스트
	 */
	public List<MessageEvent> search(String param, Paging paging, Connection conn);
	
	/**
	 * MessageEvent 테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<MessageEvent> - MessageEvent 테이블 전체 조회 결과 리스트
	 */
	public List<MessageEvent> All(Connection conn, Paging paging);
	
	/**
	 * Event 테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Event> - Event 테이블 전체 조회 결과 리스트
	 */
	public List<Event> selectEvent(Connection conn);
	
	/**
	 * 메시지 이벤트 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param messageEvent - 삽입될 게시글 내용
	 * @return 성공여부
	 */
	public int insertMessage(Connection conn, MessageEvent messageEvent);

}
