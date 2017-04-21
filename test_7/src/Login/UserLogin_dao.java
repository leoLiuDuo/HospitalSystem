package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import DB.DBManager;

public class UserLogin_dao {
	Connection connection=null;
	
	public void  startconn() {
		connection =new DBManager().getCon();
	}
public HashMap<String ,Object> search(UserLogin user) throws SQLException {
	startconn();
	String sqlString="select * from doctor where login_id=? and login_pas=?";
	ResultSet result=null;
	PreparedStatement preparedStatement=null;
	HashMap<String , Object> map=new HashMap<String, Object>();
	try {
		preparedStatement= connection.prepareStatement(sqlString);
		preparedStatement.setString(1, user.getUser_id());
		preparedStatement.setString(2, user.getPassword());
		result=preparedStatement.executeQuery();
		result.last();
		if (result.getRow()==1) {
			map.put("position", result.getString("position"));
			map.put("sex", result.getString("sex"));
			map.put("judge", true);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		}
		else {
			map.put("judge", false);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		}
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return map;
}
public boolean mod_times(String user_id) throws SQLException {
	startconn();
	String sqlString="UPDATE user SET user.login_times=user.login_times+1 WHERE user_id=?";
	PreparedStatement preparedStatement=null;
	int result=0;
	preparedStatement=connection.prepareStatement(sqlString);
	preparedStatement.setString(1, user_id);
	result=preparedStatement.executeUpdate();
	if(result!=0){
		
		preparedStatement.close();
		connection.close();
		return true;
	}else {
		preparedStatement.close();
		connection.close();
		return false;
	}
	
}

public boolean mod_duration(String user_id,int interval) throws SQLException {
	startconn();
	String sqlString="UPDATE `user` SET `user`.duration=`user`.duration+? WHERE user_id=?";
	PreparedStatement preparedStatement=null;
	int result=0;
	preparedStatement=connection.prepareStatement(sqlString);
	preparedStatement.setString(2, user_id);
	preparedStatement.setInt(1, interval);
	result=preparedStatement.executeUpdate();
	if(result!=0){
		
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
