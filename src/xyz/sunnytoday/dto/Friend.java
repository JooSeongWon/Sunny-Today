package xyz.sunnytoday.dto;

public class Friend {
	
	private int friend_no;
	private int schedule_no;
	private String name;
	
	@Override
	public String toString() {
		return "Friend [friend_no=" + friend_no + ", schedule_no=" + schedule_no + ", name=" + name + "]";
	}
	
	public int getFriend_no() {
		return friend_no;
	}
	public void setFriend_no(int friend_no) {
		this.friend_no = friend_no;
	}
	public int getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
