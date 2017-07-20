package com.test.helloandroid;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Menu;
import android.widget.ImageView;

public class PathViewer extends Activity {

	private ImageView ivMap = null;
	private ImageView ivPath = null;
	private Bitmap bitmap = null, pathBitmap = null;
	private Coordinate destCoord = null;
	private Map map = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_viewer);
		
		ivMap = (ImageView) super.findViewById(R.id.ivMap);
		ivPath = (ImageView) super.findViewById(R.id.ivPath);
		
		//load map
		//handle input
		//test data
		int level = 2;
		map = new Map(level);
		
		//handle input
		//test data
		int destLocID = 201;
		
		
		//String fileName = "res\\drawable-hdpi\\level2.jpg"; 
		
		//clean bitmap
		if(bitmap != null && !bitmap.isRecycled()){
		    bitmap.recycle();
		    System.gc();
		}
		
		//set ivMap to map in terms of levels
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 1;
		
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.level2, opts); 
		ivMap.setImageBitmap(bitmap); 
		
		//clean pathBitmap
		if(pathBitmap != null && !pathBitmap.isRecycled()){
			pathBitmap.recycle();
		    System.gc();
		}
		
		System.out.println(bitmap.getWidth() + " + " + bitmap.getHeight());
		pathBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);  
		
		PathDrawer pd = new PathDrawer(pathBitmap, map, destLocID);
		pd.drawPath();
/*
		Canvas canvas = new Canvas(pathBitmap);
		//canvas.drawColor(Color.WHITE);
		
		int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        
        Coordinate start = new Coordinate(669.333f, 899.333f);
        Coordinate dest = new Coordinate(475.333f, 189.333f);
		
		//initialise a paint
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		p.setStrokeWidth(4.0f);
		        
		//draw
		canvas.drawLine(0, 0, canvasWidth, 0, p);
		canvas.drawLine(0, 0, 0, canvasHeight, p);
		canvas.drawLine(0, canvasHeight, canvasWidth, canvasHeight, p);
		canvas.drawLine(canvasWidth, 0, canvasWidth, canvasHeight, p);
		
		p.setStyle(Paint.Style.STROKE);
	    Path path = new Path();  
	    path.moveTo(start.getX(), start.getY());    
	    path.lineTo(50, 60);    
	    path.lineTo(200,80);    
	    path.lineTo(dest.getX(), dest.getY());    

	    canvas.drawPath(path, p);
*/		
		ivPath.setImageBitmap(pd.getBitmap());
		
		//bitmap = Bitmap.createBitmap(800,671,Bitmap.Config.ARGB_8888);
		//ivMap.setDrawingCacheEnabled(true);
		//bitmap=ivMap.getDrawingCache();
		
		//test
		//destCoord = new Coordinate(30.5f, 30.5f, 238);
		//Thread_PathDrawer t_pd = new Thread_PathDrawer(bitmap, destCoord);
		
		//t_pd.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.path_viewer, menu);
		return true;
	}

}
