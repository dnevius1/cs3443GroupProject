package application;

import java.util.ArrayList;


	public class AddListModel {
		
		public static ArrayList<String> add(String name, String exp, String cost){
			ArrayList<String> tmp = new ArrayList<String>();
			
			if (!(name.equals("Item 1") || name.equals("Item 2") || name.equals("Item 3") || name.equals("Item 4") || name.equals("Item 5"))) {
	    		if(!(name.equals(""))) {
	    		tmp.add(name.trim() + ","+ exp.trim() +","+ cost.trim());
	    		return tmp;
	    		}
	    		
	    	}
	    	
	    	else if (!(name.equals(""))) {
	    		if(!(name.equals("Item 1") || name.equals("Item 2") || name.equals("Item 3") || name.equals("Item 4") || name.equals("Item 5"))) {
	    		tmp.add(name.trim() + ","+ exp.trim() +","+ cost.trim());
	    		return tmp;
	    		}
	    		
	    	}
			
	    	
			return null;
		}
	}

