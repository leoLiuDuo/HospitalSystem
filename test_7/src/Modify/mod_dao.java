package Modify;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class mod_dao {
	Connection connection;
	public mod_dao(Connection conn){
		connection=conn;
	}
public ArrayList<String> question_search(String  user) {
		String sqlString="SELECT * from answer_mod where answer!='' and login_id=?";
	
		List<String> list=new ArrayList<String>();
		
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user);
			ResultSet result=preparedStatement.executeQuery();
			
			while (result.next()) {
				list.add(result.getString("quetion"));
			}
			result.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<String>) list;
}

public boolean search_judge(String user_id,String answer,String question) {
	try {
		String sqlString="SELECT * from answer_mod where login_id=? and answer=? and quetion=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlString);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, answer);
		preparedStatement.setString(3, question);
		ResultSet result=preparedStatement.executeQuery();
		result.last();
		if (result.getRow()!=0) {
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
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("验证问题异常");
		System.out.println();
		return false;
	}
}
public HashMap<String, Object> mod_password(String user_id,String password){
	HashMap<String, Object> hashMap = new HashMap<String, Object>();
	try {
		String sqlString="UPDATE doctor set login_pas=? WHERE login_id=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlString);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, user_id);
		int result=preparedStatement.executeUpdate();
		if (result==1) {
			String sql_roleidString="select * FROM doctor where login_id=? ";
			preparedStatement=connection.prepareStatement(sql_roleidString);
			preparedStatement.setString(1, user_id);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.last();
			hashMap.put("role_id", resultSet.getString("position"));
			hashMap.put("judge",true );
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
		else {
			hashMap.put("judge",false );
			preparedStatement.close();
			connection.close();
		}
		
		return hashMap;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("修改密码有问题");
		System.out.println();
		hashMap.put("judge",false );
		return hashMap;
	}
}
}
