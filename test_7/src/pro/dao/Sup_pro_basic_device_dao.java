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
public class Sup_pro_basic_device_dao {
	private ArrayList<Device_info>Device_info_list=null;//修改
	Connection connection;
	public void db(){
		DBManager db=new DBManager();
		connection = db.getCon();//连接数据库
	}
	public Sup_pro_basic_device_dao() {
	}
	
	public  Device_info get_asset_device(String asset_id) throws SQLException//获取全部项目信息
	{
		this.db();
		pro.javabean.Device_info device_info = new pro.javabean.Device_info();
		
		String sqlString = "select * from asset_all  where asset_id1=?;";
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;
		int count=0;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,asset_id);
			
			result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {
				pro.javabean.Measure measure = new pro.javabean.Measure();                            //新建一个JAVABEAN  计量单位的对象
				measure.setMeas_name(result.getString("meas_name"));
				
               pro.javabean.Dev_type dev_type = new pro.javabean.Dev_type();                         //新建一个JAVABEAN  设备类型的对象
               dev_type.setDev_type(result.getString("dev_type"));
				
				pro.javabean.Productor productor = new pro.javabean.Productor();                      //新建一个JAVABEAN  生产厂家的对象
				productor.setProd_name(result.getString("prod_name"));
				
				              //新建一个JAVABEAN  设备表的对象
				device_info.setDev_mod((result.getString("dev_mod")));                                //设备型号  把数据库查询到的东西放到JAVABEAN里面
				device_info.setDev_name((result.getString("dev_name")));                              //设备名称
				device_info.setMeasure(measure);                                                      //计量单位对象  强制类型转换，另一个表
                device_info.setDev_type(dev_type);                                                    //设备类型另一个表
				device_info.setProductor(productor);                                                  //生产厂家，另一个表
			                       
				device_info.setMoney(result.getString("money"));                                      //金额
				
			}
			result.close();
			preparedStatement.close();
			connection.close();
			return device_info;
			}catch(Exception e){
				System.out.println("查询项目信息表错误");
				e.printStackTrace();
				result.close();
				preparedStatement.close();
				connection.close();
				return device_info;
			}
		
		
		
	}
	public HashMap<String,Object>getDevice_info(int currentPage) throws SQLException                  // 获取全部项目信息
	{
		this.db();
		Device_info_list=new ArrayList<pro.javabean.Device_info>();                                   //注意list在这里！！！！
		String sqlString = "select * from vdevice_info limit ?,?;";
		HashMap<String,Object>map=new HashMap<String, Object>();                                      //新建HASHMAP
		PreparedStatement preparedStatement=null;
		ResultSet result =null;                                                            
		int count=0;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,currentPage*5);
			preparedStatement.setInt(2,5);
			result = preparedStatement.executeQuery();//查询到记录的时候返回一个结果集
			while (result.next()) {
				pro.javabean.Device_info device_info = new pro.javabean.Device_info();  
				pro.javabean.Measure measure = new pro.javabean.Measure();                            //新建一个JAVABEAN  计量单位的对象
				measure.setMeas_name(result.getString("meas_name"));
				measure.setMeas_id(result.getString("meas_id"));
pro.javabean.Dev_type dev_type = new pro.javabean.Dev_type();                         //新建一个JAVABEAN  设备类型的对象
dev_type.setDev_type(result.getString("dev_type"));
dev_type.setDev_type_id(result.getString("dev_type_id"));
				pro.javabean.Productor productor = new pro.javabean.Productor();                      //新建一个JAVABEAN  生产厂家的对象
				productor.setProd_name(result.getString("prod_name"));
				productor.setProd_id(result.getString("prod_id"));
				              //新建一个JAVABEAN  设备表的对象
				device_info.setDev_mod((result.getString("dev_mod")));                                //设备型号  把数据库查询到的东西放到JAVABEAN里面
				device_info.setDev_name((result.getString("dev_name")));                              //设备名称
				device_info.setMeasure(measure);                                                      //计量单位对象  强制类型转换，另一个表
device_info.setDev_type(dev_type);                                                    //设备类型另一个表
				device_info.setProductor(productor);                                                  //生产厂家，另一个表
			                       
				device_info.setMoney(result.getString("money"));                                      //金额
				
				Device_info_list.add(device_info);                                                    //把（JAVABEAN）数据库的东西放到Device_info_list
			}
			sqlString = "select count(*) from vdevice_info;";
			preparedStatement= connection.prepareStatement(sqlString);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);//
		
			map.put("totalPage",count);
			map.put("Device_info_list", Device_info_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询设备信息表错误");
				e.printStackTrace();
				
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
		
	}

