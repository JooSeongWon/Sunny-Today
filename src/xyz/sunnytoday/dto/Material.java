package xyz.sunnytoday.dto;

public class Material {
	
	private int material_no;
	private int schedule_no;
	private String name;
	
	@Override
	public String toString() {
		return "Material [material_no=" + material_no + ", schedule_no=" + schedule_no + ", name=" + name + "]";
	}
	
	public int getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(int material_no) {
		this.material_no = material_no;
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
