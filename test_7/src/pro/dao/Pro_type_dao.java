package pro.dao;

 
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import pro.javabean.Pro_type;
import DB.DBManager;

public class Pro_type_dao {
   private ArrayList<Pro_type> pro_type_list ;
   Connection connection;
   public void db(){
	   DBManager db=new DBManager();
	   connection=db.getCon();
   }
  public Pro_type_dao(){
  }
 
	public HashMap<String,Object>search_proall(int currentPage) throws SQLException//查询全部项目类型
	{
		this.db();
	
		pro_type_list=new ArrayList<Pro_type>();
		String sqlString = "select * from pro_type limit ?,?;";
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
				Pro_type pro = new Pro_type();
			    pro.setPro_type_id(result.getString("pro_type_id"));
			    pro.setPro_type(result.getString("pro_type"));
			     pro_type_list.add(pro);
			    
			}
			sqlString = "select count(*) from pro_type;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		 
		  
			map.put("totalPage",count);
			map.put("pro_type_list", pro_type_list);
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

public  HashMap<String, Object> Searchpro(String pro_type,int currentPage) throws SQLException//查询单个项目类型
	{
		this.db();
		pro_type_list=new ArrayList<Pro_type>();
		Pro_type pro=new Pro_type();
		int count=0;
		String sqlString = "select * from pro_type where pro_type like ? limit ?,? ";
	    HashMap<String,Object> map=new HashMap<String,Object>();
	    PreparedStatement preparedStatement=null;
	    ResultSet result=null;
		try{
		    preparedStatement = connection.prepareStatement(sqlString);
		 
			preparedStatement.setString(1,"%"+pro_type+"%");
			 preparedStatement.setInt(2,currentPage*5);
			 preparedStatement.setInt(3,5);
			 
		    result = preparedStatement.executeQuery();
		 
			while (result.next()) {
				 pro = new Pro_type();
			    pro.setPro_type_id(result.getString("pro_type_id"));
			    pro.setPro_type(result.getString("pro_type"));
			     pro_type_list.add(pro);
			    
			}
			sqlString = "select count(*) from pro_type where pro_type like ?;";
			
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+pro_type+"%");
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		 
		  
			map.put("totalPage",count);
			map.put("pro_type_list", pro_type_list);
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

	
	public void  add(String pro_type)//项目类型添加
	{
		this.db();
		pro_type_list= new ArrayList<Pro_type>();
		int j=0;
		 
	   
	 
	 
		PreparedStatement preparedStatement=null;
		try{
		 
              	String sqlString = "INSERT INTO pro_type (pro_type) VALUES (?);";
				    preparedStatement = connection.prepareStatement(sqlString);
				  
					preparedStatement.setString(1,pro_type);
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

	public void revise(String pro_type_id,String pro_type)//项目信息修改
	{
		 
		this.db();
		pro_type_list= new ArrayList<Pro_type>();
		int j=0;
		 
	 
		PreparedStatement preparedStatement=null;
		try{
		 
              	String sqlString =  "update pro_type set pro_type =? where pro_type_id=?;";
				    preparedStatement = connection.prepareStatement(sqlString);
				  
					preparedStatement.setString(1, pro_type);
				 
					preparedStatement.setString(2, pro_type_id);
				 
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
	
	public void delete(String pro_type_id)//项目类型删除
	{
		this.db();
        String sqlString = "DELETE from pro_type  where pro_type_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection
					.prepareStatement(sqlString);
			preparedStatement.setString(1,pro_type_id);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if(preparedStatement.executeUpdate()>0)
				j++;
		 
			if(j>0){
				preparedStatement.execute("commit");//如果执行成功就提交
			 
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//如果失败就退回
			
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
