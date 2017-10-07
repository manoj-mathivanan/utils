package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter.LengthFilter;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PersonalDetails extends ActionBarActivity {
	Button savedetails;
	EditText name;
	EditText phone;
	EditText email;
	EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personaldetails);
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        savedetails = (Button)findViewById(R.id.button1);
        name = (EditText)findViewById(R.id.editText1);
        phone = (EditText)findViewById(R.id.editText2);
        email = (EditText)findViewById(R.id.editText3);
        address = (EditText)findViewById(R.id.editText4);
        name.setFocusable(true);
     
    	savedetails.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				if(name.getText().toString().compareToIgnoreCase("")==0)
				{
					Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(phone.getText().toString().compareToIgnoreCase("")==0)
					{
						Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
					}
					else
					{
						if(email.getText().toString().compareToIgnoreCase("")==0)
						{
							Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
						}
						else
						{
							db db = new db(getApplicationContext());
							db.addDetail("name", name.getText().toString());
							db.addDetail("phone", phone.getText().toString());
							db.addDetail("email", email.getText().toString());
							db.addDetail("address", address.getText().toString());
							Toast.makeText(getApplicationContext(), "Your Details have been saved", Toast.LENGTH_SHORT).show();
						Intent goToNextActivity = new Intent(PersonalDetails.this,Screen0.class);
						startActivity(goToNextActivity);
						}
					}
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.removeItem(R.id.action_settings);
    	menu.removeGroup(R.id.action_settings);
    	menu.add("Disclaimer");
        getMenuInflater().inflate(R.menu.login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().toString().compareToIgnoreCase("Disclaimer") == 0) {
        	runOnUiThread(new Runnable() {

			    @Override
			    public void run() {

			     if(!isFinishing()){
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(PersonalDetails.this);
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
