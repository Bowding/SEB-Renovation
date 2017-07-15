/**
 * a thread class used to draw path on maps
 */
package com.test.helloandroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Ning WANG
 *
 */
public class Thread_PathDrawer extends Thread {
	
	private Bitmap bitmap;
	private Coordinate coord;
	private Canvas canvas;
	
	public Thread_PathDrawer(Coordinate coord){
		
		this.bitmap = Bitmap.createBitmap(800, 671, Bitmap.Config.ARGB_8888);
		this.coord = coord;
		this.canvas = new Canvas(bitmap);
		
	}
	
	@Override
	public void run(){
		
		//configure coordinates
		float startX = 0.0f;
		float startY = 0.0f;
		float destX = coord.getX();
		float destY = coord.getY();
		
		//initialise a paint
		Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(4.0f);
        
        //draw
        canvas.drawLine(startX, startY, destX, destY, p);
		
	}
	
}
