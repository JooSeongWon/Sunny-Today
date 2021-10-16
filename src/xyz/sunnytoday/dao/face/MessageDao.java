package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dto.Message;

public interface MessageDao {
	
	/**
	 * ;;
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
	
	int selectCntAllToMe(Connection connection, int userNo);
	
	void insert(Connection connection, Message message);

	/**
	 * 특정 쪽지 조회
	 * 
	 * @param message_no - 조회할 message_no를 가진 객체
	 * @return message - 조회된 결과 객체
	 */
	Message selectMessageByMessageno(Connection conn, Message message_no);



}
