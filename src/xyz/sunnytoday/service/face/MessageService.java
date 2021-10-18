package xyz.sunnytoday.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Message;

public interface MessageService {

	/**
	 * 쪽지 목록 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Message> - 쪽지 목록 전체 조회 결과 리스트
	 */
	public List<Message> getMessageList(Paging paging, int userNo);

	
	/**
	 * 보낸 쪽지 목록 전체 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Message> - 보낸 쪽지 목록 전체 조회 결과 리스트
	 */
	public List<Message> getSendMessageList(Paging paging, int userNo);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * message테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 보낸 쪽지 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * message테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getSendPaging(HttpServletRequest req);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Message - 전달파라미터 message_no를 포함한 객체
	 */
	public Message getMessage_No(HttpServletRequest req);

	/**
	 * 쪽지 작성
	 *  입력한 쪽지 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(쪽지 내용 + 받을 회원)
	 */
	public void postMessage(HttpServletRequest req);	

	/**
	 * 주어진 message_no를 이용하여 게시글을 조회한다
	 * 
	 * @param message_no - message_no를 가지고 있는 객체
	 * @return message - 조회된 쪽지
	 */
	public Message view(Message message_no);

	/**
	 * 
	 * 
	 * @param viewMessage
	 * @return
	 */
	public Object getFromNick(Message viewMessage);
	
	/**
	 * 쪽지 삭제
	 * 
	 * @param message - 삭제할 쪽지 번호를 가진 객체
	 */
	public void delete(Message message);

	/**
	 * 리스트의 갯수를 반환
	 *  
	 * @param req - Http 요청 객체
	 * @return - 게시글의 총 갯수
	 */
	public int cntList(HttpServletRequest req);

	/**
	 * 선택된 message를 삭제
	 * 
	 * @param message - 삭제할 message
	 */
	public void deleteMessage(Message message);
















	



}
