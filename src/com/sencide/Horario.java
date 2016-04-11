package com.sencide;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Xml.Encoding;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EncodingUtils;


public class Horario extends Activity
{
	private TextView horario;
	private String horarioHtml=null;
	private String user,password;
	private String postData=null;
	private WebView myWebView;
	private String urlHorario="https://ayamictlan.uam.mx:8443/sae/azc/AEWBU004";
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
        //myWebView.getSettings().setUseWideViewPort(true);
        //myWebView.setInitialScale(70);
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.setWebViewClient(new WebViewClient());
        
        Log.w("Usuario", user);
        Log.w("Password", password);
        myWebView.loadUrl("https://ayamictlan.uam.mx:8443/sae/azc/aewbf001.omuestraframes?mod=1");
        
    }
}