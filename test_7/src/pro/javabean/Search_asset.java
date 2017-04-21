package pro.javabean;

public class Search_asset {
	
	@Override
	public String toString() {
		return "Search_asset [asset_id=" + asset_id + ", city_id=" + city_id
				+ ", cont_name=" + cont_name + ", pro_type=" + pro_type
				+ ", name=" + name + ", prod_name=" + prod_name
				+ ", daiWei_unit=" + daiWei_unit + ", dev_mod=" + dev_mod
				+ ", asset_attribute=" + asset_attribute + ", asset_status="
				+ asset_status + ", asset_use_status=" + asset_use_status
				+ ", asset_acq_type=" + asset_acq_type + ", dev_appli_sta="
				+ dev_appli_sta + ", dev_type=" + dev_type + "]";
	}
	private String asset_id;
	private String city_id;
	private String cont_name;
	private String pro_type;
	private String name;
	private String prod_name;
	private String daiWei_unit;
	private String dev_mod;
	private String asset_attribute;
	private String asset_status;
	private String asset_use_status;
	private String asset_acq_type;
	private String dev_appli_sta;
	private String dev_type;
	public Search_asset(String asset_id, String city_id, String cont_name,
			String pro_type, String name, String prod_name, String daiWei_unit,
			String dev_mod, String asset_attribute, String asset_status,
			String asset_use_status, String asset_acq_type, String dev_appli_sta,String dev_type) {
		super();
		this.asset_id = asset_id;
		this.city_id = city_id;
		this.cont_name = cont_name;
		this.pro_type = pro_type;
		this.name = name;
		this.prod_name = prod_name;
		this.daiWei_unit = daiWei_unit;
		this.dev_mod = dev_mod;
		this.asset_attribute = asset_attribute;
		this.asset_status = asset_status;
		this.asset_use_status = asset_use_status;
		this.asset_acq_type = asset_acq_type;
		this.dev_appli_sta = dev_appli_sta;
		this.dev_type=dev_type;
	}
	public Search_asset() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	
	public String getDev_type() {
		return dev_type;
	}
	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCont_name() {
		return cont_name;
	}
	public void setCont_name(String cont_name) {
		this.cont_name = cont_name;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getDaiWei_unit() {
		return daiWei_unit;
	}
	public void setDaiWei_unit(String daiWei_unit) {
		this.daiWei_unit = daiWei_unit;
	}
	public String getDev_mod() {
		return dev_mod;
	}
	public void setDev_mod(String dev_mod) {
		this.dev_mod = dev_mod;
	}
	public String getAsset_attribute() {
		return asset_attribute;
	}
	public void setAsset_attribute(String asset_attribute) {
		this.asset_attribute = asset_attribute;
	}
	public String getAsset_status() {
		return asset_status;
	}
	public void setAsset_status(String asset_status) {
		this.asset_status = asset_status;
	}
	public String getAsset_use_status() {
		return asset_use_status;
	}
	public void setAsset_use_status(String asset_use_status) {
		this.asset_use_status = asset_use_status;
	}
	public String getAsset_acq_type() {
		return asset_acq_type;
	}
	public void setAsset_acq_type(String asset_acq_type) {
		this.asset_acq_type = asset_acq_type;
	}
	public String getDev_appli_sta() {
		return dev_appli_sta;
	}
	public void setDev_appli_sta(String dev_appli_sta) {
		this.dev_appli_sta = dev_appli_sta;
	}
	
}
