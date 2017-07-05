package com.test.helloandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	private EditText tvDestination = null;
	private Button btnFindPath = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvDestination = (EditText) super.findViewById(R.id.tvDestination);
        btnFindPath = (Button) super.findViewById(R.id.btnFindPath);
        btnFindPath.setOnClickListener(new FindPathOnClickListener());
        
        String info = "Hint: try to enter a professor's full name or a specific room number";
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
        
    }
    
    private class FindPathOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String destination = tvDestination.getText().toString();
			
			String info = "Hint: try to enter a professor's full name or a specific room number";
			Toast.makeText(getApplicationContext(), destination, Toast.LENGTH_SHORT).show();
		}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
