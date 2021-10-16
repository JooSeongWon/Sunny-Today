package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.util.Paging;

public interface AdminMessageEventDao {
	
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
	
	/**
	 * 특정 이벤트 조회
	 * 
	 * @param messageno - 조회할 messageno를 가진 객체
	 * @return MessageEvent - 조회된 결과 객체
	 */
	public MessageEvent selectByMessageno(Connection conn, MessageEvent messageno);
	
	/**
	 * 메세지 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param message - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, MessageEvent message);
	
	/**
	 * 메세지 삭제
	 * 
	 * @param conn - DB연결 객체
	 * @param message - 삭제할 내용을 담은 객체
	 */
	public int delete(Connection conn, MessageEvent message);
	
	/**
	 * 메세지 삭제
	 * 
	 * @param conn - DB연결 객체
	 * @param arr2 - 삭제할 객체
	 */
	public int deleteAll(Connection conn, int arr2);
	
	/**
	 * 특정 이벤트 조회
	 * 
	 * @param parseInt - 조회할 evnetno를 가진 객체
	 * @param conn  - DB연결 객체
	 * @return List<MessageEvent> - 조회된 결과 객체
	 */
	public List<MessageEvent> selectByEventNo(int parseInt, Connection conn);
	
	/**
	 * 이벤트 분류 생성
	 * 
	 * @param event
	 * @param conn 
	 */
	public int titleWrite(String event, Connection conn);

	public List<MessageEvent> selectByMessageNo(int parseInt, Connection conn);


}
