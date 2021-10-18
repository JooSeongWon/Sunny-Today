package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Message;

public interface MessageDao {
	
	/**
	 * 
	 * 
	 * @param connection
	 * @return
	 */
	List<Message> selectAllToMe(Connection connection, Paging paging, int userNo);

	/**
	 * 쪽지 삭제
	 * 
	 * @param message - 삭제할 쪽지 번호를 담은 객체
	 */
	int delete(Connection conn, Message message);
	
	/**
	 * 
	 * @param connection
	 * @param userNo
	 * @return
	 */
	int selectCntAllToMe(Connection connection, int userNo);
	
	/**
	 * 
	 * @param connection
	 * @param message
	 */
	void insert(Connection connection, Message message);

	/**
	 * 특정 쪽지 조회
	 * 
	 * @param message_no - 조회할 message_no를 가진 객체
	 * @return message - 조회된 결과 객체
	 */
	Message selectMessageByMessageno(Connection conn, Message message_no);

	/**
	 * DB에서 검색될 리스트 / 전체 리스트의 수를 구해 페이징에 저장
	 * 
	 * @param conn - DB연결 객체
	 * @return - 페이징 객체
	 * 
	 */
	int searchCnt(Connection conn);

	/**
	 * 선택된 번호가 들어간 모든 행을 삭제
	 * 
	 * @param conn - DB 연결 객체
	 * @param param - message_dto 객체 -> 삭제할 번호가 들어가 있음
	 * @return - 삭제 성공 여부를 반환
	 * 
	 */
	int deleteMessage(Connection conn, Message message);

	Object selectNickByUserno(Connection connection, Message viewMessage);

	/**
	 * e다른사람한테 보낸 거 보여줌
	 * 
	 * @param connection
	 * @param paging
	 * @param userNo
	 * @return
	 */
	List<Message> selectAllToOther(Connection connection, Paging paging, int userNo);

	/**
	 * ㅜㅜ 맞겟지..
	 * @param connection
	 * @param attribute
	 * @return
	 */
	int selectCntAllToOther(Connection connection, int userNo);





}
