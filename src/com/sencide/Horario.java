package com.sencide;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;


public class Horario extends Activity
{
	private TextView horario;
	String horarioHtml=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        Bundle bundle = getIntent().getExtras();
        bundle.getStringArrayList("cookies");
        horario = (TextView)findViewById(R.id.horario);
        //horarioHtml = getHorario();
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