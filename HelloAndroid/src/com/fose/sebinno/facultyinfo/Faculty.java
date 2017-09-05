package com.fose.sebinno.facultyinfo;

import java.util.Locale;

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
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class Faculty extends Activity {
	
	private Configuration config;
	private DisplayMetrics dm;
	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faculty);
		
		resources = getResources();
        config = resources.getConfiguration();
        dm = resources.getDisplayMetrics();
	}

	private void showCustomViewDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.log_in);

        /**
         * 设置内容区域为自定义View
         */
        LinearLayout loginDialog= (LinearLayout) getLayoutInflater().inflate(R.layout.login,null);
        builder.setView(loginDialog);
        builder.setPositiveButton(R.string.log_in,null);
        builder.setNegativeButton(R.string.cancel,null);

        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
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
            	showCustomViewDialog();
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
        		break;
            case R.id.language:
            	if(item.getTitle().equals("English")){
            		config.locale = Locale.UK;
            		resources.updateConfiguration(config, dm);
            		//onCreate(null);
            		recreate();
            	}
            	else{
            		config.locale = Locale.SIMPLIFIED_CHINESE;
            		resources.updateConfiguration(config, dm);
            		//onCreate(null);
            		recreate();
            	}
            	
            	break;	
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }

}
