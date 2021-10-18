package xyz.sunnytoday.dto;

import java.util.Date;

public class Report {
	private int  report_no;
	private int report_c_no;
	private int user_no;
	private int target_no;
	private Date report_date;
	private String detail;
	private String report_type;
	private int comments_no;
	private String execute_result;
	private String memo;
	private int post_no;
	
	
	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", report_c_no=" + report_c_no + ", user_no=" + user_no
				+ ", target_no=" + target_no + ", report_date=" + report_date + ", detail=" + detail + ", report_type="
				+ report_type + ", comments_no=" + comments_no + ", execute_result=" + execute_result + ", memo=" + memo
				+ ", post_no=" + post_no + "]";
	}
	
	public int getComments_no() {
		return comments_no;
	}

	public void setComments_no(int comments_no) {
		this.comments_no = comments_no;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public int getReport_c_no() {
		return report_c_no;
	}
	public void setReport_c_no(int report_c_no) {
		this.report_c_no = report_c_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getTarget_no() {
		return target_no;
	}
	public void setTarget_no(int target_no) {
		this.target_no = target_no;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String string) {
		this.report_type = string;
	}
	
	public String getExecute_result() {
		return execute_result;
	}
	public void setExecute_result(String execute_result) {
		this.execute_result = execute_result;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
