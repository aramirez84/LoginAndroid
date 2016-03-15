package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAvisos extends Activity
{
	ArrayList<String> avisosNameList;
	private String UrlUAM = "http://www.azc.uam.mx/";
	private String imageHttpAddress = "";
	private ImageView imageView;
    private Bitmap loadedImage;
    List<String> imagenes;
    private ListView lista;
    
    private String lenguajeProgramacion[]=new String[]{"Java","PHP","Python","JavaScript","Ruby","C",
            "Go","Perl"};
	public void onCreate(Bundle saveInstanceState)
    {
    	super.onCreate(saveInstanceState);
        setContentView(R.layout.avisos_list);
        
        //Bitmap[] imgid = downloadImage(urlImagen, UrlUAM);
        //AvisosListAdapter adapter=new AvisosListAdapter(this,lenguajeProgramacion,urlImagen);
        /*
        lista=(ListView)findViewById(R.id.listViewAvisos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem= lenguajeProgramacion[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });
        */
        // Get the reference of ListViewAnimals
        ListView animalList=(ListView)findViewById(R.id.listViewAvisos);
           
           
        avisosNameList = new ArrayList<String>();
        getAnimalNames();
        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
        		new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, avisosNameList);
        // Set The Adapter
        animalList.setAdapter(arrayAdapter);
            
        // register onClickListener to handle click events on each item
        animalList.setOnItemClickListener(new OnItemClickListener()
        {
        	// argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
            	String selectedAnimal=avisosNameList.get(position);
                Toast.makeText(getApplicationContext(), "Animal Selected : "+selectedAnimal,   Toast.LENGTH_LONG).show();
            }
        });
    }
    
   void getAnimalNames()
    {
        avisosNameList.add("DOG");
        avisosNameList.add("CAT");
        avisosNameList.add("HORSE");
        avisosNameList.add("ELEPHANT");
        avisosNameList.add("LION");
        avisosNameList.add("COW");
        avisosNameList.add("MONKEY");
        avisosNameList.add("DEER");
        avisosNameList.add("RABBIT");
        avisosNameList.add("BEER");
        avisosNameList.add("DONKEY");
        avisosNameList.add("LAMB");
        avisosNameList.add("GOAT");
    }
   
}
