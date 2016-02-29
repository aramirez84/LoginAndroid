package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class AndroidLogin extends Activity implements OnClickListener {
	
	Button ok;
	TextView result;
	String urlLogin="https://ayamictlan.uam.mx:8443/sae/azc/AEWBU004.oIniSesWebLic?mod=1";
    String Kardex=null;
    String Informacion_Academica=null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Login button clicked
        ok = (Button)findViewById(R.id.btn_login);
        ok.setOnClickListener(this);
        
        result = (TextView)findViewById(R.id.lbl_result);
        
    }
    
    
    public void postLoginData() {
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpPost httppost = new HttpPost(urlLogin);

        try {
            // Add user name and password
        	EditText uname = (EditText)findViewById(R.id.txt_username);
        	String username = uname.getText().toString();

        	EditText pword = (EditText)findViewById(R.id.txt_password);
        	String password = pword.getText().toString();
        	
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("SIGLAS_UNI_XX.E_UNIDAD.AE02.1","AZC"));
            nameValuePairs.add(new BasicNameValuePair("%23.E_UNIDAD.AE02.1","AxJDMQ=="));
            nameValuePairs.add(new BasicNameValuePair("%23CRC.E_UNIDAD.AE02.1","0000006B"));
            nameValuePairs.add(new BasicNameValuePair("NOMBRE.IDENTIFICACION.NONMODELED", username));
            nameValuePairs.add(new BasicNameValuePair("COMPLEMENTO.IDENTIFICACION.NONMODELED", password));
            nameValuePairs.add(new BasicNameValuePair("GO.IDENTIFICACION.NONMODELED","Entrar"));
            nameValuePairs.add(new BasicNameValuePair("%25.IDENTIFICACION.NONMODELED.1",""));
            nameValuePairs.add(new BasicNameValuePair("%23.WEB_INFO.SW01.1","CxJDMjA5MzMwMDcy"));
            nameValuePairs.add(new BasicNameValuePair("%23CRC.WEB_INFO.SW01.1","00000022"));
            nameValuePairs.add(new BasicNameValuePair("%23.WEB_MOD_ASO.SW01.1",""));
            nameValuePairs.add(new BasicNameValuePair("%23.USUARIO_ANEXO.SG02.1",""));
            nameValuePairs.add(new BasicNameValuePair("%23.MODULO_UWAS.SAE01.1",""));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httppost);
            
            String str = inputStreamToString(response.getEntity().getContent()).toString();
            //Filtramos el atributo onload que nos da la valdiacion del formulario
            Pattern pattern = Pattern.compile("(\\(.*?)\\)");
            List<String> mensajes=getMensaje(pattern, str);
            //Hacemos un segundo filto para obtener solo el mensaje de validacion del formualrio
            Pattern pattern2 = Pattern.compile("[A-Z].*[a-z]");
            List<String> mensajes2=getMensaje(pattern2, mensajes.get(1));
            
            Log.w("ALERTA",mensajes2.toString());
            Log.w("SENCIDE", str);
            
            if(mensajes.size()==2)
            {
            	Header[] cookies = getCookies(httppost, httpclient);
            	Log.w("SENCIDE", "TRUE");
            	result.setText("Login successful");
            	Intent intent = new Intent(AndroidLogin.this, MenuApplication.class);
            	intent.putExtra("cookie", cookies);
            	startActivity(intent);
            }else
            {
            	Log.w("SENCIDE", "FALSE");
            	result.setText(mensajes2.toString());            	
            }

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
        Header[] cookies = responsePost.getHeaders("Set-Cookie");
        
        HttpGet httpget = new HttpGet("https://ayamictlan.uam.mx:8443/sae/azc/IEWBC020.oConsulta");

        HttpGet httpget2 = new HttpGet("https://ayamictlan.uam.mx:8443/sae/azc/IEWBC007.oConsulta");

        for (Header c : cookies) {
                httpget.addHeader("Cookie", c.getValue());
        }

        for (Header c : cookies) {
            httpget2.addHeader("Cookie", c.getValue());
        }
        
        HttpResponse responseGet = null;
        try {
                responseGet = httpclient.execute(httpget);
        } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        HttpEntity ent=responseGet.getEntity();

        try {
                Kardex=EntityUtils.toString(ent);
        } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        System.out.println(Kardex);
        
        try {
            responseGet = httpclient.execute(httpget2);
	    } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	    ent=responseGet.getEntity();
	
	    try {
	            Informacion_Academica=EntityUtils.toString(ent);
	    } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	    
        System.out.println(Informacion_Academica);
        
        httpclient.getConnectionManager().shutdown();
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
		Matcher matcher = pattern.matcher(str);
        // Guardamos los mensajes que nos da en la variable mensaje
        List<String> mensajes = new ArrayList<String>();
        while(matcher.find()){
        	mensajes.add(matcher.group(0));
        }
		return mensajes;
	}
	
	public Header[] getCookies(HttpPost httppost,HttpClient httpclient)
	{
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
        Header[] cookies = responsePost.getHeaders("Set-Cookie");
        return cookies;
	}

}