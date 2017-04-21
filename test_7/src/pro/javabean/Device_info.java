package pro.javabean;

public class Device_info {
private String dev_mod;//设备型号
private String dev_name;//设备名称
private String asset_type;//资产类型
private Measure measure;//计量单位对象
private Dev_type dev_type;//设备类型
private Productor productor;//生产厂家
private String money;//金额

public Device_info(String dev_mod, String dev_name, Measure measure,
		Dev_type dev_type, Productor productor, String asset_type,String money) {
	super();
	this.dev_mod = dev_mod;
	this.dev_name = dev_name;
	this.measure = measure;
	this.dev_type = dev_type;
	this.productor = productor;
	this.asset_type = asset_type;
	this.money=money;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
	}

public Device_info() {
	super();
	// TODO Auto-generated constructor stub
}
public String getDev_mod() {
	return dev_mod;
}
public void setDev_mod(String dev_mod) {
	this.dev_mod = dev_mod;
}
public String getDev_name() {
	return dev_name;
}
public void setDev_name(String dev_name) {
	this.dev_name = dev_name;
}
public Measure getMeasure() {
	return measure;
}
public void setMeasure(Measure measure) {
	this.measure = measure;
}
public Dev_type getDev_type() {
	return dev_type;
}
public void setDev_type(Dev_type dev_type) {
	this.dev_type = dev_type;
}
public Productor getProductor() {
	return productor;
}
public void setProductor(Productor productor) {
	this.productor = productor;
}
public String getAsset_type() {
	return asset_type;
}
public void setAsset_type(String asset_type) {
	this.asset_type = asset_type;
}

}
