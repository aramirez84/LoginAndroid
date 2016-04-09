package com.sencide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AndroidLogin extends Activity implements OnClickListener
{
	private Button ok;
	private ProgressBar pb;
	private EditText uname,pword;
	private String urlLogin="https://ayamictlan.uam.mx:8443/sae/azc/AEWBU004.oIniSesWebLic?mod=1";
	HttpClient httpclient = new DefaultHttpClient();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        uname = (EditText)findViewById(R.id.txt_username);
    	pword = (EditText)findViewById(R.id.txt_password);
    	
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		pb.setVisibility(View.GONE);
        // Login button clicked
        ok = (Button)findViewById(R.id.btn_login);
        ok.setOnClickListener(this);
    }
    
    @Override
	public void onClick(View view)
    {
    	if(view == ok){
    		Toast.makeText(getApplicationContext(), "Validando datos....", Toast.LENGTH_SHORT).show();
			pb.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute(urlLogin);
		}
	}
	
    private StringBuilder inputStreamToString(InputStream is){
    	String line = "";
    	StringBuilder total = new StringBuilder();
    	// Wrap a BufferedReader around the InputStream
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	// Read response until the end
    	try {
    		while ((line = rd.readLine()) != null) {
    			total.append(line); 
			}
		}
    	catch (IOException e) {
			e.printStackTrace();
		}
    	// Return full string
    	return total;
    }

	
	/*
	 * Metodo para filtrar contenido
	 * @arametros
	 * pattern .- Expresion regular para quitar texto no deseado
	 * str.- Texto que va a ser filtrado con la esprexion regular
	 */
	public List<String> getMensaje(Pattern pattern,String str)
	{
		Matcher matcher = pattern.matcher(str);
        // Guardamos los mensajes que nos da en la variable mensaje
        List<String> mensajes = new ArrayList<String>();
        while(matcher.find()){
        	mensajes.add(matcher.group(0));
        }
		return mensajes;
	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, String>
	{
		
		 
		@Override
		protected String doInBackground(String... params)
		{
			HttpResponse response = null;
			
			List<String> mensajes2=null;
			String resultPost=null;
			// Create a new HttpClient and Post Header
			
			HttpPost httppost = new HttpPost(params[0]);
			
			try {
				String username = uname.getText().toString();
				String password = pword.getText().toString();
	        	
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("SIGLAS_UNI_XX.E_UNIDAD.AE02.1","AZC"));
	            nameValuePairs.add(new BasicNameValuePair("%23.E_UNIDAD.AE02.1","AxJDMQ=="));
	            nameValuePairs.add(new BasicNameValuePair("%23CRC.E_UNIDAD.AE02.1","0000006B"));
	            nameValuePairs.add(new BasicNameValuePair("NOMBRE.IDENTIFICACION.NONMODELED", username));
	            nameValuePairs.add(new BasicNameValuePair("COMPLEMENTO.IDENTIFICACION.NONMODELED", password));
	            nameValuePairs.add(new BasicNameValuePair("GO.IDENTIFICACION.NONMODELED","Entrar"));
	            nameValuePairs.add(new BasicNameValuePair("%25.IDENTIFICACION.NONMODELED.1",""));
	            nameValuePairs.add(new BasicNameValuePair("%23.WEB_INFO.SW01.1","CxJDMjA5MzMwMDcy"));
	            nameValuePairs.add(new BasicNameValuePair("%23CRC.WEB_INFO.SW01.1","00000022"));
	            nameValuePairs.add(new BasicNameValuePair("%23.WEB_MOD_ASO.SW01.1",""));
	            nameValuePairs.add(new BasicNameValuePair("%23.USUARIO_ANEXO.SG02.1",""));
	            nameValuePairs.add(new BasicNameValuePair("%23.MODULO_UWAS.SAE01.1",""));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8));

	            // Execute HTTP Post Request
	            Log.w("SENCIDE", "Execute HTTP Post Request");
	            response = httpclient.execute(httppost);
	            String str = inputStreamToString(response.getEntity().getContent()).toString();
	            //Filtramos el atributo onload que nos da la valdiacion del formulario
	            Pattern pattern = Pattern.compile("(\\(.*?)\\)");
	            List<String> mensajes=getMensaje(pattern, str);
	            //Hacemos un segundo filto para obtener solo el mensaje de validacion del formualrio
	            Pattern pattern2 = Pattern.compile("[A-Z].*[a-z]");
	            mensajes2=getMensaje(pattern2, mensajes.get(1));
	            
	            Log.w("ALERTA",mensajes2.toString());
	            //Log.w("SENCIDE", str);
	            if(mensajes.size()==2)
	            {
	            	resultPost=Integer.toString(mensajes.size());
	            }else
	            {
	            	resultPost=mensajes2.toString();
	            }
			} catch (ClientProtocolException e) {
				
			} catch (IOException e) {
				
			}
			return resultPost;
			
		}
 
		protected void onPostExecute(String result)
		{
			pb.setVisibility(View.GONE);
			if(result.equals("2"))
            {
            	Log.w("SENCIDE", "TRUE");
            	Intent intent = new Intent(AndroidLogin.this, MenuApplication.class);
            	startActivity(intent);
            	uname.setText("");
            	pword.setText("");
            }else
            {
            	Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();            	
            }
		}
		
 
	}

	
}