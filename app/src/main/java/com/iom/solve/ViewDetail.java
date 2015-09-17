package com.iom.solve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


public class ViewDetail extends Activity {
    DatabaseManager database;
    Long id;
    TextView time, date, question, answer;
    String timeString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_detail);
        id = getIntent().getExtras().getLong(database.KEY_ROWID);
        time = (TextView)findViewById(R.id.time_view);
        date = (TextView) findViewById(R.id.date_view);
        question = (TextView) findViewById(R.id.question_view);
        answer = (TextView) findViewById(R.id.answer_view);
        database = new DatabaseManager(this);
        database.open();
        timeString = database.getTime(id);
        time.setText(timeString.trim().split(" ")[1]);
        date.setText(timeString.trim().split(" ")[2]);
        question.setText(database.getQuestion(id));
        answer.setText(database.getResult(id));
        database.close();

    }



    public void onCLickDelete(View view){
        // an alert box to confirm if the user really wants to delete the record
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete" + " " + question.getText().toString());
        builder.setTitle("Confirmation");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Long deleteRowID = ViewDetail.this.id;
                database.open();
                database.deleteRecord(deleteRowID);
                database.close();
                Toast.makeText(ViewDetail.this, "Deleted Succesfully", Toast.LENGTH_LONG).show();
                finish();

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
