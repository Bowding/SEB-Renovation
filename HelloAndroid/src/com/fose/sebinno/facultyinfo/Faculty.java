package com.fose.sebinno.facultyinfo;

import com.fose.sebinno.main.QuiescentState;
import com.fose.sebinno.profintro.AcademicStaff;
import com.test.helloandroid.R;
import com.test.helloandroid.R.layout;
import com.test.helloandroid.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class Faculty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faculty);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.faculty, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	AlertDialog.Builder dlg = new AlertDialog.Builder(Faculty.this);
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(Faculty.this, QuiescentState.class);
				startActivity(intent);
                break;
                
            case R.id.view_more:
            	intent = new Intent();
        		intent.setAction("android.intent.action.VIEW");
        		Uri link_url = Uri.parse("http://www.nottingham.edu.cn/en/science-engineering/index.aspx");
        		intent.setData(link_url);
        		startActivity(intent);
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }

}
