package pro.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import pro.javabean.Assert_info;
import pro.javabean.Asset_change;
import pro.javabean.Contract;
import pro.javabean.Dev_type;
import pro.javabean.Device_info;
import pro.javabean.Measure;
import pro.javabean.Pro_info;
import pro.javabean.Pro_type;
import pro.javabean.Productor;
import pro.javabean.Proxy_unit;
import pro.javabean.Search_asset;
import admin.Notice_admin.Record;
import admin.Notice_admin.User;
import DB.DBManager;

public class Asset_info_dao {
	private ArrayList<Assert_info> Assert_info_list = null;
	Connection connection;

	public void db() {
		DBManager db = new DBManager();
		connection = db.getCon();
	}

	public Asset_info_dao() {
	}

	public HashMap<String, Object> getasset_all(int currentPage)
			throws SQLException// 省级用户获取全部用户信息
	{
		this.db();

		Assert_info_list = new ArrayList<Assert_info>();
		String sqlString = "select * from asset_all limit ?,?;";
		HashMap<String, Object> map = new HashMap<String, Object>();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, currentPage * 5);
			preparedStatement.setInt(2, 5);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Pro_info pro_info = new Pro_info();
				Assert_info assert_info = new Assert_info();
				Device_info device_info = new Device_info();
				Pro_type pro_type = new Pro_type();
				Contract contract = new Contract();
				Proxy_unit daiwei_unit = new Proxy_unit();
				User user = new User();
				Dev_type dev_type = new Dev_type();
				dev_type.setDev_type(result.getString("dev_type"));
				Measure measure = new Measure();
				Productor productor = new Productor();

				productor.setProd_id(result.getString("prod_id"));
				productor.setProd_name(result.getString("prod_name"));
				measure.setMeas_id(result.getString("meas_id"));
				measure.setMeas_name(result.getString("meas_name"));

