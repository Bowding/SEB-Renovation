package com.fose.sebinno.main;

import java.util.ArrayList;
import java.util.List;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.facultyinfo.Faculty;
import com.fose.sebinno.labintro.Laboratories;
import com.fose.sebinno.navigation.Navigation;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuiescentState extends Activity {

	private LinearLayout llFaculty;
	private LinearLayout llStaff;
	private LinearLayout llLab;
	private LinearLayout llNav;
	TextView btnSignin;
	private Gradient mGradient;
	private ImageView iv0, iv1, iv2;
	
	public static DBHelper dbh;
	private int[] imageIDs;
	
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
		mGradient = (Gradient) super.findViewById(R.id.gradient);
		iv0 = (ImageView) super.findViewById(R.id.iv0);
		iv1 = (ImageView) super.findViewById(R.id.iv1);
		iv2 = (ImageView) super.findViewById(R.id.iv2);
		
		llFaculty.setOnClickListener(new FuncBtnOnClickListener());
		llStaff.setOnClickListener(new FuncBtnOnClickListener());
		llLab.setOnClickListener(new FuncBtnOnClickListener());
		llNav.setOnClickListener(new FuncBtnOnClickListener());
		btnSignin.setOnClickListener(new SigninBtnOnClickListener());
		

		//llFaculty.setOnTouchListener(new FuncBtnTouchListener());
		//llStaff.setOnTouchListener(new FuncBtnTouchListener());
		//llLab.setOnTouchListener(new FuncBtnTouchListener());
		//llNav.setOnTouchListener(new FuncBtnTouchListener());
		
		//alterDisplayedImage();
		ArrayList<ImageView> list = new ArrayList<ImageView>();
        list.add(iv0);
        list.add(iv1);
        list.add(iv2);
        
        ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://www.nottingham.edu.cn/en/news/2017/university-of-nottingham-achieves-gold-standard-in-tef.aspx");
        urls.add("http://www.nottingham.edu.cn/cn/project-transform/%E7%BD%91%E4%B8%8A%E9%A2%84%E6%B3%A8%E5%86%8C%E6%B5%81%E7%A8%8B.aspx");
        urls.add("http://www.nottingham.edu.cn/en/Faculties/Index.aspx");
        
        mGradient.setImageLinks(urls);
        mGradient.setImageViews(list);
		mGradient.setCaller(this);
	}
	
	private void startWebBrowser(){

		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri link_url = Uri.parse("http://www.nottingham.edu.cn/en/news/2017/university-of-nottingham-achieves-gold-standard-in-tef.aspx");
		intent.setData(link_url);
		startActivity(intent);

	}
	
	private class ImageClick implements OnClickListener{

        @Override
        public void onClick(View view) {
            startWebBrowser();
        }

    }
	
	class MyAnim implements Runnable{

		private ImageView target;
		private int[] targetIDs;

		public MyAnim(ImageView targetArea, int[] imageIDs){

			target = targetArea;
			targetIDs = imageIDs;

		}

		private void setAnim(){

			AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.2f);
			alphaAnim.setDuration(3000);
			alphaAnim.setFillAfter(true);
			alphaAnim.setFillBefore(false);
			alphaAnim.setRepeatCount(2);

			target.startAnimation(alphaAnim);

		}

		@Override
		public void run(){

			try{

				for(int i = 0; i <= targetIDs.length; i ++){
					//btnSignin.setText(String.valueOf(i));
					target.setImageResource(targetIDs[i]);
					setAnim();

					Thread.sleep(3000);
                    i ++;

				}

			}catch(Exception e){

				e.printStackTrace();

			}

		}

	}
	
	private void alterDisplayedImage(){

        ImageView displayArea;

        imageIDs = new int[]{R.drawable.gold, R.drawable.gaokao, R.drawable.phd};
        displayArea = (ImageView) findViewById(R.id.iv0);

		//displayArea.setImageResource(imageIDs[0]);
		//displayArea.setOnClickListener(new ImageClick());

		MyAnim myAnim = new MyAnim(displayArea, imageIDs);
        new Thread(myAnim).start();

    }
	
	private class FuncBtnOnClickListener implements OnClickListener{
		
		int funcID;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			funcID = v.getId();
			
			//v.setBackgroundColor(0xFFa7bdd6);
			
			switch(funcID){
			case R.id.llFaculty:
				//open faculty intro
				intent = new Intent(QuiescentState.this, Faculty.class);
				startActivity(intent);
				
				break;
			case R.id.llStaff:
				//open AcademicStaff
				intent = new Intent(QuiescentState.this, AcademicStaff.class);
				startActivity(intent);
				
				break;
			case R.id.llLab:
				//open Lab intro
				intent = new Intent(QuiescentState.this, Laboratories.class);
				startActivity(intent);
				
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

	@Override
	public void onDestroy(){
	    super.onDestroy();

	    System.out.println("clean animation!!!");

	    mGradient.stop();
	}
}
