package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddListController {
	File file = new File("../GroupProjectUpdated/data/nlist.txt");		//Creating new file nlist.txt under pathname
	
	/*
	 * Creating the text area variables, buttons, and mouse actions. As well as initializing text area boxes
	 */
		@FXML
	    private TextArea cost4;

	    @FXML
	    private TextArea cost3;

	    @FXML
	    private TextArea cost2;

	    @FXML
	    private TextArea cost1;

	    @FXML
	    private TextArea cost5;

	    @FXML
	    private TextArea name5;

	    @FXML
	    private TextArea name4;

	    @FXML
	    private TextArea name3;

	    @FXML
	    private TextArea exp5;

	    @FXML
	    private TextArea exp4;

	    @FXML
	    private TextArea exp3;

	    @FXML
	    private TextArea exp2;

	    @FXML
	    private TextArea exp1;

	    @FXML
	    private TextArea name2;

	    @FXML
	    private TextArea name1;
	    
	    @FXML
	    private Button mainMenu;
	    
	    /*
	     * Setting the text that list the text area box information
	     */
	    @FXML
	    void initialize() {
	    	
	    	name1.setText("Item 1");
	    	name2.setText("Item 2");
	    	name3.setText("Item 3");
	    	name4.setText("Item 4");
	    	name5.setText("Item 5");
	    	
	    	exp1.setText("Expiration\ndate");
	    	exp2.setText("Expiration date");
	    	exp3.setText("Expiration date");
	    	exp4.setText("Expiration date");
	    	exp5.setText("Expiration date");
	    	
	    	cost1.setText("Item price");
	    	cost2.setText("Item price");
	    	cost3.setText("Item price");
	    	cost4.setText("Item price");
	    	cost5.setText("Item price");
	    	
	    	
	    	
	    	
	    }
	    
	    @FXML
	    void gotoMainMenu(ActionEvent event) throws IOException {
	    	URL url = new File("src/application/MainMenu.fxml").toURI().toURL();
			AnchorPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,700,700);
	        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        window.setScene(scene);
	        window.show();
	    }
	    
	    @FXML
	    void nclick1(MouseEvent event) {
	    	name1.clear();
	    }
	    
	    @FXML
	    void nclick2(MouseEvent event) {
	    	name2.clear();
	    }
	    
	    @FXML
	    void nclick3(MouseEvent event) {
	    	name3.clear();
	    }
	    
	    @FXML
	    void nclick4(MouseEvent event) {
	    	name4.clear();
	    }
	    
	    @FXML
	    void nclick5(MouseEvent event) {
	    	name5.clear();
	    }
	    
	    
	    @FXML
	    void eclick1(MouseEvent event) {
	    	exp1.clear();
	    }
	    
	    @FXML
	    void eclick2(MouseEvent event) {
	    	exp2.clear();
	    }
	    
	    @FXML
	    void eclick3(MouseEvent event) {
	    	exp3.clear();
	    }
	    
	    @FXML
	    void eclick4(MouseEvent event) {
	    	exp4.clear();
	    }
	    
	    @FXML
	    void eclick5(MouseEvent event) {
	    	exp5.clear();
	    }
	    
	    @FXML
	    void cclick1(MouseEvent event) {
	    	cost1.clear();
	    }
	    
	    @FXML
	    void cclick2(MouseEvent event) {
	    	cost2.clear();
	    }
	    
	    @FXML
	    void cclick3(MouseEvent event) {
	    	cost3.clear();
	    }
	    
	    @FXML
	    void cclick4(MouseEvent event) {
	    	cost4.clear();
	    }
	    
	    @FXML
	    void cclick5(MouseEvent event) {
	    	cost5.clear();
	    }
	    
	    /*
	     * This will add user input to an ArrayList that will be stored into a file "nlist.txt"
	     */
	    @FXML
	    void addList(ActionEvent event) {
	    	
	    	ArrayList<String> tmp = new ArrayList<String>();
	    	ArrayList<String> tmp2 = new ArrayList<String>();
	    	
	    	
	    	/*
	    	 * Acquiring user input from text boxes when if statement executes (adds data to ArrayList)
	    	 * If box left untouched then it is considered empty
	    	 * clears name, exp, and cost text boxes when if statement executes
	    	 */
	    	tmp2= AddListModel.add(name1.getText(),exp1.getText(), cost1.getText());
	    	if(tmp2!=null) {
	    	tmp.add(tmp2.get(0));
	    	name1.clear();
	    	exp1.clear();
	    	cost1.clear();
	    	}
	    	tmp2= AddListModel.add(name2.getText(),exp2.getText(), cost2.getText());
	    	if(tmp2!=null) {
		    	tmp.add(tmp2.get(0));
		    	name2.clear();
		    	exp2.clear();
		    	cost2.clear();
		    	}
	    	tmp2= AddListModel.add(name3.getText(),exp3.getText(), cost3.getText());
	    	if(tmp2!=null) {
		    	tmp.add(tmp2.get(0));
		    	name3.clear();
		    	exp3.clear();
		    	cost3.clear();
		    	}
	    	tmp2= AddListModel.add(name4.getText(),exp4.getText(), cost4.getText());
	    	if(tmp2!=null) {
		    	tmp.add(tmp2.get(0));
		    	name4.clear();
		    	exp4.clear();
		    	cost4.clear();
		    	}
	    	tmp2= AddListModel.add(name5.getText(),exp5.getText(), cost5.getText());
	    	if(tmp2!=null) {
		    	tmp.add(tmp2.get(0));
		    	name5.clear();
		    	exp5.clear();
		    	cost5.clear();
		    	}
	    	
	    	
	   
				try {
					 
		            //Creating object from BufferedWriter to appended to file
		            BufferedWriter tm = new BufferedWriter(
		                new FileWriter(file, true));
		 
		            // Appending to file
		            for (String str : tmp) {
		            	if(   !(  str.equals(null) || str.equals("") )  ) {
		            	tm.write(str + System.lineSeparator());
		            }
		            }
		           
		 
		            // Closing file 
		            tm.close();
		        }
			
				 catch (IOException e) {
					 
			            // error message
			            System.out.println("Exception Occurred" + e);
			        }
				
}
}
	    
