package application;

/*@Peter Banh
* This class will load in the saved file of ingredients and will store it in an ArrayList.
*/
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public class listOfIngrediants {
	private static HashMap<String, Ingredient> hashIngr = new HashMap<String, Ingredient>();
	public double costOfIngredients;
	// loadData will attempt to open the file nList.txt and start loading data into as an Ingredient Object and place it in an ArrayList
	public void loadData() throws IOException {
		
		File newFile = new File("data/nlist.txt");
		if (!newFile.exists()) {
			newFile.createNewFile();
		}
		
		String[] buffer;
		FileReader file = new FileReader(newFile);
		BufferedReader read = new BufferedReader(file);
			while (read.ready()) {
				buffer = read.readLine().trim().split(",");
				if(buffer.length != 3) {
					System.out.println("listOfIngredients.30: ERROR: formating loaded data Failed");
				}
				else {
					try {
						Ingredient a = new Ingredient(buffer[0], buffer[1], Double.parseDouble(buffer[2]) );
						costOfIngredients+=Double.parseDouble(buffer[2]);
						hashIngr.putIfAbsent(buffer[0], a);
					} catch (NumberFormatException e) {
						System.out.println("listOfIngredients: NumberFormatException: attempted to parse " + buffer[2] + " as a double");
					}
				}
			}
		read.close();
	}

	/* Getter that grabs the returns the value of the private variable getHashIngr*/
	public static HashMap<String, Ingredient> getHashIngr() {
		return hashIngr;
	}

	/* Setter that assigns a value to hashIgnr*/
	public void setHashIngr(HashMap<String, Ingredient> hashIngr) {
		listOfIngrediants.hashIngr = hashIngr;
	}
	
	
}