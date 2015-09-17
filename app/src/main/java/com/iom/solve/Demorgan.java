package com.iom.solve;

import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demorgan {

	Pattern patternCombineSplit, patternSplitCombine;
	Matcher mCombineSplit, mSplitCombine ;
    boolean match;
	public boolean checkIfMatch(){
		return  match;
	}


	public Demorgan(){
	// this section will intiallize all the pattern for the fuctions

		match = false;

		patternCombineSplit = Pattern.compile("\\(([a-zA-Z0-1])\\+([a-zA-Z0-1])\\)'");
		patternSplitCombine= Pattern.compile("\\(([a-zA-Z0-1])([a-zA-Z0-1])\\)'");

	}
	
	public String runCombineSplit(String string, TextView textview){
		mCombineSplit = patternCombineSplit.matcher(string);
	    if (mCombineSplit.find()) {

				string  = mCombineSplit.replaceAll(mCombineSplit.group(1)+"'"+mCombineSplit.group(2)+"'");
				writeToNextLine(textview, "Using Demorgan's law (A+B)' = A'B', where A and B are any variables");
				writeToNextLineSingle(textview, string);
				match = true;
	   } 
	   return string;
	}
	
	public String runSplitCombine(String string, TextView textview){
	    mSplitCombine = patternSplitCombine.matcher(string);
	    if (mSplitCombine.find()) {
				string  = mSplitCombine.replaceAll(mSplitCombine.group(1)+"'+"+mSplitCombine.group(2)+"'");
				writeToNextLine(textview, "Using Demorgan's law (AB)' = A'+B', where A and B are any variables");
			  	writeToNextLineSingle(textview, string);
				match = true;
	   } 
	    return string;
	}
	


	
	public void writeToNextLine(TextView txtSolution, String nextText){
		String text = txtSolution.getText().toString();
		txtSolution.setText(text + "\n\n" + nextText);	
	}

	public void writeToNextLineSingle(TextView txtSolution, String nextText){
		String text = txtSolution.getText().toString();
		txtSolution.setText(text + "\n" + nextText);	
	}
	
	// this is a function that will print as coloured the simplified Text


}
