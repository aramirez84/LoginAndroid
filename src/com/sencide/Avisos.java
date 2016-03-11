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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Avisos extends Activity
{
	private String UrlUAM = "http://www.azc.uam.mx/";
	private String imageHttpAddress = null; 
	private ImageView imageView;
    private Bitmap loadedImage;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
        imageView = (ImageView) findViewById(R.id.image_view);       
        getConnection(UrlUAM);
        
            
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
	void downloadFile(String imageHttpAddress) {
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
            imageView.setImageBitmap(loadedImage);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void getConnection(String url)
    {
    	HttpResponse response = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try
        {
        	Log.w("Get images", "Execute HTTP UAM Request");
            response = httpclient.execute(httpget);
            HttpEntity ent=response.getEntity();  
            ent=response.getEntity();
            String str = EntityUtils.toString(ent);
            Pattern pattern = Pattern.compile("\\/privado\\/difusion\\/imagenes\\/[a-zA-Z10-9_]*.jpg");
            getImages(pattern, str);
            //System.out.println(str);
            
        }
        catch (ClientProtocolException e)
        {
        	e.printStackTrace();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
    public List<String> getImages(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
        // Guardamos los mensajes que nos da en la variable mensaje
        List<String> mensajes = new ArrayList<String>();
        while(matcher.find()){
        	mensajes.add(matcher.group(0));
        }
        Log.w("Get images", mensajes.toString());
		return mensajes;
	}
}
