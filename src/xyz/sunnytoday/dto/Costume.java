package xyz.sunnytoday.dto;

public class Costume {
	
	private int costume_no;
	private int min_temperatures;
	private int max_temperatures;
	private String type;
	private String gender;
	private int file_no;
	private String title;

	@Override
	public String toString() {
		return "Costume{" +
				"costume_no=" + costume_no +
				", min_temperatures=" + min_temperatures +
				", max_temperatures=" + max_temperatures +
				", type='" + type + '\'' +
				", gender='" + gender + '\'' +
				", file_no=" + file_no +
				", title='" + title + '\'' +
				'}';
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCostume_no() {
		return costume_no;
	}
	public void setCostume_no(int costume_no) {
		this.costume_no = costume_no;
	}
	public int getMin_temperatures() {
		return min_temperatures;
	}
	public void setMin_temperatures(int min_temperatures) {
		this.min_temperatures = min_temperatures;
	}
	public int getMax_temperatures() {
		return max_temperatures;
	}
	public void setMax_temperatures(int max_temperatures) {
		this.max_temperatures = max_temperatures;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	
}
