package pro.javabean;

public class Measure {
private String meas_id;//计量id
private String meas_name;//计量单位名称
public Measure(String meas_id, String meas_name) {
	super();
	this.meas_id = meas_id;
	this.meas_name = meas_name;
}
public String getMeas_id() {
	return meas_id;
}
public void setMeas_id(String meas_id) {
	this.meas_id = meas_id;
}
public String getMeas_name() {
	return meas_name;
}
public void setMeas_name(String meas_name) {
	this.meas_name = meas_name;
}
public Measure() {
	super();
	// TODO Auto-generated constructor stub
}

}
