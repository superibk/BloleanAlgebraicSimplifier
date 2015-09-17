package com.iom.solve;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Simplify extends Activity {
	
	EditText questionBox;
	TextView txtSolution;
	ArrayList<String> storedTokenList;
	Pattern pattern , pattern2, pattern3, validate;
	String resultFromBox, simplifliedResult;
    DatabaseManager database;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_simplify);
        database = new DatabaseManager(this);
        database.open();
		questionBox = (EditText)findViewById(R.id.question_box);
		txtSolution = (TextView)findViewById(R.id.txt_solution);
		storedTokenList =  new ArrayList<String>();
		// pattern and pattern2 are for simplification of the expression first, while pattern3 is for any bracket that
		// has no effect when removed

		// test if the text is a valid algebraic expression
		validate = Pattern.compile("[\\(\\)\\+'0-1a-zA-Z]+");
		pattern = Pattern.compile("([a-zA-Z0-1]+)\\(([a-zA-Z0-1]+(\\+[a-zA-Z0-1]+)+)\\)");
		pattern2 = Pattern.compile("\\(([a-zA-z0-1]+(\\+[a-zA-Z0-1]+)+)\\)\\(([a-zA-z0-1]+(\\+[a-zA-Z0-1]+)+)\\)");
		pattern3 = Pattern.compile("\\(.+\\)");
	}
	

public void simplify(View view){
	/* The function will simplify all from of expression and remove bracket */
	resultFromBox = questionBox.getText().toString();
	simplifliedResult =  resultFromBox;
	// this will test if its a valid regular expression

	if (testForValidation() == true) {

		while(runDemorgans()){

			//demorgan's code will run until there is nothing to simplify anymore
		}

		// this set of code will first simplify and remove or form of bracket
		while (isBracketPresent()){
			simplificationProcessOne();
			simplificationProcessTwo();
			removeEmptyBracket();
		}
		// the next two line of code, will simplify operations with AND and OR

		while (runAllAndFunction() || runAllOrFunction()){

		}

        logToDatabase(resultFromBox, getTime(), txtSolution.getText().toString());

	}
	else {
        Toast.makeText(Simplify.this, "Invalid Algebraic Expression", Toast.LENGTH_LONG).show();
   }


}


public void simplificationProcessOne(){
	// resultFromBox = resultFromBox.replaceAll("[a-zA-Z]+\\([a-zA-Z]+(\\+[a-zA-Z]+)+\\)", "matched successfully");
		// this function will extract a sub-string from bigger string using regex
		 Matcher m = pattern.matcher(simplifliedResult);
	    if (m.find()) {
	    	// this set of code will obtain the content inside and outside the bracket, send it for simplification
	    	//and return a simplified expression
	    	SimplificationClass exp = new SimplificationClass(m.group(1), m.group(2));
	    	simplifliedResult  = m.replaceFirst(exp.getSimplifiedResult());
	    	writeToNextLine("Expansion:");
	    	writeToNextLineSingle(simplifliedResult);
	   }    
}

public void simplificationProcessTwo(){
	
	// this set of code will expand  an expression of this form (a+b)(c+d)....(e+f)
		 Matcher m2 = pattern2.matcher(simplifliedResult);
	    if (m2.find()) {
	    	
	    	Toast.makeText(Simplify.this, "content1: "+ m2.group(1)+ "Content3: " + m2.group(3), Toast.LENGTH_LONG).show();
	    	SimplificationClassTwo exp = new SimplificationClassTwo(m2.group(1), m2.group(3));
	    	simplifliedResult  = m2.replaceFirst(exp.getSimplifiedResult());
	    	
	    	writeToNextLine("Expansion:");
	    	writeToNextLineSingle(simplifliedResult);
	   }    
}

