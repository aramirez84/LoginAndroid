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
	private String horarioHtml=null;
	private String user,password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        Bundle datos =getIntent().getExtras();
        user =datos.getString("user");
        password= datos.getString("pass");
        Log.w("Usuario", user);
        Log.w("Password", password);
        /*horario = (TextView)findViewById(R.id.horario);
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
        }*/
    }
}