package com.fose.sebinno.navigation;

import java.util.ArrayList;
import java.util.Hashtable;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.InputHandler;
import com.fose.sebinno.main.QuiescentState;
import com.test.helloandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Navigation extends Activity {

	private EditText tvDestination = null;
	private Button btnFindPath = null;
	private InputHandler ih;
	
	//public static DBHelper dbh;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        
        tvDestination = (EditText) super.findViewById(R.id.tvDestination);
        btnFindPath = (Button) super.findViewById(R.id.btnFindPath);
        btnFindPath.setOnClickListener(new FindPathOnClickListener());
        
        String info = "Hint: try to enter a professor's full name or a specific room number";
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
        
		//dbh = new DBHelper(this);
		ih = new InputHandler(InputHandler.NAVIGATION);
		
		
    }
    
    private class FindPathOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String destination = tvDestination.getText().toString();
			
			int destLocID;
			int level;
			
			final ArrayList<Integer> potentialDests = ih.handle(destination);
			if(potentialDests.size() == 0){
				//no result/invalid input
				AlertDialog.Builder dlg = new AlertDialog.Builder(Navigation.this);
    	        dlg.setTitle("In-building Navigation");
    	        dlg.setMessage("No relevant result found on database. Please check your spelling and try again");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.setNeutralButton("Need help?",new DialogInterface.OnClickListener() {
    	            @Override
    	            public void onClick(DialogInterface dialogInterface, int i) {
    	            	AlertDialog.Builder dlg = new AlertDialog.Builder(Navigation.this);
    	            	dlg.setTitle("Help");
    	    	        dlg.setMessage(". Please enter a valid room number if you are heading to a specific room inside the building.\n\n. In the case of searching for the path to an academic staff's office, please enter the full name of the staff, dropping the title. Please make sure that the staff you are looking for is based in this building.\n\n. Try search using the room functionality, e.g. \"Meeting Room\".\n\n. Try search for some additional facilities within the building, e.g. \"vending machine\", \"toilet\".");
    	    	        dlg.setPositiveButton("OK",null);
    	    	        dlg.show();
    	            }
    	        });
    	        dlg.show();
			}
			else{
				if(potentialDests.size() == 1){
					destLocID = potentialDests.get(0);
					level = ih.getLevel(destLocID);
					Toast.makeText(getApplicationContext(), destination, Toast.LENGTH_LONG).show();
				
					Intent intent = new Intent(Navigation.this, PathViewer.class);
		            intent.putExtra("destLocID", destLocID);
		            intent.putExtra("level", level);
					startActivity(intent);
				}
				else{
					//show selective alert
					AlertDialog.Builder dlg = new AlertDialog.Builder(Navigation.this);
	    	        dlg.setTitle("Are you going to...");
	    	        //dlg.setMessage("No relevant result found on database.");
	    	        
	    	        //ArrayList<String> Items = new ArrayList<String>();
	    	        String[] items = configItems(potentialDests);
	    	        dlg.setItems(items, new DialogInterface.OnClickListener() {
	    	            @Override
	    	            public void onClick(DialogInterface dialogInterface, int i) {
	    	                
	    	            	Integer destLocID = potentialDests.get(i);
	    	            	int level = ih.getLevel(destLocID);
	    					Toast.makeText(getApplicationContext(), destLocID.toString(), Toast.LENGTH_LONG).show();
	    				
	    					Intent intent = new Intent(Navigation.this, PathViewer.class);
	    		            intent.putExtra("destLocID", destLocID);
	    		            intent.putExtra("level", level);
	    					startActivity(intent);
	    	            }
	    	        });
	    	        //dlg.setPositiveButton("OK",null);
	    	        //dlg.setNeutralButton("Need help?",null);
	    	        dlg.show();
				}
				/*
				
				level = ih.getLevel(destLocID);
				Toast.makeText(getApplicationContext(), destination, Toast.LENGTH_LONG).show();
			
				Intent intent = new Intent(Navigation.this, PathViewer.class);
	            intent.putExtra("destLocID", destLocID);
	            intent.putExtra("level", level);
				startActivity(intent);
				*/
			}
		}
    }

    private String[] configItems(ArrayList<Integer> potentialDests){
    	
    	int itemNum = potentialDests.size();
    	String[] items = new String[itemNum];
    	Hashtable<Integer, String> specialRid = new Hashtable<Integer, String>();
    	specialRid.put(1151, "115a");
    	specialRid.put(1191, "119a");
    	specialRid.put(1192, "119b");
    	
    	String sql, tags, roomID;
    	String[] tagsInDB;
    	Integer locID, near;
    	
    	for(int i = 0; i < itemNum; i++){
    		
    		locID = potentialDests.get(i);
    		sql = "SELECT * FROM locations WHERE locationID=" + locID;
    		Cursor c = QuiescentState.dbh.select(sql);
    		c.moveToFirst();
    		    		
    		if(specialRid.containsKey(locID)){
    			roomID = specialRid.get(locID);
    			tags = c.getString(c.getColumnIndex("tags")).replace("++", " ; ").substring(4);
    		}
    		else if(locID > 10000){		//special locID
    			tagsInDB = c.getString(c.getColumnIndex("tags")).split("\\+\\+", 2);
    			near = locID/100;
    			
    			roomID = tagsInDB[0];
    			if(tagsInDB.length == 1){
    				tags = "near Room " + near.toString();
    			}
    			else{
    				tags = "near Room " + near.toString() + " ; " + tagsInDB[1].replace("++", " ; ");
    			}
    			
    		}
    		else{
    			roomID = locID.toString();
    			tags = c.getString(c.getColumnIndex("tags")).replace("++", " ; ");
    		}

    		if(tags == null || tags.isEmpty()){
    			items[i] = roomID;
    		}
    		else{
    			items[i] = roomID + " - " + tags;
    		}
    		
    		System.out.println("tags: " + tags);
    		System.out.println("item: " + items[i]);
    	}
    	
    	return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) { 
    	
    	AlertDialog.Builder dlg = new AlertDialog.Builder(Navigation.this); 
        switch (item.getItemId()){  
            case R.id.log_in:
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(Navigation.this, QuiescentState.class);
				startActivity(intent);
                break; 
            case R.id.help:
    	        dlg.setTitle("Help");
    	        dlg.setMessage(". Please enter a valid room number if you are heading to a specific room inside the building.\n\n. In the case of searching for the path to an academic staff's office, please enter the full name of the staff, dropping the title. Please make sure that the staff you are looking for is based in this building.\n\n. Try search using the room functionality, e.g. \"Meeting Room\".\n\n. Try search for some additional facilities within the building, e.g. \"vending machine\", \"toilet\".");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }
}
