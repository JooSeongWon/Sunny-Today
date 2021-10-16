package xyz.sunnytoday.dto;

import java.util.Date;

public class Ban {
	private int ban_no;
	private int user_no;
	private String ban_type;
	private Date ban_date;
	private Date expiry_date;
	private String reason;
	@Override
	public String toString() {
		return "Ban [ban_no=" + ban_no + ", user_no=" + user_no + ", ban_type=" + ban_type + ", ban_date=" + ban_date
				+ ", expiry_date=" + expiry_date + ", reason=" + reason + "]";
	}
	public int getBan_no() {
		return ban_no;
	}
	public void setBan_no(int ban_no) {
		this.ban_no = ban_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getBan_type() {
		return ban_type;
	}
	public void setBan_type(String ban_type) {
		this.ban_type = ban_type;
	}
	public Date getBan_date() {
		return ban_date;
	}
	public void setBan_date(Date ban_date) {
		this.ban_date = ban_date;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
