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

 
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import DB.DBManager;

public class Sup_pro_basic_device_type_dao {
   private ArrayList<Dev_type> dev_type_list ;
   Connection connection;
   public void db(){
	   DBManager db=new DBManager();
	   connection=db.getCon();
   }
  public Sup_pro_basic_device_type_dao(){
  }
 
	public HashMap<String,Object>search_proall(int currentPage) throws SQLException//查询全部设备类型
	{
		this.db();
	
		dev_type_list=new ArrayList<Dev_type>();
		String sqlString = "select * from dev_type limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			 preparedStatement = connection.prepareStatement(sqlString);
			 preparedStatement.setInt(1,currentPage*5);
			 preparedStatement.setInt(2,5);
			 
			 result = preparedStatement.executeQuery();
			while (result.next()) {
				Dev_type dev = new Dev_type();
			    dev.setDev_type_id(result.getString("dev_type_id"));
			    dev.setDev_type(result.getString("dev_type"));
			     dev_type_list.add(dev);
			    
			}
			sqlString = "select count(*) from dev_type;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		 
		  
			map.put("totalPage",count);
			map.put("dev_type_list", dev_type_list);
			result.close();
			preparedStatement.close();
			connection.close();
		 
			 
			 
			return map;
			}catch(Exception e){
				System.out.println("查询设备类型表错误");
				System.out.println(e.toString());
		
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
		
	}

	public  HashMap<String, Object> Searchdev(String dev_type,int currentPage)//查询单个项目类型
	{
		this.db();
		Dev_type dev=new Dev_type();
		dev_type_list=new ArrayList<Dev_type>();
		String sqlString = "select * from dev_type where dev_type like ? limit ?,?;";
	    HashMap<String,Object> map=new HashMap<String,Object>();
	  int count=0;
	   
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		 
			 preparedStatement.setString(1,"%"+dev_type+"%");
			 preparedStatement.setInt(2,currentPage*5);
			 preparedStatement.setInt(3,5);
			ResultSet result = preparedStatement.executeQuery();
 			while (result.next()) {
				 dev = new Dev_type();
			    dev.setDev_type_id(result.getString("dev_type_id"));
			      dev.setDev_type(result.getString("dev_type"));
			     dev_type_list.add(dev);
         
			}
			sqlString = "select count(*) from dev_type where dev_type like ?;";
		 	preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+dev_type+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
	 		map.put("totalPage",count);
			map.put("dev_type_list", dev_type_list);
			result.close();
			preparedStatement.close();
			connection.close();
		 
 		    return map;
			}catch(Exception e){
				System.out.println("查询设备类型表错误");
				System.out.println(e.toString());
	 	
				return map;
			}
		 
		
		
	}


	
	public void  add(String dev_type)//合同添加
	{
		this.db();
		dev_type_list= new ArrayList<Dev_type>();
		int j=0;
		 
	   
	 
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
		 
              	String sqlString = "INSERT INTO dev_type (dev_type) VALUES (?);";
				    preparedStatement = connection.prepareStatement(sqlString);
				  
					preparedStatement.setString(1,dev_type);
					 
				 
				 
				    if(preparedStatement.executeUpdate()>0)
				    	j++;
				  
				 
				 if(j>0)
				 {  preparedStatement.execute("commit");//提交 
				     
				 }
			}catch(Exception e){
				System.out.println("添加数据不成功");
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
		 
	}

	public void revise(String dev_type_id,String dev_type)//修改设备类型
	{
		 
		this.db();
		dev_type_list= new ArrayList<Dev_type>();
		int j=0;
		 
	 
		PreparedStatement preparedStatement=null;
		try{
		 
              	String sqlString =  "update dev_type set dev_type =? where dev_type_id=?;";
				    preparedStatement = connection.prepareStatement(sqlString);
				  
					preparedStatement.setString(1, dev_type);
				 
					preparedStatement.setString(2, dev_type_id);
				 
				    if(preparedStatement.executeUpdate()>0)
				    	j++;
				   
				 if(j>0)
				 { preparedStatement.execute("commit");//提交 
				  
				 }
			}catch(Exception e){
				System.out.println("修改数据不成功");
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
	}
	
	public void delete(String dev_type_id)//删除设备类型
	{
		this.db();
        String sqlString = "DELETE from dev_type  where dev_type_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1,dev_type_id);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if(preparedStatement.executeUpdate()>0)
				j++;
		 
			if(j>0){
				preparedStatement.execute("commit");//浜嬪姟鎻愪氦
			 
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//鍥炴粴
			
			}
			}catch(Exception e){
				System.out.println("删除不成功");
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
	 
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBManager db = new DBManager();
		Sup_pro_basic_device_type_dao proxy = new Sup_pro_basic_device_type_dao();
		 proxy.search_proall(0);
	}

 
}


