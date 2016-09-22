package com.androidlogin.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


	public class JSONParser
    {

        static InputStream is ;
        static JSONObject jObj = null;
        static String json = "";

        String Dataurl = "praneethambati-001-site1.smarterasp.net/upload_atten.php";
        // constructor
        public JSONParser(String url) 
        {
            Dataurl = url;
        }

        // function get json from url by making HTTP POST or GET mehtod
        public JSONObject makeHttpRequestResponse(String method,List<NameValuePair> Data_Request_Response) 
        {
            try {

                // check for request method
                if(method == "POST_Request_Response")
                {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(Dataurl);
                    httpPost.setEntity(new UrlEncodedFormEntity(Data_Request_Response));

                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();

                }
                else if(method == "GET_Request_Response")
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(Data_Request_Response, "utf-8");
                    Dataurl += "?" + paramString;
                    HttpGet httpGet = new HttpGet(Dataurl);

                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                }


            } 
            catch (UnsupportedEncodingException e) 
            {
                e.printStackTrace();
            } 
            catch (ClientProtocolException e)
            {
                e.printStackTrace();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            try 
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) 
                {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } 
            catch (Exception e) 
            {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            // try parse the string to a JSON object
            try 
            {
                jObj = new JSONObject(json);
            } 
            catch (JSONException e) 
            {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }

            // return JSON String
            return jObj;    
        }// End Http Request Response   
    }

