package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
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


public class Screen1 extends ActionBarActivity {
	Spinner screen1items;
	String selected = "";
	Button next;
	Spinner man;
	TextView tv;
	String manselected;
	String[] manu = {"Select One","Voltas","Samsung","National","Whirlpool"};
	String[] items = {"Select One","Yes","No"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        tv = (TextView)findViewById(R.id.TextView01);
        next = (Button) findViewById(R.id.next1);
        next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selected.compareTo("")==0||selected.compareToIgnoreCase("Select One")==0)
				{
					Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_LONG).show();
				}
				else
				{
					if((selected.compareToIgnoreCase("Yes")==0)&&(manselected.compareToIgnoreCase("Select One")==0))
					{
						Toast.makeText(getApplicationContext(), "Please select manufacturer", Toast.LENGTH_LONG).show();
					}
					else
					{
						if(selected.compareToIgnoreCase("Yes")==0)
							helper.out = helper.out + "\n" +"My AC is in warranty and my manufacturer is: " + manselected;
						else
							helper.out = helper.out + "\n" +"My AC is not in warranty";
						Intent goToNextActivity = new Intent(Screen1.this,Screen2.class);
						startActivity(goToNextActivity);	
					}
					
					
				}
				
			}
		});
        screen1items = (Spinner) findViewById(R.id.screen1items);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
          android.R.layout.simple_spinner_item, items);
        adapter_state
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        screen1items.setAdapter(adapter_state);
        screen1items.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				screen1items.setSelection(arg2);
				selected = (String) screen1items.getSelectedItem();
				if(selected.compareToIgnoreCase("Yes")==0)
				{
					tv.setVisibility(0);
					man.setVisibility(0);
				}
				else
				{
					tv.setVisibility(4);
					man.setVisibility(4);
				}
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        man = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter<String> adapter_state2 = new ArrayAdapter<String>(this,
          android.R.layout.simple_spinner_item, manu);
        adapter_state
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        man.setAdapter(adapter_state2);
        man.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				man.setSelection(arg2);
				manselected = (String) man.getSelectedItem();
		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
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
        	Intent goToNextActivity = new Intent(Screen1.this,PersonalDetails.class);
			startActivity(goToNextActivity);	
            return true;
        }
        if (item.getTitle().toString().compareToIgnoreCase("Disclaimer") == 0) {
        	runOnUiThread(new Runnable() {

			    @Override
			    public void run() {

			     if(!isFinishing()){
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(Screen1.this);
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
        }
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
