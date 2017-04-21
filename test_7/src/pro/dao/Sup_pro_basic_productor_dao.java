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
public class Sup_pro_basic_productor_dao {
	private ArrayList<Productor>Productor_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Sup_pro_basic_productor_dao() {
	}
	public HashMap<String,Object>getProductor(int currentPage) throws SQLException//获取全部项目信息
	{
		this.db();
	
		Productor_list=new ArrayList<pro.javabean.Productor>();
		String sqlString = "select * from productor limit ?,?;";
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
				pro.javabean.Productor productor = new pro.javabean.Productor();
			
				productor.setProd_id((result.getString("prod_id")));
				productor.setProd_name((result.getString("prod_name")));
				
				Productor_list.add(productor);
			}
			sqlString = "select count(*) from productor;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		
			map.put("totalPage",count);
			map.put("Productor_list", Productor_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询生产厂家表错误");
				System.out.println(e.toString());
				
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
		
	}

/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
	public HashMap<String,Object> SearchProductor(int currentPage,String name)//按生产厂家名字模糊查询
	{
		this.db();
		Productor_list=new ArrayList<Productor>();
		String sqlString = "select * from productor where prod_name like ? limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+name+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,5);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Productor productor = new Productor();
			
				productor.setProd_id((result.getString("prod_id")));
				productor.setProd_name((result.getString("prod_name")));
				
				Productor_list.add(productor);
			}
			sqlString = "select count(*) from productor where prod_name like ? ;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+name+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			map.put("totalPage",count);
			map.put("Productor_list", Productor_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询生产厂家表错误");
				System.out.println(e.toString());
				return map;
			}
	}

	public boolean add(String prod_name)//添加生产厂家信息
	{
		this.db();
		Productor_list= new ArrayList<Productor>();
		boolean i=false;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			
				 
				 String sqlString = "INSERT INTO productor (prod_name) VALUES (?);";//添加信息
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.execute("SET AUTOCOMMIT=0");
					preparedStatement.execute("begin");
//					preparedStatement.setString(1,prod_id);
				    preparedStatement.setString(1, prod_name);
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
				System.out.println("添加生产厂家信息异常");
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
	public boolean revise(String prod_id,String prod_name)//修改项目信息
	{
		this.db();
		Productor_list= new ArrayList<Productor>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
			String sqlString = "update productor set `prod_name`=? where prod_id=?;";//更新密码
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			preparedStatement.setString(1,prod_name);
			preparedStatement.setString(2,prod_id);
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
				System.out.println("修改生产厂家信息异常");
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
	public ArrayList<Productor>search_gai(String id)//修改用户前查询
	{
		this.db();
		Productor_list=new ArrayList<Productor>();//
		String sqlString = "select * from productor where prod_id=?";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,id);
			 result = preparedStatement.executeQuery();
			while (result.next()) {
				Productor productor = new Productor();
				
				productor.setProd_id((result.getString("prod_id")));
				productor.setProd_name((result.getString("prod_name")));
				
				Productor_list.add(productor);
			}
			return Productor_list;
		}
		catch(Exception e){
			System.out.println("修改生产厂家信息异常");
			System.out.println(e.toString());
			return Productor_list;
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
        String sqlString = "DELETE from productor where prod_id=?;";
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
				System.out.println("删除生产厂家信息异常");
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

