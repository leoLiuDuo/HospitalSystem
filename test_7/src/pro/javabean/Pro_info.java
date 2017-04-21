package pro.javabean;

public class Pro_info {
private String pro_id;//项目编号
private Pro_type dev_type;//项目类型
private String pro_name;//项目名称
private String aproval_num;//批复文号
public String getPro_id() {
	return pro_id;
}
public void setPro_id(String pro_id) {
	this.pro_id = pro_id;
}
public Pro_type getDev_type() {
	return dev_type;
}
public void setDev_type(Pro_type dev_type) {
	this.dev_type = dev_type;
}
public String getPro_name() {
	return pro_name;
}
public void setPro_name(String pro_name) {
	this.pro_name = pro_name;
}
public String getAproval_num() {
	return aproval_num;
}
public void setAproval_num(String aproval_num) {
	this.aproval_num = aproval_num;
}
public Pro_info(String pro_id, Pro_type dev_type, String pro_name,
		String aproval_num) {
	super();
	this.pro_id = pro_id;
	this.dev_type = dev_type;
	this.pro_name = pro_name;
	this.aproval_num = aproval_num;
}
public Pro_info() {
	super();
	// TODO Auto-generated constructor stub
}

}
