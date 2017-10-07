package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils.TruncateAt;
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


public class first_screen extends ActionBarActivity {
	TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        helper.out = "";
        tv1 = (TextView) this.findViewById(R.id.mywidget1first_screen);  
        tv1.setSelected(true);
        tv1.setEllipsize(TruncateAt.MARQUEE);
        tv1.setSingleLine(true);
    }
    
    public void buy_screen(View view)
    {
    	Intent goToNextActivity = new Intent(first_screen.this,buy_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void sell_screen(View view)
    {
    	Intent goToNextActivity = new Intent(first_screen.this,sell_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void service_screen(View view)
    {
    	Intent goToNextActivity = new Intent(first_screen.this,service_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void opt_amc(View view)
    {
    	helper.out = "Opt for AMC";
    	Intent goToNextActivity = new Intent(first_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void relocate_ac(View view)
    {
    	helper.out = "Relocate my AC";
    	Intent goToNextActivity = new Intent(first_screen.this,final_screen.class);
		startActivity(goToNextActivity);	
    }
    
    public void others_call(View view)
    {
    	Intent goToNextActivity = new Intent(first_screen.this,others_screen.class);
		startActivity(goToNextActivity);	
    }
    
    
    @Override
    public void onBackPressed()
    {
    	moveTaskToBack(true);
	    return;
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
        	Intent goToNextActivity = new Intent(first_screen.this,PersonalDetails.class);
			startActivity(goToNextActivity);	
            return true;
        }/*
        if (item.getTitle().toString().compareToIgnoreCase("Disclaimer") == 0) {
        	        	runOnUiThread(new Runnable() {

			    @Override
			    public void run() {
			     if(!isFinishing()){
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(Screen0.this);
			     builder.setMessage("The services associated with this app are at present available only with in city of chennai and suburbs. Intimation regarding other cities to be covered will be given as and when they become operational")
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
