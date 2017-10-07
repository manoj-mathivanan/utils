package com.manoj.sapmenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.manoj.sapmenu.R.id;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
	String dayOfTheWeek;
	String when="Lunch";
	String menu_date="";
	String[] numbers = new String[] { 
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"};
	GridView gridView;
	ArrayAdapter<String> adapter2;
	TextView textview;
	 Spinner dropdown;
	ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       dropdown = (Spinner)findViewById(R.id.spinner1);
       progress = new ProgressDialog(MainActivity.this);
       progress.setTitle("Please Wait");
       progress.setMessage("Refreshing the menu..");
        
       textview = (TextView) findViewById(R.id.textView11);
       gridView = (GridView) findViewById(R.id.gridView1);
        
        String[] items = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        
        Date d = new Date();
        int da = d.getDay();
        switch (da){
    	case 1:
    		dayOfTheWeek="Monday";
    		break;
    	case 2:
    		dayOfTheWeek="Tuesday";
    		break;
    	case 3:
    		dayOfTheWeek="Wednesday";
    		break;
    	case 4:
    		dayOfTheWeek="Thursday";
    		break;
    	case 5:
    		dayOfTheWeek="Friday";
    		break;
    	default:
    		dayOfTheWeek = "Monday";
    		da=1;
    		break;
    	}
       
        int hours = d.getHours();

        if(hours>19)
        	when = "Dinner";
        else if(hours>14)
        	when = "Snacks";
        else
        	when = "Lunch";
        
        load_UI(dayOfTheWeek, when);
        
        
        dropdown.setAdapter(adapter);
        
        dropdown.setSelection(da-1);
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				dayOfTheWeek = dropdown.getSelectedItem().toString();
				load_UI(dayOfTheWeek,when);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});
        
        Button blunch = (Button) findViewById(R.id.button1);
        blunch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				when = "Lunch";
				load_UI(dayOfTheWeek,when);
			}
		});
        
        Button bsnacks = (Button) findViewById(R.id.button2);
        bsnacks.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				when = "Snacks";
				load_UI(dayOfTheWeek,when);
			}
		});
        
        Button bdinner = (Button) findViewById(R.id.button3);
        bdinner.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				when = "Dinner";
				load_UI(dayOfTheWeek,when);
			}
		});
        
        
        
        
		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);
 
		gridView.setAdapter(adapter2);
	try{

		String input = menu_date;
		String format = "yyyyMMdd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(input);

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		int menu_week = cal1.get(Calendar.WEEK_OF_YEAR);
		Calendar cal2 = Calendar.getInstance();
		int diff = cal2.get(Calendar.WEEK_OF_YEAR) - menu_week;
		
		if(diff!=0)
		{
		DownloadTask downloadTask = new DownloadTask(MainActivity.this);
    	downloadTask.execute("https://sapmenu5hanamobile.neo.ondemand.com/sapmenu6");
		}
	}catch(Exception e)
	{e.printStackTrace();}
    	
		
    }


    protected void load_UI(String dayOfTheWeek2, String when2) {
		// TODO Auto-generated method stub
    	DataBaseHandler db = new DataBaseHandler(MainActivity.this);
    	ArrayList<Items> items = db.getAllItems(dayOfTheWeek, when);
        numbers=new String[items.size()*2];
        int j=0;
        if(items.size()>0)
        {
        	String str = items.get(0).getDate();
        	textview.setText(items.get(0).getDate());
        	menu_date=str.substring(str.length()-4);
        	if(str.contains("Jan"))
        		menu_date = menu_date+"01";
        	if(str.contains("Feb"))
        		menu_date = menu_date+"02";
        	if(str.contains("Mar"))
        		menu_date = menu_date+"03";
        	if(str.contains("Apr"))
        		menu_date = menu_date+"04";
        	if(str.contains("May"))
        		menu_date = menu_date+"05";
        	if(str.contains("Jun"))
        		menu_date = menu_date+"06";
        	if(str.contains("Jul"))
        		menu_date = menu_date+"07";
        	if(str.contains("Aug"))
        		menu_date = menu_date+"08";
        	if(str.contains("Sep"))
        		menu_date = menu_date+"09";
        	if(str.contains("Oct"))
        		menu_date = menu_date+"10";
        	if(str.contains("Nov"))
        		menu_date = menu_date+"11";
        	if(str.contains("Dec"))
        		menu_date = menu_date+"12";
        	menu_date = menu_date + str.substring(0, 2);
        }
        for(int i=0;i<items.size();i++)
        {        	
        numbers[j] = items.get(i).getItemName();
        j++;
        numbers[j] = items.get(i).getCalories() + " KCal";
        j++;
        }
        
        gridView.invalidateViews();
        
        List<String> list = new ArrayList<String>(Arrays.asList(numbers));

        adapter2 = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, list);
	    //adapter2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, numbers);
		adapter2.notifyDataSetChanged();
		gridView.setAdapter(adapter2);
		
		//gridView.setColumnWidth(200);
	}


	private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
            progress.show();
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                int fileLength = connection.getContentLength();

                input = connection.getInputStream();
                output = new FileOutputStream("/storage/emulated/0/menu.xls");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user 
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                 getClass().getName());
            mWakeLock.acquire();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false

        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
           
            if (result != null)
                Toast.makeText(context,"Download error", Toast.LENGTH_LONG).show();
            
            
                //FileInputStream file = new FileInputStream(new File("//mnt/sdcard/data/menu.xls"));

            DataBaseHandler db = new DataBaseHandler(context);
            db.droptables();
            File inputWorkbook = new File("/storage/emulated/0/menu.xls");
            if(inputWorkbook.exists()){
                Workbook w;
                try {
                    w = Workbook.getWorkbook(inputWorkbook);
                    // Get the first sheet
                    {
                    Sheet sheet = w.getSheet(0);
                    int ij = sheet.getRows();
                    String date = "";
                    // Loop over column and lines
                    for (int j = 0; j < ij; j++) {
                        Cell cell = sheet.getCell(0, j);
                        if(cell.getContents().equalsIgnoreCase("Monday")){
                        	
                            for (int i =j+2 ; i < ij; i++) 
                            {
                            	for(int k=1;k<15;k=k+3)
                            	{
                            		date = sheet.getCell(k, j).getContents();
                            		String day="";
                            	switch (k){
                            	case 1:
                            		day="Monday";
                            		break;
                            	case 4:
                            		day="Tuesday";
                            		break;
                            	case 7:
                            		day="Wednesday";
                            		break;
                            	case 10:
                            		day="Thursday";
                            		break;
                            	case 13:
                            		day="Friday";
                            		break;
                            	}
                            	
                            	if(sheet.getCell(k, i).getContents()!="")
                            	{
                            		if(sheet.getCell(k, i).getContents().contains("/"))
                            		{
                            			db.addItem(new Items(sheet.getCell(k, i).getContents().split("/")[0],sheet.getCell(k+1, i).getContents().split("/")[0],day,"Lunch",date));
                            			db.addItem(new Items(sheet.getCell(k, i).getContents().split("/")[1],sheet.getCell(k+1, i).getContents().split("/")[1],day,"Lunch",date));
                            		}
                            		else
                            		{
                            			db.addItem(new Items(sheet.getCell(k, i).getContents(),sheet.getCell(k+1, i).getContents(),day,"Lunch",date));
                            		}
                            	}
                            	}
                            }  
                            break;
                        }
                    }
                    }
                    
                    {
                        Sheet sheet = w.getSheet(1);
                        int ij = sheet.getRows();
                        String date = "";
                        // Loop over column and lines
                        for (int j = 0; j < ij; j++) {
                            Cell cell = sheet.getCell(0, j);
                            if(cell.getContents().equalsIgnoreCase("Monday")){
                            	
                                for (int i =j+2 ; i < ij; i++) 
                                {
                                	for(int k=0;k<9;k=k+2)
                                	{
                                		date = sheet.getCell(k+1, j).getContents();
                                		String day="";
                                	switch (k){
                                	case 0:
                                		day="Monday";
                                		break;
                                	case 2:
                                		day="Tuesday";
                                		break;
                                	case 4:
                                		day="Wednesday";
                                		break;
                                	case 6:
                                		day="Thursday";
                                		break;
                                	case 8:
                                		day="Friday";
                                		break;
                                	}
                                	if(sheet.getCell(k, i).getContents()!="")
                                		if(!sheet.getCell(k, i).getContents().contains("Mild"))
                                			if(!sheet.getCell(k, i).getContents().contains("Colour"))
                                				db.addItem(new Items(sheet.getCell(k, i).getContents(),sheet.getCell(k+1, i).getContents(),day,"Snacks",date));
                                	}
                                }  
                                break;
                            }
                        }
                        }
                    
                    {
                        Sheet sheet = w.getSheet(2);
                        int ij = sheet.getRows();
                        String date = "";
                        // Loop over column and lines
                        for (int j = 0; j < ij; j++) {
                            Cell cell = sheet.getCell(0, j);
                            if(cell.getContents().equalsIgnoreCase("Monday")){
                            	
                                for (int i =j+2 ; i < ij; i++) 
                                {
                                	for(int k=1;k<15;k=k+3)
                                	{
                                		date = sheet.getCell(k, j).getContents();
                                		String day="";
                                	switch (k){
                                	case 1:
                                		day="Monday";
                                		break;
                                	case 4:
                                		day="Tuesday";
                                		break;
                                	case 7:
                                		day="Wednesday";
                                		break;
                                	case 10:
                                		day="Thursday";
                                		break;
                                	case 13:
                                		day="Friday";
                                		break;
                                	}
                                	if(sheet.getCell(k, i).getContents()!="")
                                		db.addItem(new Items(sheet.getCell(k, i).getContents(),sheet.getCell(k+1, i).getContents(),day,"Dinner",date));
                                	}
                                }  
                                break;
                            }
                        }
                        }
                    
                    
                    Date d = new Date();
                    int da = d.getDay();
                    switch (da){
                	case 1:
                		dayOfTheWeek="Monday";
                		break;
                	case 2:
                		dayOfTheWeek="Tuesday";
                		break;
                	case 3:
                		dayOfTheWeek="Wednesday";
                		break;
                	case 4:
                		dayOfTheWeek="Thursday";
                		break;
                	case 5:
                		dayOfTheWeek="Friday";
                		break;
                	default:
                		dayOfTheWeek = "Monday";
                		da=1;
                		break;
                	}
                    dropdown.setSelection(da-1);
                    int hours = d.getHours();

                    if(hours>19)
                    	when = "Dinner";
                    else if(hours>14)
                    	when = "Snacks";
                    else
                    	when = "Lunch";
                    
                    load_UI(dayOfTheWeek, when);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            progress.dismiss();    
        }
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	DownloadTask downloadTask = new DownloadTask(MainActivity.this);
        	downloadTask.execute("https://sapmenu5hanamobile.neo.ondemand.com/sapmenu6");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
