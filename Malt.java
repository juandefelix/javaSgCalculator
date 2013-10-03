package com.juanortiz;

import java.io.*;
import java.util.*;

class Malt {
	private String name;
	private float weight;
	
	public Malt(String theName, float lb, int oz){
		name = theName;
		weight = lb + (Float)oz/16;
	}
	/***************************************************************************
	 * this METHOD evaluates the varialble potentialPoints of the Malt theName 
	 * to the potential points value founs for theName in BeerIngredient.txt 
	 ***************************************************************************/
	public float potentialPoints(){
		float p = 0;
		try {
			FileReader fileReader = new FileReader (new File("Beer_ingrediens_test.txt"));
			BufferedReader reader = new BufferedReader (fileReader);
			String line= null;
			
			while ((line = reader.readLine() ) !=null) {
				String [] maltDescriptionArray = line.split("\t");
				if (maltDescriptionArray[0].equals(name)) {
					String[] potentialPointsArray= maltDescriptionArray[5].split(" ");
					p = Float.parseFloat(potentialPointsArray[0]);
				} 
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		} //end catch
		return p;
	} // end method
	
	/***********************************************************
	 * this METHOD converts the weight in Ounzes Oz into Pounds 
	 ***********************************************************/
	
	public getName(){
		return name;
	}

	public getWeight(){
		return weight;
	}
	
}// end class
		
/*class MaltTestDrive {	
	public static void main(String[] args) {
		Malt m= new Malt("Acid Malt", 1, 16);
		System.out.println("Test construnctor: " + m.getName);
		System.out.println("Test potentialPoints: " + m.potentialPoints());
		System.out.println("Test weight: " + m.getWeight());
	}
}

*/