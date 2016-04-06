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

import android.app.Activity;
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
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteca_login);
        
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
           Log.w("URL", url);
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
				Log.w("SENCIDE", "Execute HTTP Post Request");
				response = httpclient.execute(httpget);
				String str = inputStreamToString(response.getEntity().getContent()).toString();
				//Log.w("SENCIDE", str);
				//Filtramos el atributo onload que nos da la valdiacion del formulario
				Pattern pattern = Pattern.compile("http:\\/\\/148.206.79.169:80\\/F\\/.*BOR-INFO");
				resultPost=getMensaje(pattern, str);
				             
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return resultPost;
			
		}
 
		protected void onPostExecute(String result)
		{
			myWebView.getSettings().setJavaScriptEnabled(true);
	        myWebView.getSettings().setUseWideViewPort(true);
	        myWebView.setInitialScale(70);
	        //myWebView.getSettings().setLoadWithOverviewMode(true);
	        myWebView.setWebViewClient(new MyWebViewClient(){
	        	@Override
	        	public void onPageFinished(WebView mywebview, String url)
	        	{
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByClassName('topbar')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByClassName('copyright')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].widht='15%'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].cellSpacing='1'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('TABLE')[1].cellPadding='1'; })()");
	        		
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByClassName('topbar2')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByClassName('bottombar')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByClassName('td2')[0].style.display='none'; })()");
	        		
	        		        		
	        		/*
	        		 *
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[0].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[1].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[2].style.display='none'; })()");
	        		mywebview.loadUrl("javascript:(function() { " +
	                        "document.getElementsByTagName('BR')[3].style.display='none'; })()");
	        		
	        		        */
	        	}
	        });
	        myWebView.loadUrl(result);
	    
		}
		
 
	}

	private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	Toast.makeText(getApplicationContext(), "Cargando: "+url, Toast.LENGTH_SHORT).show();
           view.loadUrl(url);
           return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Toast.makeText(getApplicationContext(), "Iniciando: "+url, Toast.LENGTH_SHORT).show();
            //You can add some custom functionality here
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(getApplicationContext(), "Finalizando: "+url, Toast.LENGTH_SHORT).show();
          //You can add some custom functionality here
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
          //You can add some custom functionality here
        }
     } 
}