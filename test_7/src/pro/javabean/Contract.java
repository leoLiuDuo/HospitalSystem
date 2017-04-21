package pro.javabean;

public class Contract {
private String cont_id;//合同编号
private String cont_name;//合同名称
private String buy_date;//购置日期
private String start_date;//起用时间
public String getCont_id() {
	return cont_id;
}
public void setCont_id(String cont_id) {
	this.cont_id = cont_id;
}
public String getCont_name() {
	return cont_name;
}
public void setCont_name(String cont_name) {
	this.cont_name = cont_name;
}
public String getBuy_date() {
	return buy_date;
}
public void setBuy_date(String buy_date) {
	this.buy_date = buy_date;
}
public String getStart_date() {
	return start_date;
}
public void setStart_date(String start_date) {
	this.start_date = start_date;
}
public Contract(String cont_id, String cont_name, String buy_date,
		String start_date) {
	super();
	this.cont_id = cont_id;
	this.cont_name = cont_name;
	this.buy_date = buy_date;
	this.start_date = start_date;
}
public Contract() {
	super();
	// TODO Auto-generated constructor stub
}

}
