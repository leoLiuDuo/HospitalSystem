package pro.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import pro.javabean.Count_condition;
import admin.Notice_admin.Record;
import DB.DBManager;
import Login.UserLogin;

public class Count_condition_dao {
	Connection connection;
	public void Count_condition_dao(){
		
	}
	public void  startconn() {
		connection =new DBManager().getCon();
	}
	public Count_condition getTotalNum(String cont_name,String pro_name,String daiwei_unit,String dev_type_id,String dev_appli_sta,
			String asset_attribute,String asset_status,String asset_use_status,String asset_acq_type){
		startconn();
		Count_condition count=new Count_condition();
		String sqlString = " select sum(money),count(asset_id) from asset_all WHERE (cont_name LIKE ? or cont_name is null) AND (pro_name LIKE ? or pro_name is null) AND (daiwei_unit LIKE ? or daiwei_unit is null)  AND (asset_id LIKE ? or asset_id is null) AND (dev_appli_sta LIKE ? or dev_appli_sta is null) AND (asset_attribute LIKE ? or asset_attribute is null) AND (asset_status LIKE ? or asset_status is null) AND (asset_use_status LIKE ? or asset_use_status is null) AND (asset_acq_type LIKE ? or asset_acq_type is null) ;";
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
			result = preparedStatement.executeQuery();
			while (result.next()) {
				/*System.out.println(result.getString("sum(money)"));
				System.out.println(result.getString("count(asset_id)"));*/
				
				count.setCity("全省");
				//if(Integer.valueOf(result.getString("count(asset_id)"))==0)
				if(result.getString("sum(money)")==null)
					count.setMoney(0);
					else
					count.setMoney(Double.parseDouble(result.getString("sum(money)")));
				count.setNum(Integer.valueOf(result.getString("count(asset_id)")));
			}
			result.close();
			preparedStatement.close();
			connection.close();
			
			return count;
			}catch(Exception e){
				e.printStackTrace();
			}
		return count;
	}
	public ArrayList<Count_condition> getCityNum(String cont_name,String pro_name,String daiwei_unit,String dev_type_id,String dev_appli_sta,
			String asset_attribute,String asset_status,String asset_use_status,String asset_acq_type){
		startconn();
		ArrayList<Count_condition> coun_list=new ArrayList<Count_condition>();
		System.out.print("234234");
		String sqlString ="SELECT * FROM	city LEFT JOIN ( SELECT city AS c,sum(money) AS s,count(asset_id) AS count FROM ((SELECT * FROM asset_all1 WHERE (cont_name LIKE ? or cont_name is null) AND (pro_name LIKE ? or pro_name is null) AND (daiwei_unit LIKE ? or daiwei_unit is null)  AND (asset_id LIKE ? or asset_id is null) AND (dev_appli_sta LIKE ? or dev_appli_sta is null) AND (asset_attribute LIKE ? or asset_attribute is null) AND (asset_status LIKE ? or asset_status is null) AND (asset_use_status LIKE ? or asset_use_status is null) AND (asset_acq_type LIKE ? or asset_acq_type is null)) AS asset ) GROUP BY(asset.city)) AS a ON city.city = a.c" ;
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
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Count_condition count=new Count_condition();
				count.setCity_id(result.getString("city_id"));
				count.setCity(result.getString("city"));
				if((result.getString("c")==null))
				{
					count.setMoney(0);
					count.setNum(0);
				}
				else{
				count.setMoney(Double.parseDouble(result.getString("s")));
				count.setNum(Integer.valueOf(result.getString("count")));}
				coun_list.add(count);
			}
			result.close();
			preparedStatement.close();
			connection.close();
			
			return coun_list;
			}catch(Exception e){
				System.out.println("获取各市数据失败");
				e.printStackTrace();
			}
		return coun_list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Count_condition_dao c=new Count_condition_dao();
		c.getTotalNum("%", "%","%","%","%","%","%","%","%");
		Count_condition coun=new Count_condition();
		c.getCityNum("%", "%","%","%","%","%","%","%","%");
		
		
	}

}
