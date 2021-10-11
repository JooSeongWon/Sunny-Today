package xyz.sunnytoday.dto;

import java.util.Date;

public class Post {
	
	private int post_no;
	private int board_no;
	private int user_no;
	private Date write_date;
	private Date last_modify;
	private String title;
	private String content;
	private int hit;
	@Override
	public String toString() {
		return "Post [post_no=" + post_no + ", board_no=" + board_no + ", user_no=" + user_no + ", write_date="
				+ write_date + ", last_modify=" + last_modify + ", title=" + title + ", content=" + content + ", hit="
				+ hit + "]";
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	

}
