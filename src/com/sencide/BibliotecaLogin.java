package com.sencide;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BibliotecaLogin extends Activity {
	
	private String urlLogin="http://148.206.79.169/F/ULETN4R1JRS7TV3AR5HRCYNGNY2AUANJPEV5VBCNDNJB6VHXUF-11082?func=BOR-INFO";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biblioteca_login);
        
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setInitialScale(70);
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageFinished(WebView mywebview, String url)
        	{
        		mywebview.loadUrl("javascript:(function() { " +
                        "alert(window.location); })()");
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
        myWebView.loadUrl(urlLogin);
        
    }
    
    
    	 
}