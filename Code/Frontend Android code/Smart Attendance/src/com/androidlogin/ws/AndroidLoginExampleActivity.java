package com.androidlogin.ws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidLoginExampleActivity extends Activity {
	private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL = "http://praneethambati-001-site1.smarterasp.net/Service.asmx";
    private static final String SOAP_ACTION = "http://tempuri.org/faculty";
    private static final String METHOD_NAME = "faculty";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				loginAction();
				
				
			}
		});
    }
    
    private void loginAction(){
    	SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
    	
        EditText userName = (EditText) findViewById(R.id.tf_userName);
        String username = String.valueOf(userName.getText().toString());
        EditText userPassword = (EditText) findViewById(R.id.tf_password);
        String password = String.valueOf(userPassword.getText().toString());
     /*   
        request.addProperty("username","pranu");
        request.addProperty("password","pranu");
       */ 
      //Pass value for userName variable of the web service
        PropertyInfo unameProp =new PropertyInfo();
        unameProp.setName("username");//Define the variable name in the web service method
        unameProp.setValue(username);//set value for userName variable
        unameProp.setType(String.class);//Define the type of the variable
        request.addProperty(unameProp);//Pass properties to the variable
       
      //Pass value for Password variable of the web service
        PropertyInfo passwordProp =new PropertyInfo();
        passwordProp.setName("password");
        passwordProp.setValue(password);
        passwordProp.setType(String.class);
        request.addProperty(passwordProp);
        
      
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
       // androidHttpTransport.debug = true;
        
        try{
        	androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            androidHttpTransport.call(SOAP_ACTION,envelope);
           
           SoapObject response=(SoapObject)envelope.bodyIn;
            String result=response.getProperty(0).toString();
           
            Log.i("info","Received :" + result);
               //SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
               
            System.out.println("response:"+response.toString());
              
            if(result.contentEquals("success"))
            {
            	Intent it=new Intent(AndroidLoginExampleActivity.this,Subjects.class);
            	it.putExtra("user",username.toString());
            	startActivity(it);
            	Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                
            }
            else if(result.contentEquals("invalid"))
            {
            	Toast.makeText(getApplicationContext(),"Invalid login details,Try again",Toast.LENGTH_LONG).show();
            }
               System.out.println("Response:"+result.toString());
               
            	   
               
        }
        
    	
        catch(Exception e){
          
        }	
       }

	
    
}