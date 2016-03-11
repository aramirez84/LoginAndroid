package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAvisos extends Activity
{
	ArrayList<String> avisosNameList;
	private String urlAvisos="http://www.azc.uam.mx/";
	private List<String> listAvisos;
    public void onCreate(Bundle saveInstanceState)
    {
    	super.onCreate(saveInstanceState);
        setContentView(R.layout.avisos_list);
        //downloadFile(imageHttpAddress);
            
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
    
    public void getAvisos()
    {
    	String strAvisos = null;
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpGet httpget = new HttpGet(urlAvisos);
        try
        {
        	// Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httpget);
            String str = inputStreamToString(response.getEntity().getContent()).toString();
            Pattern pattern = Pattern.compile("<span>.*[a-z].");
            List<String> avisos=getListAvisos(pattern, str);
            Log.w("AVISOS",avisos.toString());
            
            
        }
        catch (ClientProtocolException e)
        {
        	e.printStackTrace();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
		//return strAvisos;   
    }
    
    public List<String> getListAvisos(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
        // Guardamos los mensajes que nos da en la variable mensaje
        List<String> avisos = new ArrayList<String>();
        while(matcher.find()){
        	avisos.add(matcher.group(0));
        }
		return avisos;
	}
    
    private StringBuilder inputStreamToString(InputStream is) {
    	String line = "";
    	StringBuilder total = new StringBuilder();
    	// Wrap a BufferedReader around the InputStream
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	// Read response until the end
    	try {
			while ((line = rd.readLine()) != null) { 
				total.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	// Return full string
    	return total;
    }
   
    void getAnimalNames()
    {
    	getAvisos();
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
