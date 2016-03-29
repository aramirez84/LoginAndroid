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
import org.w3c.dom.Text;

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
	private ImageView imageAviso1,imageAviso2,imageAviso3,imageAviso4,imageAviso5;
    private TextView textAviso1,textAviso2,textAviso3,textAviso4,textAviso5;
    private TextView textConsejo1,textConsejo2,textConsejo3;
    private TextView textDetalleConsejo1,textDetalleConsejo2,textDetalleConsejo3;
    private ImageView imageAgenda1,imageAgenda2,imageAgenda3,imageAgenda4;
    private TextView textAgenda1,textAgenda2,textAgenda3,textAgenda4;
    private ImageView imageNoticia1,imageNoticia2;
    private TextView textNoticia1,textNoticia2;
    private Bitmap loadedImage;
    
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
        
               
        String urlImagen=getConnection(UrlUAM);
                
        /*************************	Tab 1	***************************/
        Pattern pattern = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.jpg|\\/coordinaciones\\/difusion\\/imagenes\\/[a-zA-Z10-9_\\s-]*.gif");
        List<String> imagenes=getImages(pattern, urlImagen);
        Pattern patternDetalles = Pattern.compile("\\/agenda.*div=1");
        List<String> detalles=getImages(patternDetalles, urlImagen);
        
        imageAviso1 = (ImageView) findViewById(R.id.imageAviso1);
        imageAviso2 = (ImageView) findViewById(R.id.imageAviso2);
        imageAviso3 = (ImageView) findViewById(R.id.imageAviso3);
        imageAviso4 = (ImageView) findViewById(R.id.imageAviso4);
        imageAviso5 = (ImageView) findViewById(R.id.imageAviso5);
        
        textAviso1=(TextView)findViewById(R.id.textViewAviso1);
        textAviso2=(TextView)findViewById(R.id.textViewAviso2);
        textAviso3=(TextView)findViewById(R.id.textViewAviso3);
        textAviso4=(TextView)findViewById(R.id.textViewAviso4);
        textAviso5=(TextView)findViewById(R.id.textViewAviso5);
                
        setDetalle(detalles, UrlUAM, 0, textAviso1);
        setDetalle(detalles, UrlUAM, 1, textAviso2);
        setDetalle(detalles, UrlUAM, 2, textAviso3);
        setDetalle(detalles, UrlUAM, 3, textAviso4);
        setDetalle(detalles, UrlUAM, 4, textAviso5);
        
        imageAviso1.setImageBitmap(downloadImage(imagenes, UrlUAM,0));
        imageAviso2.setImageBitmap(downloadImage(imagenes, UrlUAM,1));
        imageAviso3.setImageBitmap(downloadImage(imagenes, UrlUAM,2));
        imageAviso4.setImageBitmap(downloadImage(imagenes, UrlUAM,3));
        imageAviso5.setImageBitmap(downloadImage(imagenes, UrlUAM,4));
        
        /*************************	Tab 2	***************************/
        Pattern patternConsejo = Pattern.compile("<span class=\"titulohome\">[A-Z0].*");
        Pattern patternDetallesConsejo = Pattern.compile("app\\/ca\\/docs\\/[a-z0-9_-].*\\.pdf|http:\\/\\/consejoacademico.azc.uam.mx\\/mod\\/folder\\/view\\.php.*[0-9]");
        List<String> consejo=getImages(patternConsejo, urlImagen);
        List<String> detallesConsejo=getImages(patternDetallesConsejo, urlImagen);
        
        textConsejo1=(TextView)findViewById(R.id.textViewConsejo1);
        textConsejo2=(TextView)findViewById(R.id.textViewConsejo2);
        textConsejo3=(TextView)findViewById(R.id.textViewConsejo3);
        textDetalleConsejo1=(TextView)findViewById(R.id.textViewDetalleConsejo1);
        textDetalleConsejo2=(TextView)findViewById(R.id.textViewDetalleConsejo2);
        textDetalleConsejo3=(TextView)findViewById(R.id.textViewDetalleConsejo3);
        setConsejo(consejo, 3, textConsejo1);
        setConsejo(consejo, 4, textConsejo2);
        setConsejo(consejo, 5, textConsejo3);
        setDetalle(detallesConsejo,UrlUAM+"/", 1, textDetalleConsejo1);
        setDetalle(detallesConsejo,"", 2, textDetalleConsejo2);
        setDetalle(detallesConsejo,UrlUAM+"/", 3, textDetalleConsejo3);
        
        /*************************	Tab 3	***************************/
        Pattern patternActividades = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[A-Z-a-z0-9-_\\s]*.jpg");
        List<String> actividades=getImages(patternActividades, urlImagen);
        imageAgenda1=(ImageView)findViewById(R.id.imageAgenda1);
        imageAgenda2=(ImageView)findViewById(R.id.imageAgenda2);
        imageAgenda3=(ImageView)findViewById(R.id.imageAgenda3);
        imageAgenda4=(ImageView)findViewById(R.id.imageAgenda4);
        textAgenda1=(TextView)findViewById(R.id.textViewAgenda1);
        textAgenda2=(TextView)findViewById(R.id.textViewAgenda2);
        textAgenda3=(TextView)findViewById(R.id.textViewAgenda3);
        textAgenda4=(TextView)findViewById(R.id.textViewAgenda4);
        
        imageAgenda1.setImageBitmap(downloadImage(actividades, UrlUAM,actividades.size()-4));
        imageAgenda2.setImageBitmap(downloadImage(actividades, UrlUAM,actividades.size()-3));
        imageAgenda3.setImageBitmap(downloadImage(actividades, UrlUAM,actividades.size()-2));
        imageAgenda4.setImageBitmap(downloadImage(actividades, UrlUAM,actividades.size()-1));
        setDetalle(detalles, UrlUAM, actividades.size()-4, textAgenda1);
        setDetalle(detalles, UrlUAM, actividades.size()-3, textAgenda2);
        setDetalle(detalles, UrlUAM, actividades.size()-2, textAgenda3);
        setDetalle(detalles, UrlUAM, actividades.size()-1, textAgenda4);
        
        /*************************	Tab 4	***************************/
        Pattern patternNoticias = Pattern.compile("\\/privado\\/noticias\\/imagenes\\/[a-z0-9_A-Z-\\s]*\\.(jpg|gif)");
        Pattern patternDetalleNoticias = Pattern.compile("\\/noticias\\.php\\?id=[a-z0-9\\&;=]*");
        List<String> noticias=getImages(patternNoticias, urlImagen);
        List<String> detallesNoticias=getImages(patternDetalleNoticias, urlImagen);
        
        imageNoticia1=(ImageView)findViewById(R.id.imageNoticia1);
        imageNoticia2=(ImageView)findViewById(R.id.imageNoticia2);
        textNoticia1=(TextView)findViewById(R.id.textViewNoticia1);
        textNoticia2=(TextView)findViewById(R.id.textViewNoticia2);
        imageNoticia1.setImageBitmap(downloadImage(noticias, UrlUAM,0));
        imageNoticia2.setImageBitmap(downloadImage(noticias, UrlUAM,1));
        setDetalle(detallesNoticias, UrlUAM, 0, textNoticia1);
        setDetalle(detallesNoticias, UrlUAM, 1, textNoticia2);
        
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
        		if(tabId=="mitab3")
        		{
        			Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
        		}
        		if(tabId=="mitab4")
        		{
        			Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
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
	    httpclient.getConnectionManager().shutdown();
	    return str;
	}
	
	public List<String> getImages(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
	    // Guardamos los mensajes que nos da en la variable mensaje
	    List<String> mensajes = new ArrayList<String>();
	    //int i=0;
	    //Log.w("Regexp: ",pattern.toString());
	    while(matcher.find())
	    {
	    	mensajes.add(matcher.group(0));
	    	//i=i+1;
	       	//Log.w("Get Recurso: "+i,matcher.group(0).toString());
	    }
	    return mensajes;
	}
	
	public Bitmap downloadImage(List<String> imagenes,String url,Integer index)
	{
		URL imageUrl = null;
	    try
	    {
	    	imageHttpAddress = "";
	    	imageHttpAddress=url+imagenes.get(index).replace(" ","%20").replace("class=\"detalle\">Ver detalle>", "");
	    	imageUrl = new URL(imageHttpAddress);
	    	HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
	    	conn.connect();
	    	loadedImage=(BitmapFactory.decodeStream(conn.getInputStream()));
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
	}
	public void setConsejo(List<String> imagenes,Integer index,TextView text)
	{
		String newText=null;
		newText=imagenes.get(index).replace("<span class=\"titulohome\">","");
		newText=newText.replace("</span>","");
		newText=newText.replace("<br>","");
		text.setText(newText);
	}

}
