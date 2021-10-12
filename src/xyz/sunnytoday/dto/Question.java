package xyz.sunnytoday.dto;

import java.util.Date;

public class Question {
	private int question_no;
	private int user_no;
	private Date write_date;
	private Date answer_date;
	private String title;
	private String content;
	private String answer;
	private int admin_no;
	private String id;
	
	
	@Override
	public String toString() {
		return "Question [question_no=" + question_no + ", user_no=" + user_no + ", write_date=" + write_date
				+ ", answer_date=" + answer_date + ", title=" + title + ", content=" + content + ", answer=" + answer
				+ ", admin_no=" + admin_no + ", id=" + id + "]";
	}
	public int getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
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
	public Date getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
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
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
