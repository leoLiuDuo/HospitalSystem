package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import pro.javabean.Valid_asset;
import DB.DBManager;

public class Valid_asset_dao {
	
	Connection connection=null;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	
	public Valid_asset getasset(String asset_id) throws SQLException//验证资产信息
	{
		Valid_asset valid_asset=new Valid_asset();
		this.db();
		String sqlString = "select * from asset_all where asset_id1=?";
		int asset_idint=Integer.parseInt(asset_id);
		
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		 preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,asset_idint);
			result = preparedStatement.executeQuery();
			result.next();
			if(result.getRow()>0){
				
				valid_asset.setAsset_id(result.getString("asset_id"));
				valid_asset.setCont_name(result.getString("cont_name"));
				valid_asset.setDaiwei_unit(result.getString("daiwei_unit"));
				valid_asset.setDev_name(result.getString("dev_name"));
				valid_asset.setName(result.getString("name"));
				valid_asset.setPro_name(result.getString("pro_name"));
			}
		return valid_asset;
		
	}
	
	}
