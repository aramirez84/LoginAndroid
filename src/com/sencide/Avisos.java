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
	private String imageHttpAddress = "";
	private ImageView imageView;
    private Bitmap loadedImage;
    List<String> imagenes;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
        //getConnection(UrlUAM);
                
            
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
}
