package pro.javabean;

public class Dev_type {
	private String dev_type_id;//设备类型编号
	private String dev_type;//设备类型名称
public Dev_type(String dev_type_id, String dev_type) {
		super();
		this.dev_type_id = dev_type_id;
		this.dev_type = dev_type;
	}
public Dev_type() {
	super();
	// TODO Auto-generated constructor stub
}
public String getDev_type_id() {
	return dev_type_id;
}
public void setDev_type_id(String dev_type_id) {
	this.dev_type_id = dev_type_id;
}
public String getDev_type() {
	return dev_type;
}
public void setDev_type(String dev_type) {
	this.dev_type = dev_type;
}

}
