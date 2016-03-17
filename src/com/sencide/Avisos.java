package com.sencide;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class Avisos extends Activity
{
	private String UrlUAM = "http://www.azc.uam.mx/";
	private String imageHttpAddress = "";
	private ImageView imageAviso1,imageAviso2,imageAviso3,imageAviso4,imageAviso5,imageAviso6;
    private Bitmap loadedImage;
    private List<String> imagenes;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
        List<String> urlImagen=getConnection(UrlUAM);
        
        imageAviso1 = (ImageView) findViewById(R.id.imageAviso1);
        imageAviso2 = (ImageView) findViewById(R.id.imageAviso2);
        imageAviso3 = (ImageView) findViewById(R.id.imageAviso3);
        imageAviso4 = (ImageView) findViewById(R.id.imageAviso4);
        imageAviso5 = (ImageView) findViewById(R.id.imageAviso5);
        imageAviso6 = (ImageView) findViewById(R.id.imageAviso6);
        imageAviso1.setImageBitmap(downloadImage(urlImagen, UrlUAM,0));
        imageAviso2.setImageBitmap(downloadImage(urlImagen, UrlUAM,1));
        imageAviso3.setImageBitmap(downloadImage(urlImagen, UrlUAM,2));
        imageAviso4.setImageBitmap(downloadImage(urlImagen, UrlUAM,3));
        imageAviso5.setImageBitmap(downloadImage(urlImagen, UrlUAM,4));
        imageAviso6.setImageBitmap(downloadImage(urlImagen, UrlUAM,5));
        Resources res = getResources();
        
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.Avisos);
        spec.setIndicator("Avisos", 
        		res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.Consejo);
        spec.setIndicator("Consejo", 
        		res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.Actividades);
        spec.setIndicator("Agenda", 
        		res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.Divisiones);
        spec.setIndicator("Divisiones", 
        		res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        tabs.setCurrentTab(0);
        
        tabs.setOnTabChangedListener(new OnTabChangeListener()
        {
        	public void onTabChanged(String tabId)
        	{
        		if(tabId=="mitab2")
        		{
        			Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
        		}
			}
		});        
    }
	public  List<String> getConnection(String url)
	{
		HttpResponse response = null;
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url);
	    try
	    {
	    	response = httpclient.execute(httpget);
	        HttpEntity ent=response.getEntity();  
	        ent=response.getEntity();
	        String str = EntityUtils.toString(ent);
	        Pattern pattern = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.jpg|\\/coordinaciones\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.gif");
	        Pattern pattern2 = Pattern.compile("\\/agenda\\.php\\?id=[0-9]*\\&[a-z]*;[a-z]*=[0-9]*\\&[a-z]*;div=1");
	        imagenes=getImages(pattern, str);
	        Log.w("urlUNAM",imagenes.toString());
	    }
	    catch (ClientProtocolException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    return imagenes;
	}
	
	public List<String> getImages(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
	    // Guardamos los mensajes que nos da en la variable mensaje
	    List<String> mensajes = new ArrayList<String>();
	    while(matcher.find())
	    {
	       	mensajes.add(matcher.group(0));
	    }
	    return mensajes;
	}
	
	public Bitmap downloadImage(List<String> imagenes,String url,Integer index)
	{
		URL imageUrl = null;
	    try
	    {
	    	imageHttpAddress = "";
	    	imageHttpAddress=url+imagenes.get(index);
	    	imageUrl = new URL(imageHttpAddress);
	    	HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
	    	conn.connect();
	    	loadedImage=(BitmapFactory.decodeStream(conn.getInputStream()));
	    	Log.w("imagenes", loadedImage.toString());
	   	}
	    catch (IOException e)
	    {
	    	Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
	        e.printStackTrace();
	    }
	    return loadedImage;
	}

}
