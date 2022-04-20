package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/* Displays the monthly budget and allows user to 
 * switch scenes in the program
 * */
public class MainMenuController {
	public double monthlyBudget=500.00;
    @FXML
    private Button addItem;
    @FXML
    private Button recipes;
    @FXML
    private Button viewItem;
    @FXML
    private Label budget;
    @FXML
    private TextField budgetTxt;
    @FXML
    private Button set;
    /*Loads the monthly budget upon entering the scene*/
    @FXML
    public void initialize() throws IOException {
    	double budgetRemaining=0.0;
    	listOfIngrediants l=new listOfIngrediants();
    	l.loadData();
    	budgetRemaining=monthlyBudget - l.costOfIngredients;
    	budget.setText("Budget Remaining: "+budgetRemaining);
    	budgetTxt.setText(String.valueOf(monthlyBudget));
    }
    /* Calculates the new remaining budget if user decides
     * to change the budget amount
     * */
    @FXML
    void setBudget(ActionEvent event) throws IOException {
    	monthlyBudget=Double.parseDouble(budgetTxt.getText());
    	double budgetRemaining=0.0;
    	listOfIngrediants l=new listOfIngrediants();
    	l.loadData();
    	budgetRemaining=monthlyBudget - l.costOfIngredients;
    	budget.setText("Budget Remaining: "+budgetRemaining);
    	budgetTxt.setText(String.valueOf(monthlyBudget));
    }
    /* Changes to the AddList FXML when user clicks on 
     * Add Item button*/
    @FXML
    void gotoAddItem(ActionEvent event) throws IOException {
    	URL url = new File("src/application/AddList.fxml").toURI().toURL();
		BorderPane root = FXMLLoader.load(url);
		Scene scene = new Scene(root,700,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /* Changes to the RecipeBook FXML when user clicks on 
     * Recipes button*/
    @FXML
    void gotoRecipes(ActionEvent event) throws IOException {
    	listOfIngrediants ingr = new listOfIngrediants();
		ingr.loadData();
    	URL url = new File("src/application/RecipeBook.fxml").toURI().toURL();
		AnchorPane root = FXMLLoader.load(url);
		Scene scene = new Scene(root,700,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /* Changes to the ViewItems FXML when user clicks on 
     * View Item button*/
    @FXML
    void gotoViewItem(ActionEvent event) throws IOException {
    	URL url = new File("src/application/ViewItems.fxml").toURI().toURL();
		AnchorPane root = FXMLLoader.load(url);
		Scene scene = new Scene(root,700,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
