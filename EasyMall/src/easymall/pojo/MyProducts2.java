package easymall.pojo;

public class MyProducts2 {
	private String name;
	private Integer soldnum;
	public MyProducts2() {}
	public MyProducts2(String name, Integer soldnum) {
		super();
		this.name = name;
		this.soldnum = soldnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSoldnum() {
		return soldnum;
	}
	public void setSoldnum(Integer soldnum) {
		this.soldnum = soldnum;
	}
	
	
}
