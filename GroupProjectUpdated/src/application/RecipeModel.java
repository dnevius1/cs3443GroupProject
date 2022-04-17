package application;

import java.util.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecipeModel{
	
	/* Helper function that will iterated through an ArrayList of selected ingredients and return String value of all ingredients
	 * Returns nothing if arrayList is empty*/
	
    private static HashMap<String, String> recipeMap = new HashMap<String, String>();
    private static HashMap<String, Ingredient> initalMap = new HashMap<String, Ingredient>();
    private static Properties properties = new Properties();
    private static double recipeTotal = 0.00;
    private static String exprItems = "";
    private static String nonExprItems = "";
    
    /*addRecipe will take the user input of a recipe name along with the ingredients the user selected and add it as an element to 
     * the HashMap. If the user did select any ingredients, it will prompt the user to select ingredients. */
    public static boolean addRecipe(String recipeName) throws IOException {
    	if(recipeName.isEmpty() || recipeName.equals(null) || recipeName.matches("^[ ]+$") || recipeMap.containsKey(recipeName)) {
    		return false;	
    	}
    	else {
    		ArrayList<Ingredient> selectedIngr = checkList(new ArrayList<Ingredient>()); 
    		if (selectedIngr.isEmpty())
    			return false;
    		else {
    			String ingrBuffer = "";
    	    	Iterator<Ingredient> i = selectedIngr.iterator();
    	    	while(i.hasNext()) {
    	    		ingrBuffer += i.next().getName().trim() + " ";
    	    	}
    	    	ingrBuffer.trim();

        		recipeMap.put(recipeName, ingrBuffer);
        		properties.putAll(recipeMap);
   	    		FileOutputStream writer = new FileOutputStream(new File("recipes.properties"), true);
   	    		properties.store(writer, null);
   				return true;
   			}
    	}    	
    }

    /* deleteRecipe function will take the current listView and update it with the new listView after deleting the selected item. 
     * This function will show an alert if there are no selected items on the listView*/
    public static ListView<String> deleteRecipe(ListView<String> listView) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	ObservableList<String> selectedItem = listView.getSelectionModel().getSelectedItems();
    	if (selectedItem.isEmpty()) {
    		alert.setAlertType(AlertType.ERROR);
    		alert.setHeaderText("Missing Selection");
    		alert.setContentText("Please select a recipe");
    		alert.showAndWait();
    	}
  
    	else {
    		String buffer = ""; 
			for(String a: selectedItem) {
				buffer += a; 
			}
			listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
			FileReader reader = new FileReader(new File("recipes.properties"));
			FileWriter writer = new FileWriter(new File("recipes.properties"));
			properties.load(reader);
			properties.remove(buffer);
			properties.store(writer, null);
			writer.close();
		
			alert.setHeaderText("SUCCESFULLY DELETED");
			alert.setContentText(buffer + " was deleted.");
			alert.showAndWait();
    	}
		return listView;
    }
    
    // displayIngr will take an ObservableList<String> of a single recipe that was selected from the listView and sets string values for 
    // list of ingredients of selected recipe as well as a list of expired ingredients in the recipe. This function will return true if 
    // there are expired items. 
    public static boolean displayIngr(ObservableList<String> selectedItem) {
    	String buffer = "";
    	nonExprItems = ""; exprItems = "";
    	String [] arryBuffer;
    	recipeTotal = 0.00;
    	boolean expr = false; 
    	
		for(String a: selectedItem) {
			buffer += a; 
		}
	
		HashMap<String, String> recipeMap = RecipeModel.getRecipeMap();
		HashMap<String, Ingredient> initalMap = RecipeModel.getInitalMap();
		
		arryBuffer = recipeMap.get(buffer).split(" ");
		
		for (int i = 0; i < arryBuffer.length; i++) {
			try {
				recipeTotal += Double.parseDouble(initalMap.get(arryBuffer[i]).getCost());
			} catch (NumberFormatException e) {
				System.out.println("RecipeModel.107: Display failed: attempted to parse " + arryBuffer[i] + " into double.");
			}
				
			if (!initalMap.get(arryBuffer[i]).getIsExp()) {
				nonExprItems += initalMap.get(arryBuffer[i]).getName() + ", ";
			}
			else {
				exprItems += initalMap.get(arryBuffer[i]).getName() + ", ";
				expr = true;
			}
		}
    	
    	return expr;
    }
    /* LoadData load/create a properties file for the HashMap to hold saved data as well as loading ingredient data into ObservableList<Ingredient>
     * listData. The function will take a new ObservableList<Ingredient> listData variable and load all ingredients into it.
     * listData will be returned to be added into the TableView. 
     * */
    public static ObservableList<Ingredient> loadData(ObservableList<Ingredient> listData) throws IOException {
		File file = new File("recipes.properties");
		file.createNewFile();
		FileInputStream input = new FileInputStream(file);
		properties.load(input);
		input.close();
		if (recipeMap.isEmpty()) {
			for (Object key: properties.keySet()) {
				recipeMap.put((String)key, properties.getProperty((String) key));
			}
		}
		else 
			System.out.println("recipe is not empty");
		initalMap = listOfIngrediants.getHashIngr();
		for(String key: initalMap.keySet()) {
			listData.add(initalMap.get(key));
		}
		return listData;
    }

	/* checkedList() will take check all ingredients that are stored and add it to an ArrayList
    	if it is selected. This will also uncheck all ingredients.*/
	public static ArrayList<Ingredient> checkList(ArrayList<Ingredient> checked){
		
		for (String key: initalMap.keySet()) {
			if(initalMap.get(key).getSelected().isSelected())
				checked.add(initalMap.get(key));
		}
		
    	Iterator<Ingredient> j = checked.iterator();
		while (j.hasNext()) {
				j.next().getSelected().setSelected(false);
		}
		
		return checked;
	}
	/*Getter for recipeMap that contains all the recipes*/
	public static HashMap<String, String> getRecipeMap() {
		return recipeMap;
	}
	//Setter that assigns a new value for the recipeMap
	public static void setRecipeMap(HashMap<String, String> recipeMap) {
		RecipeModel.recipeMap = recipeMap;
	}
	// Getter that returns a HashMap of all available ingredients
	public static HashMap<String, Ingredient> getInitalMap() {
		return initalMap;
	}
	// Setter that assigns new HashMap value for initialMap()
	public static void setInitalMap(HashMap<String, Ingredient> initalMap) {
		RecipeModel.initalMap = initalMap;
	}
	// Getter that returns a Properties variable that holds all the saved recipe data
	public static Properties getProperties() {
		return properties;
	}
	// Setter that assigns a new value to the Properties properties variable
	public static void setProperties(Properties properties) {
		RecipeModel.properties = properties;
	}
	// Getter that returns the total cost of the recipe as a double
	public static double getRecipeTotal() {
		return recipeTotal;
	}
	// Setter that assigns a new value to recipeToal 
	public static void setRecipeTotal(double recipeTotal) {
		RecipeModel.recipeTotal = recipeTotal;
	}
	// Getter that returns a String of expired items found in a recipe
	public static String getExprItems() {
		return exprItems;
	}
	// Setter that assigns a new String to exprItems list
	public static void setExprItems(String exprItems) {
		RecipeModel.exprItems = exprItems;
	}
	// Getter that returns a String of items that have NOT expired in a recipe
	public static String getNonExprItems() {
		return nonExprItems;
	}
	// Setter that assigns a new String value to nonExprItems
	public static void setNonExprItems(String nonExprItems) {
		RecipeModel.nonExprItems = nonExprItems;
	}
	

}
