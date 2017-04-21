package pro.javabean;

public class Zhuangu {
	private String asset_type;//资产类型
	private String zhuangu_num;
	private String asset_status_sum;//总资产
	private String zhuangu_rate;
	
	
	public Zhuangu() {
		super();
	}
	
	public Zhuangu(String asset_type, String zhuangu_num,
			String asset_status_sum, String zhuangu_rate) {
		super();
		this.asset_type = asset_type;
		this.zhuangu_num = zhuangu_num;
		this.asset_status_sum = asset_status_sum;
		this.zhuangu_rate = zhuangu_rate;
	}
	
	
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getZhuangu_num() {
		return zhuangu_num;
	}
	public void setZhuangu_num(String zhuangu_num) {
		this.zhuangu_num = zhuangu_num;
	}
	public String getAsset_status_sum() {
		return asset_status_sum;
	}
	public void setAsset_status_sum(String asset_status_sum) {
		this.asset_status_sum = asset_status_sum;
	}
	public String getZhuangu_rate() {
		return zhuangu_rate;
	}
	public void setZhuangu_rate(String zhuangu_rate) {
		this.zhuangu_rate = zhuangu_rate;
	}
	
	
 

}
