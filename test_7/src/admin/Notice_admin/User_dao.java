package admin.Notice_admin;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.Flags.Flag;

import admin.Notice_admin.Record;
import DB.DBManager;
public class User_dao {
	private ArrayList<User>user_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public User_dao() {
	}
	
	public static void main(String args[]) throws SQLException {
		new User_dao().getUser(0, "");
	}
	public HashMap<String,Object>getUser(int currentPage,String city_id) throws SQLException//获取全部用户信息
	{
		this.db();
	
		user_list=new ArrayList<admin.Notice_admin.User>();
		String sqlString = "select * from person where city_id like ? limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			 preparedStatement = connection.prepareStatement(sqlString);
			 preparedStatement.setString(1,"%"+city_id+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,5);
			 result = preparedStatement.executeQuery();
			while (result.next()) {
				admin.Notice_admin.User user = new admin.Notice_admin.User();
				user.setName((result.getString("name")));
				user.setUser_id(result.getString("user_id"));
				user.setCity(result.getString("city_id"));
				user.setRole(result.getString("role"));
				user.setPassword(result.getString("password"));
				user_list.add(user);
			}
			sqlString = "select count(*) from person where city_id like ?";
			
			preparedStatement= connection.prepareStatement(sqlString);
			 preparedStatement.setString(1,"%"+city_id+"%");
			result=preparedStatement.executeQuery();
			result.last();
			if (result.getRow()>0) {
				count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			}
			else {
				count=0;
			}
		
		
			map.put("totalPage",count);
			map.put("userlist", user_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				e.printStackTrace();
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
		
	}

	public User SearchUser(String id)//查询个人用户
	{
		this.db();
		
		User user=new User();
		
		String sqlString = "select * from person_info where login_id=? ";
	
		if("".equals(id)){
			return user;
		}
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,id);
			ResultSet result = preparedStatement.executeQuery();
			result.last();
			if(result.getRow()==3){
				result.first();
				System.out.println("test");
				user.setName((result.getString("name")));
				user.setUser_id(result.getString("login_id"));
				//city为性别，role为职位
				user.setCity(result.getString("sex"));
				user.setRole(result.getString("position"));
				user.setQuestion1(result.getString("quetion"));
				System.out.println(result.getString("quetion"));
				result.next();
				user.setQuestion2(result.getString("quetion"));
				result.next();
				user.setQuestion3(result.getString("quetion"));
			}
			else {
				sqlString = "select * from doctor where login_id="+id;
				preparedStatement=connection.prepareStatement(sqlString);
				result = preparedStatement.executeQuery();
				result.next();
				user.setName((result.getString("name")));
				user.setUser_id(result.getString("login_id"));
				user.setCity(result.getString("sex"));
				user.setRole(result.getString("position"));
				System.out.println("test1");
			}
			result.close();
			preparedStatement.close();
			connection.close();
			return user;
			}catch(Exception e){
				System.out.println("查询个人用户错误");
				e.printStackTrace();
				return user;
			}
		
	}
	
	public boolean SearchUser_id(String user_id) throws SQLException//查询单个用户
	{
		this.db();
		user_list=new ArrayList<User>();
		String sqlString = "select * from person where user_id='"+user_id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			ResultSet result = preparedStatement.executeQuery();
			result.last();
			System.out.println(result.getRow());
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
			
			
		
	}
	
	
	
	public HashMap<String,Object>SearchUser(int currentPage,String id,String name,String city_id)//查询单个用户
	{
		this.db();
		user_list=new ArrayList<User>();
		String sqlString = "select * from person where user_id like ? and name like ? and city_id like ? limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		int count=0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+id+"%");
			preparedStatement.setString(2, "%"+name+"%");
			preparedStatement.setString(3,"%"+city_id+"%" );
			preparedStatement.setInt(4,currentPage*5);
			preparedStatement.setInt(5,5);
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next()) {
				
				User user = new User();
				user.setName((result.getString("name")));
				user.setUser_id(result.getString("user_id"));
				user.setCity(result.getString("city_id"));
				user.setRole(result.getString("role"));
				user.setPassword(result.getString("password"));
				
			
				user_list.add(user);
			}
			
			sqlString = "select count(*) from person where user_id like ? and name like ? and city_id like ?;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+id+"%");
			preparedStatement.setString(2, "%"+name+"%");
			preparedStatement.setString(3, "%"+city_id+"%");
			result=preparedStatement.executeQuery();
			result.last();
			if (result.getRow()>0) {
				count=Integer.valueOf(result.getString(1));
				System.out.println(result.getRow());
				System.out.println(count);
				count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			}
			else {
				count=0;
			}
			
			map.put("totalPage",count);
			map.put("userlist", user_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询用户表错误");
				e.printStackTrace();
				return map;
			}
		
	}
	public boolean add(String id,String name,String city,String role_id) throws SQLException//添加用户，无密保问题与答案
	{
		this.db();
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
				 String sqlString = "INSERT INTO user (user_id,name,city_id,role_id) VALUES (?,?,?,?);";//添加信息
					 preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.execute("SET AUTOCOMMIT=0");
					preparedStatement.execute("begin");
					preparedStatement.setString(1,id);
					preparedStatement.setString(2,name);
				    preparedStatement.setString(3, city);
				    preparedStatement.setString(4, role_id);
				   
					if(preparedStatement.executeUpdate()>0)
						j++;
					if(j>0){
					preparedStatement.execute("commit");//事务提交
				return true;
					}
					else
					{
					preparedStatement.execute("ROLLBACK;");//回滚
					return false;
					}
			}catch(Exception e){
				System.out.println("添加用户异常");
				System.out.println(e.toString());
				return false;
			}
		
		
	}
	public boolean revise(String password,String id ,String city_id,Answer answer)//修改用户
	{
		this.db();
		user_list= new ArrayList<User>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
			String sqlString = "update user set `password`=? city_id=? where user_id=?;";//更新密码
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			preparedStatement.setString(1,password);
			preparedStatement.setString(2,city_id);
			preparedStatement.setString(3,id);
			if(preparedStatement.executeUpdate()>0)
				j++;
			sqlString="update answer set `answer`=? where user_id=? and question_id=?;";//更新问题一和答案
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, answer.getAnswer1());
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, answer.getQuestion1());
			if(preparedStatement.executeUpdate()>0)
				j++;
			sqlString="update answer set `answer`=? where user_id=? and question_id=?;";//更新问题二和答案
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, answer.getAnswer2());
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, answer.getQuestion2());
			if(preparedStatement.executeUpdate()>0)
				j++;
			
			sqlString="update answer set `answer`=? where user_id=? and question_id=?;";//更新问题三和答案
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, answer.getAnswer3());
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, answer.getQuestion3());
			if(preparedStatement.executeUpdate()>0)
				j++;
			
			if(j>=4){
			preparedStatement.execute("commit");//事务提交
			
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//回滚
			
			}
			}catch(Exception e){
				System.out.println("修改用户异常");
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
	public boolean mod_user_info(String user_id,String city_id,String name,String re_user_id)//修改用户前查询
	{
		this.db();
		user_list=new ArrayList<User>();
		String sqlString = "UPDATE user set user_id=?,city_id=? ,name=? WHERE user_id=?";
		int result=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,re_user_id);
			preparedStatement.setString(2,city_id);
			preparedStatement.setString(3,name);
			preparedStatement.setString(4,user_id);
			 result+= preparedStatement.executeUpdate();
			if(result!=0){
				return true;
			}else {
				return false;
			}
			
		}
		catch(Exception e){
			System.out.println("修改用户异常");
			System.out.println(e.toString());
			return false;
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
	public boolean delete(String id) throws SQLException//删除用户
	{
		this.db();
        String sqlString = "DELETE from user where user_id=?;";
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,id);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if(preparedStatement.executeUpdate()>0)
				j++;
			if(j>0){
				preparedStatement.execute("commit");//事务提交
				preparedStatement.close();
				connection.close();
				return true;
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//回滚
			preparedStatement.close();
			connection.close();
			return false;
			}
			}catch(Exception e){
				System.out.println("删除用户异常");
				System.out.println(e.toString());
				preparedStatement.close();
				connection.close();
				return false;
			}
		finally {
			
				preparedStatement.close();
				connection.close();
			
		}
		
	}
	
}
