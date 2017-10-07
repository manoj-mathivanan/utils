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


public class Screen0 extends ActionBarActivity {
	Spinner screen0items;
	String selected = "";
	Button next;
	TextView tv1;
	 private String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen0);
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        db db = new db(getApplicationContext());
        items = new String[10];
        items = db.getvalues("screen0");
        tv1 = (TextView) this.findViewById(R.id.mywidget1);  
        tv1.setSelected(true);
        tv1.setEllipsize(TruncateAt.MARQUEE);
        tv1.setSingleLine(true);
       
        next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selected.compareTo("")==0||selected.compareToIgnoreCase("Select One")==0)
				{
					Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_LONG).show();
				}
				else
				{
					if(selected.compareToIgnoreCase("I want to service my AC. Please call me")==0)
					{
						helper.out = "Hi,\n\n" + selected;
						Intent goToNextActivity = new Intent(Screen0.this,Screen1.class);
						startActivity(goToNextActivity);	
					}
					else
					{
						helper.out = "Hi,\n\n" + selected;
						Intent goToNextActivity = new Intent(Screen0.this,Screen2.class);
						startActivity(goToNextActivity);
					}
				}
				
			}
		});
        screen0items = (Spinner) findViewById(R.id.screen0items);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
          android.R.layout.simple_spinner_item, items);
        adapter_state
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        screen0items.setAdapter(adapter_state);
        screen0items.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				screen0items.setSelection(arg2);
				selected = (String) screen0items.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    @Override
    public void onBackPressed()
    {
    	moveTaskToBack(true);
	    return;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add("Declaration");
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent goToNextActivity = new Intent(Screen0.this,PersonalDetails.class);
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
