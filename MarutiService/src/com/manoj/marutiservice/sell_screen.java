package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class sell_screen extends ActionBarActivity {
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_screen);
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
    }
    public void sell_ac(View view)
    {
    	helper.out = "Sell my AC";
    	Intent goToNextActivity = new Intent(sell_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void sell_water_cooler(View view)
    {
    	helper.out = "Sell my Water Cooler";
    	Intent goToNextActivity = new Intent(sell_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void sell_power_saver(View view)
    {
    	helper.out = "Sell my Power Saver";
    	Intent goToNextActivity = new Intent(sell_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void exchange_ac2(View view)
    {
    	helper.out = "Exchange my AC";
    	Intent goToNextActivity = new Intent(sell_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//menu.add("Declaration");
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent goToNextActivity = new Intent(sell_screen.this,PersonalDetails.class);
			startActivity(goToNextActivity);	
            return true;
        }
        switch (item.getItemId()) 
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }/*
        if (item.getTitle().toString().compareToIgnoreCase("Disclaimer") == 0) {
        	runOnUiThread(new Runnable() {

			    @Override
			    public void run() {

			     if(!isFinishing()){
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(Buy_new.this);
			     builder.setMessage("the services associated with this app are at present available only with in city of chennai and suburbs. intimation regarding other cities to be covered will be given as and when they become operational")
			            .setCancelable(false)
			            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                     //do things
			                }
			            });
			     AlertDialog alert = builder.create();
			     
			     alert.show();
			
		}}});
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.toleft);
        super.onPause();
    }
    
    
    @Override
    public void onResume(){
        // TODO LC: preliminary support for views transitions
        this.overridePendingTransition(R.anim.fromleft, R.anim.toleft);
        super.onResume();
    }
}
