package com.sencide;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MenuApplication extends Activity implements OnClickListener{
	private String urlFinSession="https://ayamictlan.uam.mx:8443/sae/azc/AEWBU005.oFinSesion";
	
	//private List<String> cookies;
	private ProgressBar pb;
	private Button horario,biblioteca,avisos,contactos;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menuapplication);
        pb=(ProgressBar)findViewById(R.id.progressBar1);
		pb.setVisibility(View.GONE);
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
	@Override
	public void onClick(View view) {
		String nameButton=null;
		
		if(view == horario)
		{
			nameButton=(String) horario.getText();
			Toast.makeText(getApplicationContext(), "Obteniendo Horario....", Toast.LENGTH_SHORT).show();
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(nameButton);
		}
		if(view == avisos)
		{
			nameButton=(String) avisos.getText();
			Toast.makeText(getApplicationContext(), "Obteniendo Avisos....", Toast.LENGTH_SHORT).show();
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(nameButton);
		}
		if(view == biblioteca)
		{
			nameButton=(String) biblioteca.getText();
			Toast.makeText(getApplicationContext(), "Conectando ....", Toast.LENGTH_SHORT).show();
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(nameButton);
		}
	}
	 

	@SuppressWarnings("unused")
	public void cerrarSession() {
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpGet httpget = new HttpGet(urlFinSession);
        
        try {
        	   	
            // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httpget);
                        
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    } 
  
  	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.action_logout)
		{
			cerrarSession();
			finish();
			//return true;
		}
		if(id == R.id.action_help)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, String>
	{
		
		 
		@Override
		protected String doInBackground(String... params)
		{
			String resultButton=null;
			resultButton=params[0];
			return resultButton;
			
		}
 
		protected void onPostExecute(String result)
		{
			pb.setVisibility(View.GONE);
			if(result.equals("Horario"))
            {
				//Intent intent = new Intent(MenuApplication.this, Horario.class);
	        	//intent.putStringArrayListExtra("cookies", (ArrayList<String>) cookies);
	        	//startActivity(intent);
            }
			if(result.equals("Avisos de la Universidad"))
            {
            	Intent intent = new Intent(MenuApplication.this, Avisos.class);
            	startActivity(intent);
            }
			if(result.equals("Biblioteca"))
            {
            	Intent intent = new Intent(MenuApplication.this, BibliotecaLogin.class);
            	startActivity(intent);
            }
		}
		
 
	}
}
