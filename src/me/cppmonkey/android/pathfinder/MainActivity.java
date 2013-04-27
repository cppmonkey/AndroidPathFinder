package me.cppmonkey.android.pathfinder;

import java.io.File;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	private ImageAdapter mImageAdapter;
	private Spinner mMapList;
	private MapReader mMapReader;
	private GridView mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mMapList = (Spinner)findViewById(R.id.spinnerMapList);
        populateMapList(mMapList);
        mMapList.setOnItemSelectedListener(this);
        
        // Load map details
        mMapReader = new MapReader(this);
        mImageAdapter = new ImageAdapter(this);
        mMap = (GridView)findViewById(R.id.gridViewMap);
        mMap.setAdapter(mImageAdapter);
        
    }

    private void populateMapList(Spinner mapList){
    	try {
			String[] fileList = getAssets().list("maps");
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, fileList);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mapList.setAdapter(dataAdapter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
    	try{
    	String loadFile = parent.getItemAtPosition(pos).toString(); 
    	mImageAdapter.setMapValues(mMapReader.load("maps" +File.separator+ loadFile));
    	
    	mMap.invalidateViews();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
