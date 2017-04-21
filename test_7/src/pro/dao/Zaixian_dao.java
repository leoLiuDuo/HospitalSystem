package pro.dao;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import admin.Notice_admin.User;

import pro.javabean.*;

import DB.DBManager;
public class Zaixian_dao {
	
	private ArrayList<res_of_search> history_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public Zaixian_dao() {
	}
	
	
	
	public HashMap<String, Object> display(int currentPage)  {
		System.out.println("进入dao中：");
		this.db();//建立连接
		history_list=new ArrayList<res_of_search>();
		String sqlString;
	 	HashMap<String,Object>map=new HashMap<String, Object>();
	  
		int count=0;
		System.out.println("这里1：");
		sqlString = "SELECT * from res_of_search limit ?,?;";
			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setInt(1,currentPage*5);
				preparedStatement.setInt(2,5);
				ResultSet result = preparedStatement.executeQuery();
				int c=0;
				while (result.next()) {
					System.out.println("第"+(c+1)+"个");
					System.out.println("进入循环");
					res_of_search res=new  res_of_search();
					res.setSEARCH_ID(result.getInt("SEARCH_ID"));
					res.setLOGIN_ID(result.getString("LOGIN_ID"));
					if(result.getInt("SEARCH_SEX")==1)
				 	    res.setSEARCH_SEX("男");
					else if(result.getInt("SEARCH_SEX")==1)
						res.setSEARCH_SEX("女");
					else 
						res.setSEARCH_SEX("不限");
				    res.setAGE1(result.getInt("AGE1"));
				    res.setAGE2( result.getInt ("AGE2"));
				    res.setEXAM_CLASS(result.getString("EXAM_CLASS"));
				    res.setTEST_CLASS_NO1(result.getString("TEST_CLASS_NO1"));
				    res.setTEST_CALSS_NO2(result.getString("TEST_CALSS_NO2"));
				    res.setHOS_DEP_NO1_ID(result.getString("HOS_DEP_NO1_ID"));
				    res.setHOS_DEP_NO2_ID(result.getString("HOS_DEP_NO2_ID"));
	 			    boolean contain=false;
 				    for(int i=0;i<history_list.size();i++)
 				    {
 				    	System.out.println("进入for循环");
 				    	res_of_search r_list=history_list.get(i);
 				    	System.out.println("(r_list.getLOGIN_ID()="+ r_list.getLOGIN_ID());
 				    	System.out.println("(res.getLOGIN_ID()="+res.getLOGIN_ID());
 				    	if(r_list.getLOGIN_ID().equals(res.getLOGIN_ID())&&r_list.getSEARCH_SEX().equals(res.getSEARCH_SEX())&&r_list.getEXAM_CLASS().equals(res.getEXAM_CLASS())
 	 				    		&&r_list.getTEST_CLASS_NO1().equals(res.getTEST_CLASS_NO1())&&r_list.getTEST_CALSS_NO2().equals(res.getTEST_CALSS_NO2())&&r_list.getHOS_DEP_NO1_ID().equals(res.getHOS_DEP_NO1_ID())
 	 				    		&&r_list.getHOS_DEP_NO2_ID().equals(res.getHOS_DEP_NO2_ID()))
 				    		{
 				    		
 				    		    System.out.println("它们相等******************"+i+"****"+history_list.size());
 				    	 	    res_of_search r=history_list.get(i);
				    			 r.setSEARCH_DATE(result.getDate("SEARCH_DATE"));
				    			 history_list.remove(i);
				    			  history_list.add(i, r);
 				    	         System.out.println("日期大小为："+history_list.get(i).getSEARCH_DATE().size());
 				    	          contain=true;

 	 				    		    System.out.println("它们相等******************"+i+"****"+history_list.size());
 				    		     break;
 				    		}
 				    }
 			 	    if(!contain)
 				    {
 			 	    	
 				    System.out.println("日期："+result.getDate("SEARCH_DATE"));
 				    Date d=result.getDate("SEARCH_DATE");
 				   DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
 			        System.out.println(df2.format(d));
 				    res.setSEARCH_DATE(d);
 				    res.setNum(c++);
 					history_list.add(res);
 					System.out.println("**************8另成一家*************************大小为"+history_list.size());
  				    }
 		 				 }
				
				
				  System.out.println("循环结束，大小为："+history_list.size());			 
			 	sqlString="select count(*) from res_of_search";
			 	  preparedStatement = connection.prepareStatement(sqlString);
				  result = preparedStatement.executeQuery();
				   result.next();
				   count=Integer.valueOf(result.getString(1));
				   count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
				   map.put("totalPage", count);
				   map.put("history_list", history_list);
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
				}catch(Exception e){
					System.out.println("查询历史记录错误");
					System.out.println(e.toString());
					return map;
				}
		
	 
		
	}
	 
}
