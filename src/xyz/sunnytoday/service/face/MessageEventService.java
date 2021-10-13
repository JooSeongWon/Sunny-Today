package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.dto.Event;
import xyz.sunnytoday.dto.MessageEvent;
import xyz.sunnytoday.util.Paging;

public interface MessageEventService {
	
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

}
