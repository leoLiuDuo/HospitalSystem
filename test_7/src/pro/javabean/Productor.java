package pro.javabean;

public class Productor {
private String prod_id;//生产厂家编号
private String prod_name;//生产厂家名称
public Productor(String prod_id, String prod_name) {
	super();
	this.prod_id = prod_id;
	this.prod_name = prod_name;
}
public Productor() {
	super();
	// TODO Auto-generated constructor stub
}
public String getProd_id() {
	return prod_id;
}
public void setProd_id(String prod_id) {
	this.prod_id = prod_id;
}
public String getProd_name() {
	return prod_name;
}
public void setProd_name(String prod_name) {
	this.prod_name = prod_name;
}

}
