package xyz.sunnytoday.dto;

import java.util.Date;

public class Comments {
	private int comment_no;
	private int post_no;
	private Date write_date;
	private Date last_modify;
	private String content;
	@Override
	public String toString() {
		return "Comments [comment_no=" + comment_no + ", post_no=" + post_no + ", write_date=" + write_date
				+ ", last_modify=" + last_modify + ", content=" + content + "]";
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public Date getLast_modify() {
		return last_modify;
	}
	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
}
