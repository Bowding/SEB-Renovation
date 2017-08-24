package com.fose.sebinno.main;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.navigation.Navigation;
import com.fose.sebinno.profintro.AcademicStaff;
import com.test.helloandroid.R;
import com.test.helloandroid.R.layout;
import com.test.helloandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuiescentState extends Activity {

	private LinearLayout llFaculty;
	private LinearLayout llStaff;
	private LinearLayout llLab;
	private LinearLayout llNav;
	private TextView btnSignin;
	
	public static DBHelper dbh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiescent);
		
		dbh = new DBHelper(this);
		
		llFaculty = (LinearLayout) super.findViewById(R.id.llFaculty);
		llStaff = (LinearLayout) super.findViewById(R.id.llStaff);
		llLab = (LinearLayout) super.findViewById(R.id.llLab);
		llNav = (LinearLayout) super.findViewById(R.id.llNav);
		btnSignin = (TextView) super.findViewById(R.id.btnSignin);
		
		llFaculty.setOnClickListener(new FuncBtnOnClickListener());
		llStaff.setOnClickListener(new FuncBtnOnClickListener());
		llLab.setOnClickListener(new FuncBtnOnClickListener());
		llNav.setOnClickListener(new FuncBtnOnClickListener());
		btnSignin.setOnClickListener(new SigninBtnOnClickListener());
		
		//llFaculty.setOnTouchListener(new FuncBtnTouchListener());
		//llStaff.setOnTouchListener(new FuncBtnTouchListener());
		//llLab.setOnTouchListener(new FuncBtnTouchListener());
		//llNav.setOnTouchListener(new FuncBtnTouchListener());
		
		
	}
	
	private class FuncBtnOnClickListener implements OnClickListener{
		
		int funcID;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			funcID = v.getId();
			
			v.setBackgroundColor(0xFFa7bdd6);
			
			switch(funcID){
			case R.id.llFaculty:
				//open faculty intro
				break;
			case R.id.llStaff:
				//open AcademicStaff
				intent = new Intent(QuiescentState.this, AcademicStaff.class);
				startActivity(intent);
				
				break;
			case R.id.llLab:
				//open Lab intro
				break;
			case R.id.llNav:
				//open Navigation
				intent = new Intent(QuiescentState.this, Navigation.class);
				startActivity(intent);
			}
			
			
		}
    	
    }
	
	public class FuncBtnTouchListener implements OnTouchListener{  
          
        int funcID;
        
        @Override
        public boolean onTouch(View v, MotionEvent event) {  
            //Log.e("test","onTouch,action="+event.getAction());  
        	Intent intent;
			funcID = v.getId();
			
			switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(0xFFa7bdd6);
				
				switch(funcID){
				case R.id.llFaculty:
					//open faculty intro
					break;
				case R.id.llStaff:
					//open AcademicStaff
					intent = new Intent(QuiescentState.this, AcademicStaff.class);
					startActivity(intent);
					
					break;
				case R.id.llLab:
					//open Lab intro
					break;
				case R.id.llNav:
					//open Navigation
					intent = new Intent(QuiescentState.this, Navigation.class);
					startActivity(intent);
				}
				v.setBackgroundColor(0xaaa7bdd6);
				break;
			 case MotionEvent.ACTION_UP:
				 v.setBackgroundColor(0xaaa7bdd6);
				 break;
			}
			
				
        	
            return false;  
        }
  
    } 
	
	private class SigninBtnOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			AlertDialog.Builder dlg = new AlertDialog.Builder(QuiescentState.this);
	        dlg.setTitle("Sign In");
	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
	        dlg.setPositiveButton("OK",null);
	        dlg.show();
		}
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiensent, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	AlertDialog.Builder dlg = new AlertDialog.Builder(QuiescentState.this);
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }

}
