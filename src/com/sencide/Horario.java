package com.sencide;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Horario extends Activity
{
	private List<String> cookies;
	private String urlHorario="https://ayamictlan.uam.mx:8443/sae/azc/IEWBC002.oConsulta";
	private TextView horario;
	String horarioHtml=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        Bundle bundle = getIntent().getExtras();
        cookies=bundle.getStringArrayList("cookies");
        horario = (TextView)findViewById(R.id.horario);
        horarioHtml = getHorario();
        horario.setText(Html.fromHtml(horarioHtml));
        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        for(int i = 0; i < 15; i++)
        {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(Integer.toString(i));
            elementos.add("Casilla [" + i + ", 0]");
            elementos.add("Casilla [" + i + ", 1]");
            elementos.add("Casilla [" + i + ", 2]");
            elementos.add("Casilla [" + i + ", 3]");
            tabla.agregarFilaTabla(elementos);
        }
    }
    
    public String getHorario()
    {
    	String strHorario = null;
    	HttpResponse response = null;
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        
        
        HttpGet httpget = new HttpGet(urlHorario);
        httpget=setCookies(cookies,httpget);

        try {
        	   	
            // Execute HTTP Post Request
            Log.w("SENCIDE", "Execute HTTP Post Request");
            response = httpclient.execute(httpget);
            HttpEntity ent=response.getEntity();  
            ent=response.getEntity();
            strHorario = EntityUtils.toString(ent);
            System.out.println(strHorario);
            
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return strHorario;   
    } 
    public HttpGet setCookies(List<String> cookies,HttpGet httpget)
	{
		for (int i=0;i<cookies.size();i++)
		{
			Log.w("cookies Horario", cookies.get(i));
			httpget.addHeader("Cookie", cookies.get(i));
		}
		return httpget;
	}
    

}