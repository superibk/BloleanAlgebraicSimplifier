package com.iom.solve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class History extends Activity implements AdapterView.OnItemClickListener {

    DatabaseManager database;
    ListView listNote;
    Cursor cr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history);
        database = new DatabaseManager(this);
        database.open();
        listNote = (ListView) findViewById(R.id.listView_history);
        populateListViewFromDatabase();
        listNote.setOnItemClickListener(this);
    }



    public void populateListViewFromDatabase() {
        cr = database.getAllData();
        startManagingCursor(cr);
        // Create an array to specify the fields we want
        String[] from = new String[] {DatabaseManager.KEY_QUESTION, DatabaseManager.KEY_TIME };
        // and an array of the fields we want to bind in the view
        int[] to = new int[] { R.id.item_question_history, R.id.item_time_date };
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list_item, cr, from, to);
        listNote.setAdapter(cursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(History.this, ViewDetail.class);
        Bundle sendid = new Bundle();
        sendid.putLong(DatabaseManager.KEY_ROWID, id);
        i.putExtras(sendid);
        startActivity(i);
    }


    public void onClickClear(View view){


        // an alert box to confirm if the user really wants to delete the record

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to clear the database");
        builder.setTitle("Confirmation");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id) {
                database.deleteDatabase();
                database = new DatabaseManager(History.this);
                database.open();
                populateListViewFromDatabase();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();








    }

    public void onClickBack(View view){
        finish();
    }
}
