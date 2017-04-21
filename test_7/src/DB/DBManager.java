package DB;

import java.sql.*;

public class DBManager {
	Connection conn=null;
	String user="root";
	String password="";
	String url="jdbc:mysql://localhost:3306/hospital?characterEncoding=utf8&useSSL=true";
	
	/*
	 * 
	 * 鍒濆鍖栭摼鎺ョ被锛屽苟涓斿姞杞界鐞嗙被鍖�
	 */
public DBManager() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("椹卞姩鍖呮湁闂");
		System.out.println();
	}
	
}
/*
 * 閫氳繃getcon()鏂规硶鑾峰緱閾炬帴
 */
public Connection getCon(){
			try {
				conn=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				System.out.println("閾炬帴寮傚父");
			}
	return conn;
}

}
