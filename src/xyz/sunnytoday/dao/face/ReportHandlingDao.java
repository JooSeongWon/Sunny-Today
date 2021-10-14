package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Report;
import xyz.sunnytoday.util.Paging;

public interface ReportHandlingDao {

	
	/**
	 * 검색 조건을 통한 조회 / 모든 신고자 조회
	 * @param param - 조회할 정보 dto 객체
	 * @param param2 
	 * @param paging - 페이징 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 리스트 반환
	 */
	public List<Map<String, Object>> searchReportList(Member param, Report param2, Paging paging, Connection conn);
	
	/**
	 * 선택된 번호가 들어간 모든 행을 삭제
	 * @param conn - DB 연결 객체
	 * @param param - Report dto 객체 -> 삭제할 번호가 들어가있음
	 * @return - 삭제 성공 여부를 반환
	 */
	public int deleteReport(Connection conn, Report param);

	/**
	 * 신고글의 세부정보를 조회
	 * @param param - 신고글의 정보 객체
	 * @param conn - DB 연결 객체
	 * @return - 조회된 리스트를 반환
	 */
	public List<Map<String, Object>> ReportDatilList(Report param, Connection conn);

}