/*********************************************************************************************/
/*********************************************************************************************/
/**
 * @throws SQLException *******************************************************************************************/
//	public Device_info SearchDeviceinfo(String prod_id)//按生产厂家编号查询设备信息
//	{
//		this.db();
//		Device_info device_info=new Device_info();
//		
//		String sqlString = "select * from device_info where prod_id=? ";//应该是查视图的，暂时用表代替
//	
//		if("".equals(prod_id)){
//			return device_info;
//		}
//		try{
//			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,prod_id);
//			ResultSet result = preparedStatement.executeQuery();
//			result.next();
//				
//			device_info.setDev_mod((result.getString("dev_mod")));// 设备型号  可能要修改的地方三
//			device_info.setDev_name((result.getString("dev_name")));//设备名称
//			device_info.setMeasure((Measure)(result));//计量单位对象  强制类型转换
//			device_info.setDev_type((Dev_type)(result));//设备类型
//			device_info.setProductor((Productor)(result));//生产厂家
//			device_info.setAsset_type(result.getString("asset_type"));//资产类型
//				
//			result.close();
//			preparedStatement.close();
//			connection.close();
//			return device_info;
//			}catch(Exception e){
//				System.out.println("查询设备信息错误");
//				System.out.println(e.toString());
//				return device_info;
//			}
//		
//	}
	
	
	
	public HashMap<String,Object>SearchDevice_info2(int currentPage,String prod_name) throws SQLException            //按生产厂家prod_name
	{
		this.db();
		Device_info_list=new ArrayList<pro.javabean.Device_info>();        
		String sqlString =  "select * from vdevice_info where prod_name like ?  limit ?,?;";  //查询的SQL语句
		HashMap<String,Object>map=new HashMap<String, Object>();
		PreparedStatement preparedStatement=null;
		ResultSet result =null;	
		int count=0;		
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+prod_name+"%");
			preparedStatement.setInt(2,currentPage*5);
			preparedStatement.setInt(3,5);
			result=preparedStatement.executeQuery();
			
            while(result.next()) {
            	pro.javabean.Measure measure = new pro.javabean.Measure();                           //新建一个JAVABEAN  计量单位的对象
				measure.setMeas_name(result.getString("meas_name"));
				System.out.println(measure.getMeas_name());
				measure.setMeas_id(result.getString("meas_id"));
				pro.javabean.Dev_type dev_type= new pro.javabean.Dev_type();                         //新建一个JAVABEAN  设备类型的对象
				dev_type.setDev_type(result.getString("dev_type"));
				dev_type.setDev_type_id(result.getString("dev_type_id"));
				System.out.println(dev_type.getDev_type());
				
				pro.javabean.Productor productor = new pro.javabean.Productor();                      //新建一个JAVABEAN  生产厂家的对象
				productor.setProd_name(result.getString("prod_name"));
				productor.setProd_id(result.getString("prod_id"));
				System.out.println(productor.getProd_name());
				
				pro.javabean.Device_info device_info = new pro.javabean.Device_info();                //新建一个JAVABEAN  设备表的对象
				device_info.setDev_mod((result.getString("dev_mod")));                                //设备型号  把数据库查询到的东西放到JAVABEAN里面
				device_info.setDev_name((result.getString("dev_name")));                              //设备名称
				device_info.setMeasure(measure);                                                      //计量单位对象  强制类型转换，另一个表
				device_info.setDev_type(dev_type);                                                    //设备类型另一个表
				device_info.setProductor(productor);                                                  //生产厂家，另一个表
//				device_info.setAsset_type(result.getString("asset_type"));                            //资产类型
				device_info.setMoney(result.getString("money"));                                      //金额
				
				System.out.println(device_info.getAsset_type());
				System.out.println(device_info.getDev_mod());
				System.out.println(device_info.getDev_name());
				
				
				

				Device_info_list.add(device_info);                                                    //把（JAVABEAN）数据库的东西放到Device_info_list
			}
             
			sqlString = "select count(*) from vdevice_info where prod_name like ? ;";
			preparedStatement= connection.prepareStatement(sqlString);
			preparedStatement.setString(1,"%"+prod_name+"%");
