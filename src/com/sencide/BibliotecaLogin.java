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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BibliotecaLogin extends Activity {
	
	private Button ok;
	private TextView result;
	private String urlLogin="http://148.206.79.169/F/ULETN4R1JRS7TV3AR5HRCYNGNY2AUANJPEV5VBCNDNJB6VHXUF-11082?func=BOR-INFO";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteca_login);
        
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageFinished(WebView mywebview, String url)
        	{
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('copyright')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('topbar')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('middlebar')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('bottombar')[0].style.display='none'; })()");
        		mywebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('feedbackbar')[0].style.display='none'; })()");
        	}
        });
        myWebView.loadUrl(urlLogin);
        
    }
    
    
    	 
}