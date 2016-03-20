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
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class Avisos extends Activity
{
	private String UrlUAM = "http://www.azc.uam.mx";
	private String imageHttpAddress = "";
	private ImageView imageAviso1,imageAviso2,imageAviso3,imageAviso4,imageAviso5,imageAviso6;
    private TextView textAviso1,textAviso2,textAviso3,textAviso4,textAviso5,textAviso6;
	private Bitmap loadedImage;
    
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
        
        Pattern pattern = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.jpg|\\/coordinaciones\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.gif");
        Pattern patternDetalles = Pattern.compile("\\/agenda.*");
        Pattern patternConsejo = Pattern.compile("<span class=\"titulohome\">[A-Z0].*");
        Pattern patternDetallesConsejo = Pattern.compile("app\\/ca\\/docs\\/[a-z].*[;]|http:\\/\\/consejoacademico.azc.uam.mx\\/mod\\/folder\\/view\\.php\\?id=[0-9]*");
        Pattern patternActividades = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[A-Z-a-z0-9-_]*.(jpg|gif)");
        Pattern patternNoticias = Pattern.compile("\\/privado\\/noticias\\/imagenes\\/[a-z0-9_A-Z]*\\.(jpg|gif)");
        Pattern patternDetalleNoticias = Pattern.compile("\\/noticias\\.php\\?id=[a-z0-9\\&;=]*");
        
        
        String urlImagen=getConnection(UrlUAM);
        List<String> imagenes=getImages(pattern, urlImagen);
        List<String> detalles=getImages(patternDetalles, urlImagen);
        List<String> consejo=getImages(patternConsejo, urlImagen);
        List<String> detallesConsejo=getImages(patternDetallesConsejo, urlImagen);
                
        /*************************	Tab 1	***************************/
        
        imageAviso1 = (ImageView) findViewById(R.id.imageAviso1);
        imageAviso2 = (ImageView) findViewById(R.id.imageAviso2);
        imageAviso3 = (ImageView) findViewById(R.id.imageAviso3);
        imageAviso4 = (ImageView) findViewById(R.id.imageAviso4);
        imageAviso5 = (ImageView) findViewById(R.id.imageAviso5);
        imageAviso6 = (ImageView) findViewById(R.id.imageAviso6);
        textAviso1=(TextView)findViewById(R.id.textViewAviso1);
        textAviso2=(TextView)findViewById(R.id.textViewAviso2);
        textAviso3=(TextView)findViewById(R.id.textViewAviso3);
        textAviso4=(TextView)findViewById(R.id.textViewAviso4);
        textAviso5=(TextView)findViewById(R.id.textViewAviso5);
        textAviso6=(TextView)findViewById(R.id.textViewAviso6);
        
        setDetalle(detalles, UrlUAM, 1, textAviso1);
        setDetalle(detalles, UrlUAM, 2, textAviso2);
        setDetalle(detalles, UrlUAM, 3, textAviso3);
        setDetalle(detalles, UrlUAM, 4, textAviso4);
        setDetalle(detalles, UrlUAM, 5, textAviso5);
        setDetalle(detalles, UrlUAM, 6, textAviso6);
        imageAviso1.setImageBitmap(downloadImage(imagenes, UrlUAM,0));
        imageAviso2.setImageBitmap(downloadImage(imagenes, UrlUAM,1));
        imageAviso3.setImageBitmap(downloadImage(imagenes, UrlUAM,2));
        imageAviso4.setImageBitmap(downloadImage(imagenes, UrlUAM,3));
        imageAviso5.setImageBitmap(downloadImage(imagenes, UrlUAM,4));
        imageAviso6.setImageBitmap(downloadImage(imagenes, UrlUAM,5));
        
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
        			Log.i("AndroidTabsDemo", "Pulsada pesta�a: " + tabId);
        		}
        		if(tabId=="mitab3")
        		{
        			Log.i("AndroidTabsDemo", "Pulsada pesta�a: " + tabId);
        		}
        		if(tabId=="mitab4")
        		{
        			Log.i("AndroidTabsDemo", "Pulsada pesta�a: " + tabId);
        		}
			}
		});        
    }
	public  String getConnection(String url)
	{
		String str=null;
		HttpResponse response = null;
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url);
	    try
	    {
	    	response = httpclient.execute(httpget);
	        HttpEntity ent=response.getEntity();  
	        ent=response.getEntity();
	        str = EntityUtils.toString(ent);	        
	    }
	    catch (ClientProtocolException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    return str;
	}
	
	public List<String> getImages(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
	    // Guardamos los mensajes que nos da en la variable mensaje
	    List<String> mensajes = new ArrayList<String>();
	    int i=0;
	    Log.w("Regexp: ",pattern.toString());
	    while(matcher.find())
	    {
	    	mensajes.add(matcher.group(0));
	    	i=i+1;
	       	Log.w("Get Recurso: "+i,matcher.group(0).toString());
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
	    	//Log.w("imagenes", loadedImage.toString());
	   	}
	    catch (IOException e)
	    {
	    	Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
	        e.printStackTrace();
	    }
	    return loadedImage;
	}
	public void setDetalle(List<String> imagenes,String url,Integer index,TextView text)
	{
		imageHttpAddress=url+imagenes.get(index);
		text.setText(Html.fromHtml("<a href="+imageHttpAddress+">Detalles ....</a>"));
		text.setMovementMethod(LinkMovementMethod.getInstance());
		Log.w("imagenes", imagenes.get(index).toString());
	}

}
