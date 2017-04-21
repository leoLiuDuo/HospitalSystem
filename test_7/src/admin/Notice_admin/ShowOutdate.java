package admin.Notice_admin;

import java.security.Timestamp;

public class ShowOutdate {
      private String asset_id;//资产编号
      private String dev_name;//设备名称
      private String maint_end_time;//保修终止时间
      private String daiWei_unit;//代维单位名称
      private String postcode;//邮政编码
      private String unit_res;//单位负责人
      private String tel_day;//白天固定电话
      private String tel_night;//夜间固定电话
      private String mol_tel;//移动电话
      private String email;//Email地址
      private String address;//代维单位地址
      private String dai_controller;//代维主管单位
      private String prod_name;//生产产厂家
      
      public  ShowOutdate(){
	 
 }
    


	public ShowOutdate(String asset_id, String dev_name,
			String maint_end_time, String daiWei_unit, String postcode,
			String unit_res, String tel_day, String tel_night, String mol_tel,
			String email, String address, String dai_controller,
			String prod_name) {
		super();
		this.asset_id = asset_id;
		this.dev_name = dev_name;
		this.maint_end_time = maint_end_time;
		this.daiWei_unit = daiWei_unit;
		this.postcode = postcode;
		this.unit_res = unit_res;
		this.tel_day = tel_day;
		this.tel_night = tel_night;
		this.mol_tel = mol_tel;
		this.email = email;
		this.address = address;
		this.dai_controller = dai_controller;
		this.prod_name = prod_name;
	}


	public String getAsset_id() {
		return asset_id;
	}



	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}



	public String getDev_name() {
		return dev_name;
	}



	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}



	public String getMaint_end_time() {
		return maint_end_time;
	}



	public void setMaint_end_time(String maint_end_time) {
		this.maint_end_time = maint_end_time;
	}



	public String getDaiWei_unit() {
		return daiWei_unit;
	}



	public void setDaiWei_unit(String daiWei_unit) {
		this.daiWei_unit = daiWei_unit;
	}



	public String getPostcode() {
		return postcode;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public String getUnit_res() {
		return unit_res;
	}



	public void setUnit_res(String unit_res) {
		this.unit_res = unit_res;
	}



	public String getTel_day() {
		return tel_day;
	}



	public void setTel_day(String tel_day) {
		this.tel_day = tel_day;
	}



	public String getTel_night() {
		return tel_night;
	}



	public void setTel_night(String tel_night) {
		this.tel_night = tel_night;
	}



	public String getMol_tel() {
		return mol_tel;
	}



	public void setMol_tel(String mol_tel) {
		this.mol_tel = mol_tel;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDai_controller() {
		return dai_controller;
	}



	public void setDai_controller(String dai_controller) {
		this.dai_controller = dai_controller;
	}



	public String getProd_name() {
		return prod_name;
	}



	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}



	
}

