package com.manoj.marutiservice;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class final_screen extends ActionBarActivity {
	Button savedetails;
	EditText name;
	EditText phone;
	EditText email;
	EditText address;
	EditText reason;
	ProgressDialog progress;
	String complainout;
	String customerout;
	db db;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_screen);
        db = new db(getApplicationContext());
        progress = new ProgressDialog(final_screen.this);
        progress.setTitle("Please Wait");
        progress.setMessage("Please wait while we are sending your request...");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        savedetails = (Button)findViewById(R.id.finalsubmit);
        name = (EditText)findViewById(R.id.finalname);
        phone = (EditText)findViewById(R.id.finalphone);
        email = (EditText)findViewById(R.id.finalemail);
        address = (EditText)findViewById(R.id.finaladdress);
        reason = (EditText)findViewById(R.id.finalreason);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        name.setFocusable(true);
        reason.setText(helper.out);
        name.setText(db.getvalue("name"));
        phone.setText(db.getvalue("phone"));
        email.setText(db.getvalue("email"));
        address.setText(db.getvalue("address"));
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
							
							db.cleardetails("name");
							db.cleardetails("phone");
							db.cleardetails("email");
							db.cleardetails("address");
							db.addDetail("name", name.getText().toString());
							db.addDetail("phone", phone.getText().toString());
							db.addDetail("email", email.getText().toString());
							db.addDetail("address", address.getText().toString());
							complainout = "Hi,\n\nI want to:\n" + helper.out + ".\nMy Contact details are:\nName: "+name.getText().toString()+"\nPhone: "+phone.getText().toString()+"\nEmail: "+email.getText().toString()+"\nAddress: "+address.getText().toString() + "\n\nRegards\n"+name.getText().toString();
							customerout = "Hi "+ name.getText().toString() +",\n\nYour request to:\n" + helper.out + " has been submitted.\nPlease wait till the incharge person gets in touch with you." + "\nYour Contact details are:\nName: "+name.getText().toString()+"\nPhone: "+phone.getText().toString()+"\nEmail: "+email.getText().toString()+"\nAddress: "+address.getText().toString() + "\n\nRegards\n"+"Maruti Service";
							progress.show();
							sendmail task = new sendmail();
			                task.execute(new String[] {});
						
						}
					}
				}
			}
		});
    }
    
    private class sendmail extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... numbers) {
        	try {   
                GmailSender sender = new GmailSender(helper.mymail, helper.password);
                sender.sendMail("New Request from app",   
                        complainout,   
                        helper.mymail,   
                        helper.mymail);   
                String emailt = db.getvalue("email");
                sender.sendMail("Your new request",   
                        customerout,   
                        helper.mymail,   
                        emailt); 
                progress.dismiss();
                return "success";               	
            } catch (Exception e) {   
            	progress.dismiss();
                return "fail";
            } 

        }

        @Override
        protected void onPostExecute(String result) {
        	if(result.compareToIgnoreCase("success")==0)
        	{
        	AlertDialog.Builder builder1 = new AlertDialog.Builder(final_screen.this);
            builder1.setMessage("Your request has been mailed. You will be getting a mail confirmation");
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	//Toast.makeText(getApplicationContext(), "Your request has been mailed. You will be getting a mail confirmation", Toast.LENGTH_SHORT).show();
                	Intent goToNextActivity = new Intent(final_screen.this,first_screen.class);
        			startActivity(goToNextActivity);
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        	}
        	else
        	{
        		//Toast.makeText(getApplicationContext(), "Request Not sent. Please check your internet connection", Toast.LENGTH_SHORT).show();
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(final_screen.this);
                builder1.setMessage("Request Not sent. Please check your internet connection");
                builder1.setCancelable(true);
                builder1.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
        	}
        }
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//menu.add("Declaration");
        //getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent goToNextActivity = new Intent(final_screen.this,PersonalDetails.class);
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
