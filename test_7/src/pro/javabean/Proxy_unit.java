package pro.javabean;

public class Proxy_unit {
private String daiwei_id;//代维单位编号
private String daiwei_unit;//代维单位名称
private String postcode;//邮政编码
private String unit_res;//单位负责人
private String tel_day;//白天固定电话
private String tel_night;//夜间固定电话
private String mol_tel	;//移动电话
private String email;//Email地址
private String address;//代维单位地址
private String dai_controller;//代维主管单位

public Proxy_unit(String daiwei_id, String daiwei_unit, String postcode,
		String unit_res, String tel_day, String tel_night, String mol_tel,
		String email, String address, String dai_controller) {
	super();
	this.daiwei_id = daiwei_id;
	this.daiwei_unit = daiwei_unit;
	this.postcode = postcode;
	this.unit_res = unit_res;
	this.tel_day = tel_day;
	this.tel_night = tel_night;
	this.mol_tel = mol_tel;
	this.email = email;
	this.address = address;
	this.dai_controller = dai_controller;
}

public String getDaiwei_id() {
	return daiwei_id;
}

public void setDaiwei_id(String daiwei_id) {
	this.daiwei_id = daiwei_id;
}

public String getDaiwei_unit() {
	return daiwei_unit;
}

public void setDaiwei_unit(String daiwei_unit) {
	this.daiwei_unit = daiwei_unit;
}

public String getPostcode() {
	return postcode;
}

public void setPostcode(String postcode) {
	this.postcode = postcode;
}

public String getUnit_res() {
	return unit_res;
}

public void setUnit_res(String unit_res) {
	this.unit_res = unit_res;
}

public String getTel_day() {
	return tel_day;
}

public void setTel_day(String tel_day) {
	this.tel_day = tel_day;
}

public String getTel_night() {
	return tel_night;
}

public void setTel_night(String tel_night) {
	this.tel_night = tel_night;
}

public String getMol_tel() {
	return mol_tel;
}

public void setMol_tel(String mol_tel) {
	this.mol_tel = mol_tel;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getDai_controller() {
	return dai_controller;
}

public void setDai_controller(String dai_controller) {
	this.dai_controller = dai_controller;
}

public Proxy_unit() {
	super();
	// TODO Auto-generated constructor stub
}

}
