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
public class Sup_pro_basic_proinfo_dao {
	private ArrayList<Pro_info>Proinfo_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Sup_pro_basic_proinfo_dao() {
	}
	public HashMap<String,Object>getProinfo(int currentPage) throws SQLException//获取全部项目信息
	{
		this.db();
		
		Proinfo_list=new ArrayList<pro.javabean.Pro_info>();
		String sqlString = "select * from vpro_info  limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,currentPage*5);
			preparedStatement.setInt(2,5);
			result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {
				pro.javabean.Pro_info proj = new pro.javabean.Pro_info();
				Pro_type dev_type =new Pro_type();
//				dev_type.setPro_type("pro_type");
				dev_type.setPro_type(result.getString("pro_type"));
				dev_type.setPro_type_id(result.getString("pro_type_id"));
				proj.setPro_name((result.getString("pro_name")));
				proj.setPro_id((result.getString("pro_id")));
				proj.setDev_type(dev_type);//注意这里的set的参数不是String
				proj.setAproval_num((result.getString("aproval_num")));
				
				Proinfo_list.add(proj);
			}
			sqlString = "select count(*) from vpro_info;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		
			map.put("totalPage",count);
			map.put("Proinfo_list", Proinfo_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询项目信息表错误");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
			}
		
		
		
	}

	
	/*
	 * 获得和资产有关的项目信息：
	 */
	public  Pro_info get_asset_Proinfo(String asset_id) throws SQLException//获取全部项目信息
	{
		this.db();
		
		pro.javabean.Pro_info proj = new pro.javabean.Pro_info();
		String sqlString = "select * from asset_all  where asset_id1=?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,asset_id);
			
			result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {
				Pro_type dev_type =new Pro_type();
				dev_type.setPro_type("pro_type");
				dev_type.setPro_type(result.getString("pro_type"));
				dev_type.setPro_type_id(result.getString("pro_type_id"));
				proj.setPro_name((result.getString("pro_name")));
				proj.setPro_id((result.getString("pro_id")));
				proj.setDev_type(dev_type);//注意这里的set的参数不是String
				proj.setAproval_num((result.getString("aproval_num")));
			}
			result.close();
			preparedStatement.close();
			connection.close();
			return proj;
			}catch(Exception e){
				System.out.println("查询项目信息表错误");
				e.printStackTrace();
				result.close();
				preparedStatement.close();
				connection.close();
				return proj;
			}
		
		
		
	}
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
//	public Pro_info SearchProinfo(String id) //按项目编号查询项目信息
//	{
//		this.db();
//		Pro_info proinfo=new Pro_info();
//		
//		String sqlString = "select * from vpro_info where pro_id=? ";//应该是查视图的，暂时用表代替
//	
//		if("".equals(id)){
//			return proinfo;
//		}
//		try{
//			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,id);
//			ResultSet result = preparedStatement.executeQuery();
//			result.next();
//				
//				proinfo.setPro_id((result.getString("pro_id")));
//				proinfo.setDev_type((Pro_type)(result));
//				proinfo.setPro_name((result.getString("pro_name")));
//				proinfo.setAproval_num((result.getString("aproval_num")));
//				
//			result.close();
//			preparedStatement.close();
//			connection.close();
//			return proinfo;
//			}catch(Exception e){
//				System.out.println("查询项目信息错误");
//				System.out.println(e.toString());
//				return proinfo;
//			}
//		
//	}

	
	public HashMap<String,Object>SearchProinfo (int currentPage,String ids)//按项目编号查询
	{
		this.db();
		Proinfo_list=new ArrayList<Pro_info>();
		String sqlString = "select * from vpro_info where pro_id like ? limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+ids+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,5);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				pro.javabean.Pro_info proj = new pro.javabean.Pro_info();
				Pro_type dev_type =new Pro_type();
//				dev_type.setPro_type("pro_type");
				dev_type.setPro_type(result.getString("pro_type"));
				dev_type.setPro_type_id(result.getString("pro_type_id"));
				proj.setPro_name((result.getString("pro_name")));
				proj.setPro_id((result.getString("pro_id")));
				proj.setDev_type(dev_type);//注意这里的set的参数不是String
				proj.setAproval_num((result.getString("aproval_num")));
				
				Proinfo_list.add(proj);
			}
			sqlString = "select count(*) from vpro_info where pro_id like ?;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1, "%"+ids+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			map.put("totalPage",count);
			map.put("Proinfo_list", Proinfo_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询项目信息表错误");
				System.out.println(e.toString());
				return map;
			}
		
	}
	
	public boolean add(String pro_id,String pro_type_id,String pro_name,String aproval_num)//添加项目信息
	{
		this.db();
		Proinfo_list= new ArrayList<Pro_info>();
		boolean i=true;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			
				 String sqlString = "INSERT INTO pro_info (pro_id,pro_type_id,pro_name,aproval_num) VALUES (?,?,?,?);";//添加信息
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.execute("SET AUTOCOMMIT=0");
					preparedStatement.execute("begin");
					preparedStatement.setString(1,pro_id);
					preparedStatement.setString(2,pro_type_id);
				    preparedStatement.setString(3, pro_name);
				    preparedStatement.setString(4, aproval_num);
					if(preparedStatement.executeUpdate()>0)
						j++;
					
					
//					sqlString="INSERT INTO pro_type (pro_type_id,pro_type) VALUES (?,?);";//添加项目类型
//					preparedStatement = connection.prepareStatement(sqlString);
//					preparedStatement.setString(1,result2.getString("pro_type_id"));
//					preparedStatement.setString(2,dev_type);	
//					if(preparedStatement.executeUpdate()>0)
//						j++;
					
					if(j>=1){
					preparedStatement.execute("commit");//事务提交
				
					}
					else
					{
					preparedStatement.execute("ROLLBACK;");//回滚
					
					}
					
			}catch(Exception e){
				
				System.out.println("添加项目信息异常");
				System.out.println(e.toString());
				return i=false;
				
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
	public boolean revise(String pro_id,String pro_type_id,String pro_name,String aproval_num)//修改项目信息
	{
		this.db();
		Proinfo_list= new ArrayList<Pro_info>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
//			System.out.println(pro_id);
//			System.out.println(dev_type);
//			System.out.println(pro_name);
//			System.out.println(aproval_num);
			String sqlString = "update pro_info set pro_name=?, aproval_num=?, pro_type_id=? where pro_id=?;";//更新密码
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			preparedStatement.setString(1,pro_name);
			preparedStatement.setString(2,aproval_num);
			preparedStatement.setString(3,pro_type_id);
			preparedStatement.setString(4,pro_id);
			if(preparedStatement.executeUpdate()>0)
				j++;
//			sqlString="update pro_type set `pro_type`=? where pro_type_id=?;";//更新问题一和答案
//			preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1, dev_type.getPro_type());
//			preparedStatement.setString(2, dev_type.getPro_type_id());
//			if(preparedStatement.executeUpdate()>0)
//				j++;
			
			if(j>=1){
			preparedStatement.execute("commit");//事务提交
			
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//回滚
			
			}
			}catch(Exception e){
				System.out.println("修改项目信息异常");
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
	public ArrayList<Pro_type>getType()//获得所有项目类型
	{
		this.db();
		ArrayList<Pro_type> type_list=new ArrayList<Pro_type>();
		String sqlString = "select * from pro_type ";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Pro_type dev_type=new Pro_type();
				dev_type.setPro_type(result.getString("pro_type")); 
				dev_type.setPro_type_id(result.getString("pro_type_id"));
				
				type_list.add(dev_type);
			}
			return type_list;
		}
		catch(Exception e){
			System.out.println("获取项目类型异常");
			System.out.println(e.toString());
			return type_list;
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
	public int delete(String id)//删除用户
	{
		this.db();
        String sqlString = "DELETE from pro_info where pro_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1,id);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if(preparedStatement.executeUpdate()>0)
				j++;
			//删除项目信息的时候不用删除项目类型表里的内容
			if(j>=1){
				preparedStatement.execute("commit");//事务提交
			
			}
			else
			{
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

