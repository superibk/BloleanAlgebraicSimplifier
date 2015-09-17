package com.iom.solve;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Instruction extends Activity{

    ListView list_instruction;
    String[] displaymenu ={
            "Use character \" \' \" for all form of negation, and should always be come after the " +
                    "character implied. examples a', b', (c+d)' ",
            "Do not add space after any character",
            "Invalid character will render the whole expression invalid",
            "Lower and Upper case character are accepted",
            "Place two or more character together to perform a logical AND operation(s) " +
                    "characters such as \" * \" \".\" or \" & \" can not be use" +
                    "examples: use ab not a*b ",
            "Use the plus symbol(+) for logical OR operations, example,  ",
            "All alphabetic letters are valid character",
            "Demorgan's law is as stated 1. (a+b)' = a'b' 2. (ab)' = a'+b' "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_instruction);
        list_instruction = (ListView) findViewById(R.id.listView_instruction);

        // set a custom shadow that overlays the main content when the drawer opens

        // set up the drawer's list view with items and click listener
        list_instruction.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, displaymenu));



    }


}
