package xyz.sunnytoday.dto;

import java.sql.Date;

public class Comments {

	private int comments_no;
	private int post_no;
	private int user_no;
	private Date write_date;
	private Date last_modify;
	private String content;
	@Override
	public String toString() {
		return "Comments [comments_no=" + comments_no + ", post_no=" + post_no + ", user_no=" + user_no
				+ ", write_date=" + write_date + ", last_modify=" + last_modify + ", content=" + content + "]";
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
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
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
