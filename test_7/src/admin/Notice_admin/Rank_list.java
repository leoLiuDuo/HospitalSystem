package admin.Notice_admin;

public class Rank_list {
private String name;
private int login_times;
private int duration;
private String  city;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getLogin_times() {
	return login_times;
}
public void setLogin_times(int login_times) {
	this.login_times = login_times;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public  Rank_list() {
	
}
public  Rank_list( String name,int login_times,int duration,String  city ) {
	this.name=name;
	this.login_times= login_times;
	this.duration=duration;
	this.city=city;
	
}
}
