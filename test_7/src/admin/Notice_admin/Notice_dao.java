package admin.Notice_admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import DB.DBManager;
import Login.UserLogin;

public class Notice_dao {
	Connection connection;
	
	public void  startconn() {
		connection =new DBManager().getCon();
	}
	//资产操作
	public HashMap<String,Object>getEdit(int currentPage,String city) throws SQLException//获取所有操作的信息
	{
		 startconn();
		ArrayList<ShowEdit> show_list=new ArrayList<ShowEdit>();
		String sqlString = "select * from showedit where city_id like ? limit ?,5;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		ResultSet result =null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+city+"%");
			preparedStatement.setInt(2,currentPage*5);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				ShowEdit showedit=new ShowEdit();
				showedit.setName((result.getString("name")));
				showedit.setDate(Timestamp.valueOf(result.getString("date")));
				showedit.setCity(result.getString("city"));
				showedit.setAsset_id((result.getString("asset_id")));
				showedit.setDev_name(result.getString("dev_name"));
				showedit.setOper_type(result.getString("oper_type"));
				showedit.setContent_before(result.getString("content_before"));
				showedit.setContent_after(result.getString("content_after"));
				showedit.setIp(result.getString("ip"));
				show_list.add(showedit);
			}
			System.out.println("city"+city);
			sqlString = "select count(*) from showedit where city_id like ?;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+city+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			map.put("totalPage",count);
			map.put("showlist", show_list);
			
			return map;
			}catch(Exception e){
				System.out.println("查询动态操作错误");
				System.out.println(e.toString());
				return map;
			}
		finally{
				result.close();
				preparedStatement.close();
				connection.close();
		}
		
	}
	
	//到期资产
	
	
	public HashMap<String,Object>getShowOutdate(int currenString) throws SQLException{
		startconn();
		int count=0;
		 ArrayList< ShowOutdate> ShowOutdate_list=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		 ShowOutdate_list=new ArrayList< ShowOutdate>();
		String sqlString = "SELECT * from showoutdate limit ?,5";
		try{
		preparedStatement = connection.prepareStatement(sqlString);
		preparedStatement.setInt(1, currenString);
			 result = preparedStatement.executeQuery();
			while (result.next()) {
				ShowOutdate ShowOutdate = new ShowOutdate();
			
				
				ShowOutdate.setAsset_id(result.getString("asset_id"));
				ShowOutdate.setDev_name(result.getString("dev_name"));
				ShowOutdate.setMaint_end_time(result.getString("maint_end_time"));
				ShowOutdate.setDaiWei_unit(result.getString("DaiWei_unit"));
				ShowOutdate.setPostcode(result.getString("postcode"));
				ShowOutdate.setUnit_res(result.getString("unit_res"));
				ShowOutdate.setTel_day(result.getString("tel_day"));
				ShowOutdate.setTel_night(result.getString("tel_night"));
				ShowOutdate.setMol_tel(result.getString("mol_tel"));
				ShowOutdate.setEmail(result.getString("Email"));
				ShowOutdate.setAddress(result.getString("address"));
				ShowOutdate.setDai_controller(result.getString("Dai_controller"));
				ShowOutdate.setProd_name(result.getString("prod_name"));
				ShowOutdate_list.add(ShowOutdate);
				
			}
			 sqlString = "SELECT count(*) from showoutdate";
			 result = preparedStatement.executeQuery(sqlString);
			 result.next();
			 count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			
				hashMap.put("totalPage",count);
			hashMap.put("ShowOutdate_list", ShowOutdate_list);
			hashMap.put("judge", true);
			result.close();
			preparedStatement.close();
			connection.close();
			return hashMap;
			}catch(Exception e){
				System.out.println("到期资产异常");
				System.out.println(e.toString());
				hashMap.put("ShowOutdate_list", ShowOutdate_list);
				hashMap.put("judge", false);
				result.close();
				preparedStatement.close();
				connection.close();
				return hashMap;
			}
		

	}
	//日志
	public HashMap<String, Object> getRecord(int currentPage){
	
		startconn();
		 ArrayList<Record> record_list=new ArrayList<Record>();
		String sqlString = " select * from record limit ?,5";
		HashMap<String, Object> map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setInt(1, currentPage);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Record record = new Record();
				record.setDate(result.getString("date"));
				record.setAsset_id(result.getString("asset_id"));
				record.setContent_before(result.getString("content_before"));
				record.setContent_after(result.getString("content_after"));
				record.setName(result.getString("name"));
				record.setOper_type(result.getString("oper_type"));
				record.setIp(result.getString("ip"));
				record_list.add(record);
			}
			sqlString = "select count(*) from record";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count = (int)Math.ceil((count + 1.0 - 1.0) / 5);
			map.put("totalPage", count);
			map.put("arrayList", record_list);
			result.close();
			preparedStatement.close();
			connection.close();
			
			return map;
			}catch(Exception e){
				System.out.println("日志信息查询异常");
				System.out.println(e.toString());
			}
		return map;
	}
	//登录排行
