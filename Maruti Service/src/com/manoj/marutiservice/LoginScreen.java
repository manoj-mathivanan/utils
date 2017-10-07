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