public boolean isBracketPresent(){
	boolean booleanOutput = false;
	Matcher m3 = pattern3.matcher(simplifliedResult);
	if(m3.find()) {
        booleanOutput = true;
    }
	return booleanOutput;
}

	public boolean testForValidation(){
		boolean result = false;
		Matcher confirm = validate.matcher(simplifliedResult);
		// the matches function will match the entire string instead of part of the string as find()
		if(confirm.matches()) {
			result = true;
		}
		return  result;
	}




public void removeEmptyBracket(){
	//case1, if the  bracket is in the middle of the expression
	simplifliedResult = simplifliedResult.replaceAll("\\+\\(([^)2-9]+)\\)\\+", "+$1+");
	// case2, if the bracket is starting from the beginning of the expression
	simplifliedResult = simplifliedResult.replaceAll("^\\(([^)2-9]+)\\)\\+", "$1+");
	//case3, if the bracket is ending the expression
	simplifliedResult = simplifliedResult.replaceAll("\\+\\(([^)2-9]+)\\)$", "+$1");
	writeToNextLine("Remove Bracket");
	writeToNextLineSingle(simplifliedResult);
  }


public boolean runDemorgans(){
	Demorgan demorganFunctions = new Demorgan();
	simplifliedResult = demorganFunctions.runCombineSplit(simplifliedResult, txtSolution);
	simplifliedResult = demorganFunctions.runSplitCombine(simplifliedResult, txtSolution);
	return demorganFunctions.checkIfMatch();
}

public boolean runAllAndFunction(){
	
// this will call the AndFunctions
	AndFunctions andFuctions = new AndFunctions();
	simplifliedResult = andFuctions.runTestZero(simplifliedResult, txtSolution);
	simplifliedResult = andFuctions.runTestOne(simplifliedResult, txtSolution);
	simplifliedResult = andFuctions.runTestSame(simplifliedResult, txtSolution);
	simplifliedResult = andFuctions.runTestNegation(simplifliedResult, txtSolution);
	return andFuctions.checkIfMatch();
}

public boolean runAllOrFunction(){
	
	// this will calll the AndFunctions
	OrFunctions orFuctions = new OrFunctions();
		simplifliedResult = orFuctions.runTestZero(simplifliedResult, txtSolution);
		simplifliedResult = orFuctions.runTestOne(simplifliedResult, txtSolution);
		simplifliedResult = orFuctions.runTestSame(simplifliedResult, txtSolution);
		simplifliedResult = orFuctions.runTestNegation(simplifliedResult, txtSolution);
		simplifliedResult = orFuctions.runTestAB(simplifliedResult, txtSolution);
	   return orFuctions.checkIfMatch();
	}


public void writeToNextLine(String nextText){
	String text = txtSolution.getText().toString();
//	txtSolution.setText(text + "\n\n" + nextText);
    String result = text + "\n\n" + nextText;
    int lenght = result.length();

    txtSolution.setText(colorChange( result,0 ,lenght-1));
}

public void writeToNextLineSingle(String nextText){
	String text = txtSolution.getText().toString();
//	txtSolution.setText(text + "\n" + nextText);
    String result = text + "\n" + nextText;
    int lenght_new = result.length();

    txtSolution.setText(colorChange( result,lenght_new/2 ,lenght_new -1));
}

    public SpannableStringBuilder colorChange(String textToChange, int start,int  end){
        final SpannableStringBuilder sb = new SpannableStringBuilder(textToChange);
        // Span to set text color to some RGB value
        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);
        // Span to make text bold
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        // Set the text color for first 4 characters
        sb.setSpan(fcs, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        // make them also bold
        sb.setSpan(bss, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }







    public void onClickClear(View view){
	txtSolution.setText("");
	questionBox.setText("");
}

	public void logToDatabase(String question, String time, String result){
        database.createRecords(question, time, result);

    }


    public String getTime(){
        Time dtNow = new Time();
        dtNow.setToNow();
        return dtNow.format("%Y.%m.%d  %H:%M");    // YYYYMMDDTHHMMSS
    }


	
	
	
	
}
