package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* @Peter Banh
 * Controller class that handles actionEvents and controllers found in the RecipeBook.fxml
 * RecipeBookController will allow the user to add and remove recipes and view the ingredients that make up a recipe.*/
public class RecipeBookController implements Initializable{

	@FXML
	private AnchorPane RecipeBookPane; 
    @FXML
    private TextField recipeName;
    @FXML
    private Button addToBook;
    @FXML
    private Button deleteBttn;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button showBttn;
    @FXML
    private TableView<Ingredient> ingredients; 
    @FXML 
    private Text ingrText;
    @FXML
    private Label totalCost;
    @FXML
    private Text exprText;
    @FXML
    private Label exprMessage;
    @FXML
    private ScrollPane exprScrollPane;
    @FXML
    private Button mainMenu;
    
    /* Adds a new recipe to recipe.properties and will update the TableView with the new recipe. */
    @FXML
    void addNewRecipie(ActionEvent event) throws IOException {
    	String recipeTitle = recipeName.getText();
    	boolean addNewRecipe = false;
    	
    	try{
    		addNewRecipe = RecipeModel.addRecipe(recipeTitle);
    	}
    	catch(IOException e) {
    		System.out.println("Properties File not found.");
    	}

    	if (addNewRecipe) {
    		listView.getItems().add(recipeTitle);
    	}
      	else {	
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("ERROR");
    		a.setHeaderText("Invalid user input");
    		a.setContentText("Please enter a UNIQUE recipe name and select at least one ingredient.");
    		a.showAndWait();
    	}
    	
    	recipeName.setText("");
    }
    
    /* DisplayInfo will show information on the recipe selected.
     * This class will grab isExp variable from each of the selected recipe's ingredients and 
     * will notify the user of all items that have past their expiration date */
    @FXML
    void displayInfo(ActionEvent event) {
    	boolean expr = false; 
    	ingrText.setText("");
    	exprText.setText("");
    	exprScrollPane.setOpacity(0);
    	exprMessage.setOpacity(0);
    	
    	ObservableList<String> selectedItem = listView.getSelectionModel().getSelectedItems();
    	if (selectedItem.isEmpty()) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("No recipe selected");
    		a.setContentText("Please select a single recipe");
    		a.showAndWait();
    	}
    	else 
    		expr = RecipeModel.displayIngr(selectedItem);

    		totalCost.setText("$" + String.format( "%.2f", RecipeModel.getRecipeTotal()));
    		ingrText.setText(RecipeModel.getNonExprItems().substring(0, RecipeModel.getNonExprItems().length() - 2));
    		
    		if (expr) {
    			exprText.setText(RecipeModel.getExprItems().substring(0, RecipeModel.getExprItems().length() - 2));
    			exprText.setFill(Color.RED);
    			exprScrollPane.setOpacity(1);
    	    	exprMessage.setOpacity(1);
    		}
    	
    }
    /* Removes the selected recipe the user has selected from the ListView
     * This class will also clear any text regarding the recipe ingredient, cost and expired items */
    @FXML
    void deleteRecipie(ActionEvent event) throws IOException {
    	
    	exprScrollPane.setOpacity(0);
    	exprMessage.setOpacity(0);
    	exprText.setText("");
    	ingrText.setText("");
    	totalCost.setText("$0.00");
    	
    	try {
    	listView = RecipeModel.deleteRecipe(listView);
    	} catch (IOException e) {
    		System.out.println("Delete Button: Failed to update properties file");
    	}

    }
    /*Returns to main menu*/
    @FXML
    void gotoMainMenu(ActionEvent event) throws IOException {
    	URL url = new File("src/application/MainMenu.fxml").toURI().toURL();
		AnchorPane root = FXMLLoader.load(url);
		Scene scene = new Scene(root,700,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /* initialize class will load data stored in nlist.txt and recipes.properties to their respective controllers utilizing HashSets. 
     * This class will also format the tableView.
     * */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Properties being loaded...");
		
		ObservableList<Ingredient> listData = FXCollections.observableArrayList();
		try {
			listData = RecipeModel.loadData(listData);
		} catch (IOException e) {
			System.out.println("File not loaded.");
		}
		
		Properties temp = RecipeModel.getProperties();
		for (Object key: temp.keySet() ) {
			listView.getItems().add(key.toString());
		}

		TableColumn<Ingredient, String> firstColumn = new TableColumn<Ingredient, String>("Ingredients");
		TableColumn<Ingredient, CheckBox> secondColumn = new TableColumn<Ingredient, CheckBox>("Selected");

		ingredients.setMaxWidth(ingredients.getPrefWidth());
		firstColumn.setPrefWidth(ingredients.getPrefWidth() - 65);
		firstColumn.setMinWidth(firstColumn.getPrefWidth());
		firstColumn.setMaxWidth(firstColumn.getPrefWidth());
		
		secondColumn.setPrefWidth(ingredients.getPrefWidth() - firstColumn.getPrefWidth() - 11);
		secondColumn.setMaxWidth(secondColumn.getPrefWidth());
		ingredients.getColumns().add(firstColumn);
		ingredients.getColumns().add(secondColumn);
		
		firstColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
		secondColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, CheckBox>("Selected"));
		firstColumn.setStyle("-fx-alignment: CENTER;");
		secondColumn.setStyle("-fx-alignment: CENTER;");
		
		ingredients.setItems(listData);

	}
}