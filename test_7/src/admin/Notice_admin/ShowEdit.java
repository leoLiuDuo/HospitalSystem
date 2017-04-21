package admin.Notice_admin;

import java.sql.Timestamp;

public class ShowEdit {
	private String name;//用户名
	private Timestamp date;//时间
	private String city;//城市
	private String asset_id;//资产编号
	private String dev_name;//设备名称
	private String oper_type;//操作类型
	private String content_before;//操作前
	private String content_after;//操作后
	private String ip;//ip地址
	public ShowEdit(){
		
	}
	public ShowEdit(String name, Timestamp date, String city, String asset_id,
			String dev_name, String oper_type, String content_before,
			String content_after, String ip) {
		super();
		this.name = name;
		this.date = date;
		this.city = city;
		this.asset_id = asset_id;
		this.dev_name = dev_name;
		this.oper_type = oper_type;
		this.content_before = content_before;
		this.content_after = content_after;
		this.ip = ip;
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
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the dev_name
	 */
	public String getDev_name() {
		return dev_name;
	}
	/**
	 * @param dev_name the dev_name to set
	 */
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	/**
	 * @return the oper_type
	 */
	public String getOper_type() {
		return oper_type;
	}
	/**
	 * @param oper_type the oper_type to set
	 */
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	/**
	 * @return the content_before
	 */
	public String getContent_before() {
		return content_before;
	}
	/**
	 * @param content_before the content_before to set
	 */
	public void setContent_before(String content_before) {
		this.content_before = content_before;
	}
	/**
	 * @return the content_after
	 */
	public String getContent_after() {
		return content_after;
	}
	/**
	 * @param content_after the content_after to set
	 */
	public void setContent_after(String content_after) {
		this.content_after = content_after;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
