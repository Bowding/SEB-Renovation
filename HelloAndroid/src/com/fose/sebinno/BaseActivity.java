package com.fose.sebinno;

import com.fose.sebinno.main.QuiescentState;
import com.fose.sebinno.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

public abstract class BaseActivity extends Activity{

	private Handler handler = new Handler();
	private long time = 5000;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            handler.removeCallbacks(runnable);
	            break;
	        case MotionEvent.ACTION_UP:
	            startAD();
	            break;
	    }
	    Toast.makeText(getApplicationContext(), "Time up!!!!", Toast.LENGTH_LONG).show();
	    //return super.onTouchEvent(event);
	    return false;
	}
	private Runnable runnable = new Runnable() {
	    @Override
	    public void run() {
	    	Intent intent = new Intent(BaseActivity.this, Navigation.class);
			startActivity(intent);
	    }
	};
	public void startAD() {
	    handler.removeCallbacks(runnable);
	    handler.postDelayed(runnable, time);
	}
	
	

}
