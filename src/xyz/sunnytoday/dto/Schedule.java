package xyz.sunnytoday.dto;

import java.util.Date;

public class Schedule {
	
	private int schedule_no;
	private int user_no;
	private Date create_date;
	private Date schedule_date;
	private String title;
	private String content;
	private String memo;
	private double latitude;
	private double longitude;
	
	@Override
	public String toString() {
		return "Schedule [schedule_no=" + schedule_no + ", user_no=" + user_no + ", create_date=" + create_date
				+ ", schedule_date=" + schedule_date + ", title=" + title + ", content=" + content + ", memo=" + memo
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public int getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Date schedule_date) {
		this.schedule_date = schedule_date;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
