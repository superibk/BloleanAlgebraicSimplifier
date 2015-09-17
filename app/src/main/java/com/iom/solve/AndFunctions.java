package com.iom.solve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class AndFunctions {
	
	Pattern patternZero, patternOne, patternSame, patternNegation;
	Matcher mZero, mOne, mSame, mNegation;
    boolean match;
	public boolean checkIfMatch(){
		return  match;
	}


	public AndFunctions(){
	// this section will intiallize all the pattern for the fuctions

		match = false;

		patternZero = Pattern.compile("([a-zA-Z0-1])0(?!.)|0([a-zA-Z0-1])(?!.)");
		patternOne= Pattern.compile("([a-zA-Z0-1])1(?!.)|1([a-zA-Z0-1])(?!.)");
		patternSame = Pattern.compile("([a-zA-Z0-1])\\1(?!')");
		patternNegation= Pattern.compile("([a-zA-Z0-1])\\1'(?!.)|([a-zA-Z0-1])'\\2(?!.)");
	}
	
	public String runTestZero(String string, TextView textview){
		mZero = patternZero.matcher(string);
	    if (mZero.find()) {

			// If group #n participated in the match, start(n) will be non-negative.
			if (mZero.start(1) != -1) {
				string  = mZero.replaceAll("0");
				writeToNextLine(textview, "Using A.0 = 0, where A is any variable");

			} else if (mZero.start(2) != -1) {
				string  =mZero.replaceAll("0");
				writeToNextLine(textview, "Using 0.A = 0, where A is any variable");
			}
	    	writeToNextLineSingle(textview, string);
			match = true;
	   } 
	   return string;
	}
	
	public String runTestOne(String string, TextView textview){
	    mOne = patternOne.matcher(string);
	    if (mOne.find()) {

			// If group #n participated in the match, start(n) will be non-negative.
			if (mOne.start(1) != -1) {
				string  = mOne.replaceAll(mOne.group(1));
				writeToNextLine(textview, "Using A.1 = A, where A is any variable");

			} else if (mOne.start(2) != -1) {
				string  = mOne.replaceAll(mOne.group(2));
				writeToNextLine(textview, "Using 1.A = A, where A is any variable");
			}
             writeToNextLineSingle(textview, string);
			match = true;
	   } 
	    return string;
	}
	
	public String runTestSame(String string, TextView textview){
		mSame = patternSame.matcher(string);
	    if (mSame.find()) {
	    	string  = mSame.replaceAll(mSame.group(1));
	    	writeToNextLine(textview, "Using A.A = A, where A is any variable");
	    	writeToNextLineSingle(textview, string);
			match = true;
	   } 
		return string;
	}
	
	public String runTestNegation(String string, TextView textview){
		 mNegation = patternNegation.matcher(string);
		    if (mNegation.find()) {
//
				// If group #n participated in the match, start(n) will be non-negative.
				if (mNegation.start(1) != -1) {
					string  = mNegation.replaceAll("0");
					writeToNextLine(textview, "Using A.A' = 0, where A is any variable");

				} else if (mNegation.start(2) != -1) {
					string  = mNegation.replaceAll("0");
					writeToNextLine(textview, "Using A'.A = 0, where A is any variable");
				}
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
