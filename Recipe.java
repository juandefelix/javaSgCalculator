package com.juanortiz;

import java.util.*;
import java.io.*;


class Recipe{
	ArrayList<Malt> myMalts;
	float volume;

	/**************
	 * CONSTRUCTOR*
	 **************/
	
	public Recipe(float f){
		volume = f;
	}
		
	/**********************************************************
	 * This METHOD returns a float representing the specific  * 
	 * gravity in a recipe based in the malts of myMalts      *
	 **********************************************************/
	float specificGravity(){
		float theGravity=0;
		for (Malt m : myMalts) {
		 theGravity += ((m.potentialPoints()-1)* m.getWeight()/volume);
		} 
		return theGravity + 1;
	}
}//end class

/*class RecipeTestDrive {
	public static void main(String[] args) {
		Recipe r = new Recipe();
		r.myMalts = new ArrayList<Malt>();
		r.volume = 5;
		Malt m = new Malt("Pale Malt (2 Row) UK"); m.lb = 10; m.oz = 0; r.myMalts.add(m); 		Malt n = new Malt("Vienna Malt"); n.lb = 2.5f; n.oz = 0; r.myMalts.add(n);
		Malt o = new Malt("Caramel/Crystal Malt – 60L"); o.lb = 1; o.oz = 0; r.myMalts.add(o);
		for (Malt x : r.myMalts) {
			System.out.println(x.potentialPoints());

		}
		System.out.printf("Specific Gravity: %.3f" ,r.specificGravity());
	}
}
*/