				device_info.setAsset_type(result.getString("asset_type"));
				device_info.setDev_mod(result.getString("dev_mod"));
				device_info.setDev_name(result.getString("dev_name"));
				device_info.setMoney(result.getString("money"));
				device_info.setDev_type(dev_type);
				device_info.setMeasure(measure);
				device_info.setProductor(productor);
				contract.setBuy_date(result.getString("buy_date"));
				contract.setCont_id(result.getString("cont_id"));
				contract.setCont_name(result.getString("cont_name"));
				contract.setStart_date(result.getString("start_date"));
				daiwei_unit.setAddress(result.getString("address"));
				daiwei_unit.setDai_controller(result
						.getString("dai_controller"));
				daiwei_unit.setDaiwei_id(result.getString("daiWei_id"));
				daiwei_unit.setDaiwei_unit(result.getString("daiWei_unit"));
				daiwei_unit.setEmail(result.getString("email"));
				daiwei_unit.setMol_tel(result.getString("mol_tel"));
				daiwei_unit.setPostcode(result.getString("postcode"));
				daiwei_unit.setTel_day(result.getString("tel_day"));
				daiwei_unit.setTel_night(result.getString("tel_night"));
				daiwei_unit.setUnit_res(result.getString("unit_res"));
				pro_type.setPro_type(result.getString("pro_type"));
				pro_type.setPro_type_id(result.getString("pro_type_id"));
				user.setUser_id(result.getString("user_id"));
				user.setName(result.getString("name"));
				user.setCity(result.getString("city_id"));
				pro_info.setAproval_num(result.getString("aproval_num"));
				pro_info.setDev_type(pro_type);
				pro_info.setPro_id(result.getString("pro_id"));
				pro_info.setPro_name(result.getString("pro_name"));
				assert_info.setDevice_info(device_info);
				assert_info.setAllo_info(result.getString("allo_info"));
				assert_info.setAss_controller(result
						.getString("ass_controller"));
				assert_info.setAsset_acq_type(result
						.getString("asset_acq_type"));
				assert_info.setAsset_attribute(result
						.getString("asset_attribute"));
				assert_info.setAsset_id(result.getString("asset_id"));
				assert_info.setAsset_status(result.getString("asset_status"));
				assert_info.setAsset_use_status(result
						.getString("asset_use_status"));
				assert_info.setContract(contract);
				assert_info.setDaiWei_cont_peri(result
						.getString("daiWei_cont_peri"));
				assert_info.setDaiwei_unit(daiwei_unit);
				assert_info.setDev_appli_sta(result.getString("dev_appli_sta"));
				assert_info.setMaint_end_time(result
						.getString("maint_end_time"));
				assert_info.setMaint_period(result.getString("maint_period"));
				assert_info.setPro_info(pro_info);
				assert_info.setServ_end_time(result.getString("serv_end_time"));
				assert_info.setStorage_loca(result.getString("storage_loca"));
				assert_info.setUser(user);
				assert_info.setDev_type(result.getString("asset_type"));
				assert_info.setCity_id(result.getString("city_id"));
				assert_info.setAsset_id1(result.getString("asset_id1"));
				assert_info.setMoney(result.getString("money"));
				System.out.println(assert_info.getMoney());
				Assert_info_list.add(assert_info);
			}
			sqlString = "select count(*) from asset_all;";
			preparedStatement = connection.prepareStatement(sqlString);
			result = preparedStatement.executeQuery();
			result.next();
			count = Integer.valueOf(result.getString(1));
			count = (int) Math.ceil((count + 1.0 - 1.0) / 5);

			map.put("totalPage", count);
			map.put("assert_info_list", Assert_info_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();

			return map;
		} finally {
			result.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public HashMap<String, Object> getasset_all(int currentPage, String city_id)
			throws SQLException// 市级用户获取全部用户信息
	{
		this.db();

		Assert_info_list = new ArrayList<Assert_info>();
		String sqlString = "select * from asset_all WHERE city_id=?  limit ?,?";
		HashMap<String, Object> map = new HashMap<String, Object>();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, city_id);
			preparedStatement.setInt(2, currentPage * 5);
			preparedStatement.setInt(3, 5);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Pro_info pro_info = new Pro_info();
				Assert_info assert_info = new Assert_info();
				Device_info device_info = new Device_info();
				Pro_type pro_type = new Pro_type();
				Contract contract = new Contract();
				Proxy_unit daiwei_unit = new Proxy_unit();
				User user = new User();
				Dev_type dev_type = new Dev_type();
				Measure measure = new Measure();
				Productor productor = new Productor();

				productor.setProd_id(result.getString("prod_id"));
				productor.setProd_name(result.getString("prod_name"));
				measure.setMeas_id(result.getString("meas_id"));
				measure.setMeas_name(result.getString("meas_name"));

				device_info.setAsset_type(result.getString("asset_type"));
				device_info.setDev_mod(result.getString("dev_mod"));
				device_info.setDev_name(result.getString("dev_name"));
				device_info.setDev_type(dev_type);
				device_info.setMeasure(measure);
				device_info.setProductor(productor);
				contract.setBuy_date(result.getString("buy_date"));
				contract.setCont_id(result.getString("cont_id"));
				contract.setCont_name(result.getString("cont_name"));
				contract.setStart_date(result.getString("start_date"));
				daiwei_unit.setAddress(result.getString("address"));
				daiwei_unit.setDai_controller(result
						.getString("dai_controller"));
				daiwei_unit.setDaiwei_id(result.getString("daiWei_id"));
				daiwei_unit.setDaiwei_unit(result.getString("daiWei_unit"));
				daiwei_unit.setEmail(result.getString("email"));
				daiwei_unit.setMol_tel(result.getString("mol_tel"));
				daiwei_unit.setPostcode(result.getString("postcode"));
				daiwei_unit.setTel_day(result.getString("tel_day"));
				daiwei_unit.setTel_night(result.getString("tel_night"));
				daiwei_unit.setUnit_res(result.getString("unit_res"));
				pro_type.setPro_type(result.getString("pro_type"));
				pro_type.setPro_type_id(result.getString("pro_type_id"));
				user.setUser_id(result.getString("user_id"));
				user.setName(result.getString("name"));
				user.setCity(result.getString("city_id"));
				pro_info.setAproval_num(result.getString("aproval_num"));
				pro_info.setDev_type(pro_type);
				pro_info.setPro_id(result.getString("pro_id"));
				pro_info.setPro_name(result.getString("pro_name"));
				assert_info.setDevice_info(device_info);
				assert_info.setAllo_info(result.getString("allo_info"));
				assert_info.setAss_controller(result
						.getString("ass_controller"));
				assert_info.setAsset_acq_type(result
						.getString("asset_acq_type"));
				assert_info.setAsset_attribute(result
						.getString("asset_attribute"));
				assert_info.setAsset_id(result.getString("asset_id"));
				assert_info.setAsset_status(result.getString("asset_status"));
				assert_info.setAsset_use_status(result
						.getString("asset_use_status"));
				assert_info.setContract(contract);
				assert_info.setDaiWei_cont_peri(result
						.getString("daiWei_cont_peri"));
				assert_info.setDaiwei_unit(daiwei_unit);
				assert_info.setDev_appli_sta(result.getString("dev_appli_sta"));
				assert_info.setMaint_end_time(result
						.getString("maint_end_time"));
				assert_info.setMaint_period(result.getString("maint_period"));
				assert_info.setPro_info(pro_info);
				assert_info.setServ_end_time(result.getString("serv_end_time"));
				assert_info.setStorage_loca(result.getString("storage_loca"));
				assert_info.setUser(user);
				assert_info.setDev_type(result.getString("asset_type"));
				assert_info.setCity_id(result.getString("city_id"));
				assert_info.setAsset_id1(result.getString("asset_id1"));
				assert_info.setMoney(result.getString("money"));
				Assert_info_list.add(assert_info);
			}
			sqlString = "select count(*) from asset_all where city_id=?;";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, city_id);
			result = preparedStatement.executeQuery();
			result.next();
			count = Integer.valueOf(result.getString(1));
			count = (int) Math.ceil((count + 1.0 - 1.0) / 5);
			System.out.println(count);
			map.put("totalPage", count);
			map.put("assert_info_list", Assert_info_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();

			return map;
		} finally {
			result.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public HashMap<String, Object> getasset_all_city(int currentPage,
			String role_id) throws SQLException// 实际用户获取全部用户信息
	{
		this.db();

		Assert_info_list = new ArrayList<Assert_info>();
		String sqlString = "select * from asset_all where role_id=? limit ?,?;";
		HashMap<String, Object> map = new HashMap<String, Object>();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, role_id);
			preparedStatement.setInt(2, currentPage * 5);
			preparedStatement.setInt(3, 5);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Pro_info pro_info = new Pro_info();
				Assert_info assert_info = new Assert_info();
				Device_info device_info = new Device_info();
				Pro_type pro_type = new Pro_type();
				Contract contract = new Contract();
				Proxy_unit daiwei_unit = new Proxy_unit();
				User user = new User();
				Dev_type dev_type = new Dev_type();
				Measure measure = new Measure();
				Productor productor = new Productor();

				productor.setProd_id(result.getString("prod_id"));
				productor.setProd_name(result.getString("prod_name"));
				measure.setMeas_id(result.getString("meas_id"));
				measure.setMeas_name(result.getString("meas_name"));
				dev_type.setDev_type(result.getString("dev_type"));
				device_info.setAsset_type(result.getString("asset_type"));
				device_info.setDev_mod(result.getString("dev_mod"));
				device_info.setDev_name(result.getString("dev_name"));
				device_info.setDev_type(dev_type);
				device_info.setMeasure(measure);
				device_info.setProductor(productor);
				contract.setBuy_date(result.getString("buy_date"));
				contract.setCont_id(result.getString("cont_id"));
				contract.setCont_name(result.getString("cont_name"));
				contract.setStart_date(result.getString("start_date"));
				daiwei_unit.setAddress(result.getString("address"));
				daiwei_unit.setDai_controller(result
						.getString("dai_controller"));
				daiwei_unit.setDaiwei_id(result.getString("daiWei_id"));
				daiwei_unit.setDaiwei_unit(result.getString("daiWei_unit"));
				daiwei_unit.setEmail(result.getString("email"));
				daiwei_unit.setMol_tel(result.getString("mol_tel"));
				daiwei_unit.setPostcode(result.getString("postcode"));
				daiwei_unit.setTel_day(result.getString("tel_day"));
				daiwei_unit.setTel_night(result.getString("tel_night"));
				daiwei_unit.setUnit_res(result.getString("unit_res"));
				pro_type.setPro_type(result.getString("pro_type"));
				pro_type.setPro_type_id(result.getString("pro_type_id"));
				user.setUser_id(result.getString("user_id"));
				user.setName(result.getString("name"));
				user.setCity(result.getString("city_id"));
				pro_info.setAproval_num(result.getString("aproval_num"));
				pro_info.setDev_type(pro_type);
				pro_info.setPro_id(result.getString("pro_id"));
				pro_info.setPro_name(result.getString("pro_name"));
				assert_info.setDevice_info(device_info);
				assert_info.setAllo_info(result.getString("allo_info"));
				assert_info.setAss_controller(result
						.getString("ass_controller"));
				assert_info.setAsset_acq_type(result
						.getString("asset_acq_type"));
				assert_info.setAsset_attribute(result
						.getString("asset_attribute"));
				assert_info.setAsset_id(result.getString("asset_id"));
				assert_info.setAsset_status(result.getString("asset_status"));
				assert_info.setAsset_use_status(result
						.getString("asset_use_status"));
				assert_info.setContract(contract);
				assert_info.setDaiWei_cont_peri(result
						.getString("daiWei_cont_peri"));
				assert_info.setDaiwei_unit(daiwei_unit);
				assert_info.setDev_appli_sta(result.getString("dev_appli_sta"));
				assert_info.setMaint_end_time(result
						.getString("maint_end_time"));
				assert_info.setMaint_period(result.getString("maint_period"));
				assert_info.setPro_info(pro_info);
				assert_info.setServ_end_time(result.getString("serv_end_time"));
				assert_info.setStorage_loca(result.getString("storage_loca"));
				assert_info.setMoney(result.getString("money"));
				assert_info.setUser(user);
				Assert_info_list.add(assert_info);
			}
			sqlString = "select count(*) from asset_all where role_id=?;";

			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, role_id);
			result = preparedStatement.executeQuery();
			result.next();
			count = Integer.valueOf(result.getString(1));
			count = (int) Math.ceil((count + 1.0 - 1.0) / 5);

			map.put("totalPage", count);
			map.put("assert_info_list", Assert_info_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		} finally {
			result.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public User get_user(String asset_id) throws SQLException {// 获得资产的所有人
		this.db();
		User user = new User();
		String sqlString = "SELECT role ,name,user_id,city_id from asset_all WHERE asset_id1=?";
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		preparedStatement = connection.prepareStatement(sqlString);
		preparedStatement.setString(1, asset_id);

		result = preparedStatement.executeQuery();
		result.next();
		if (result.getRow() > 0) {
			user.setName(result.getString("name"));
			user.setCity(result.getString("city_id"));
			user.setRole(result.getString("role"));
			user.setUser_id(result.getString("user_id"));
		}

		return user;
	}

	public Assert_info get_others(String asset_id) throws SQLException {// 获得资产其他属性
		this.db();
		Assert_info assert_info = new Assert_info();

		String sqlString = "SELECT * from asset_info where asset_id=?";
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		preparedStatement = connection.prepareStatement(sqlString);
		preparedStatement.setString(1, asset_id);
		result = preparedStatement.executeQuery();
		result.next();
		assert_info.setAllo_info(result.getString("allo_info"));
		assert_info.setAss_controller(result.getString("ass_controller"));
		assert_info.setAsset_acq_type(result.getString("asset_acq_type"));
		assert_info.setAsset_attribute(result.getString("asset_attribute"));
		assert_info.setAsset_status(result.getString("asset_status"));
		assert_info.setAsset_use_status(result.getString("asset_use_status"));
		assert_info.setDaiWei_cont_peri(result.getString("daiWei_cont_peri"));
		assert_info.setDev_appli_sta(result.getString("dev_appli_sta"));
		assert_info.setMaint_end_time(result.getString("maint_end_time"));
		assert_info.setMaint_period(result.getString("maint_period"));
		assert_info.setServ_end_time(result.getString("serv_end_time"));
		assert_info.setStorage_loca(result.getString("storage_loca"));
		assert_info.setAsset_id(result.getString("asset_id"));
		assert_info.setDev_type(result.getString("asset_type"));
		assert_info.setCity_id(result.getString("city_id"));
		return assert_info;
	}

	public HashMap<String, Object> getasset_search(int currentPage,
			Search_asset search_asset) throws SQLException// 获取搜索条件得用户信息
	{
		this.db();
		Assert_info_list = new ArrayList<Assert_info>();
		String sqlString = "(select * from asset_all where Asset_acq_type like ?  and  Asset_attribute"
				+ " like ? and  asset_id like ? and  Asset_status like ? and  Asset_use_status like ? and  City_id like ? and  "
				+ "(Cont_name like ? or Cont_name is null ) and  (DaiWei_unit like ? or DaiWei_unit is null )  and  Dev_appli_sta like ? and  (Dev_mod like ? or Dev_mod is null )  and  (Name like ? or Name is null )  "
				+ "and  (Pro_type like ? or Pro_type is null )  and  (Prod_name like ? or Prod_name is null )  and asset_type like ?) "

				+ "limit ?,?;";
		HashMap<String, Object> map = new HashMap<String, Object>();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		int count = 0;
		try {
			System.out.println(search_asset.getAsset_id());
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,
					"%" + search_asset.getAsset_acq_type() + "%");
			preparedStatement.setString(2,
					"%" + search_asset.getAsset_attribute() + "%");
			preparedStatement.setString(3, "%" + search_asset.getAsset_id()
					+ "%");
			preparedStatement.setString(4, "%" + search_asset.getAsset_status()
					+ "%");
			preparedStatement.setString(5,
					"%" + search_asset.getAsset_use_status() + "%");
			preparedStatement.setString(6, "%" + search_asset.getCity_id()
					+ "%");
			preparedStatement.setString(7, "%" + search_asset.getCont_name()
					+ "%");
			preparedStatement.setString(8, "%" + search_asset.getDaiWei_unit()
					+ "%");
			preparedStatement.setString(9,
					"%" + search_asset.getDev_appli_sta() + "%");
			preparedStatement.setString(10, "%" + search_asset.getDev_mod()
					+ "%");
			preparedStatement.setString(11, "%" + search_asset.getName() + "%");
			preparedStatement.setString(12, "%" + search_asset.getPro_type()
					+ "%");
			preparedStatement.setString(13, "%" + search_asset.getProd_name()
					+ "%");
			preparedStatement.setString(14, "%" + search_asset.getDev_type()
					+ "%");

			preparedStatement.setInt(15, currentPage * 5);
			preparedStatement.setInt(16, 5);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Pro_info pro_info = new Pro_info();
				Assert_info assert_info = new Assert_info();
				Device_info device_info = new Device_info();
				Pro_type pro_type = new Pro_type();
				Contract contract = new Contract();
				Proxy_unit daiwei_unit = new Proxy_unit();
				User user = new User();

				Measure measure = new Measure();
				Productor productor = new Productor();
				productor.setProd_id(result.getString("prod_id"));
				productor.setProd_name(result.getString("prod_name"));
				measure.setMeas_id(result.getString("meas_id"));
				measure.setMeas_name(result.getString("meas_name"));

				device_info.setAsset_type(result.getString("asset_type"));
				device_info.setDev_mod(result.getString("dev_mod"));
				device_info.setDev_name(result.getString("dev_name"));

				device_info.setMeasure(measure);
				device_info.setProductor(productor);
				contract.setBuy_date(result.getString("buy_date"));
				contract.setCont_id(result.getString("cont_id"));
				contract.setCont_name(result.getString("cont_name"));
				contract.setStart_date(result.getString("start_date"));
				daiwei_unit.setAddress(result.getString("address"));
				daiwei_unit.setDai_controller(result
						.getString("dai_controller"));
				daiwei_unit.setDaiwei_id(result.getString("daiWei_id"));
				daiwei_unit.setDaiwei_unit(result.getString("daiWei_unit"));
				daiwei_unit.setEmail(result.getString("email"));
				daiwei_unit.setMol_tel(result.getString("mol_tel"));
				daiwei_unit.setPostcode(result.getString("postcode"));
				daiwei_unit.setTel_day(result.getString("tel_day"));
				daiwei_unit.setTel_night(result.getString("tel_night"));
				daiwei_unit.setUnit_res(result.getString("unit_res"));
				pro_type.setPro_type(result.getString("pro_type"));
				pro_type.setPro_type_id(result.getString("pro_type_id"));
				user.setUser_id(result.getString("user_id"));
				user.setName(result.getString("name"));
				user.setCity(result.getString("city_id"));
				pro_info.setAproval_num(result.getString("aproval_num"));
				pro_info.setDev_type(pro_type);
				pro_info.setPro_id(result.getString("pro_id"));
				pro_info.setPro_name(result.getString("pro_name"));
				assert_info.setDevice_info(device_info);

				assert_info.setAllo_info(result.getString("allo_info"));
				assert_info.setAss_controller(result
						.getString("ass_controller"));
				assert_info.setAsset_acq_type(result
						.getString("asset_acq_type"));
				assert_info.setAsset_attribute(result
						.getString("asset_attribute"));
				assert_info.setAsset_id(result.getString("asset_id"));
				assert_info.setAsset_status(result.getString("asset_status"));
				assert_info.setAsset_use_status(result
						.getString("asset_use_status"));
				assert_info.setContract(contract);
				assert_info.setDaiWei_cont_peri(result
						.getString("daiWei_cont_peri"));
				assert_info.setDaiwei_unit(daiwei_unit);
				assert_info.setDev_appli_sta(result.getString("dev_appli_sta"));
				assert_info.setMaint_end_time(result
						.getString("maint_end_time"));
				assert_info.setMaint_period(result.getString("maint_period"));
				assert_info.setPro_info(pro_info);
				assert_info.setServ_end_time(result.getString("serv_end_time"));
				assert_info.setStorage_loca(result.getString("storage_loca"));
				assert_info.setUser(user);
				assert_info.setDev_type(result.getString("asset_type"));
				assert_info.setCity_id(result.getString("city_id"));
				assert_info.setAsset_id1(result.getString("asset_id1"));
				assert_info.setMoney(result.getString("money"));
				System.out.println("hang" + result.getRow());
				Assert_info_list.add(assert_info);
			}

			sqlString = "select count(*) from ((select * from asset_all where Asset_acq_type like ?  and  Asset_attribute"
					+ " like ? and  asset_id like ? and  Asset_status like ? and  Asset_use_status like ? and  City_id like ? and  "
					+ "(Cont_name like ? or Cont_name is null ) and  (DaiWei_unit like ? or DaiWei_unit is null )  and  Dev_appli_sta like ? and  (Dev_mod like ? or Dev_mod is null )  and  (Name like ? or Name is null )  "
					+ "and  (Pro_type like ? or Pro_type is null )  and  (Prod_name like ? or Prod_name is null )  and asset_type like ?) ) as asset_all_search";

			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,
					"%" + search_asset.getAsset_acq_type() + "%");
			preparedStatement.setString(2,
					"%" + search_asset.getAsset_attribute() + "%");
			preparedStatement.setString(3, "%" + search_asset.getAsset_id()
					+ "%");
			preparedStatement.setString(4, "%" + search_asset.getAsset_status()
					+ "%");
			preparedStatement.setString(5,
					"%" + search_asset.getAsset_use_status() + "%");
			preparedStatement.setString(6, "%" + search_asset.getCity_id()
					+ "%");
			preparedStatement.setString(7, "%" + search_asset.getCont_name()
					+ "%");
			preparedStatement.setString(8, "%" + search_asset.getDaiWei_unit()
					+ "%");
			preparedStatement.setString(9,
					"%" + search_asset.getDev_appli_sta() + "%");
			preparedStatement.setString(10, "%" + search_asset.getDev_mod()
					+ "%");
			preparedStatement.setString(11, "%" + search_asset.getName() + "%");
			preparedStatement.setString(12, "%" + search_asset.getPro_type()
					+ "%");
			preparedStatement.setString(13, "%" + search_asset.getProd_name()
					+ "%");
			preparedStatement.setString(14, "%" + search_asset.getDev_type()
					+ "%");

			result = preparedStatement.executeQuery();
			result.next();
			count = Integer.valueOf(result.getString(1));
			count = (int) Math.ceil((count + 1.0 - 1.0) / 5);

			map.put("totalPage", count);

			map.put("assert_info_list", Assert_info_list);
			result.close();
			preparedStatement.close();
			connection.close();
			return map;
		} catch (Exception e) {
			System.out.println("资产——search有错误");
			System.out.println(e.toString());
			e.printStackTrace();
			return map;
		} finally {
			result.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public boolean delete(String assert_id, Asset_change asset_change)
			throws SQLException// 删除资产
	{
		this.db();

		PreparedStatement preparedStatement = null;
		try {
			String sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			preparedStatement.setString(1, asset_change.getAssert_info());
			preparedStatement.setString(2, asset_change.getContent_after());
			preparedStatement.setString(3, asset_change.getContent_before());
			preparedStatement.setString(4, asset_change.getDate());
			preparedStatement.setString(5, asset_change.getIp());
			preparedStatement.setString(6, asset_change.getOper_type());
			preparedStatement.setString(7, asset_change.getUser());
			int i = preparedStatement.executeUpdate();
			preparedStatement.close();
			sqlString = "DELETE from asset_info where asset_id=?;";
			int j = 0;
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);

			if (preparedStatement.executeUpdate() > 0)
				j++;
			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("删除资产异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}

	}

	public boolean mod_user(String assert_id, String user_id, String date1,
			String user, String ip) throws SQLException// 修改资产用户
	{
		this.db();
		String oper = new String();
		String content_before = null;
		ResultSet resultSet = null;
		String sqlString = "";
		int j = 0;
		PreparedStatement preparedStatement = null;
		try {
			sqlString = "SELECT * from asset_info WHERE asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getRow() > 0) {
				content_before = resultSet.getString("user_id");
				oper = "改";
				resultSet.close();
				preparedStatement.close();
				if (content_before != null) {
					sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
							+ "VALUE(?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.setString(1, assert_id);
					preparedStatement.setString(2, user_id);
					preparedStatement.setString(3, content_before);
					preparedStatement.setString(4, date1);
					preparedStatement.setString(5, ip);
					preparedStatement.setString(6, oper);
					preparedStatement.setString(7, user);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
				}

			}
			sqlString = "update asset_info set user_id=? where asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user_id);
			preparedStatement.setString(2, assert_id);

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;

			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}

	}

	public boolean mod_cont(String assert_id, String cont_id, String date1,
			String user, String ip) throws SQLException// 修改合同
	{
		this.db();
		String oper = new String();
		String content_before = null;
		ResultSet resultSet = null;
		String sqlString = "";
		int j = 0;
		PreparedStatement preparedStatement = null;
		try {
			sqlString = "SELECT * from asset_info WHERE asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getRow() > 0) {
				content_before = resultSet.getString("cont_id");
				oper = "改";
				resultSet.close();
				preparedStatement.close();
				if (content_before != null) {
					sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
							+ "VALUE(?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.setString(1, assert_id);
					preparedStatement.setString(2, cont_id);
					preparedStatement.setString(3, content_before);
					preparedStatement.setString(4, date1);
					preparedStatement.setString(5, ip);
					preparedStatement.setString(6, oper);
					preparedStatement.setString(7, user);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
				}

			}
			sqlString = "update asset_info set cont_id=? where asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, cont_id);
			preparedStatement.setString(2, assert_id);

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;

			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}
	}

	public boolean mod_proj_info(String assert_id, String proj_id,
			String date1, String user, String ip) throws SQLException// 修改合同
	{
		this.db();
		String oper = new String();
		String content_before = null;
		ResultSet resultSet = null;
		String sqlString = "";
		int j = 0;
		PreparedStatement preparedStatement = null;
		try {
			sqlString = "SELECT * from asset_all WHERE asset_id1=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getRow() > 0) {
				content_before = resultSet.getString("pro_id");
				oper = "改";
				resultSet.close();
				preparedStatement.close();
				if (content_before != null) {
					sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
							+ "VALUE(?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.setString(1, assert_id);
					preparedStatement.setString(2, proj_id);
					preparedStatement.setString(3, content_before);
					preparedStatement.setString(4, date1);
					preparedStatement.setString(5, ip);
					preparedStatement.setString(6, oper);
					preparedStatement.setString(7, user);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
				}

			}
			sqlString = "update asset_info set pro_id=? where asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, proj_id);
			preparedStatement.setString(2, assert_id);

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;

			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}
	}

	public boolean mod_proxy_unit(String assert_id, String daiwei_id,
			String date1, String user, String ip) throws SQLException// 修改代维单位
	{
		this.db();
		String oper = new String();
		String content_before = null;
		ResultSet resultSet = null;
		String sqlString = "";
		int j = 0;
		PreparedStatement preparedStatement = null;
		try {
			sqlString = "SELECT * from asset_all WHERE asset_id1=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getRow() > 0) {
				content_before = resultSet.getString("daiwei_id");
				oper = "改";
				resultSet.close();
				preparedStatement.close();
				if (content_before != null) {
					sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
							+ "VALUE(?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.setString(1, assert_id);
					preparedStatement.setString(2, daiwei_id);
					preparedStatement.setString(3, content_before);
					preparedStatement.setString(4, date1);
					preparedStatement.setString(5, ip);
					preparedStatement.setString(6, oper);
					preparedStatement.setString(7, user);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
				}

			}
			sqlString = "update asset_info set daiwei_id=? where asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, daiwei_id);
			preparedStatement.setString(2, assert_id);

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;

			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}
	}

	public boolean mod_device_info(String assert_id, String dev_mod,
			String date1, String user, String ip) throws SQLException// 修改设备信息
	{
		this.db();
		String oper = new String();
		String content_before = null;
		ResultSet resultSet = null;
		String sqlString = "";
		int j = 0;
		PreparedStatement preparedStatement = null;
		try {
			sqlString = "SELECT * from asset_all WHERE asset_id1=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getRow());
			if (resultSet.getRow() > 0) {
				content_before = resultSet.getString("dev_mod");
				oper = "改";
				resultSet.close();
				preparedStatement.close();
				if (content_before != null) {
					sqlString = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
							+ "VALUE(?,?,?,?,?,?,?)";
					preparedStatement = connection.prepareStatement(sqlString);
					preparedStatement.setString(1, assert_id);
					preparedStatement.setString(2, dev_mod);
					preparedStatement.setString(3, content_before);
					preparedStatement.setString(4, date1);
					preparedStatement.setString(5, ip);
					preparedStatement.setString(6, oper);
					preparedStatement.setString(7, user);
					int i = preparedStatement.executeUpdate();
					preparedStatement.close();
				}

			}
			sqlString = "update asset_info set dev_mod=? where asset_id=?";
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, dev_mod);
			preparedStatement.setString(2, assert_id);

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;

			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}
	}

	public String add_assert_info(Assert_info assert_info, String date1,
			String user_id, String ip) throws SQLException// //修改资产信息
	{
		this.db();
		int j = 0;
		String asset_id = "";
		PreparedStatement preparedStatement = null;
		String sqlString = "insert into  asset_info(Allo_info,Ass_controller,Asset_acq_type,Asset_attribute"
				+ ",Asset_status,Asset_use_status,DaiWei_cont_peri,Dev_appli_sta,Maint_end_time,Maint_period,Serv_end_time,"
				+ "Storage_loca,city_id,asset_type) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		preparedStatement = connection.prepareStatement(sqlString);
		preparedStatement.setString(1, assert_info.getAllo_info());
		preparedStatement.setString(2, assert_info.getAss_controller());
		preparedStatement.setString(3, assert_info.getAsset_acq_type());
		preparedStatement.setString(4, assert_info.getAsset_attribute());
		preparedStatement.setString(5, assert_info.getAsset_status());
		preparedStatement.setString(6, assert_info.getAsset_use_status());
		if ("".equals(assert_info.getDaiWei_cont_peri())) {
			assert_info.setDaiWei_cont_peri(null);
		}
		preparedStatement.setInt(7,
				Integer.parseInt(assert_info.getDaiWei_cont_peri()));

		preparedStatement.setString(8, assert_info.getDev_appli_sta());
		preparedStatement.setString(9, assert_info.getMaint_end_time());
		if ("".equals(assert_info.getMaint_period())) {
			assert_info.setMaint_period(null);
		}
		preparedStatement.setInt(10,
				Integer.parseInt(assert_info.getMaint_period()));
		preparedStatement.setString(11, assert_info.getServ_end_time());
		preparedStatement.setString(12, assert_info.getStorage_loca());
		preparedStatement.setString(13, assert_info.getCity_id());
		preparedStatement.setString(14, assert_info.getDev_type());
		preparedStatement.execute("SET AUTOCOMMIT=0");
		preparedStatement.execute("begin");
		if (preparedStatement.executeUpdate() > 0) {
			sqlString = "SELECT * from asset_info";
			ResultSet result = preparedStatement.executeQuery(sqlString);
			result.last();

			if (result.getRow() > 0) {

				asset_id = result.getString("asset_id");

			}
			j++;
			assert_info.setAsset_id(asset_id);
		}
		preparedStatement.close();
		String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getAsset_id());
		preparedStatement.setString(3, assert_info.getAsset_id());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getAsset_attribute());
		preparedStatement.setString(3, assert_info.getAsset_attribute());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getAsset_status());
		preparedStatement.setString(3, assert_info.getAsset_status());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getAsset_use_status());
		preparedStatement.setString(3, assert_info.getAsset_use_status());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getDev_appli_sta());
		preparedStatement.setString(3, assert_info.getDev_appli_sta());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
				+ "VALUE(?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, assert_info.getAsset_id());
		preparedStatement.setString(2, assert_info.getAsset_acq_type());
		preparedStatement.setString(3, assert_info.getAsset_acq_type());
		preparedStatement.setString(4, date1);
		preparedStatement.setString(5, ip);
		preparedStatement.setString(6, "增");
		preparedStatement.setString(7, user_id);
		if (preparedStatement.executeUpdate() > 0) {
			j++;
		}
		if (j >=6) {
			preparedStatement.execute("commit");// 事务提交
			preparedStatement.close();
			connection.close();
			return asset_id;
		} else {
			preparedStatement.execute("ROLLBACK;");// 回滚
			preparedStatement.close();
			connection.close();
			return "";
		}

	}

	public boolean mod_assert_info(String assert_id, Assert_info assert_info,
			Assert_info assert_info2, String user_id, String date1, String ip)
			throws SQLException// //修改资产信息
	{
		this.db();
		PreparedStatement preparedStatement = null;

		if (!assert_info2.getAsset_attribute().equals(
				assert_info.getAsset_attribute())) {
			String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assert_id);
			preparedStatement.setString(2, assert_info.getAsset_attribute());
			preparedStatement.setString(3, assert_info2.getAsset_attribute());
			preparedStatement.setString(4, date1);
			preparedStatement.setString(5, ip);
			preparedStatement.setString(6, "改");
			preparedStatement.setString(7, user_id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}

		if (!assert_info2.getAsset_acq_type().equals(
				assert_info.getAsset_acq_type())) {
			String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assert_id);
			preparedStatement.setString(2, assert_info.getAsset_acq_type());
			preparedStatement.setString(3, assert_info2.getAsset_acq_type());
			preparedStatement.setString(4, date1);
			preparedStatement.setString(5, ip);
			preparedStatement.setString(6, "改");
			preparedStatement.setString(7, user_id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		if (!assert_info2.getAsset_status().equals(
				assert_info.getAsset_status())) {
			String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assert_id);
			preparedStatement.setString(2, assert_info.getAsset_status());
			preparedStatement.setString(3, assert_info2.getAsset_status());
			preparedStatement.setString(4, date1);
			preparedStatement.setString(5, ip);
			preparedStatement.setString(6, "改");
			preparedStatement.setString(7, user_id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		if (!assert_info2.getAsset_use_status().equals(
				assert_info.getAsset_use_status())) {
			String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assert_id);
			preparedStatement.setString(2, assert_info.getAsset_use_status());
			preparedStatement.setString(3, assert_info2.getAsset_use_status());
			preparedStatement.setString(4, date1);
			preparedStatement.setString(5, ip);
			preparedStatement.setString(6, "改");
			preparedStatement.setString(7, user_id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		if (!assert_info2.getDev_appli_sta().equals(
				assert_info.getDev_appli_sta())) {
			String sql = "INSERT into asset_change(asset_id,content_after,content_before,date,ip,oper_type,user_id)"
					+ "VALUE(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assert_id);
			preparedStatement.setString(2, assert_info.getDev_appli_sta());
			preparedStatement.setString(3, assert_info2.getDev_appli_sta());
			preparedStatement.setString(4, date1);
			preparedStatement.setString(5, ip);
			preparedStatement.setString(6, "改");
			preparedStatement.setString(7, user_id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		String sqlString = "update asset_info set Allo_info=?,Ass_controller=?,Asset_acq_type=?,Asset_attribute=?,"
				+ "Asset_status=?,Asset_use_status=?,DaiWei_cont_peri=?,Dev_appli_sta=?,"
				+ "Maint_end_time=?,Maint_period=?,Serv_end_time=?,Storage_loca=? where asset_id="
				+ assert_id;
		int j = 0;

		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, assert_info.getAllo_info());
			preparedStatement.setString(2, assert_info.getAss_controller());
			preparedStatement.setString(3, assert_info.getAsset_acq_type());
			preparedStatement.setString(4, assert_info.getAsset_attribute());

			preparedStatement.setString(5, assert_info.getAsset_status());
			preparedStatement.setString(6, assert_info.getAsset_use_status());
			preparedStatement.setString(7, assert_info.getDaiWei_cont_peri());
			preparedStatement.setString(8, assert_info.getDev_appli_sta());
			preparedStatement.setString(9, assert_info.getMaint_end_time());
			preparedStatement.setString(10, assert_info.getMaint_period());
			preparedStatement.setString(11, assert_info.getServ_end_time());
			preparedStatement.setString(12, assert_info.getStorage_loca());

			preparedStatement.execute("SET AUTOCOMMIT=0");
			preparedStatement.execute("begin");
			if (preparedStatement.executeUpdate() > 0)
				j++;
			if (j > 0) {
				preparedStatement.execute("commit");// 事务提交
				preparedStatement.close();
				connection.close();
				return true;
			} else {
				preparedStatement.execute("ROLLBACK;");// 回滚
				preparedStatement.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("修改资产用户异常");
			e.printStackTrace();
			preparedStatement.close();
			connection.close();
			return false;
		} finally {

			preparedStatement.close();
			connection.close();

		}

	}

}
