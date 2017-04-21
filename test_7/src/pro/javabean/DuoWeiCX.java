package pro.javabean;

public class DuoWeiCX {
	private String yuyinname;
	private int sex;
	private String  relevant_clinic_diag; //临床诊断
	private int  age; 
	private int  hospital_id;//住院号
	private int  hospital_time;//住院次数
	
	public DuoWeiCX(String yuyinname, int sex, String relevant_clinic_diag, int age, int hospital_id,
			int hospital_time) {
		super();
		this.yuyinname = yuyinname;
		this.sex = sex;
		this.relevant_clinic_diag = relevant_clinic_diag;
		this.age = age;
		this.hospital_id = hospital_id;
		this.hospital_time = hospital_time;
	}
	public String getYuyinname() {
		return yuyinname;
	}
	public void setYuyinname(String yuyinname) {
		this.yuyinname = yuyinname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getRelevant_clinic_diag() {
		return relevant_clinic_diag;
	}
	public void setRelevant_clinic_diag(String relevant_clinic_diag) {
		this.relevant_clinic_diag = relevant_clinic_diag;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public int getHospital_time() {
		return hospital_time;
	}
	public void setHospital_time(int hospital_time) {
		this.hospital_time = hospital_time;
	}
	

	
	
	
	
	
	

}
