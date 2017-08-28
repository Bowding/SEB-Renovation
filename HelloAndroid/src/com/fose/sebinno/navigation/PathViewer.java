package com.fose.sebinno.navigation;

import java.util.ArrayList;

import com.fose.sebinno.Coordinate;
import com.fose.sebinno.main.QuiescentState;
import com.test.helloandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PathViewer extends Activity {

	private ImageView ivMap, ivMap2, ivMap3, ivMap4;
	private ImageView ivPath = null;
	private TextView tvInstruction = null;
	private Bitmap bitmap = null, pathBitmap = null;
	private Coordinate destCoord = null;
	private Map map = null;
	
	ArrayList<ImageView> ivMaps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_viewer);
		
		ivMap = (ImageView) super.findViewById(R.id.ivMap);
		//ivMap2 = (ImageView) super.findViewById(R.id.ivMap2);
		//ivMap3 = (ImageView) super.findViewById(R.id.ivMap3);
		//ivMap4 = (ImageView) super.findViewById(R.id.ivMap4);
		ivPath = (ImageView) super.findViewById(R.id.ivPath);
		tvInstruction = (TextView) super.findViewById(R.id.tvInstruction);
		
		//ivMaps = new ArrayList<ImageView>();
		//ivMaps.add(ivMap1);
		//ivMaps.add(ivMap2);
		//ivMaps.add(ivMap3);
		//ivMaps.add(ivMap4);
		
		Intent getIntent = getIntent();  
        int destLocID = getIntent.getIntExtra("destLocID", -1);  
        int level = getIntent.getIntExtra("level", -1);  
        
        String instructionStr = "Please head to level " + level + " using stairs or lift, and follow the detailed path on that floor as displayed.";
        tvInstruction.setText(instructionStr);

		//load map
		//handle input
		//test data
		//int level = 2;
		map = new Map(level);
		
		//handle input
		//test data
		//int destLocID = 201;
		
		
		//String fileName = "res\\drawable-hdpi\\level2.jpg"; 
		
		//clean bitmap
		//if(bitmap != null){
		//	System.out.println("clean bitmap!!!");
		//    bitmap.recycle();
		//    bitmap = null;
		//    System.gc();
		//}
		
		//set ivMap to map in terms of levels
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 1;
		opts.inPreferredConfig = Config.RGB_565;
		
		bitmap = BitmapFactory.decodeResource(getResources(), map.getMapResourceID(), opts); 
		ivMap.setImageBitmap(bitmap); 
		//setMap(level);
		
		//clean pathBitmap
		if(pathBitmap != null && !pathBitmap.isRecycled()){
			pathBitmap.recycle();
			pathBitmap = null;
		    System.gc();
		}
		
		System.out.println(bitmap.getWidth() + " + " + bitmap.getHeight());
		pathBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);  
		
		PathDrawer pd = new PathDrawer(pathBitmap, map, destLocID);
		pd.drawPath();
	
		ivPath.setImageBitmap(pd.getBitmap());
		
		//bitmap = Bitmap.createBitmap(800,671,Bitmap.Config.ARGB_8888);
		//ivMap.setDrawingCacheEnabled(true);
		//bitmap=ivMap.getDrawingCache();
		
		//test
		//destCoord = new Coordinate(30.5f, 30.5f, 238);
		//Thread_PathDrawer t_pd = new Thread_PathDrawer(bitmap, destCoord);
		
		//t_pd.start();
	}

	private void setMap(int level){
		for(int i = 1; i <= ivMaps.size(); i++){
			if(i == level){
				ivMaps.get(i).setVisibility(View.VISIBLE);
			}
			else{
				ivMaps.get(i).setVisibility(View.INVISIBLE);
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.path_viewer, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	AlertDialog.Builder dlg = new AlertDialog.Builder(PathViewer.this);
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(PathViewer.this, QuiescentState.class);
				startActivity(intent);
                break;  
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }
	
	@Override
	public void onDestroy(){
	    super.onDestroy();

	    System.out.println("clean bitmap!!!");
		bitmap.recycle();
		bitmap = null;
		System.gc();
		
		pathBitmap.recycle();
		pathBitmap = null;
	    System.gc();
	}

}
