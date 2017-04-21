package pro.javabean;

public class Zichan {
	private String asset_type;
	private String kucun_num;
	private String asset_status_sum;
	private String kucun_rate;
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getKucun_num() {
		return kucun_num;
	}
	public void setKucun_num(String kucun_num) {
		this.kucun_num = kucun_num;
	}
	public String getAsset_status_sum() {
		return asset_status_sum;
	}
	public void setAsset_status_sum(String asset_status_sum) {
		this.asset_status_sum = asset_status_sum;
	}
	public String getKucun_rate() {
		return kucun_rate;
	}
	public void setKucun_rate(String kucun_rate) {
		this.kucun_rate = kucun_rate;
	}
	public Zichan(){
		
	}
	public Zichan(String asset_type, String kucun_num, String asset_status_sum,
			String kucun_rate) {
		super();
		this.asset_type = asset_type;
		this.kucun_num = kucun_num;
		this.asset_status_sum = asset_status_sum;
		this.kucun_rate = kucun_rate;
	}
	
}
