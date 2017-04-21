package pro.javabean;

public class Count_condition_summary {
	private String asset_id;
	private String allo_info;
	private String dev_mod;
	private String daiwei_unit;
	private String dev_appli_sta;
	private String daiwei_cont_peri;
	private String name;
	private String maint_period;
	private String maint_end_time;
	private String money;
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Count_condition_summary(){
		
	}
	/**
	 * @return the asset_id
	 */
	public String getAsset_id() {
		return asset_id;
	}
	/**
	 * @param asset_id the asset_id to set
	 */
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	/**
	 * @return the allo_info
	 */
	public String getAllo_info() {
		return allo_info;
	}
	/**
	 * @param allo_info the allo_info to set
	 */
	public void setAllo_info(String allo_info) {
		this.allo_info = allo_info;
	}
	/**
	 * @return the dev_mod
	 */
	public String getDev_mod() {
		return dev_mod;
	}
	/**
	 * @param dev_mod the dev_mod to set
	 */
	public void setDev_mod(String dev_mod) {
		this.dev_mod = dev_mod;
	}
	/**
	 * @return the daiwei_unit
	 */
	public String getDaiwei_unit() {
		return daiwei_unit;
	}
	/**
	 * @param daiwei_unit the daiwei_unit to set
	 */
	public void setDaiwei_unit(String daiwei_unit) {
		this.daiwei_unit = daiwei_unit;
	}
	/**
	 * @return the dev_appli_sta
	 */
	public String getDev_appli_sta() {
		return dev_appli_sta;
	}
	/**
	 * @param dev_appli_sta the dev_appli_sta to set
	 */
	public void setDev_appli_sta(String dev_appli_sta) {
		this.dev_appli_sta = dev_appli_sta;
	}
	/**
	 * @return the daiwei_cont_peri
	 */
	public String getDaiwei_cont_peri() {
		return daiwei_cont_peri;
	}
	/**
	 * @param daiwei_cont_peri the daiwei_cont_peri to set
	 */
	public void setDaiwei_cont_peri(String daiwei_cont_peri) {
		this.daiwei_cont_peri = daiwei_cont_peri;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the maint_period
	 */
	public String getMaint_period() {
		return maint_period;
	}
	/**
	 * @param maint_period the maint_period to set
	 */
	public void setMaint_period(String maint_period) {
		this.maint_period = maint_period;
	}
	/**
	 * @return the maint_end_time
	 */
	public String getMaint_end_time() {
		return maint_end_time;
	}
	/**
	 * @param maint_end_time the maint_end_time to set
	 */
	public void setMaint_end_time(String maint_end_time) {
		this.maint_end_time = maint_end_time;
	}
	public Count_condition_summary(String asset_id, String allo_info,
			String dev_mod, String daiwei_unit, String dev_appli_sta,
			String daiwei_cont_peri, String name, String maint_period,
			String maint_end_time) {
		super();
		this.asset_id = asset_id;
		this.allo_info = allo_info;
		this.dev_mod = dev_mod;
		this.daiwei_unit = daiwei_unit;
		this.dev_appli_sta = dev_appli_sta;
		this.daiwei_cont_peri = daiwei_cont_peri;
		this.name = name;
		this.maint_period = maint_period;
		this.maint_end_time = maint_end_time;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
