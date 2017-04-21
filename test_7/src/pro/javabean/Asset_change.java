package pro.javabean;

import admin.Notice_admin.User;

public class Asset_change {

private String date;//时间
private String user;//用户名
private String  assert_info;//资产编号
private String oper_type;//操作类型
private String ip;//操作人所用的IP地址
private String content_before;//操作前内容
private String content_after;//操作后内容
public Asset_change(String date, String user, String assert_info,
		String oper_type, String ip, String content_before, String content_after) {
	super();
	this.date = date;
	this.user = user;
	this.assert_info = assert_info;
	this.oper_type = oper_type;
	this.ip = ip;
	this.content_before = content_before;
	this.content_after = content_after;
}
public Asset_change() {
	super();
	// TODO Auto-generated constructor stub
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getAssert_info() {
	return assert_info;
}
public void setAssert_info(String assert_info) {
	this.assert_info = assert_info;
}
public String getOper_type() {
	return oper_type;
}
public void setOper_type(String oper_type) {
	this.oper_type = oper_type;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getContent_before() {
	return content_before;
}
public void setContent_before(String content_before) {
	this.content_before = content_before;
}
public String getContent_after() {
	return content_after;
}
public void setContent_after(String content_after) {
	this.content_after = content_after;
}


}