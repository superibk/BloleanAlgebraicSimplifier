package com.iom.solve;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends Activity {

	Typeface font;
	TextView splashText, splashText2;

// learning made easy


	Handler handler;
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			
			splashText2.setText("L");

		}
	};

	Runnable runnable2 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "ea");

		}
	};

	Runnable runnable3 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "rni");

		}
	};

	Runnable runnable4 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "ng");

		}
	};

	Runnable runnable5= new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + " ");
		}
	};

	Runnable runnable6 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");

		}
	};

	Runnable runnable7 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable8 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "M");
		}
	};

	Runnable runnable9 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "ad");
		}
	};

	Runnable runnable10 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "e E");
		}
	};

	Runnable runnable11= new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "asy");
		}
	};

	Runnable runnable12 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");

		}
	};

	Runnable runnable13 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable14 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable15 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable16 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable17 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable18 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};

	Runnable runnable19 = new Runnable() {

		@Override
		public void run() {
			splashText2.setText(splashText2.getText().toString() + "");
		}
	};




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash_screen);
		handler = new Handler();
		splashText = (TextView)findViewById(R.id.slash_test);
		splashText2 =  (TextView)findViewById(R.id.slash_test2);
		font =  Typeface.createFromAsset(this.getAssets(), "CROCHET PATTERN.ttf");
        splashText.setTypeface(font);

		
	Thread myTread = new Thread(){
		public void run() {
			try {

				sleep(200);
				handler.post(runnable);
				sleep(200);
				handler.post(runnable2);
				sleep(200);
				handler.post(runnable3);
				sleep(200);
				handler.post(runnable4);
				sleep(200);
				handler.post(runnable5);
				sleep(200);
				handler.post(runnable6);
				sleep(200);
				handler.post(runnable7);
				sleep(200);
				handler.post(runnable8);
				sleep(200);
				handler.post(runnable9);
				sleep(200);
				handler.post(runnable10);
				sleep(200);
				handler.post(runnable11);
				sleep(200);
				handler.post(runnable12);
				sleep(200);
				handler.post(runnable13);
				sleep(200);
				handler.post(runnable14);
				sleep(200);
				handler.post(runnable15);
				sleep(200);
				handler.post(runnable16);
				sleep(200);
				handler.post(runnable17);
				sleep(200);
				handler.post(runnable18);
				sleep(200);
				handler.post(runnable19);
				
				Intent changeActivity = new Intent(SplashScreen.this, Menu.class);
				startActivity(changeActivity);
				finish();
			              }
			catch (InterruptedException e) {}
	 	};

	};
	 myTread.start();
	}
	
	
	
	
}
