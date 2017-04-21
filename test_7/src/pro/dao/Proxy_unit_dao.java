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

import pro.javabean.Pro_info;
import pro.javabean.Pro_type;
import pro.javabean.Proxy_unit;
import pro.servlet.pro_sup_servlet;


import DB.DBManager;
public class Proxy_unit_dao 
{
	private static int currentPage;
	private ArrayList<Proxy_unit>proxy_list=null;
	Connection connection;
	public void db()
	{
		DBManager db=new DBManager();
		connection = db.getCon();
	}

	
	public  Proxy_unit get_asset_proxy_ubit(String asset_id) throws SQLException//获取全部项目信息
	{
		this.db();
		
		
		String sqlString = "select * from asset_all  where asset_id1=?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		pro.javabean.Proxy_unit proxy = new pro.javabean.Proxy_unit();
		int count=0;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,asset_id);
			
			result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {

				proxy.setDaiwei_id((result.getString("DaiWei_id")));	  
				proxy.setDaiwei_unit((result.getString("DaiWei_unit")));
				proxy.setUnit_res((result.getString("unit_res")));
				proxy.setTel_day((result.getString("tel_day")));
				proxy.setTel_night((result.getString("tel_night")));
				proxy.setMol_tel((result.getString("mol_tel")));
				proxy.setAddress((result.getString("address")));
				proxy.setEmail((result.getString("email")));
				proxy.setPostcode((result.getString("postcode")));
				proxy.setDai_controller((result.getString("dai_controller")));
				
			}
			result.close();
			preparedStatement.close();
			connection.close();
			return proxy;
			}catch(Exception e){
				System.out.println("查询项目信息表错误");
				e.printStackTrace();
				result.close();
				preparedStatement.close();
				connection.close();
				return proxy;
			}
		
		
		
	}
	public HashMap<String,Object>getProxy(int currentPage) throws SQLException//获取全部信息
	{
		this.db();
	
		proxy_list=new ArrayList<pro.javabean.Proxy_unit>();
		String sqlString = "select * from proxy_unit limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			 preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,currentPage*5);
			preparedStatement.setInt(2,5);
			 result = preparedStatement.executeQuery();
			while (result.next()) 
			{
				pro.javabean.Proxy_unit proxy = new pro.javabean.Proxy_unit();
		
				proxy.setDaiwei_id((result.getString("DaiWei_id")));	  
				proxy.setDaiwei_unit((result.getString("DaiWei_unit")));
				proxy.setUnit_res((result.getString("unit_res")));
				proxy.setTel_day((result.getString("tel_day")));
				proxy.setTel_night((result.getString("tel_night")));
				proxy.setMol_tel((result.getString("mol_tel")));
				proxy.setAddress((result.getString("address")));
				proxy.setEmail((result.getString("email")));
				proxy.setPostcode((result.getString("postcode")));
				proxy.setDai_controller((result.getString("dai_controller")));
				
				
				proxy_list.add(proxy);
			}
			sqlString = "select count(*) from proxy_unit;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		
			map.put("totalPage",count);
			map.put("proxylist", proxy_list);
		
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				e.printStackTrace();
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
			}
	
		
		
	}


	public HashMap<String,Object>SearchProxy(int currentPage, String daiwei_unit)//查询单个用户
	{
		this.db();
		proxy_list=new ArrayList<Proxy_unit>();
		String sqlString = "select * from proxy_unit where DaiWei_unit like ?  limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+daiwei_unit+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,5);
		
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				pro.javabean.Proxy_unit proxy = new pro.javabean.Proxy_unit();
				 

				proxy.setDaiwei_id((result.getString("daiWei_id")));
				proxy.setDaiwei_unit((result.getString("DaiWei_unit")));
				proxy.setUnit_res((result.getString("unit_res")));
				proxy.setTel_day((result.getString("tel_day")));
				proxy.setTel_night((result.getString("tel_night")));
				proxy.setMol_tel((result.getString("mol_tel")));
				proxy.setAddress((result.getString("address")));
				proxy.setEmail((result.getString("email")));
				proxy.setPostcode((result.getString("postcode")));
				proxy.setDai_controller((result.getString("dai_controller")));
				
				
				proxy_list.add(proxy);
			}
			
			sqlString = "select count(*) from proxy_unit where DaiWei_unit like ? ;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+daiwei_unit+"%");

			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			map.put("totalPage",count);
			map.put("proxylist", proxy_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				e.printStackTrace();
				return map;
			}
		
	}
	public boolean add(String DaiWei_unit,String unit_res,String tel_day,String tel_night,String mol_tel,String address,String email,String postcode,String dai_controller) throws SQLException//添加用户
	{
		this.db();
		proxy_list= new ArrayList<Proxy_unit>();
		boolean i=false;
		int j=0;
		
		PreparedStatement preparedStatement=null;
		try{
				 String sqlString = "INSERT INTO proxy_unit (DaiWei_unit,unit_res,tel_day,tel_night,mol_tel,address,email,postcode,dai_controller) VALUES (?,?,?,?,?,?,?,?,?);";//添加信息
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.execute("SET AUTOCOMMIT=0");
					preparedStatement.execute("begin");
					preparedStatement.setString(1,DaiWei_unit);
					preparedStatement.setString(2,unit_res);
				    preparedStatement.setString(3, tel_day);
				    preparedStatement.setString(4, tel_night);
				    preparedStatement.setString(5, mol_tel);
				    preparedStatement.setString(6, address);
				    preparedStatement.setString(7, email);
				    preparedStatement.setString(8, postcode);
				    preparedStatement.setString(9, dai_controller);
					if(preparedStatement.executeUpdate()>0)
						j++;
					if(j>=1){
					preparedStatement.execute("commit");//事务提交		
					}
					else
					{
					preparedStatement.execute("ROLLBACK;");//回滚
					}
			}catch(Exception e){
				System.out.println("添加代维信息异常");
				System.out.println(e.toString());
			}
		finally {
			try {

				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;
		
	}
	public boolean revise(String DaiWei_id,String DaiWei_unit,String unit_res,String tel_day,String tel_night,String mol_tel,String address,String Email,String postcode,String Dai_controller)//修改用户
	{
		this.db();
		proxy_list= new ArrayList<Proxy_unit>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
			String sqlString = "select * from proxy_unit where DaiWei_id=?;";
			System.out.println("查找类型表完成");
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,DaiWei_unit);
			ResultSet result2 = preparedStatement.executeQuery();
			result2.next();
			
		    sqlString = "update proxy_unit set DaiWei_unit=?,unit_res=?,tel_day=?,tel_night=?,mol_tel=?,address=?,Email=?,postcode=?,Dai_controller=? where DaiWei_id=?;";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
		
			preparedStatement.setString(1,DaiWei_unit);
			preparedStatement.setString(2,unit_res);
		    preparedStatement.setString(3, tel_day);
		    preparedStatement.setString(4, tel_night);
		    preparedStatement.setString(5, mol_tel);
		    preparedStatement.setString(6, address);
		    preparedStatement.setString(7, Email);
		    preparedStatement.setString(8, postcode);
		    preparedStatement.setString(9, Dai_controller);
		    preparedStatement.setString(10, DaiWei_id);
			if(preparedStatement.executeUpdate()>0)
				j++;			
			if(j>=1)
			{
			preparedStatement.execute("commit;");//事务提交			
			
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//回滚
			
			}
			}catch(Exception e){
				System.out.println("修改异常");
				System.out.println(e.toString());							
			}
		finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return i;
	}	
	public int delete(String daiWei_id)//删除用户
	{
		System.out.println(daiWei_id);
		this.db();
        String sqlString = "DELETE from proxy_unit where DaiWei_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,daiWei_id);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if(preparedStatement.executeUpdate()>0)
				j++;
			//删除项目信息的时候不用删除项目类型表里的内容
			if(j>=1){
				System.out.println("888888");
				preparedStatement.execute("commit");//事务提交
			
			}
			else
			{
				System.out.println("5555");
			preparedStatement.execute("ROLLBACK;");//回滚
			
			}
			}catch(Exception e){
				System.out.println("删除项目信息异常");
				System.out.println(e.toString());
				
			}
		finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return i;
	}
	
}