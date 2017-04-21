package pro.javabean;

import java.sql.Date;
import java.util.ArrayList;
 
public class res_of_search {
	
	private int SEARCH_ID;
	private String LOGIN_ID;
	private String SEARCH_SEX;
	private int AGE1;
	private int AGE2;
	private String EXAM_CLASS;
	private  String TEST_CLASS_NO1;
	private String TEST_CALSS_NO2;
	private ArrayList<DATE> SEARCH_DATE=new ArrayList<DATE>();
	private String HOS_DEP_NO1_ID;
	private String HOS_DEP_NO2_ID;
	private int num;
 	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		res_of_search other = (res_of_search) obj;
		if (AGE1 != other.AGE1)
			return false;
		if (AGE2 != other.AGE2)
			return false;
		if (EXAM_CLASS == null) {
			if (other.EXAM_CLASS != null)
				return false;
		} else if (!EXAM_CLASS.equals(other.EXAM_CLASS))
			return false;
		if (HOS_DEP_NO1_ID == null) {
			if (other.HOS_DEP_NO1_ID != null)
				return false;
		} else if (!HOS_DEP_NO1_ID.equals(other.HOS_DEP_NO1_ID))
			return false;
		if (HOS_DEP_NO2_ID == null) {
			if (other.HOS_DEP_NO2_ID != null)
				return false;
		} else if (!HOS_DEP_NO2_ID.equals(other.HOS_DEP_NO2_ID))
			return false;
		if (LOGIN_ID == null) {
			if (other.LOGIN_ID != null)
				return false;
		} else if (!LOGIN_ID.equals(other.LOGIN_ID))
			return false;
		if (SEARCH_DATE == null) {
			if (other.SEARCH_DATE != null)
				return false;
		} else if (!SEARCH_DATE.equals(other.SEARCH_DATE))
			return false;
		if (SEARCH_ID != other.SEARCH_ID)
			return false;
		if (SEARCH_SEX != other.SEARCH_SEX)
			return false;
		if (TEST_CALSS_NO2 == null) {
			if (other.TEST_CALSS_NO2 != null)
				return false;
		} else if (!TEST_CALSS_NO2.equals(other.TEST_CALSS_NO2))
			return false;
		if (TEST_CLASS_NO1 == null) {
			if (other.TEST_CLASS_NO1 != null)
				return false;
		} else if (!TEST_CLASS_NO1.equals(other.TEST_CLASS_NO1))
			return false;
		return true;
	}

	public res_of_search() {
	  }
	 
	public res_of_search(int sEARCH_ID, String lOGIN_ID, String sEARCH_SEX,
			int aGE1, int aGE2, String eXAM_CLASS, String tEST_CLASS_NO1,
			String tEST_CALSS_NO2,  String hOS_DEP_NO1_ID,
			String hOS_DEP_NO2_ID) {
		super();
		SEARCH_ID = sEARCH_ID;
		LOGIN_ID = lOGIN_ID;
		SEARCH_SEX = sEARCH_SEX;
		AGE1 = aGE1;
		AGE2 = aGE2;
		EXAM_CLASS = eXAM_CLASS;
		TEST_CLASS_NO1 = tEST_CLASS_NO1;
		TEST_CALSS_NO2 = tEST_CALSS_NO2;
		 
		HOS_DEP_NO1_ID = hOS_DEP_NO1_ID;
		HOS_DEP_NO2_ID = hOS_DEP_NO2_ID;
	}
	public int getSEARCH_ID() {
		return SEARCH_ID;
	}
	public void setSEARCH_ID(int sEARCH_ID) {
		SEARCH_ID = sEARCH_ID;
	}
	public String getLOGIN_ID() {
		return LOGIN_ID;
	}
	public void setLOGIN_ID(String lOGIN_ID) {
		LOGIN_ID = lOGIN_ID;
	}
	public String getSEARCH_SEX() {
		return SEARCH_SEX;
	}
	public void setSEARCH_SEX(String sEARCH_SEX) {
		SEARCH_SEX = sEARCH_SEX;
	}
	public int getAGE1() {
		return AGE1;
	}
	public void setAGE1(int aGE1) {
		AGE1 = aGE1;
	}
	public int getAGE2() {
		return AGE2;
	}
	public void setAGE2(int aGE2) {
		AGE2 = aGE2;
	}
	public String getEXAM_CLASS() {
		return EXAM_CLASS;
	}
	public void setEXAM_CLASS(String eXAM_CLASS) {
		EXAM_CLASS = eXAM_CLASS;
	}
	public String getTEST_CLASS_NO1() {
		return TEST_CLASS_NO1;
	}
	public void setTEST_CLASS_NO1(String tEST_CLASS_NO1) {
		TEST_CLASS_NO1 = tEST_CLASS_NO1;
	}
	public String getTEST_CALSS_NO2() {
		return TEST_CALSS_NO2;
	}
	public void setTEST_CALSS_NO2(String tEST_CALSS_NO2) {
		TEST_CALSS_NO2 = tEST_CALSS_NO2;
	}
	public ArrayList<DATE> getSEARCH_DATE() {
		return SEARCH_DATE;
	}
	public void setSEARCH_DATE(Date sEARCH_DATE) {
		System.out.println("函数中！！！！！！！！！！！！！！！！！！！！！！："+sEARCH_DATE);
		DATE d=new DATE(sEARCH_DATE);
		SEARCH_DATE.add(d);
	    System.out.println(SEARCH_DATE.get(SEARCH_DATE.size()-1).getDate());
	    System.out.println("完毕");
	}
	public String getHOS_DEP_NO1_ID() {
		return HOS_DEP_NO1_ID;
	}
	public void setHOS_DEP_NO1_ID(String hOS_DEP_NO1_ID) {
		HOS_DEP_NO1_ID = hOS_DEP_NO1_ID;
	}
	public String getHOS_DEP_NO2_ID() {
		return HOS_DEP_NO2_ID;
	}
	public void setHOS_DEP_NO2_ID(String hOS_DEP_NO2_ID) {
		HOS_DEP_NO2_ID = hOS_DEP_NO2_ID;
	}	
	
	
	

}
