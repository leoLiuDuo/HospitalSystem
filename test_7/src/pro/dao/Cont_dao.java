package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import pro.javabean.Contract;
import DB.DBManager;
 
public class Cont_dao {
	private ArrayList<Contract>cont_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Cont_dao() {
	}
	public HashMap<String,Object>getCont(int currentPage) throws SQLException//查询全部合同
	{
		this.db();
	
		cont_list=new ArrayList<Contract>();
		String sqlString = "select * from contract limit ?,?;";
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
				Contract cont = new Contract();
			    cont.setCont_id(result.getString("cont_id"));
			    cont.setCont_name(result.getString("cont_name"));
			    cont.setStart_date(result.getString("start_date"));
			    cont.setBuy_date(result.getString("buy_date"));
			 
			     cont_list.add(cont);
			}
			sqlString = "select count(*) from contract;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			 
		 
			map.put("totalPage",count);
			map.put("contlist", cont_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询用户表错误");
				System.out.println(e.toString());
				
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
		
	}

	public   HashMap<String,Object> Searchcont(String cont_name,int currentPage)//查询单个合同
	{
		this.db();
		int count=0;
		
		cont_list=new ArrayList<Contract>();
		String sqlString = "select * from contract where cont_name like ? limit ?,? ";
	    HashMap<String,Object> map=new HashMap<String,Object>();
	   
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		 
			preparedStatement.setString(1,"%"+cont_name+"%");
			 preparedStatement.setInt(2,currentPage*5);
			 preparedStatement.setInt(3,5);
			ResultSet result = preparedStatement.executeQuery();
			 
			while (result.next()) {
				Contract cont = new Contract();
			    cont.setCont_id(result.getString("cont_id"));
			    cont.setCont_name(result.getString("cont_name"));
			    cont.setStart_date(result.getString("start_date"));
			    cont.setBuy_date(result.getString("buy_date"));
			 
			     cont_list.add(cont);
			}
			sqlString = "select count(*) from contract where cont_name like ?;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+cont_name+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			 
		 
			map.put("totalPage",count);
			map.put("contlist", cont_list);
			result.close();
			preparedStatement.close();
			connection.close();
			 return map;
			}catch(Exception e){
				System.out.println("查询用户表错误");
				System.out.println(e.toString());
				 return map;
			}
		
	}
	public   boolean Searchcont_id(String cont_id)//查询单个合同
	{
		this.db();
		int count=0;
		String sqlString = "select * from contract where cont_id='"+cont_id+"'";
	   
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			ResultSet result = preparedStatement.executeQuery();
			result.last();
			if (result.getRow()>0) {
				result.close();
				preparedStatement.close();
				connection.close();
				return true;
			}
			else {
				result.close();
				preparedStatement.close();
				connection.close();
				return false;
			}
			}catch(Exception e){
				e.printStackTrace();
				 return true;
			}
		
	}
	public Contract getsinglecont(String asset_id)//查询单个合同编号
	{
		this.db();
		Contract cont=new Contract();
		String sqlString = "select * from asset_all where asset_id1 =? ";
	    HashMap<String,Object> map=new HashMap<String,Object>();
	   
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		 
			preparedStatement.setString(1,asset_id);
		 
			ResultSet result = preparedStatement.executeQuery();
			 
			result.next();
		 if(result.getRow()>0){
			 cont.setCont_name(result.getString("cont_name"));
			 
			    cont.setCont_id(result.getString("cont_id"));
			    cont.setStart_date(result.getString("start_date"));
			    cont.setBuy_date(result.getString("buy_date"));
			 
		 }
			    
 
			result.close();
			preparedStatement.close();
			connection.close();
			 return cont;
			}catch(Exception e){
				e.printStackTrace();
				 return cont;
			}
		
	}
	public void  add(String cont_id,String cont_name,String buy_date,String start_date)//合同添加
	{
		this.db();
		cont_list= new ArrayList<Contract>();
		int j=0;
		 
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
		 
                	String sqlString = "INSERT INTO contract (cont_id,cont_name,buy_date,start_date) VALUES (?,?,?,?);";
				    preparedStatement = connection.prepareStatement(sqlString);
				 
					preparedStatement.setString(1,cont_id);
				 
					preparedStatement.setString(2,cont_name);
					 
				    preparedStatement.setString(3,buy_date);
				     
				    preparedStatement.setString(4,start_date);
				 
				    if(preparedStatement.executeUpdate()>0)
				    	j++;
		 
				 if(j>0)
				 { preparedStatement.execute("commit");//提交 
				 
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

	public void revise(String cont_id,String cont_name,String buy_date,String start_date)//删除
	{
	 
		this.db();
		cont_list= new ArrayList<Contract>();
		int j=0;
	 
	 
		PreparedStatement preparedStatement=null;
		try{
		 
                	String sqlString =  "update contract set cont_name=?,buy_date=?, start_date=? where cont_id=?;";
				    preparedStatement = connection.prepareStatement(sqlString);
				  
					preparedStatement.setString(1,cont_name);
				 
					preparedStatement.setString(2,buy_date);
					 
				    preparedStatement.setString(3,start_date);
				  
				    preparedStatement.setString(4,cont_id);
				   
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
	 
	public void delete(String cont_id)//鍒犻櫎鐢ㄦ埛
	{
		this.db();
        String sqlString = "DELETE from contract  where cont_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,cont_id);
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
	

}
