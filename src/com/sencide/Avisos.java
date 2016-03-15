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
        Resources res = getResources();
        
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.Avisos);
        spec.setIndicator("Avisos", 
        		res.getDrawable(android.R.drawable.ic_btn_speak_now));
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
				Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
			}
		});        
            
        // Get The Refference Of Button  
        Button btnShowAnimal=(Button)findViewById(R.id.butttonShowAnimal);
        
        // Set OnClick Listener on  button  and start AnimalListActivity when clicked on Button
        btnShowAnimal.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent intentAnimalList=new Intent(getApplicationContext(),ShowAvisos.class);
                startActivity(intentAnimalList);
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
	        Pattern pattern = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[a-zA-Z10-9_]*.jpg");
	        imagenes=getImages(pattern, str);
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
	
	public void downloadImage(List<String> imagenes,String url)
	{
		URL imageUrl = null;
	    try
	    {
	    	for (int i=0;i<imagenes.size();i++)
	   		{
	    		imageHttpAddress = "";
	    		imageHttpAddress=url+imagenes.get(i);
	    		imageUrl = new URL(imageHttpAddress);
	    		HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
	    		conn.connect();
	    		loadedImage=(BitmapFactory.decodeStream(conn.getInputStream()));
	    		Log.w("imagenes", loadedImage.toString());
	    		imageAviso1.setImageBitmap(loadedImage);
	   		}
	    }
	    catch (IOException e)
	    {
	    	Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
	        e.printStackTrace();
	    }
	}

}
