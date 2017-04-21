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
public class Sup_pro_basic_measure_dao {
	private ArrayList<Measure>Measure_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Sup_pro_basic_measure_dao() {
	}
	
	public HashMap<String,Object>getMeasure(int currentPage) throws SQLException//获取全部计量单位信息
	{
		this.db();
	
		Measure_list=new ArrayList<pro.javabean.Measure>();
		String sqlString = "select * from measure limit ?,?;";
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
				pro.javabean.Measure measure = new pro.javabean.Measure();
			
				measure.setMeas_id((result.getString("meas_id")));
				measure.setMeas_name((result.getString("meas_name")));
				
				Measure_list.add(measure);
			}
			sqlString = "select count(*) from measure;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
		
			map.put("totalPage",count);
			map.put("Measure_list", Measure_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询计量单位表错误");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
			}
		
			
		}
		
		
	

/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
	public HashMap<String,Object>SearchMeasure(int currentPage,String name)//按照计量单位名字查询
	{
		this.db();
		Measure_list=new ArrayList<pro.javabean.Measure>();
		String sqlString = "select * from measure where meas_name like ? limit ?,?;";//这里有几个问号就和下面的setInt的第一个参数相同
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+name+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,15);
			ResultSet result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {
				pro.javabean.Measure measure = new pro.javabean.Measure();
				measure.setMeas_name(result.getString("meas_name"));
			
				measure.setMeas_id(result.getString("meas_id"));
				
				
				Measure_list.add(measure);
			}
			sqlString = "select count(*) from measure where meas_name like ?;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+name+"%");
			result=preparedStatement.executeQuery();
			result.next();
   		    count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			map.put("totalPage",count);
			map.put("Measure_list", Measure_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询计量单位错误");
				System.out.println(e.toString());
				return map;
			}
		
	}
//    	public Measure SearchMeasure(String meas_name)//按计量单位名字查询计量单位
//	{
//		this.db();
//		Measure measure=new Measure();
//		
//		String sqlString = "select * from measure where meas_name=? ";//应该是查视图的，暂时用表代替
//	
//		if("".equals(meas_name)){
//			return measure;
//		}
//		try{
//			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,meas_name);
//			ResultSet result = preparedStatement.executeQuery();
//			result.next();
//				
//				measure.setMeas_id((result.getString("meas_id")));
//				measure.setMeas_name((result.getString("meas_name")));
//				
//			result.close();
//			preparedStatement.close();
//			connection.close();
//			return measure;
//			}catch(Exception e){
//				System.out.println("用名字查询计量单位信息错误");
//				System.out.println(e.toString());
//				return measure;
//			}
//		
//	}

	public boolean add(String meas_name)//添加计量单位信息
	{
		this.db();
		Measure_list= new ArrayList<Measure>();
		boolean i=false;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			
			
				 String sqlString = "INSERT INTO measure (meas_name) VALUES (?);";//添加计量单位信息
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.execute("SET AUTOCOMMIT=0");
					preparedStatement.execute("begin");
				    preparedStatement.setString(1, meas_name);
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
				System.out.println("添加计量单位信息异常");
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
	
	public boolean revise(String meas_id,String meas_name)//修改计量单位信息
	{
		this.db();
		Measure_list= new ArrayList<Measure>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
			String sqlString = "update  measure set meas_name=? where meas_id=?;";//更新计量单位
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			preparedStatement.setString(1,meas_name);
			preparedStatement.setString(2,meas_id);
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
				System.out.println("修改计量单位信息异常");
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
//	public ArrayList<Measure>search_gai(String id)//修改用户前查询
//	{
//		this.db();
//		Measure_list=new ArrayList<Measure>();//
//		String sqlString = "select * from measure where meas_id=?";
//		ResultSet result=null;
//		PreparedStatement preparedStatement=null;
//		try{
//			preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,id);
//			 result = preparedStatement.executeQuery();
//			while (result.next()) {
//				Measure measure = new Measure();
//				
//				measure.setMeas_id((result.getString("meas_id")));
//				measure.setMeas_name((result.getString("meas_name")));
//				
//				Measure_list.add(measure);
//			}
//			return Measure_list;
//		}
//		catch(Exception e){
//			System.out.println("修改计量单位信息异常");
//			System.out.println(e.toString());
//			return Measure_list;
//		}
//		finally {
//			try {
//				result.close();
//				preparedStatement.close();
//				connection.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	
//	}
	public int delete(String id)//删除用户
	{
		this.db();
        String sqlString = "DELETE from measure where meas_id=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
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

