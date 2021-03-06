package com.sencide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class Horario extends Activity
{
	private String user,password;
	private WebView myWebView;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        Bundle datos =getIntent().getExtras();
        user =datos.getString("user");
        password= datos.getString("pass");
        
        myWebView = (WebView) this.findViewById(R.id.webViewHorario);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        //myWebView.setInitialScale(70);
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        
        myWebView.loadUrl("https://ayamictlan.uam.mx:8443/sae/azc/aewbf001.omuestraframes?mod=1");
        
    }
	private class MyWebViewClient extends WebViewClient {
		private Integer count=0;
				
		@Override
        public boolean shouldOverrideUrlLoading(WebView mywebview, String url) {
					
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].value='"+user+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].value='"+password+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[3].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[4].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[5].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[8].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[9].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[10].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[11].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[12].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[13].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[14].style.display='none' })()");
			mywebview.loadUrl(url);
			count=count+1;
			Log.w("UrlLoading Count", count.toString());
        	return true;
        }

        @Override
        public void onPageStarted(WebView mywebview, String url, Bitmap favicon) {
            super.onPageStarted(mywebview, url, favicon);
            
            mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].value='"+user+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].value='"+password+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[3].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[4].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[5].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[8].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[9].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[10].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[11].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[12].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[13].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[14].style.display='none' })()");
			count=count+1;
			Log.w("onPageStarted Count", count.toString());
        }

        @Override
        public void onPageFinished(WebView mywebview, String url) {
            super.onPageFinished(mywebview, url);
            
            mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].value='"+user+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].value='"+password+"' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('NOMBRE.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByName('COMPLEMENTO.IDENTIFICACION.NONMODELED')[0].style.display='none'; })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TD')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('TR')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[0].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[2].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[3].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[4].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[5].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[6].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[7].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[8].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[9].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[10].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[11].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[12].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[13].style.display='none' })()");
			mywebview.loadUrl("javascript:(function() { " +
                    "window.frames['bodyFrame'].window.frames['controlFrame'].window.frames['menuFrame'].document.getElementsByTagName('LI')[14].style.display='none' })()");
			count=count+1;
			Log.w("onPageFinished Count", count.toString());
        }
        

        @Override
        public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {
        	super.onReceivedError(view, errorCode, description, failingUrl);
            //You can add some custom functionality here
            Log.d("error", description);
            Log.d("error", failingUrl);
        }
        
        
        
     } 

}