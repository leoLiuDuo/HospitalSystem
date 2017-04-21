package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.DBManager;

import pro.javabean.DuoWeiCX;

public class DuoWeiCX_Dao {
	
	Connection connection=null;
	public void  startconn() {
		
		connection =new DBManager().getCon();
		System.out.println(connection);
	}
	
	public List<DuoWeiCX> getAll(){
		startconn();
		List<DuoWeiCX> duoWeiCXs=new ArrayList<DuoWeiCX>();
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		
		try {
			String sql="select * from duoweicx";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String yuyinname=resultSet.getString(1);
				int sex=resultSet.getInt(2);
				int age=resultSet.getInt(3);
				String relevant_clinic_diag=resultSet.getString(4);
				int hospital_id=resultSet.getInt(5);
				int hospital_time=resultSet.getInt(6);
				
				DuoWeiCX duoWeiCX=new DuoWeiCX(yuyinname, sex, relevant_clinic_diag, age, hospital_id, hospital_time);
			    duoWeiCXs.add(duoWeiCX);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(resultSet !=null){
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			try {
				if(preparedStatement !=null){
					preparedStatement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			try {
				if(connection !=null){
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return duoWeiCXs;
		
	}

}
