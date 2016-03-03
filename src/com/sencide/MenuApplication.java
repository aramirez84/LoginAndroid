package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuApplication extends Activity implements OnClickListener{
	private String urlFinSession="https://ayamictlan.uam.mx:8443/sae/azc/AEWBU005.oFinSesion";
	private String urlKardex="https://ayamictlan.uam.mx:8443/sae/azc/IEWBC020.oConsulta";
	private List<String> cookies;
	private Button horario,biblioteca,avisos,contactos;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menuapplication);
        Bundle bundle = getIntent().getExtras();
        cookies=bundle.getStringArrayList("cookies");
        horario = (Button)findViewById(R.id.btn_horario);
        biblioteca=(Button)findViewById(R.id.btn_biblioteca);
        avisos=(Button)findViewById(R.id.btn_avisos);
        contactos=(Button)findViewById(R.id.btn_contactos);
        horario.setOnClickListener(this);
        biblioteca.setOnClickListener(this);
        avisos.setOnClickListener(this);
        contactos.setOnClickListener(this);
        
     }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void postLoginData() {
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpGet httpget = new HttpGet(urlKardex);
        httpget=cerrarSession(cookies,httpget);

        try {
        	   	
            // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httpget);
            HttpEntity ent=response.getEntity();  
            ent=response.getEntity();
            String str = EntityUtils.toString(ent);
            System.out.println(str);
            
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			postLoginData();
			finish();
			//return true;
		}
		if (id == R.id.action_help) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View view) {
		if(view == horario){
			postLoginData();
		}
	}
	
	public HttpGet cerrarSession(List<String> cookies,HttpGet httpget)
	{
		for (int i=0;i<cookies.size();i++)
		{
			Log.w("cookies add", cookies.get(i));
			httpget.addHeader("Cookie", cookies.get(i));
		}
		return httpget;
	}
}
