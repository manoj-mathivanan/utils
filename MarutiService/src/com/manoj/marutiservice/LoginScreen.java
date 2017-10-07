package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LoginScreen extends ActionBarActivity {

	Button explore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        db db = new db(getApplicationContext());
        if(db.getvalue("name").compareToIgnoreCase("")!=0)
        {
        	Intent goToNextActivity = new Intent(LoginScreen.this,first_screen.class);
			startActivity(goToNextActivity);	
        }else{
        db.cleardetails("screen0");
        db.addDetail("name", "");
        db.addDetail("email", "");
        db.addDetail("phone", "");
        db.addDetail("address", "");
        /*db.addDetail("screen0", "Select One");
        db.addDetail("screen0", "I want to buy an AC. Please get in touch with me");
        db.addDetail("screen0", "I want to sell my old AC and buy new AC");
        db.addDetail("screen0", "I want to buy water dispenser/water cooler/deepfreezer etc. Call Me");
        db.addDetail("screen0", "I want to service my AC. Please call me");
        db.addDetail("screen0", "My AC is in amc with you. Please call and come");
        db.addDetail("screen0", "I want to cover my acs with annual maintenance contracts. Please call for details");
        db.addDetail("screen0", "I want to buy energy saver to save 30% on my electricity bill. Please call me");
        db.addDetail("screen0", "I want to remove my existing ac and fix it in a new place");
        db.addDetail("screen0", "Others");*/
        }
        setContentView(R.layout.activity_login_screen);
        
        explore = (Button)findViewById(R.id.button1);
    	explore.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				Intent goToNextActivity = new Intent(LoginScreen.this,PersonalDetails.class);
				startActivity(goToNextActivity);				
			}
		});
    }

}
