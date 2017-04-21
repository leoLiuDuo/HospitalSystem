package pro.dao;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import admin.Notice_admin.User;

import pro.javabean.*;

import DB.DBManager;
public class Liushi_dao {
	private ArrayList<Liushi>Liushi_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Liushi_dao() {
	}
	
	public HashMap<String,Object>Searchls (int currentPage,String city,String startTime,String endTime)//按条件查询
	{
		this.db();
		Liushi_list=new ArrayList<Liushi>();
		String sqlString;
		
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		if(city.equals("全省")){
			sqlString = "SELECT asset_all_change.asset_type AS asset_type,Count(asset_all_change.dev_appli_sta) AS liushi_num,all_asset_status.asset_status_sum AS asset_status_sum,((count(asset_all_change.dev_appli_sta) / all_asset_status.asset_status_sum)*100) AS liushi_rate " +
				"FROM ((asset_all_change) ,all_asset_status)	 " +
				"WHERE	(`asset_all_change`.`dev_appli_sta` = '设备丢失' and asset_all_change.date BETWEEN ? and ?) "+
				"GROUP BY `asset_all_change`.`asset_type` "+ 
				" limit ?,?;";
			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setString(1,startTime);
				preparedStatement.setString(2,endTime);
				preparedStatement.setInt(3,currentPage*5);
				preparedStatement.setInt(4,5);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					pro.javabean.Liushi ls = new pro.javabean.Liushi();
					
					ls.setAsset_type((result.getString("asset_type")));
					ls.setLiushi_num((result.getString("liushi_num")));
					ls.setAsset_status_sum((result.getString("asset_status_sum")));
					ls.setLiushi_rate((result.getString("liushi_rate")));
					
					Liushi_list.add(ls);
				}
				sqlString = "select count(*) from("+
						"SELECT asset_all_change.asset_type AS asset_type,Count(asset_all_change.dev_appli_sta) AS liushi_num,all_asset_status.asset_status_sum AS asset_status_sum,((count(asset_all_change.dev_appli_sta) / all_asset_status.asset_status_sum)*100) AS liushi_rate " +
						"FROM ((asset_all_change) ,all_asset_status) " +
						"WHERE	(`asset_all_change`.`dev_appli_sta` = '设备丢失' and asset_all_change.date BETWEEN ? and ?) " +
						"GROUP BY `asset_all_change`.`asset_type` "+
						")as mytalble";
				preparedStatement= connection.prepareStatement(sqlString);
				preparedStatement.setString(1,startTime);
				preparedStatement.setString(2,endTime);
				result=preparedStatement.executeQuery();
				result.next();
				count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
				map.put("totalPage",count);
				map.put("Liushi_list", Liushi_list);
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
				}catch(Exception e){
					System.out.println("查询流失资产报表错误");
					System.out.println(e.toString());
					return map;
				}
		
		}else{
			sqlString ="SELECT a.asset_type AS asset_type,Count(a.dev_appli_sta) AS liushi_num,b.asset_status_sum AS asset_status_sum,((count(a.dev_appli_sta) / b.asset_status_sum)*100) AS liushi_rate "+
			"FROM (asset_all_change a INNER JOIN allcity_asset_status b ON a.city=b.city ) "+
			"WHERE(a.content_after = '设备丢失' AND a.city = ? AND a.date BETWEEN ? AND ?) "+
			"GROUP BY `a`.`asset_type` "+
			"limit ?,?;";
			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setString(1,city);
				preparedStatement.setString(2,startTime);
				preparedStatement.setString(3,endTime);
				preparedStatement.setInt(4,currentPage*5);
				preparedStatement.setInt(5,5);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					pro.javabean.Liushi ls = new pro.javabean.Liushi();
					
					ls.setAsset_type((result.getString("asset_type")));
					ls.setLiushi_num((result.getString("liushi_num")));
					ls.setAsset_status_sum((result.getString("asset_status_sum")));
					ls.setLiushi_rate((result.getString("liushi_rate")));
					
					Liushi_list.add(ls);
				}
				sqlString = "select count(*) from("+
						"SELECT a.asset_type AS asset_type,Count(a.dev_appli_sta) AS liushi_num,b.asset_status_sum AS asset_status_sum,((count(a.dev_appli_sta) / b.asset_status_sum)*100) AS liushi_rate " +
						"FROM (asset_all_change a INNER JOIN allcity_asset_status b ON a.city=b.city ) "+
						"WHERE(a.content_after = '设备丢失' AND a.city = ? AND a.date BETWEEN ? AND ? ) "+
						"GROUP BY `a`.`asset_type`"+
						")as mytalble";
				preparedStatement= connection.prepareStatement(sqlString);
				preparedStatement.setString(1,city);
				preparedStatement.setString(2,startTime);
				preparedStatement.setString(3,endTime);
				result=preparedStatement.executeQuery();
				result.next();
				count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
				map.put("totalPage",count);
				map.put("Liushi_list", Liushi_list);
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
				}catch(Exception e){
					System.out.println("查询库存资产报表错误");
					System.out.println(e.toString());
					return map;
				}
		}
		
	}
	public ArrayList<City>getType()//获得所有项目类型
	{
		this.db();
		ArrayList<City> City_list=new ArrayList<City>();
		String sqlString = "select * from city ";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				City city=new City();
				city.setCity((result.getString("city")));
				city.setCity_id((result.getString("city_id")));
				
				City_list.add(city);
			}
			return City_list;
		}
		catch(Exception e){
			System.out.println("获取城市列表类型异常");
			System.out.println(e.toString());
			return City_list;
		}
		finally {
			try {
				result.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
}
