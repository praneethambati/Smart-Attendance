package com.androidlogin.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DispInfo extends Activity {
	String subject;
	 ArrayList<String> lessName = new ArrayList<String>();
	 ArrayList<String> lessPhone = new ArrayList<String>();
	 ArrayList<Integer> eachpercentage = new ArrayList<Integer>();

    

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispinfo);
		
		// map.put("Student Name", "Percentage");
		
		 ListView list = (ListView) findViewById(R.id.listView1);
	        
		  ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		   
	        
	        
		 Bundle bdl=getIntent().getExtras();
			 subject=bdl.getString("subject");
			final String user=bdl.getString("user");
			final String date=bdl.getString("date");
			final ArrayList<String> stu=bdl.getStringArrayList("stu");
			
			System.out.println("subject:"+subject);
			System.out.println("user:"+user);
			System.out.println("date:"+date);
			System.out.println("stu:"+stu);
			
			StringBuilder s=new StringBuilder();
			s.append("["+stu.get(0)+",");
			for(int i=1;i<stu.size()-1;i++)
			{
			s.append(stu.get(i)+",");
			}		
			s.append(stu.get(stu.size()-1)+"]");
			System.out.println("S.."+s);
			InputStream is = null;
			String result = "";
			//the year data to send
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						
			nameValuePairs.add(new BasicNameValuePair("username",user));
			nameValuePairs.add(new BasicNameValuePair("subject", subject));
			nameValuePairs.add(new BasicNameValuePair("date", date));
			nameValuePairs.add(new BasicNameValuePair("stu",s.toString()));

			//System.out.println(stu.get(i).toString());
			
			System.out.println(nameValuePairs);
			
		
			//http post
			try{
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://praneethambati-001-site1.smarterasp.net/upload_atten9.php");
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
			System.out.println("res:"+result);
			//parse json data
			 ArrayList<String> studentName = new ArrayList<String>();
			 ArrayList<Integer> percentages = new ArrayList<Integer>();
			 ArrayList<String> phones = new ArrayList<String>();
				
			 
				
			try{
			        JSONArray jArray = new JSONArray(result);
			        for(int i=0;i<jArray.length();i++){
			                JSONObject json_data = jArray.getJSONObject(i);
			                
			                //getting all student names in studentName
			                String student =jArray.getJSONObject(i).getString("student");
			               studentName.add(student);
			              
			               //getting each student percentage in percentages
			               int percentage =jArray.getJSONObject(i).getInt("percen");
			               percentages.add(percentage);
			               
			               //getting each student phone num in phones
			               String phone=jArray.getJSONObject(i).getString("phone");
			              phones.add(phone);
			               
			                Log.i("log_tag","user: "+json_data.getString("username")+
			                        ", sub: "+json_data.getString("subject")+
			                        ", day: "+json_data.getString("day")+
			                        ", month: "+json_data.getString("month")+
			                        ", year: "+json_data.getString("year")+
			                        	", student name"+json_data.getString("student")+
	", percen:"+json_data.getString("percen")+", phone no:"+json_data.getString("phone")
	
			                        
			                        	
			                        
			                );
			                HashMap<String, String> map = new HashMap<String, String>();
			                
			    	      //  map.put("train", "101");
			    	        map.put("student", studentName.get(i));
			    	        map.put("percentages", percentages.get(i)+"%");
			    	        mylist.add(map);
			    	    
			        
			    	        if(percentages.get(i)<=75)
			    	        {
			    	        	String lName=studentName.get(i);
			    	        	String lPhone=phones.get(i);

					             int eachatten1=percentages.get(i);
					              //eachpercentage.add(eachatten1);
					             
			    	        	//int eachpercentage1=percentages.get(i);
			    	        	
			    	        	lessName.add(lName);
			    	        	lessPhone.add(lPhone);
			    	       	eachpercentage.add(eachatten1);
			    	        	
			    	        	
			    	        	
			    	        }
			    	        System.out.println("lessName:"+lessName);
			    	        System.out.println("lessPhone:"+lessPhone);
			    	        System.out.println("Less student percentages:"+eachpercentage);
			    	        
			        }
			      
			
			}catch(JSONException e){
			        Log.e("log_tag", "Error parsing data "+e.toString());
			}
			/*
			final ListView lv=(ListView) findViewById(R.id.listView1);
			ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(
	                DispInfo.this,android.R.layout.simple_list_item_1, arrProducts);
			productAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
	        lv.setAdapter(productAdapter);
	        
	        
	        */
			
	       // mylist.add(map);
	        // ...
	        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.row,
	                    new String[] { "student", "percentages"}, new int[] { R.id.FROM_CELL, R.id.TO_CELL});
	        list.setAdapter(mSchedule);
	        
	    /*    
	        Intent it=new Intent(DispInfo.this,SendSms.class);
		       it.putStringArrayListExtra("lName", lessName);
		       it.putStringArrayListExtra("lPhone", lessPhone);
		       startActivity(it);
		  */
	        sendSMS();
	        
	}
public void sendSMS() {
		
		for(int i=0;i<lessName.size();i++)
		{
		
	    String phoneNumber = lessPhone.get(i);
	    String StuName=lessName.get(i);
	  int StuPercen=eachpercentage.get(i);
	    String message = "This is to inform that your ward \""+StuName+"\"is having less attendance with "+ StuPercen+"% in "+subject+" subject .So,Kindly warn your ward to maintain minimum attendance.REGARDS: DCP PROJECT!.";
	    

	    SmsManager smsManager = SmsManager.getDefault();
	    ArrayList<String> parts = smsManager.divideMessage(message); 
	    smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
	 }
	}

	
}
