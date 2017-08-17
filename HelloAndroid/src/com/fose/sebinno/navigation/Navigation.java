package com.fose.sebinno.navigation;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.InputHandler;
import com.fose.sebinno.main.QuiescentState;
import com.test.helloandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
	
	public static DBHelper dbh;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvDestination = (EditText) super.findViewById(R.id.tvDestination);
        btnFindPath = (Button) super.findViewById(R.id.btnFindPath);
        btnFindPath.setOnClickListener(new FindPathOnClickListener());
        
        String info = "Hint: try to enter a professor's full name or a specific room number";
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
        
		dbh = new DBHelper(this);
		ih = new InputHandler(InputHandler.NAVIGATION);
		
		
    }
    
    private class FindPathOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String destination = tvDestination.getText().toString();
			int destLocID = ih.handle(destination);
			int level = ih.getLevel(destLocID);
			
			//String info = "Hint: try to enter a professor's full name or a specific room number";
			Toast.makeText(getApplicationContext(), destination, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(Navigation.this, PathViewer.class);
            intent.putExtra("destLocID", destLocID);
            intent.putExtra("level", level);
			startActivity(intent);
		}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	AlertDialog.Builder dlg = new AlertDialog.Builder(Navigation.this);
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(Navigation.this, QuiescentState.class);
				startActivity(intent);
                break;  
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }
}
