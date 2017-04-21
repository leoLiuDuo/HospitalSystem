package pro.javabean;

public class Sunhuai {
	   private String asset_type;
	   private String sunhuai_num;
	   private String asset_status_sum;
	   private String sunhuai_rate;
	   
	   
   public Sunhuai() {
		super();
	}

   
   public Sunhuai(String asset_type, String sunhuai_num, String asset_status_sum,
		String sunhuai_rate) {
	super();
	this.asset_type = asset_type;
	this.sunhuai_num = sunhuai_num;
	this.asset_status_sum = asset_status_sum;
	this.sunhuai_rate = sunhuai_rate;
}

   
   public String getAsset_type() {
	return asset_type;
}
public void setAsset_type(String asset_type) {
	this.asset_type = asset_type;
}
public String getSunhuai_num() {
	return sunhuai_num;
}
public void setSunhuai_num(String sunhuai_num) {
	this.sunhuai_num = sunhuai_num;
}
public String getAsset_status_sum() {
	return asset_status_sum;
}
public void setAsset_status_sum(String asset_status_sum) {
	this.asset_status_sum = asset_status_sum;
}
public String getSunhuai_rate() {
	return sunhuai_rate;
}
public void setSunhuai_rate(String sunhuai_rate) {
	this.sunhuai_rate = sunhuai_rate;
}

}