public HashMap<String, Object > search_list(int currentPage) {
	startconn();
	
	String sqlString="select * from rank_list  LIMIT ? ,5";
	ArrayList<Rank_list> arrayList=new ArrayList<Rank_list>();
	HashMap<String, Object> map=new HashMap<String, Object>();
	int count=0;
	try {
		PreparedStatement preparedStatement= connection.prepareStatement(sqlString);
		preparedStatement.setInt(1, currentPage*5);
		ResultSet result=preparedStatement.executeQuery();
		while (result.next()) {
			Rank_list rank_list=new Rank_list();
			rank_list.setName(result.getString("name"));
			rank_list.setLogin_times(Integer.valueOf(result.getString("login_times")));
			rank_list.setDuration(Integer.valueOf(result.getString("duration")));
			rank_list.setCity(result.getString("city"));
			arrayList.add(rank_list);
			
		}
		sqlString = "select count(*) from rank_list";
		preparedStatement= connection.prepareStatement(sqlString);
		result=preparedStatement.executeQuery();
		result.next();
		count=Integer.valueOf(result.getString(1));
		count = (int)Math.ceil((count + 1.0 - 1.0) / 5);
		map.put("totalPage", count);
		map.put("arrayList", arrayList);
		result.close();
		preparedStatement.close();
		connection.close();
		return map;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("查询排行榜异常");
		System.out.println();
		return map;
	}
	
	
}

public HashMap<String,Object>getShowOutdate(int currenString,String city_id) throws SQLException{
	startconn();
	int count=0;
	 ArrayList< ShowOutdate> ShowOutdate_list=null;
	PreparedStatement preparedStatement=null;
	ResultSet result=null;
	HashMap<String,Object> hashMap=new HashMap<String, Object>();
	 ShowOutdate_list=new ArrayList< ShowOutdate>();
	String sqlString = "SELECT * from showoutdate WHERE city_id=? limit ?,5";
	try{
	preparedStatement = connection.prepareStatement(sqlString);
	System.out.println("city_id"+city_id);
	preparedStatement.setString(1, city_id);
	preparedStatement.setInt(2, currenString);
		 result = preparedStatement.executeQuery();
		while (result.next()) {
			ShowOutdate ShowOutdate = new ShowOutdate();
		
			
			ShowOutdate.setAsset_id(result.getString("asset_id"));
			ShowOutdate.setDev_name(result.getString("dev_name"));
			ShowOutdate.setMaint_end_time(result.getString("maint_end_time"));
			ShowOutdate.setDaiWei_unit(result.getString("DaiWei_unit"));
			ShowOutdate.setPostcode(result.getString("postcode"));
			ShowOutdate.setUnit_res(result.getString("unit_res"));
			ShowOutdate.setTel_day(result.getString("tel_day"));
			ShowOutdate.setTel_night(result.getString("tel_night"));
			ShowOutdate.setMol_tel(result.getString("mol_tel"));
			ShowOutdate.setEmail(result.getString("Email"));
			ShowOutdate.setAddress(result.getString("address"));
			ShowOutdate.setDai_controller(result.getString("Dai_controller"));
			ShowOutdate.setProd_name(result.getString("prod_name"));
			ShowOutdate_list.add(ShowOutdate);
			
		}
		 sqlString = "SELECT count(*) from showoutdate WHERE city_id='"+city_id+"'";
		 result = preparedStatement.executeQuery(sqlString);
		 System.out.println(city_id);
		 result.next();
		 count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			hashMap.put("totalPage",count);
		hashMap.put("ShowOutdate_list", ShowOutdate_list);
		hashMap.put("judge", true);
		result.close();
		preparedStatement.close();
		connection.close();
		return hashMap;
		}catch(Exception e){
			System.out.println("到期资产异常");
			e.printStackTrace();
			hashMap.put("ShowOutdate_list", ShowOutdate_list);
			hashMap.put("judge", false);
			result.close();
			preparedStatement.close();
			connection.close();
			return hashMap;
		}
	

}

}
