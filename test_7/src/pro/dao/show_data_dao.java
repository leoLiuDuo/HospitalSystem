package pro.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Calendar;
import java.util.*;

import admin.Notice_admin.User;

import pro.javabean.*;

import DB.DBManager;
public class show_data_dao {
	private ArrayList<Pro_info>Proinfo_list=null;
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();
	}
	public show_data_dao() {
	}
/*****************************************************************************
获取患者数量，检查项数量、检验项数量
*****************************************************************************/
	public HashMap<String,Object>getData() throws SQLException
	{
		this.db();
		
		//Proinfo_list=new ArrayList<pro.javabean.Pro_info>();
		String sqlString = "";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			sqlString = "select count(*) from patient;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("totalPatients",count);
			
			sqlString = "select count(*) from exam_record;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("totalExamRecords",count);
			
			sqlString = "select count(*) from test_record;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("totalTestRecords",count);
			
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询错误");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
			}	
	}
	
/*****************************************************************************
获取按一年显示所需的每个月的患者数量
*****************************************************************************/	
	public static Calendar lastMonth(Calendar cal) {
		int year=cal.get(Calendar.YEAR);    
		int month=cal.get(Calendar.MONTH)-1;    
		int day=cal.get(Calendar.DATE);
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
		if (month == 2) {
			if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
		    	day = 29;
		    } else
		    	day = 28;
		    } else if ((month == 4 || month == 6 || month == 9 || month == 11)&& day == 31) {
		    	day = 30;
		    }
		}
		cal.set(year, month, day);
		return cal;
	}

	
	public HashMap<String,Object>getYear() throws SQLException
	{
		this.db();
		
		String sqlString = "";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;

		int y,m,d;
		String pdate="",tdate="";
		Calendar cal=Calendar.getInstance();
		
		try{
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			d=cal.get(Calendar.DATE);
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d)+" 23:59:59";
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m12",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			d=cal.get(Calendar.DATE);
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m11",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m10",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m9",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m8",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m7",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m6",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m5",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m4",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m3",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m2",count);
			
			cal=lastMonth(cal);
			y=cal.get(Calendar.YEAR);    
			m=cal.get(Calendar.MONTH)+1;
			pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-1"+" 00:00:00";
			if((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-31"+" 23:59:59";
			}else if(m==2){
				if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-29";
				}else{
					tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-28";
				}
			}else{
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-30";
			}
			sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,pdate);
			preparedStatement.setString(2,tdate);
			result = preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 ));
			map.put("m1",count);
			
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		}catch(Exception e){
				System.out.println("查询错误getYear()");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
		}
			
	}
	
/*****************************************************************************
获取按一月显示所需的每天的患者数量
*****************************************************************************/	
	public HashMap<String,Object>getMonth() throws SQLException
	{
		this.db();
		
		String sqlString = "";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;

		int y,m,d;
		String pdate="",tdate="";
		Calendar cal=Calendar.getInstance();
		
		try{
			
			
			int[] dnum;
			dnum=new int[30];
			for(int i=0;i<30;i++){
				y=cal.get(Calendar.YEAR);    
				m=cal.get(Calendar.MONTH)+1;
				d=cal.get(Calendar.DATE);
				pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d)+" 00:00:00";
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d)+" 23:59:59";
				sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
				preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setString(1,pdate);
				preparedStatement.setString(2,tdate);
				result = preparedStatement.executeQuery();
				result.next();
				count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 ));
				dnum[29-i]=count;
				cal.add(Calendar.DATE,-1);
			}
			map.put("dnum", dnum);
			
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		}catch(Exception e){
				System.out.println("查询错误getMonth()");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
		}
			
	}

/*****************************************************************************
获取按一周显示所需的每天的患者数量
*****************************************************************************/	
	public HashMap<String,Object>getWeek() throws SQLException
	{
		this.db();
		
		String sqlString = "";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;

		int y,m,d;
		String pdate="",tdate="";
		Calendar cal=Calendar.getInstance();
		
		try{
			
			int[] dnum;
			dnum=new int[7];
			for(int i=0;i<7;i++){
				y=cal.get(Calendar.YEAR);    
				m=cal.get(Calendar.MONTH)+1;
				d=cal.get(Calendar.DATE);
				pdate=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d)+" 00:00:00";
				tdate=String.valueOf(y)+"-"+String.valueOf(m)+"-"+String.valueOf(d)+" 23:59:59";
				sqlString=" SELECT count(DISTINCT PATIEN_ID) FROM hospital_record WHERE IN_HOSPITAL_DATE BETWEEN ? and ?";
				preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setString(1,pdate);
				preparedStatement.setString(2,tdate);
				result = preparedStatement.executeQuery();
				result.next();
				count=Integer.valueOf(result.getString(1));
				count=(int)Math.ceil((count + 1.0 - 1.0 ));
				dnum[6-i]=count;
				cal.add(Calendar.DATE,-1);
			}
			map.put("dnum", dnum);
			
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		}catch(Exception e){
				System.out.println("查询错误getWeek()");
				System.out.println(e.toString());
				result.close();
				preparedStatement.close();
				connection.close();
				return map;
		}
			
	}
	
}
