package com.iom.solve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
	}
	
	
	
	public void onPressSimplify(View view){
		Intent changeActivity = new Intent(Menu.this, Simplify.class);
		startActivity(changeActivity);
	}
	
	public void onPressAbout(View view){
		Intent changeActivity = new Intent(Menu.this, About.class);
		startActivity(changeActivity);
	}

	public void onPressHistory(View view){
		Intent changeActivity = new Intent(Menu.this, History.class);
		startActivity(changeActivity);
	}


	public  void onClickExit(View view){
	// an alert box to confirm if the user really wants to delete the record

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit" );
		builder.setTitle("Confirmation");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int id) {
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

	public void onclickInstruction(View view){
		Intent changeActivity = new Intent(Menu.this, Instruction.class);
		startActivity(changeActivity);

	}

	
}
