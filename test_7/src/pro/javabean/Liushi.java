package pro.javabean;

public class Liushi {
	private String asset_type;//资产类型
	private String liushi_num;
	private String asset_status_sum;//总资产
	private String liushi_rate;
	
	public Liushi() {
		super();
	}
	
	
	public Liushi(String asset_type, String liushi_num,
			String asset_status_sum, String liushi_rate) {
		super();
		this.asset_type = asset_type;
		this.liushi_num = liushi_num;
		this.asset_status_sum = asset_status_sum;
		this.liushi_rate = liushi_rate;
	}
	
	
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getLiushi_num() {
		return liushi_num;
	}
	public void setLiushi_num(String liushi_num) {
		this.liushi_num = liushi_num;
	}
	public String getAsset_status_sum() {
		return asset_status_sum;
	}
	public void setAsset_status_sum(String asset_status_sum) {
		this.asset_status_sum = asset_status_sum;
	}
	public String getLiushi_rate() {
		return liushi_rate;
	}
	public void setLiushi_rate(String liushi_rate) {
		this.liushi_rate = liushi_rate;
	}

}
