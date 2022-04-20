package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewItemController {
	public static File text = new File("../GroupProjectUpdated/data/nlist.txt");

    @FXML
    private TextField typegrocery;

    @FXML
    private TextArea results;

   

    @FXML
    void homebutton(ActionEvent event) throws IOException {
    	//main menu button
    	URL url = new File("src/application/MainMenu.fxml").toURI().toURL();
		AnchorPane root = FXMLLoader.load(url);
		Scene scene = new Scene(root,700,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
         window.setScene(scene);
         window.show();
    }
    
    @FXML
    void find(ActionEvent event) throws IOException {
    	//gets text from the text field and uses that as a key, creates a hash map which uses the key to see if the item exists and sets text area text to item if its there or prints item not found if not found
    	String linkthis = typegrocery.getText();
    	typegrocery.clear();
    	Scanner scnr = new Scanner(text); 
    	scnr.useDelimiter(",|\\n");
    	int itemfound=0;
    	String name, date, price;
    	while(scnr.hasNext()) {
    		name=scnr.next();
    		date=scnr.next();
    		price=scnr.next();
    		if(name.equals(linkthis)) {
    			results.setText("\n\n\n               Your Item: "+name+"\n"+"\n"+"\n"+"    EXPIRES ON"+"             "+"PRICE"+"\n    "+date+"             $"+price);
    			itemfound=1;
    			break;
    		}
    	}
    	if(itemfound==0) {
    		results.setText("Item not found \nplease try again!");
    	}
    	scnr.close();
    }
    
    @FXML
   	private void initialize() throws FileNotFoundException, IOException {
    	//initilzes the class with loading the properties from the nlist file
    	
    

}

}
