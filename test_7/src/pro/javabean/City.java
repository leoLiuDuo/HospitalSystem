package pro.javabean;

public class City {
	private String city_id;
	private String city;
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public City(){
		
	}
	public City(String city_id, String city) {
		super();
		this.city_id = city_id;
		this.city = city;
	}
}
