package xyz.sunnytoday.dto;

import java.util.Date;

public class Message {
	private int message_no;	
	private int too;
	private int fromm;
	private Date post_date;
	private String read;
	private String title;
	private String content;
	private int rnum;
	
	@Override
	public String toString() {
		return "Message [message_no=" + message_no + ", too=" + too + ", fromm=" + fromm + ", post_date=" + post_date
				+ ", read=" + read + ", title=" + title + ", content=" + content + "]";
	}
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getMessage_no() {
		return message_no;
	}
	public void setMessage_no(int message_no) {
		this.message_no = message_no;
	}
	public int getToo() {
		return too;
	}
	public void setToo(int too) {
		this.too = too;
	}
	public int getFromm() {
		return fromm;
	}
	public void setFromm(int fromm) {
		this.fromm = fromm;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
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
	
}
