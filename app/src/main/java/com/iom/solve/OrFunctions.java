package com.iom.solve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class OrFunctions {
	

	Pattern patternZero, patternOne, patternSame, patternNegation, patternAB;
	Matcher mZero, mOne, mSame, mNegation, mAB;
	boolean match;




	public boolean checkIfMatch(){
		return  match;
	}


	public OrFunctions(){
	// this section will intiallize all the pattern for the fuctions
		// this represent A+0 = A or 0 + A= A
		match = false;

		patternZero = Pattern.compile("([a-zA-Z0-1])\\+0(?!.)|0\\+([a-zA-Z0-1])(?!.)");
		patternOne= Pattern.compile("([a-zA-Z0-1])\\+1(?!.)|1\\+([a-zA-Z0-1])(?!.)");
		patternSame = Pattern.compile("([a-zA-Z0-1]+)\\+\\1(?!('|[a-zA-Z0-1]))");
		patternNegation= Pattern.compile("([a-zA-Z0-1])\\+\\1'(?!.)|([a-zA-Z0-1])'\\+\\2(?!.)");
		patternAB= Pattern.compile("([a-zA-Z0-1])\\+\\1[a-zA-Z0-1](?!.)|([a-zA-Z0-1])\\+[a-zA-Z0-1]\\2(?!.)|([a-zA-Z0-1])[a-zA-Z0-1]\\+\\3(?!.)|[a-zA-Z0-1]([a-zA-Z0-1])\\+\\4(?!.)");
  }
	
	public String runTestZero(String string, TextView textview){
		mZero = patternZero.matcher(string);
	    if (mZero.find()) {

			// If group #n participated in the match, start(n) will be non-negative.
			if (mZero.start(1) != -1) {
				string  = mZero.replaceAll(mZero.group(1));
	    		writeToNextLine(textview, "Using A+0 = A, where A is any variable");

			} else if (mZero.start(2) != -1) {
				string  = mZero.replaceAll(mZero.group(2));
	    	    writeToNextLine(textview, "Using 0+A = A, where A is any variable");
			}
//			startNumber = mZero.start();
//			endNumber = mZero.end();
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
				string  = mOne.replaceAll("1");
				writeToNextLine(textview, "Using A + 1 = 1, where A is any variable");

			} else if (mOne.start(2) != -1) {
				string  = mOne.replaceAll("1");
				writeToNextLine(textview, "Using 1 + A = 1, where A is any variable");
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
	    	writeToNextLine(textview, "Using A + A = A, where A is any variable");
	    	writeToNextLineSingle(textview, string);
			match = true;
	   } 
		return string;
	}
	
	public String runTestNegation(String string, TextView textview){
		 mNegation = patternNegation.matcher(string);
		    if (mNegation.find()) {

				// If group #n participated in the match, start(n) will be non-negative.
				if (mNegation.start(1) != -1) {
					string  = mNegation.replaceAll("1");
					writeToNextLine(textview, "Using A + A' = 1, where A is any variable");

				} else if (mNegation.start(2) != -1) {
					string  = mNegation.replaceAll("1");
					writeToNextLine(textview, "Using A' + A = 1, where A is any variable");
				}
				writeToNextLineSingle(textview, string);
				match = true;
		   } 
		return string;
	}
	
	public String runTestAB(String string, TextView textview){
		 mAB = patternAB.matcher(string);
		    if (mAB.find()) {

				// If group #n participated in the match, start(n) will be non-negative.
				if (mAB.start(1) != -1 ) {
					string  = mAB.replaceAll(mAB.group(1));
					writeToNextLine(textview, "Using A + AB = A, where A and B are any variable");
				} else if (mAB.start(2) != -1) {
					string  = mAB.replaceAll(mAB.group(2));
					writeToNextLine(textview, "Using A + BA = A, where A and B are any variable");
				} else if (mAB.start(3) != -1) {
					string  = mAB.replaceAll(mAB.group(3));
					writeToNextLine(textview, "Using AB + A = A, where A and B are any variable");
				}else if (mAB.start(4) != -1) {
					string  = mAB.replaceAll(mAB.group(4));
					writeToNextLine(textview, "Using BA + A = A, where A and B are any variable");
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



}
