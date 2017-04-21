package pro.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;

import pro.javabean.Count_change;
import pro.javabean.Count_condition;
import DB.DBManager;

public class Count_change_dao {
	Connection connection;
	public void Count_condition_dao(){
		
	}
	public void  startconn() {
		connection =new DBManager().getCon();
	}
	public ArrayList<Count_change> getlist(String date_start,String date_end) throws IOException
	{
		startconn();
		int id=1;
		Count_condition count=new Count_condition();
		String sqlString = " select * from asset_change where oper_type='改' and  date BETWEEN ? and ? and (content_after='局端' or content_after='用户端' or content_after='在线' or content_after='非在线' or content_after='转固' or content_after='在建' or content_after='设备完好' or content_after='损坏' )"; 
		PreparedStatement preparedStatement=null;
		ResultSet result=null;
		ArrayList<Count_change>chang_list=new ArrayList<Count_change>();
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1, date_start);
			preparedStatement.setString(2, date_end);
			result = preparedStatement.executeQuery();
			
			while (result.next()) {
				/*System.out.println(result.getString("sum(money)"));
				System.out.println(result.getString("count(asset_id)"));*/
				Count_change change=new Count_change();
				
				change.setDate(result.getString("date"));
				change.setAsset_id(result.getString("asset_id"));
				change.setContent_before(result.getString("content_before"));
				change.setContent_after(result.getString("content_after"));
				change.setId(id++);
				chang_list.add(change);
			}
			result.close();
			preparedStatement.close();
			connection.close();
			
			return chang_list;
			}catch(Exception e){
				System.out.println("获取改变统计数据失败");
				System.out.println(e.toString());
			}
		return chang_list;
		
	}
	public HashMap<String,Object> detail(int currentPage,String date_start,String date_end) throws IOException
	{
		startconn();
		int id=1;
		int gong=0;
//oper_type='改'
		HashMap<String,Object>map=new HashMap<String, Object>();
		Count_condition count=new Count_condition();
		String sqlString = " select * from asset_change where  oper_type='改' and date BETWEEN ? and ? and (content_after='局端' or content_after='用户端' or content_after='在线' or content_after='非在线' or content_after='转固' or content_after='在建' or content_after='设备完好' or content_after='损坏' ) limit ?,?;"; 
		PreparedStatement preparedStatement=null;
		ResultSet result=null;
		ArrayList<Count_change>chang_list=new ArrayList<Count_change>();
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1, date_start);
			preparedStatement.setString(2, date_end);
			preparedStatement.setInt(3,currentPage*10);
			preparedStatement.setInt(4,10);
			result = preparedStatement.executeQuery();
			
			while (result.next()) {
				/*System.out.println(result.getString("sum(money)"));
				System.out.println(result.getString("count(asset_id)"));*/
				Count_change change=new Count_change();
				
				change.setDate(result.getString("date"));
				change.setAsset_id(result.getString("asset_id"));
				change.setContent_before(result.getString("content_before"));
				change.setContent_after(result.getString("content_after"));
				change.setId(id++);
				chang_list.add(change);
			}
			sqlString = " select count(*) from asset_change where oper_type='改' and date BETWEEN ? and ? and (content_after='局端' or content_after='用户端' or content_after='在线' or content_after='非在线' or content_after='转固' or content_after='在建' or content_after='设备完好' or content_after='损坏' )";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,date_start);
			preparedStatement.setString(2,date_end);
			result=preparedStatement.executeQuery();
			result.next();

			System.out.print(result.getString(1));
			gong=Integer.valueOf(result.getString(1));
			gong=(int)Math.ceil((gong + 1.0 - 1.0 )/10);
			map.put("totalPage",gong);
			map.put("change_list", chang_list);
			result.close();
			preparedStatement.close();
			connection.close();
			
			return map;
			}catch(Exception e){
				System.out.println("获取改变统计数据失败");
				System.out.println(e.toString());
			}
		return map;
		
	}
		/**
	}
	 * @param args
		 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Count_change_dao c=new Count_change_dao();
		c.detail(0, "2014-01-01", "2017-01-01");
	}

}
