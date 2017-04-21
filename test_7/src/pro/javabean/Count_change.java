package pro.javabean;

public class Count_change {
	private int id;
	private String date;
	private String asset_id;
	private String content_before;
	private String content_after;
	public Count_change(){
		
	}
	
	public Count_change(int id, String date, String asset_id,
			String content_before, String content_after) {
		super();
		this.id = id;
		this.date = date;
		this.asset_id = asset_id;
		this.content_before = content_before;
		this.content_after = content_after;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the asset_id
	 */
	public String getAsset_id() {
		return asset_id;
	}

	/**
	 * @param asset_id the asset_id to set
	 */
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	/**
	 * @return the content_before
	 */
	public String getContent_before() {
		return content_before;
	}

	/**
	 * @param content_before the content_before to set
	 */
	public void setContent_before(String content_before) {
		this.content_before = content_before;
	}

	/**
	 * @return the content_after
	 */
	public String getContent_after() {
		return content_after;
	}

	/**
	 * @param content_after the content_after to set
	 */
	public void setContent_after(String content_after) {
		this.content_after = content_after;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
