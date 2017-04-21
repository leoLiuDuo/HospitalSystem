package pro.javabean;

public class Valid_asset {
private String pro_name;//xiangmuxinxi
private String cont_name;//shangwu
private String daiwei_unit;//daiwei
private String dev_name;//shebei
private String asset_id;//zichan
private String name;//guanliren
public String getPro_name() {
	return pro_name;
}
public void setPro_name(String pro_name) {
	this.pro_name = pro_name;
}
public String getCont_name() {
	return cont_name;
}
public void setCont_name(String cont_name) {
	this.cont_name = cont_name;
}
public String getDaiwei_unit() {
	return daiwei_unit;
}
public void setDaiwei_unit(String daiwei_unit) {
	this.daiwei_unit = daiwei_unit;
}
public String getDev_name() {
	return dev_name;
}
public void setDev_name(String dev_name) {
	this.dev_name = dev_name;
}
public String getAsset_id() {
	return asset_id;
}
public void setAsset_id(String asset_id) {
	this.asset_id = asset_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Valid_asset(String pro_name, String cont_name, String daiwei_unit,
		String dev_name, String asset_id, String name) {
	super();
	this.pro_name = pro_name;
	this.cont_name = cont_name;
	this.daiwei_unit = daiwei_unit;
	this.dev_name = dev_name;
	this.asset_id = asset_id;
	this.name = name;
}
public Valid_asset() {
	super();
	// TODO Auto-generated constructor stub
}

}
