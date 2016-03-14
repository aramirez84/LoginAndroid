package com.sencide;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AvisosListAdapter extends ArrayAdapter<String>
{
	private final Activity context;
    private final String[] itemname;
    private final  List<String> imagenes;
    private Bitmap loadedImage;

    public AvisosListAdapter(Activity context, String[] itemname , List<String> imagenes) {
        super(context, R.layout.item_aviso, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imagenes=imagenes;
    }

    public View getView(int posicion,View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_aviso,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname[posicion]);
        URL imageUrl = null;
        try {
     	   for (int i=0;i<imagenes.size();i++)
    			{
     		   imageHttpAddress = "";
     		   imageHttpAddress=url+imagenes.get(i);
     		   imageUrl = new URL(imageHttpAddress);
     		   HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
     		   conn.connect();
     		   loadedImage[i]=(BitmapFactory.decodeStream(conn.getInputStream()));
     		   Log.w("imagenes", loadedImage.toString());
     		   //imageView.setImageBitmap(loadedImage);
    			}
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        //imageView.setImageBitmap(integers[posicion]);
        etxDescripcion.setText("Description "+itemname[posicion]);

        return rowView;
    }

}
