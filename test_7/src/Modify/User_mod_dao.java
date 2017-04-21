package Modify;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import admin.Notice_admin.Answer;
import DB.DBManager;


public class User_mod_dao {
	Connection connection;
	public User_mod_dao(){
		
	}
public void startconn(){
	connection=new DBManager().getCon();
	}
//修改标识位
public boolean mark_search(String  user_id) {
	startconn();
		String sqlString="SELECT * from `doctor` WHERE `doctor`.mark_security=0 and login_id=?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user_id);
			ResultSet result=preparedStatement.executeQuery();
			result.last();
			int i=0;
			i=result.getRow();
			if(i>0){
				preparedStatement.close();
				connection.close();
				return true;
			}else {
				preparedStatement.close();
				connection.close();
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
}

//修改密保问题
public boolean mod_question(Answer answer,String user_id) throws SQLException {
	startconn();
	String sqlString1="INSERT into answer VALUES(?,?,?);";
			String sqlString2="INSERT into answer VALUES(?,?,?);";
					String sqlString3=	"INSERT into answer VALUES(?,?,?);";
	PreparedStatement preparedStatement=null;
	int result=0;
	preparedStatement=connection.prepareStatement(sqlString1);
	preparedStatement.setString(2,answer.getQuestion1());
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(3,answer.getAnswer1());
	result=result+preparedStatement.executeUpdate();
	preparedStatement.setString(2,answer.getQuestion2());
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(3,answer.getAnswer2());
	result+=preparedStatement.executeUpdate();
	preparedStatement.setString(2,answer.getQuestion3());
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(3,answer.getAnswer3());
	result+=preparedStatement.executeUpdate();
	preparedStatement.close();
	sqlString1="update doctor set mark_security=1 WHERE login_id=?";
	preparedStatement=connection.prepareStatement(sqlString1);
	preparedStatement.setString(1,user_id);
	result+=preparedStatement.executeUpdate();
	
	if(result==4){
		
		preparedStatement.close();
		connection.close();
		return true;
	}else {
		preparedStatement.close();
		connection.close();
		return false;
	}
	
}

//重置密保问题，包括删除，改变标志位

public boolean recover_question(String user_id) throws SQLException {
	startconn();
	String sqlString1="UPDATE `user` set  mark_security=0 WHERE user_id=?";
	PreparedStatement preparedStatement=null;
	preparedStatement=connection.prepareStatement(sqlString1);
	preparedStatement.setString(1, user_id);
	int result=0;
	result+=preparedStatement.executeUpdate();
	preparedStatement.close();
	sqlString1="DELETE FROM answer WHERE user_id=?";
	preparedStatement=connection.prepareStatement(sqlString1);
	preparedStatement.setString(1, user_id);
	result+=preparedStatement.executeUpdate();
	if(result==4){
		
		preparedStatement.close();
		connection.close();
		return true;
	}else {
		
		preparedStatement.close();
		connection.close();
		return false;
	}
	
}
//重置密码
public boolean recover_password(String user_id) throws SQLException {
	
	startconn();
	String sqlString1="UPDATE `user` set  `user`.`password`=123456 WHERE user_id=?";
	PreparedStatement preparedStatement=null;
	preparedStatement=connection.prepareStatement(sqlString1);
	preparedStatement.setString(1, user_id);
	int result=0;
	result+=preparedStatement.executeUpdate();
	if(result==1){
		
		preparedStatement.close();
		connection.close();
		return true;
	}else {
		
		preparedStatement.close();
		connection.close();
		return false;
	}
	
}

}
