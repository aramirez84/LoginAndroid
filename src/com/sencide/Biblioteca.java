package com.sencide;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;


public class Biblioteca extends Activity
{
	private List<String> cookies;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla_horario);
        Bundle bundle = getIntent().getExtras();
        cookies=bundle.getStringArrayList("cookies");
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
}