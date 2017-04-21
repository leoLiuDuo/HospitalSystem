package admin.Notice_admin;

import java.sql.Date;
import java.sql.Timestamp;

public class Record {
	private String date;
	private String asset_id;
	private String content_before;
	private String content_after;
	private String name;
	private String oper_type;
	private String ip;
	/**
	 * @return the date
	 */
	public Record (){
		
	}
	public Record(String date,String asset_id,String content_before,String content_after,String name,String oper_type,String ip){
		 this.date=date;
		 this.asset_id=asset_id;
		 this.content_before=content_before;
		 this.content_after=content_after;
		 this.name=name;
		 this.oper_type=oper_type;
		 this.ip=ip;
	}
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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

}
