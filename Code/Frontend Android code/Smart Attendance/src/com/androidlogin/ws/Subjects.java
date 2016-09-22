package com.androidlogin.ws;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class Subjects extends Activity {
	
	
	String tv1=null;
	int day,year,month;
	DatePicker dp;
	Button btnChangeDate;
	String fDate;
	TextView dateview;
	 
	static final int DATE_DIALOG_ID=999;
	
		
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subjects);
	
		setCurrentDateOnView();
		addListenerOnButton();
		
		/*	
		Spinner spi= (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adp= ArrayAdapter.createFromResource(this, R.array.subjects_list,android.R.layout.simple_spinner_item);
		adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spi.setAdapter(adp);
		
		*/
		
		Bundle bdl=getIntent().getExtras();
		final String user=bdl.getString("user");
		
		TextView user1= (TextView) findViewById(R.id.textView3);
		user1.setText(user);
		
		
		InputStream is = null;
		String result = "";
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username",user));
		 
		//http post
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://praneethambati-001-site1.smarterasp.net/subjects.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        HttpEntity entity = response.getEntity();
		         is = entity.getContent();
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		                sb.append(line + "\n");
		        }
		        is.close();
		 
		        result=sb.toString();
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		 
		//parse json data
		 ArrayList<String> arrProducts = new ArrayList<String>();
		try{
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                
		                String str =jArray.getJSONObject(i).getString("subject");
		               arrProducts.add(str);
		               
		               
		                Log.i("log_tag","user: "+json_data.getString("username")+
		                        ", sub: "+json_data.getString("subject")
		                        
		                );
		        }
		
		}catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
		
		
		final Spinner spi=(Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(
                Subjects.this,android.R.layout.simple_spinner_item, arrProducts);
		productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(productAdapter);
        
        spi.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				
				final String tv2=spi.getSelectedItem().toString();
				System.out.println("value:"+tv2);
				 Toast.makeText(getApplicationContext(),  "You selected: " + tv2,
			              Toast.LENGTH_LONG).show();
				 
				 
/*
				DatePicker dp=(DatePicker) findViewById(R.id.datePicker1);
					int day=dp.getDayOfMonth();
						int month=dp.getMonth();
						int year=dp.getYear()-1900;
				

					   
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				@SuppressWarnings("deprecation")
				final String fDate = sdf.format(new Date(year, month, day));
				System.out.println(fDate);
			*/	 
				 Button submit = (Button) findViewById(R.id.button1);
					submit.setOnClickListener(new View.OnClickListener() {
						
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent it= new Intent(Subjects.this,Students.class);
							it.putExtra("subject", tv2);
							it.putExtra("username", user);
				it.putExtra("date",fDate);
				
							startActivity(it);
						}
					});
					
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
       
        Button logout= (Button) findViewById(R.id.button2);
        logout.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(Subjects.this,AndroidLoginExampleActivity.class);
				startActivity(it);
				Toast.makeText(getApplicationContext(),  "You have been Logged out successfully!",
			              Toast.LENGTH_LONG).show();
			}
		});
       
	
        
       
		
	}
		
		public void setCurrentDateOnView() {
			 
			dateview = (TextView) findViewById(R.id.textView4);
			dp = (DatePicker) findViewById(R.id.datePicker1);
	 
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
	 
			
			//display date in text view
			dateview.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(day).append("-").append(month + 1).append("-")
			.append(year).append(" "));
			// set current date into datepicker
			dp.init(year, month+1, day, null);
	 
		}
		public void addListenerOnButton() {
			 
			btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
	 
			btnChangeDate.setOnClickListener(new View.OnClickListener() {
	 
				public void onClick(View v) {
					
					showDialog(DATE_DIALOG_ID);
	 
				}
	 
			});
	 
		}
		
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_ID:
			   // set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			}
			return null;
		}
		
		private DatePickerDialog.OnDateSetListener datePickerListener 
        = new DatePickerDialog.OnDateSetListener() {

// when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
		int selectedMonth, int selectedDay) {
	year = selectedYear;
	month = selectedMonth+1;
	day = selectedDay;

	dateview.setText(new StringBuilder()
	// Month is 0 based, just add 1
	.append(day).append("-").append(month ).append("-")
	.append(year).append(" "));
	// set selected date into datepicker also
	dp.init(year, month, day, null);
	fDate=day+"-"+month+"-"+year;
}
};
	 

		

	}


