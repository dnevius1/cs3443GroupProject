package application;
/* Stores the individual values of the grocery
 * item input by the user*/
public class groceryItem {
	
	private String name;
	private String exp;
	private String cost;
	
	/*Constructor*/
	public groceryItem(String name, String exp, String cost) {
		
		this.name = name;
		this.exp = exp;
		this.cost = cost;
		
	}

/*Getters and Setters*/
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
