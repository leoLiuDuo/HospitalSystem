package pro.javabean;

public class Pro_type {
private String pro_type_id;//项目类型编号
private String pro_type;//项目类型名称
public Pro_type(String pro_type_id, String pro_type) {
	super();
	this.pro_type_id = pro_type_id;
	this.pro_type = pro_type;
}
public Pro_type() {
	super();
	// TODO Auto-generated constructor stub
}
public String getPro_type_id() {
	return pro_type_id;
}
public void setPro_type_id(String pro_type_id) {
	this.pro_type_id = pro_type_id;
}
public String getPro_type() {
	return pro_type;
}
public void setPro_type(String pro_type) {
	this.pro_type = pro_type;
}

}
