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


public class Screen2 extends ActionBarActivity {
	Button savedetails;
	EditText name;
	EditText phone;
	EditText email;
	EditText address;
	ProgressDialog progress;
	String out;
	db db;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
        db = new db(getApplicationContext());
        progress = new ProgressDialog(Screen2.this);
        progress.setTitle("Please Wait");
        progress.setMessage("Please wait while we are sending your request...");
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        overridePendingTransition(R.anim.fromleft, R.anim.hold);
        savedetails = (Button)findViewById(R.id.button12);
        name = (EditText)findViewById(R.id.editText12);
        phone = (EditText)findViewById(R.id.editText22);
        email = (EditText)findViewById(R.id.editText32);
        address = (EditText)findViewById(R.id.editText42);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        name.setFocusable(true);
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
							helper.out = helper.out + "\nMy Contact details are:\nName: "+name.getText().toString()+"\nPhone: "+phone.getText().toString()+"\nEmail: "+email.getText().toString()+"\nAddress: "+address.getText().toString();
							helper.out = helper.out + "\n\nRegards\n"+name.getText().toString();
							out = "Your request has been sent. Please wait till the incharge person gets in touch with you." + "\nYour Contact details are:\nName: "+name.getText().toString()+"\nPhone: "+phone.getText().toString()+"\nEmail: "+email.getText().toString()+"\nAddress: "+address.getText().toString();
							out = out + "\n\nRegards\n"+"Maruti Service";
							
							sendmail task = new sendmail();
			                task.execute(new String[] {});
			                
			                
							progress.show();
							
							   
							
						
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
                        helper.out,   
                        helper.mymail,   
                        helper.mymail);   
                String emailt = db.getvalue("email");
                sender.sendMail("Your new request",   
                        out,   
                        helper.mymail,   
                        emailt); 
                progress.dismiss();
            } catch (Exception e) {   
            	progress.dismiss();
                //Log.e("SendMail", e.getMessage(), e);   
            	Toast.makeText(getApplicationContext(), "Request Not sent. Please check your internet connection", Toast.LENGTH_SHORT).show();
            } 
          return "";
        }

        @Override
        protected void onPostExecute(String result) {
        	Toast.makeText(getApplicationContext(), "Your request has been mailed. You will be getting a mail confirmation", Toast.LENGTH_SHORT).show();
        	Intent goToNextActivity = new Intent(Screen2.this,Screen0.class);
			startActivity(goToNextActivity);
        }
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.removeItem(R.id.action_settings);
    	menu.removeGroup(R.id.action_settings);
    	menu.add("Declaration");
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().toString().compareToIgnoreCase("Disclaimer") == 0) {
        	runOnUiThread(new Runnable() {

			    @Override
			    public void run() {

			     if(!isFinishing()){
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(Screen2.this);
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
