package com.iom.solve;

import java.util.ArrayList;

public class SimplificationClass {
	
	private String expandedResult ="(";
	
	
	public  SimplificationClass(String txtbeforeBracket,String contentOfBracket) {
		ArrayList<String>  listOfToken = new ArrayList<String>();
		 for (String tokenValues: contentOfBracket.split("\\+")){
				listOfToken.add(tokenValues);
		 }
		for (String token: listOfToken){
				expandedResult = expandedResult  + txtbeforeBracket + token + "+"; 
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
