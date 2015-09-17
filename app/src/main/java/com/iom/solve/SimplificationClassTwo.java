package com.iom.solve;

import java.util.ArrayList;

public class SimplificationClassTwo {
	

	
	private String expandedResult ="(";
	
	
	public  SimplificationClassTwo(String contentOfBracketOne,String contentOfBracketTwo) {
		ArrayList<String>  listOfTokenOne = new ArrayList<String>();
		ArrayList<String>  listOfTokenTwo = new ArrayList<String>();
		 
		for (String tokenValuesOne: contentOfBracketOne.split("\\+")){
				listOfTokenOne.add(tokenValuesOne);
		 }
		for (String tokenValuesTwo: contentOfBracketTwo.split("\\+")){
			listOfTokenTwo.add(tokenValuesTwo);
	    }
		for (String tokenOne: listOfTokenOne){
			for (String tokenTwo: listOfTokenTwo){
				expandedResult = expandedResult  + tokenOne + tokenTwo + "+"; 
		    }
		}
		
		// this will remove the last character.. i.e remove the last plus sign
		expandedResult = expandedResult.substring(0, expandedResult.length()-1);
		// add bracket close to the last character
		expandedResult = expandedResult  + ")" ;
    
	}
	
	
	public String getSimplifiedResult(){
		return this.expandedResult;
	}

}
