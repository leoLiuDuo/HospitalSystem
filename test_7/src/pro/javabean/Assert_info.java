package pro.javabean;

import admin.Notice_admin.User;

public class Assert_info {
private String asset_id;//资产编号
private String dev_type;//资产类型
private String city_id;//资产城市
private String allo_info;//资产配置信息
private String storage_loca;//资产存放地点
private Device_info device_info;//设备型号
private Pro_info pro_info;//项目编号
private Contract contract;//合同编号
private Proxy_unit daiwei_unit;//代维单位编号
private User user;//用户名
private String asset_attribute;//资产属性
private String asset_status;//资产状态
private String asset_use_status;//资产使用状态
private String dev_appli_sta;//设备应用状态
private String asset_acq_type;//资产购置类型
private String daiWei_cont_peri;//代维单位合同期限
private String serv_end_time;//服务终止时间
private String maint_period;//保修期
private String maint_end_time;//保修终止时间
private String ass_controller;//资产主管单位
private String asset_id1;//保修终止时间
private String money;
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
public String getAsset_id1() {
	return asset_id1;
}
public void setAsset_id1(String asset_id1) {
	this.asset_id1 = asset_id1;
}
public String getAsset_id() {
	return asset_id;
}
public String getDev_type() {
	return dev_type;
}
public void setDev_type(String dev_type) {
	this.dev_type = dev_type;
}
public String getCity_id() {
	return city_id;
}
public void setCity_id(String city_id) {
	this.city_id = city_id;
}
public void setAsset_id(String asset_id) {
	this.asset_id = asset_id;
}
public String getAllo_info() {
	return allo_info;
}
public void setAllo_info(String allo_info) {
	this.allo_info = allo_info;
}
public String getStorage_loca() {
	return storage_loca;
}
public void setStorage_loca(String storage_loca) {
	this.storage_loca = storage_loca;
}
public Device_info getDevice_info() {
	return device_info;
}
public void setDevice_info(Device_info device_info) {
	this.device_info = device_info;
}
public Pro_info getPro_info() {
	return pro_info;
}
public void setPro_info(Pro_info pro_info) {
	this.pro_info = pro_info;
}
public Contract getContract() {
	return contract;
}
public void setContract(Contract contract) {
	this.contract = contract;
}
public Proxy_unit getDaiwei_unit() {
	return daiwei_unit;
}
public void setDaiwei_unit(Proxy_unit daiwei_unit) {
	this.daiwei_unit = daiwei_unit;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public String getAsset_attribute() {
	return asset_attribute;
}
public void setAsset_attribute(String asset_attribute) {
	this.asset_attribute = asset_attribute;
}
public String getAsset_status() {
	return asset_status;
}
public void setAsset_status(String asset_status) {
	this.asset_status = asset_status;
}
public String getAsset_use_status() {
	return asset_use_status;
}
public void setAsset_use_status(String asset_use_status) {
	this.asset_use_status = asset_use_status;
}
public String getDev_appli_sta() {
	return dev_appli_sta;
}
public void setDev_appli_sta(String dev_appli_sta) {
	this.dev_appli_sta = dev_appli_sta;
}
public String getAsset_acq_type() {
	return asset_acq_type;
}
public void setAsset_acq_type(String asset_acq_type) {
	this.asset_acq_type = asset_acq_type;
}
public String getDaiWei_cont_peri() {
	return daiWei_cont_peri;
}
public void setDaiWei_cont_peri(String daiWei_cont_peri) {
	this.daiWei_cont_peri = daiWei_cont_peri;
}
public String getServ_end_time() {
	return serv_end_time;
}
public void setServ_end_time(String serv_end_time) {
	this.serv_end_time = serv_end_time;
}
public String getMaint_period() {
	return maint_period;
}
public void setMaint_period(String maint_period) {
	this.maint_period = maint_period;
}
public String getMaint_end_time() {
	return maint_end_time;
}
public void setMaint_end_time(String maint_end_time) {
	this.maint_end_time = maint_end_time;
}
public String getAss_controller() {
	return ass_controller;
}
public void setAss_controller(String ass_controller) {
	this.ass_controller = ass_controller;
}
public Assert_info(String asset_id, String allo_info, String storage_loca,
		Device_info device_info, Pro_info pro_info, Contract contract,
		Proxy_unit daiwei_unit, User user, String asset_attribute,
		String asset_status, String asset_use_status, String dev_appli_sta,
		String asset_acq_type, String daiWei_cont_peri, String serv_end_time,
		String maint_period, String maint_end_time, String ass_controller) {
	super();
	this.asset_id = asset_id;
	this.allo_info = allo_info;
	this.storage_loca = storage_loca;
	this.device_info = device_info;
	this.pro_info = pro_info;
	this.contract = contract;
	this.daiwei_unit = daiwei_unit;
	this.user = user;
	this.asset_attribute = asset_attribute;
	this.asset_status = asset_status;
	this.asset_use_status = asset_use_status;
	this.dev_appli_sta = dev_appli_sta;
	this.asset_acq_type = asset_acq_type;
	this.daiWei_cont_peri = daiWei_cont_peri;
	this.serv_end_time = serv_end_time;
	this.maint_period = maint_period;
	this.maint_end_time = maint_end_time;
	this.ass_controller = ass_controller;
}
public Assert_info() {
	super();
	// TODO Auto-generated constructor stub
}

}
