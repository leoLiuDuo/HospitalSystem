package pro.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import pro.javabean.Count_condition_summary;

import com.mysql.fabric.xmlrpc.base.Array;

import admin.Notice_admin.Record;
import DB.DBManager;
import Login.UserLogin;
public class summary_city_dao {
	Connection connection;
	public void Count_condition_summary_dao(){
		
	}
	public void  startconn() {
		connection =new DBManager().getCon();
	}
	public ArrayList<Count_condition_summary> getcity(String cont_name,String pro_name,String daiwei_unit,String dev_type_id,String dev_appli_sta,
			String asset_attribute,String asset_status,String asset_use_status,String asset_acq_type,String city){
				
		startconn();
		ArrayList<Count_condition_summary> coun_list=new ArrayList<Count_condition_summary>();
		
		String sqlString = "select asset_id,allo_info,dev_mod,DaiWei_unit,dev_appli_sta,DaiWei_cont_peri,name,maint_period,maint_end_time,money from asset_all1 WHERE  (cont_name LIKE ? or cont_name is null) AND (pro_name LIKE ? or pro_name is null) AND (daiwei_unit LIKE ? or daiwei_unit is null)  AND (asset_id LIKE ? or asset_id is null) AND (dev_appli_sta LIKE ? or dev_appli_sta is null) AND (asset_attribute LIKE ? or asset_attribute is null) AND (asset_status LIKE ? or asset_status is null) AND (asset_use_status LIKE ? or asset_use_status is null) AND (asset_acq_type LIKE ? or asset_acq_type is null) and city=?";
		PreparedStatement preparedStatement=null;
		ResultSet result=null;
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1, cont_name);
			preparedStatement.setString(2, pro_name);
			preparedStatement.setString(3, daiwei_unit);
			preparedStatement.setString(4, dev_type_id);
			preparedStatement.setString(5, dev_appli_sta);
			preparedStatement.setString(6, asset_attribute);
			preparedStatement.setString(7, asset_status);
			preparedStatement.setString(8, asset_use_status);
			preparedStatement.setString(9, asset_acq_type);
			preparedStatement.setString(10, city);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Count_condition_summary count=new Count_condition_summary();
				count.setAsset_id(result.getString("asset_id"));
				count.setAllo_info(result.getString("allo_info"));
				count.setDev_mod(result.getString("dev_mod"));
				count.setDaiwei_unit(result.getString("daiwei_unit"));
				count.setDev_appli_sta(result.getString("dev_appli_sta"));
				count.setDaiwei_cont_peri(result.getString("daiwei_cont_peri"));
				count.setName(result.getString("name"));
				count.setMaint_period(result.getString("maint_period"));
				count.setMaint_end_time(result.getString("maint_end_time"));
				count.setMoney(result.getString("money"));
				coun_list.add(count);
			}
			result.close();
			preparedStatement.close();
			connection.close();
			
			return coun_list;
			}catch(Exception e){
				System.out.println("获取市级数据失败");
				e.printStackTrace();
			}
		return coun_list;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
