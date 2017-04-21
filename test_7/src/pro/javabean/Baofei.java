package pro.javabean;

public class Baofei {
	private String asset_type;//资产类型
	private String baofei_num;
	private String asset_status_sum;//总资产
	private String baofei_rate;
	
	public Baofei() {
		super();
	}
	public Baofei(String asset_type, String baofei_num,
			String asset_status_sum, String baofei_rate) {
		super();
		this.asset_type = asset_type;
		this.baofei_num = baofei_num;
		this.asset_status_sum = asset_status_sum;
		this.baofei_rate = baofei_rate;
	}
	
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getBaofei_num() {
		return baofei_num;
	}
	public void setBaofei_num(String baofei_num) {
		this.baofei_num = baofei_num;
	}
	public String getAsset_status_sum() {
		return asset_status_sum;
	}
	public void setAsset_status_sum(String asset_status_sum) {
		this.asset_status_sum = asset_status_sum;
	}
	public String getBaofei_rate() {
		return baofei_rate;
	}
	public void setBaofei_rate(String baofei_rate) {
		this.baofei_rate = baofei_rate;
	}
	

}
