package application;

import javafx.scene.control.CheckBox;
import java.text.SimpleDateFormat;
import java.util.Date;

/*@Peter Banh
 * Class Ingredient acts as a Model for the RecipeBook Controller.
 */
public class Ingredient extends groceryItem {
	private CheckBox selected; 
	private boolean isExp;
	
	/* Constructor that will initialize all ingredients as loaded from the text file. 
	 * In comparison to it's parent class, this will also initiate a CheckBox variable that will determine if the Ingredient is selected in the view.
	 * */
	Ingredient(String name, String exprDate, double cost) {
		super(name.trim(), exprDate.trim(), String.valueOf(cost).trim());
		this.selected = new CheckBox();
		this.isExp = checkExpiration(exprDate.trim());
	}
	
	/* A static helper function that will determine if the given expiration date has passed. */
	private static boolean checkExpiration(String exprDate) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		String currentDate = dateFormat.format(date);
		//System.out.print(exprDate + " > " + currentDate + ": ");
		
		String curYr = currentDate.substring(6);
		String exprYr = exprDate.substring(6);
		String curMonth = currentDate.substring(0, 2);
		String exprMonth = exprDate.substring(0, 2);
		String curDay = currentDate.substring(3,5);
		String exprDay = exprDate.substring(3,5);
		
		if (curYr.compareTo(exprYr) > 0) {
			return true;
		}
		else if (curYr.equals(exprYr)) {
			if (curMonth.compareTo(exprMonth) > 0) {
				return true;
			}
			if (curMonth.equals(exprMonth) && curDay.compareTo(exprDay) > 0)  {
				return true;
			}
		}
		return false;
	}
	
	/* Getter that grabs the returns the value of the private variable isExp*/
	public boolean getIsExp() {
		return this.isExp;
	}

	/* Setter that assigns new value to isExp*/
	public void setIsExp(boolean isExp) {
		this.isExp = isExp;
	}

	/* Getter that grabs the returns the value of the private variable selected*/
	public CheckBox getSelected() {
		return this.selected;
	}

	/* Setter that assigns new value to selected*/
	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}
	
}
