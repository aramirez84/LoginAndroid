package com.sencide;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAvisos extends Activity
{
	ArrayList<String> avisosNameList;
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
