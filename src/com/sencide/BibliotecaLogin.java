package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BibliotecaLogin extends Activity implements OnClickListener {
	
	private Button ok;
	private TextView result;
	private String urlLogin="http://espartaco.azc.uam.mx/ALEPH";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteca_login);
        
        // Login button clicked
        ok = (Button)findViewById(R.id.btn_login);
        ok.setOnClickListener(this);
        
        result = (TextView)findViewById(R.id.lbl_result);
        
    }
    
    
    public void postLoginData() {
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpGet httppost = new HttpGet(urlLogin);

        try {
            // Add user name and password
        	EditText uname = (EditText)findViewById(R.id.txt_username);
        	String username = uname.getText().toString();

        	EditText pword = (EditText)findViewById(R.id.txt_password);
        	String password = pword.getText().toString();
        	
            /*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("func","login-session"));
            nameValuePairs.add(new BasicNameValuePair("login_source","LOGIN-BOR"));
            nameValuePairs.add(new BasicNameValuePair("bor_id", username));
            nameValuePairs.add(new BasicNameValuePair("bor_verification", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));*/

            // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httppost);
            String str = inputStreamToString(response.getEntity().getContent()).toString();
            //Log.w("SENCIDE", str);
            //Filtramos el atributo onload que nos da la valdiacion del formulario
            Pattern pattern = Pattern.compile("http:\\/\\/148.206.79.169:80\\/F\\/.*BOR-INFO");
            List<String> mensajes=getMensaje(pattern, str);
            
            
            /*if(mensajes.size()==2)
            {
            	//cookies = etCookies(response);
            	Log.w("SENCIDE", "TRUE");
            	result.setText("Login successful");
            	Intent intent = new Intent(BibliotecaLogin.this, MenuApplication.class);
            	startActivity(intent);
            	uname.setText("");
            	pword.setText("");
            	result.setText("");
            }else
            {
            	Log.w("SENCIDE", "FALSE");
            	result.setText(mensajes2.toString());            	
            }*/

        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        HttpResponse responsePost = null;
        try {
            responsePost = httpclient.execute(httppost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            } 
     
    private StringBuilder inputStreamToString(InputStream is) {
    	String line = "";
    	StringBuilder total = new StringBuilder();
    	// Wrap a BufferedReader around the InputStream
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	// Read response until the end
    	try {
			while ((line = rd.readLine()) != null) { 
				total.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	// Return full string
    	return total;
    }

	@Override
	public void onClick(View view) {
		if(view == ok){
			postLoginData();
		}
	}
	
	public List<String> getMensaje(Pattern pattern,String str)
	{
		String url=null;
		Matcher matcher = pattern.matcher(str);
		// Guardamos los mensajes que nos da en la variable mensaje
        List<String> mensajes = new ArrayList<String>();
        while(matcher.find()){
        	mensajes.add(matcher.group(0));
        }
        url=mensajes.get(0).substring(385);
        Log.w("URL", url);
        return mensajes;
	}
	 
}