//			preparedStatement.setString(2,devtype);
			result=preparedStatement.executeQuery();
			result.next();
			count=Integer.valueOf(result.getString(1));
			count=(int)Math.ceil((count + 1.0 - 1.0 )/5);
			System.out.println("==============="+count);
			map.put("totalPage",count);
			map.put("Device_info_list", Device_info_list);
			
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
			}catch(Exception e){
				System.out.println("查询设备信息错误");
				System.out.println(e.toString());
				return map;
			}
		finally{
			result.close();
			preparedStatement.close();
			connection.close();
		}
		
	}



	public boolean add(String dev_mod,String dev_name,String meas_id,String dev_type_id,String prod_id,String money)//添加设备信息
	{
		this.db();
		Device_info_list= new ArrayList<Device_info>();
		boolean i=false;
		int j=0;
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
//			     String sqlString = "select * from measure where meas_name=?;";                        //通过输入框里计量单位名字查找出整条计量单位信息
//			     preparedStatement = connection.prepareStatement(sqlString);
//			     preparedStatement.setString(1,meas_name);
//			     ResultSet result1 = preparedStatement.executeQuery();
//			     result1.next();
//			     System.out.println("dao层获取计量单位的id是"+result1.getString("meas_id"));
//			     
//			     sqlString = "select * from dev_type where dev_type=?;";                               //通过输入框里设备类型名字查找出整条设备类型信息
//			     preparedStatement = connection.prepareStatement(sqlString);
//			     preparedStatement.setString(1,dev_type);
//			     ResultSet result2 = preparedStatement.executeQuery();
//			     result2.next();
//			     System.out.println("dao层获取设备类型的id是"+result2.getString("dev_type_id"));
//			     
//			     sqlString = "select * from productor where prod_name=?;";                              //通过生产厂家名字查找出整条生产厂家信息
//			     preparedStatement = connection.prepareStatement(sqlString);
//			     preparedStatement.setString(1,prod_name);
//			     ResultSet result3 = preparedStatement.executeQuery();
//			     result3.next();
//			     System.out.println(result3.getString("prod_id"));
				 
				String sqlString = "INSERT INTO device_info(dev_mod,dev_name,meas_id,dev_type_id,prod_id,money) VALUES (?,?,?,?,?,?);";//添加信息
				 preparedStatement = connection.prepareStatement(sqlString);
				 preparedStatement.execute("SET AUTOCOMMIT=0");
				 preparedStatement.execute("begin");
				 preparedStatement.setString(1,dev_mod);
				 preparedStatement.setString(2, dev_name);
				 preparedStatement.setString(3,meas_id);
		         preparedStatement.setString(4,dev_type_id);
			     preparedStatement.setString(5,prod_id);
		    
				 preparedStatement.setString(6, money);
				    if(preparedStatement.executeUpdate()>0)
						j++;
					
//					sqlString="INSERT INTO measure (meas_id,meas_name) VALUES (?,?);";                     //添加计量单位
//					preparedStatement = connection.prepareStatement(sqlString);
//					preparedStatement.setString(1,result1.getString("meas_id"));
//					preparedStatement.setString(2,meas_name);			    
//					if(preparedStatement.executeUpdate()>0)
//						j++;
//					
//					sqlString="INSERT INTO dev_type (dev_type_id,dev_type) VALUES (?,?);";                 //添加设备类型
//					preparedStatement = connection.prepareStatement(sqlString);
//						preparedStatement.setString(1,result2.getString("dev_type_id"));
//						preparedStatement.setString(2,dev_type);
//					if(preparedStatement.executeUpdate()>0)
//							j++;
//				    
//					sqlString="INSERT INTO productor (prod_id,prod_name) VALUES (?,?);";                   //添加生产厂家
//					preparedStatement = connection.prepareStatement(sqlString);
//						preparedStatement.setString(1,result3.getString("prod_id"));
//						preparedStatement.setString(2,prod_name);
//					if(preparedStatement.executeUpdate()>0)
//							j++;
					
					
					if(j>=1){
					preparedStatement.execute("commit");//事务提交
				
					}
					else
					{
					preparedStatement.execute("ROLLBACK;");//回滚
					
					}
			}catch(Exception e){
				System.out.println("添加设备信息异常");
				System.out.println(e.toString());
				
			}
		
		return i;
	}
	
	
	public boolean revise(String dev_mod,String dev_name,String meas_id,String dev_type_id,String prod_id,String money)//修改设备信息
	{
		this.db();
		Device_info_list= new ArrayList<Device_info>();
        int j=0;
		boolean i=true;
		PreparedStatement preparedStatement=null;
		try{
//		     String sqlString = "select * from measure where meas_name=?;";                        //通过输入框里计量单位名字查找出整条计量单位信息
//		     preparedStatement = connection.prepareStatement(sqlString);
//		     preparedStatement.setString(1,meas_name);
//		     ResultSet result1 = preparedStatement.executeQuery();
//		     result1.next();
//		     System.out.println("dao层获取计量单位的id是"+result1.getString("meas_id"));
//		     
//		     sqlString = "select * from dev_type where dev_type=?;";                               //通过输入框里设备类型名字查找出整条设备类型信息
//		     preparedStatement = connection.prepareStatement(sqlString);
//		     preparedStatement.setString(1,dev_type);
//		     ResultSet result2 = preparedStatement.executeQuery();
//		     result2.next();
//		     System.out.println("dao层获取设备类型的id是"+result2.getString("dev_type_id"));
//		     
//		     sqlString = "select * from productor where prod_name=?;";                              //通过生产厂家名字查找出整条生产厂家信息
//		     preparedStatement = connection.prepareStatement(sqlString);
//		     preparedStatement.setString(1,prod_name);
//		     ResultSet result3 = preparedStatement.executeQuery();
//		     result3.next();
//		     System.out.println(result3.getString("prod_id"));
		     
			 String sqlString = "update device_info set dev_name=?,meas_id=?,dev_type_id=?,prod_id=?,money=? where dev_mod=?;";//更新信息表
			 preparedStatement = connection.prepareStatement(sqlString);
			 preparedStatement.execute("SET AUTOCOMMIT=0");
			 preparedStatement.execute("begin");
			 preparedStatement.setString(1,dev_name);
			 preparedStatement.setString(2,meas_id);
			 preparedStatement.setString(3,dev_type_id);
			 preparedStatement.setString(4,prod_id);
			
			 preparedStatement.setString(5,money);
			 preparedStatement.setString(6,dev_mod);
			 if(preparedStatement.executeUpdate()>0)
				j++;
			
			
//			sqlString="update measure set `meas_name`=? where meas_id=?;";//更新计量单位对象
//			preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,measure.getMeas_name());
//			preparedStatement.setString(2, measure.getMeas_id());
//			if(preparedStatement.executeUpdate()>0)
//				j++;
//			
//			sqlString="update dev_type set dev_type=? where dev_type_id=?;";//更新设备类型
//			preparedStatement = connection.prepareStatement(sqlString);
//			 preparedStatement.setString(1,dev_type.getDev_type());
//				preparedStatement.setString(2,dev_type.getDev_type_id());
//			if(preparedStatement.executeUpdate()>0)
//					j++;
//		    
//			sqlString="update productor set prod_name=? where prod_id=?;";//更新生产厂家
//			preparedStatement = connection.prepareStatement(sqlString);
//			 preparedStatement.setString(1,productor.getProd_name());
//				preparedStatement.setString(2,productor.getProd_id());
//			if(preparedStatement.executeUpdate()>0)
//					j++;
//			
			if(j>=1){
			preparedStatement.execute("commit");//事务提交
			
			}
			else
			{
			preparedStatement.execute("ROLLBACK;");//回滚
			
			}
			}catch(Exception e){
				System.out.println("修改设备信息异常");
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

//	public ArrayList<Device_info>search_gai(String dev_mod)        //修改用户前查询
//	{
//		this.db();
//		Device_info_list=new ArrayList<Device_info>();
//		String sqlString = "select * from vdevice_ where dev_mod=?";//主键是设备型号 Dev_mod
//		ResultSet result=null;
//		PreparedStatement preparedStatement=null;
//		try{
//			preparedStatement = connection.prepareStatement(sqlString);
//			preparedStatement.setString(1,dev_mod);
//			 result = preparedStatement.executeQuery();
//			while (result.next()) {
//				pro.javabean.Measure measure = new pro.javabean.Measure();                           //新建一个JAVABEAN  计量单位的对象
//				measure.setMeas_name(result.getString("meas_name"));
//				
//				pro.javabean.Dev_type dev_type= new pro.javabean.Dev_type();                         //新建一个JAVABEAN  设备类型的对象
//				dev_type.setDev_type(result.getString("dev_type"));
//				
//				pro.javabean.Productor productor = new pro.javabean.Productor();                      //新建一个JAVABEAN  生产厂家的对象
//				productor.setProd_name(result.getString("prod_name"));
//				
//				pro.javabean.Device_info device_info = new pro.javabean.Device_info();                //新建一个JAVABEAN  设备表的对象
//				device_info.setDev_mod((result.getString("dev_mod")));                                //设备型号  把数据库查询到的东西放到JAVABEAN里面
//				device_info.setDev_name((result.getString("dev_name")));                              //设备名称
//				device_info.setMeasure(measure);                                                      //计量单位对象  强制类型转换，另一个表
//				device_info.setDev_type(dev_type);                                                    //设备类型另一个表
//				device_info.setProductor(productor);                                                  //生产厂家，另一个表
//				device_info.setAsset_type(result.getString("asset_type"));                            //资产类型
//				device_info.setMoney(result.getString("money"));                                      //金额
//				
//				Device_info_list.add(device_info);
//			}
//			return Device_info_list;
//		}
//		catch(Exception e){
//			System.out.println("修改设备信息前查询异常");
//			System.out.println(e.toString());
//			return Device_info_list;
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
	
	public ArrayList<Dev_type>getDev_Type()//获得所有设备类型
	{
		this.db();
		ArrayList<Dev_type> dev_type_list=new ArrayList<Dev_type>();
		String sqlString = "select * from dev_type";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Dev_type dev_type=new Dev_type();
				dev_type.setDev_type(result.getString("dev_type")); 
				dev_type.setDev_type_id(result.getString("dev_type_id"));			
				dev_type_list.add(dev_type);
			}
			return dev_type_list;
		}
		catch(Exception e){
			System.out.println("获取项目类型异常");
			System.out.println(e.toString());
			return dev_type_list;
		}
		finally {
			try {
				result.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public ArrayList<Productor>getProductor()//获得所有生产厂家
	{
		this.db();
		ArrayList<Productor> productor_list=new ArrayList<Productor>();
		String sqlString = "select * from productor";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Productor productor=new Productor();
				productor.setProd_name(result.getString("prod_name")); 
				productor.setProd_id(result.getString("prod_id")); 			
				productor_list.add(productor);
			}
			return productor_list;
		}
		catch(Exception e){
			System.out.println("获取项目类型异常");
			System.out.println(e.toString());
			return productor_list;
		}
		finally {
			try {
				result.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public ArrayList<Measure>getMeasure()//获得所有计量单位
	{
		this.db();
		ArrayList<Measure> measure_list=new ArrayList<Measure>();
		String sqlString = "select * from measure";
		ResultSet result=null;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Measure measure=new Measure();
				measure.setMeas_name(result.getString("meas_name"));
				measure.setMeas_id(result.getString("meas_id"));		
				measure_list.add(measure);
			}
			return measure_list;
		}
		catch(Exception e){
			System.out.println("获取项目类型异常");
			System.out.println(e.toString());
			return measure_list;
		}
		finally {
			try {
				result.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public int delete(String dev_mod)//删除用户
	{
		this.db();
        String sqlString = "DELETE from device_info where dev_mod=?;";
		int i=-1;
		int j=0;
		PreparedStatement preparedStatement=null;
		try{
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,dev_mod);
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

