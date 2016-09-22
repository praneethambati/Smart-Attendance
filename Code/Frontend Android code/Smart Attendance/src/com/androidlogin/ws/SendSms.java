package com.androidlogin.ws;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class SendSms extends Activity {
	ArrayList<String> lessName;
	 ArrayList<String> lessPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bdl=getIntent().getExtras();
		  lessName=bdl.getStringArrayList("lName");
		 lessPhone=bdl.getStringArrayList("lPhone");
		
		
		
		
		 sendSMS();
	
	}
	public void sendSMS() {
		
		for(int i=0;i<lessName.size();i++)
		{
		
	    String phoneNumber = lessPhone.get(i);
	    String StuName=lessName.get(i);
	    String message = "*** This is to inform that your ward "+StuName+" is having less attendance.";

	    SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	}
	}
	

}
