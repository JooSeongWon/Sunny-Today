package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.util.Paging;

public interface AdminMessageEventService {
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Board테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	
	/**
	 * 이벤트 메세지 목록 조회
	 * @param paging 
	 * 
	 * @return List<MessageEvent> - 메세지 이벤트 조회 결과 리스트
	 */
	public List<MessageEvent> getlist(HttpServletRequest req, Paging paging);

	/**
	 * 이벤트 목록 조회
	 * 
	 * @return List<Event> - 이벤트 조회 결과 리스트
	 */
	public List<Event> getEvent();

	/**
	 * 이벤트 메세지 작성
	 *  입력한 이벤트 메세지를 DB에 저장
	 *  
	 * @param req - 요청정보객체
	 */
	public void write(HttpServletRequest req);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return MessageEvent - 전달파라미터 MessageEvent_no를 포함한 객체
	 */
	public MessageEvent getEno(HttpServletRequest req);

	/**
	 * 주어진 MessageEvent_no를 이용하여 게시글을 조회한다
	 * 
	 * @param messageno - messageno를 가지고 있는 객체
	 * @return MessageEvent - 조회된 이벤트문
	 */
	public MessageEvent view(MessageEvent messageno);

	/**
	 * 쪽지이벤트 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);
	
	
	/**
	 * 쪽지이벤트 삭제
	 * 
	 * @param message - 삭제할 쪽지번호를 가진 객체
	 */
	public void delete(MessageEvent message);

	/**
	 * 쪽지이벤트 삭제
	 * 
	 * @param arr2 - 삭제할 쪽지번호를 가진 객체
	 */
	public void deleteAll(int arr2);

<<<<<<< HEAD
=======
	/**
	 * 이벤트 메세지 목록 조회
	 * 
	 * @param req - event_no
	 * @return List<MessageEvent> - 메세지 이벤트 조회 결과 리스트
	 */
	public List<MessageEvent> getEventList(HttpServletRequest req);

	
	public List<MessageEvent> getMassageList(HttpServletRequest req);

	/**
	 * 메세지 이벤트 등록
	 * 
	 * @param req - 전달파라미터(분류,타이틀)
	 */
	public void titleWrite(HttpServletRequest req);

>>>>>>> d567e8d14b7b5bda567b23c39c9eb843567d12a8
}
