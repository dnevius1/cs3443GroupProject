package application;

public class groceryItem {
	
	private String name;
	private String exp;
	private String cost;
	
	
	public groceryItem(String name, String exp, String cost) {
		
		this.name = name;
		this.exp = exp;
		this.cost = cost;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getExp() {
		return exp;
	}


	public void setExp(String exp) {
		this.exp = exp;
	}


	public String getCost() {
		return cost;
	}


	public void setCost(String cost) {
		this.cost = cost;
	}	

}
