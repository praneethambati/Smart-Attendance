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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Students extends Activity {
	
	//toggle status
	String strStatus; 
	
	//toggle button 
	ToggleButton tgl;
	
	//student names that will be retrieved from database will be stored in stu_names array
	String[] stu_names = null;
	
	String[] upload_stu=null;
	
	String[] upload_sta=null;
	boolean[] status;
	
	TextView dateDisp;
	
	ArrayList<String> limits = new ArrayList<String>();	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);
        
        //getting values of selected subjects and faculty name from subjects page
        Bundle bdl=getIntent().getExtras();
		final String subject=bdl.getString("subject");
		final String user=bdl.getString("username");
		final String date=bdl.getString("date");
		TextView user1= (TextView) findViewById(R.id.pranu);
		user1.setText(subject);
		
		dateDisp=(TextView) findViewById(R.id.textView1);
		dateDisp.setText(date);
		
		System.out.println("Received Date:"+date);
		
		InputStream is = null;
		 String result = "";
	
		//request the subject and username values to the php script 
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("subject",subject));
		nameValuePairs.add(new BasicNameValuePair("username",user));
		
		//http post
				try{
				        HttpClient httpclient = new DefaultHttpClient();
				        HttpPost httppost = new HttpPost("http://praneethambati-001-site1.smarterasp.net/students.php");
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
				 final ArrayList<String> arrProducts = new ArrayList<String>();
				try{
					
					   final ListView stulist = (ListView) findViewById(R.id.ListView1);
				        
					   
				        // Each row in the list stores student name and its status
				        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

					
				        final JSONArray jArray = new JSONArray(result);
				        for(int i=0;i<jArray.length();i++){
				                JSONObject json_data = jArray.getJSONObject(i);
				                
				                String str =jArray.getJSONObject(i).getString("name");
				               arrProducts.add(str);
				          
				               
				               stu_names= new String[arrProducts.size()];
				               
				              stu_names[i]=json_data.getString("name");
				              System.out.println(i+json_data.getString("name")+",,,"+strStatus); 
				              
				                Log.i("log_tag","user: "+json_data.getString("name")+
				                        ", sub: "+json_data.getString("subject")+
				                        ", faculty: "+json_data.getString("faculty")
				                        
				                );
				        
				        
				             
						      //  for(int j=0;i<jArray.length();j++){
						        	//System.out.println("names:"+ stu_names[j]);
						            HashMap<String, String> hm = new HashMap<String,String>();
						            hm.put("txt" , stu_names[i]);
						            //hm.put("stat",status[i]);
						            
						            aList.add(hm);
						            
						    //  }
						        
						        // Keys used in Hashmap
						        String[] from = {"txt"};

						        // Ids of views in listview_layout
						        int[] to = { R.id.tv_item};


						        // Instantiating an adapter to store each items
						        // R.layout.listview_layout defines the layout of each item
						        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.st_layout, from, to);
						        
						        stulist.setAdapter(adapter);
						
				        
				        
				        }
				        
				      
				        

				       
				   				       
						/* Restore from the previous state if exists 
				        if(savedInstanceState!=null){
				        	status = savedInstanceState.getBooleanArray("status");
				        }
				        	*/
				            
				        OnItemClickListener itemClickListener = new OnItemClickListener() {
				        	public void onItemClick(AdapterView<?> lv, View item, int position, long id) {
				        		
				        		ListView lView = (ListView) lv;
				        		
				        		SimpleAdapter adapter = (SimpleAdapter) lView.getAdapter();
				        		
				        		HashMap<String,Object> hm = (HashMap) adapter.getItem(position);
				        		
				        		/** The clicked Item in the ListView */
				        		RelativeLayout rLayout = (RelativeLayout) item;
				        		
				        		/** Getting the toggle button corresponding to the clicked item */
				        	 tgl = (ToggleButton) rLayout.getChildAt(1);
				        		
				        	
				        		String strStatus = "";
				        		if(tgl.isChecked()){
				        			tgl.setChecked(false);
				        			strStatus = "Absent";
				        			
									//status[position]=false;
				        		}else{
				        			tgl.setChecked(true);
				        			strStatus = "Present";
				        		//	status[position]=true;        			
				        		}
				        		
				        		Toast.makeText(getBaseContext(), (String) hm.get("txt") + " : " + strStatus, Toast.LENGTH_SHORT).show();
				        		System.out.println("Status:"+hm.get("txt")+":"+strStatus+".."+status);
				        	
				        		limits.add(hm.get("txt")+".."+strStatus);
				        		
				        		System.out.println(limits);
				        		    
							      
				        	}
						};
				        
				        stulist.setOnItemClickListener(itemClickListener);
				        
				     //   JSONArray jA=new JSONArray(result);
					      
				/*    	  for(int i=0;i<jArray.length();i++)
				      
				        {
				        	JSONObject jd=jArray.getJSONObject(i);
				        	upload_stu=new String[arrProducts.size()];
				        	
				        	
				        	upload_stu[i]= stulist.getAdapter().getItem(i).toString();
				        	
				        	upload_stu[i]=jd.getString("name");
				        	
				        	//System.out.println("Try.."+upload_stu[i]+strStatus);
				        	
				        	
				        	 HashMap<String, String> hm2 = new HashMap<String,String>();
					         hm2.put("txt", upload_stu[i]);
					         
					       
					         
					         System.out.println( hm2);
				        	
				        	System.out.println(i+".."+upload_stu[i]+"..."+strStatus);
				        	
				        	
				        	
				        }
				    */
				}catch(JSONException e){
				        Log.e("log_tag", "Error parsing data "+e.toString());
				}
		

				
				//Uploading Attendance to db
       Button b=(Button) findViewById(R.id.button1);
       b.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			
			Intent it=new Intent(Students.this,DispInfo.class);
			it.putExtra("user", user);
			it.putExtra("date", date);
			it.putExtra("subject", subject);
		
			it.putStringArrayListExtra("stu",limits);
			startActivity(it);
			
		}
	});
       
       
    }
    
	
	
    /** Saving the current state of the activity 
     * for configuration changes [ Portrait <=> Landscape ] 
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);    	
    	outState.putBoolean("status", false);
    	
    }
    

}

