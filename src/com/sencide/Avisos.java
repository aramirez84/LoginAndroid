package com.sencide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Avisos extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.avisos);
            
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
