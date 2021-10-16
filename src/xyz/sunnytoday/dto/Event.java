package xyz.sunnytoday.dto;

public class Event {
	
	private int event_no;
	private String name;
	
	@Override
	public String toString() {
		return "Event [event_no=" + event_no + ", name=" + name + "]";
	}
	public int getEvent_no() {
		return event_no;
	}
	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
