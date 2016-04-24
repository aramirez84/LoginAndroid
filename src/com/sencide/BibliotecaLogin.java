package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BibliotecaLogin extends Activity {
	private String urlLogin="http://espartaco.azc.uam.mx/ALEPH";
	//private String urlLogin="http://148.206.79.169/F/ULETN4R1JRS7TV3AR5HRCYNGNY2AUANJPEV5VBCNDNJB6VHXUF-11082?func=BOR-INFO";
	private WebView myWebView;
	private Pattern pattern;
	private String user;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteca_login);
        Bundle datos =getIntent().getExtras();
        user =datos.getString("user");
        myWebView = (WebView) this.findViewById(R.id.webView);
        new MyAsyncTask().execute(urlLogin);
            
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
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	// Return full string
    	return total;
    	}
 
    public String getMensaje(Pattern pattern,String str)
    {
    	String url=null;
    	Matcher matcher = pattern.matcher(str);
    	// Guardamos los mensajes que nos da en la variable mensaje
           List<String> mensajes = new ArrayList<String>();
           while(matcher.find()){
           	mensajes.add(matcher.group(0));
           }
           url=mensajes.get(0).substring(385).replace("ef=\"","");
           return url;
    }
    
	private class MyAsyncTask extends AsyncTask<String, String, String>
	{
		
		 
		@Override
		protected String doInBackground(String... params)
		{
			HttpResponse response = null;
			String resultPost=null;
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(params[0]);
 
			try {
				
				// Execute HTTP Post Request
				response = httpclient.execute(httpget);
				String str = inputStreamToString(response.getEntity().getContent()).toString();
				//Log.w("SENCIDE", str);
				//Filtramos el atributo onload que nos da la valdiacion del formulario
				pattern = Pattern.compile("http:\\/\\/148.206.79.169:80\\/F\\/.*BOR-INFO");
				resultPost=getMensaje(pattern, str);
				             
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return resultPost;
			
		}
 
		@SuppressLint("SetJavaScriptEnabled")
		protected void onPostExecute(String result)
		{
			myWebView.getSettings().setJavaScriptEnabled(true);
			myWebView.addJavascriptInterface(new JavaScriptInterface(getApplicationContext()), "Android");
	        myWebView.getSettings().setUseWideViewPort(true);
	        myWebView.setInitialScale(70);
	        //myWebView.getSettings().setLoadWithOverviewMode(true);
	        myWebView.setWebViewClient(new MyWebViewClient());
	        //myWebView.setWebViewClient(new WebViewClient());
	        myWebView.loadUrl(result);
		}
	}

	private class MyWebViewClient extends WebViewClient {
		private String urlLogibBiblioteca="http:\\/\\/148.206.79.169:80\\/F\\/.*BOR-INFO";
		private String urlLogiBiblioteca="http:\\/\\/148.206.79.169:80\\/F\\/.*bor-info";
		private String urlRenovarPrestamo="http:\\/\\/148.206.79.169:80\\/F\\/.*bor-loan";
		private String urlDetallesPrestamo="http:\\/\\/148.206.79.169:80\\/F\\/.*library=AZC50";
		@SuppressWarnings("unused")
		private String urlBiblioteca="[0-9]{5}[^\\?=\\d&]";
		private int count1=0;
		private int count2=0;
		
		@Override
        public boolean shouldOverrideUrlLoading(WebView mywebview, String url) {
        	Pattern pat = Pattern.compile(urlLogibBiblioteca);
			Matcher mat = pat.matcher(url);
			
			Pattern pat2 = Pattern.compile(urlRenovarPrestamo);
			Matcher mat2 = pat2.matcher(url);
			
			Pattern pat3 = Pattern.compile(urlDetallesPrestamo);
			Matcher mat3 = pat3.matcher(url);
			
			Pattern pat4 = Pattern.compile(urlLogiBiblioteca);
			Matcher mat4=pat4.matcher(url);
			
			Toast.makeText(getApplicationContext(), "Cargando: "+url, Toast.LENGTH_SHORT).show();
			mywebview.loadUrl("javascript:(function() { " +
                    " document.getElementsByTagName('TR')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "document.getElementsByTagName('TR')[3].style.display='none'; })()");
        	
			if (mat.matches()) {
				count1++;
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
            }
			if (mat2.matches()) {
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[5].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[6].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[7].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[24].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[25].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[26].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[31].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[32].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[33].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[34].style.display='none'; })()");
            }
			if(mat3.matches())
            {
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('IMG')[1].style.display='none'; })()");
            }
			if(mat4.matches())
			{
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[6].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[7].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[8].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[9].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[10].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[11].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[12].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[13].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('HR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('SPAN')[2].width=20; })()");
			}
			if(url.length()>=80 && url.length()<=83)
			{
				count2++;
				if(count2<=1)
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
				}
				else
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[6].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[7].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[8].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[9].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[10].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[11].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[12].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[13].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('HR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('SPAN')[2].width=20; })()");
				}
				
            }
        	mywebview.loadUrl(url);
        	Log.w("Count1", Integer.toString(count1));
        	Log.w("Count2", Integer.toString(count2));
           return true;
        }

        @Override
        public void onPageStarted(WebView mywebview, String url, Bitmap favicon) {
            super.onPageStarted(mywebview, url, favicon);
            Pattern pat = Pattern.compile(urlLogibBiblioteca);
            Matcher mat = pat.matcher(url);
            
            Pattern pat2 = Pattern.compile(urlRenovarPrestamo);
			Matcher mat2 = pat2.matcher(url);
			
			Pattern pat3 = Pattern.compile(urlDetallesPrestamo);
			Matcher mat3 = pat3.matcher(url);
			
			Pattern pat4 = Pattern.compile(urlLogiBiblioteca);
			Matcher mat4=pat4.matcher(url);
			
			Toast.makeText(getApplicationContext(), "Iniciando: "+url, Toast.LENGTH_SHORT).show();
            /*if (mat.matches()) {
            	count1++;
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
            }
            if (mat2.matches()) {
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[5].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[6].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[7].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[24].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[25].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[26].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[31].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[32].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[33].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[34].style.display='none'; })()");
            }
            if(mat3.matches())
            {
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('IMG')[1].style.display='none'; })()");
            }
            if(url.length()>=80 && url.length()<=83)
            {
            	count2++;
            	if(count2<=1)
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
				}
				else
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[6].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[7].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[8].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[9].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[10].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[11].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[12].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[13].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('HR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('SPAN')[2].width=20; })()");
				}
            }*/
            Log.w("Count1", Integer.toString(count1));
        	Log.w("Count2", Integer.toString(count2));
        }

        @Override
        public void onPageFinished(WebView mywebview, String url) {
            super.onPageFinished(mywebview, url);
            Pattern pat = Pattern.compile(urlLogibBiblioteca);
            Matcher mat = pat.matcher(url);
            
            Pattern pat2 = Pattern.compile(urlRenovarPrestamo);
			Matcher mat2 = pat2.matcher(url);
			
			Pattern pat3 = Pattern.compile(urlDetallesPrestamo);
			Matcher mat3 = pat3.matcher(url);
			
			Pattern pat4 = Pattern.compile(urlLogiBiblioteca);
			Matcher mat4=pat4.matcher(url);
			
						
			if (mat.matches()) {
				count1++;
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
            }
			if (mat2.matches()) {
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[5].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[6].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TH')[7].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[24].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[25].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[26].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[31].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[32].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[33].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TD')[34].style.display='none'; })()");
            }
			if(mat3.matches())
            {
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[3].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('IMG')[1].style.display='none'; })()");
            }
			if(mat4.matches())
			{
				mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[6].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[7].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[8].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[9].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[10].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[11].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[12].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('TR')[13].style.display='none'; })()");
            	mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('HR')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('SPAN')[2].width=20; })()");
			}
			if(url.length()>=80 && url.length()<=83)
			{
				
				count2++;
            	if(count2<=1)	
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByName('bor_id')[0].value='"+user+"' })()");
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[1].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[5].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[0].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[9].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[10].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[22].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[23].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TD')[11].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].width=20; })()");
				}
				else
				{
					mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[2].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[4].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[6].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[7].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[8].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[9].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[10].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[11].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[12].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TR')[13].style.display='none'; })()");
	            	mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('HR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('SPAN')[2].width=20; })()");
				}
            }
			Log.w("Count1", Integer.toString(count1));
        	Log.w("Count2", Integer.toString(count2));
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
          //You can add some custom functionality here
            Log.d("error description", description);
            Log.d("error falling", failingUrl);
        }
     }
	
	public class JavaScriptInterface
	{
		Context mContext;
	    JavaScriptInterface(Context c)
	    {
	    	mContext = c;
	    }
	     
	    public void processContent(String aContent) 
	    { 
	       //this method will be called from within the javascript method that you will write.
	       final String content = aContent;
	       Log.e("The content of the current page is ",content);
	    } 
	}
}