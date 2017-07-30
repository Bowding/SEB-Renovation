/**
 * a thread class used to draw path on maps
 */
package com.test.helloandroid;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * @author Ning WANG
 *
 */
public class PathDrawer{
	
	private Bitmap bitmap;
	private Map map;
	private Location start, dest;
	//private Coordinate coord;
	//private Canvas canvas;
	
	public PathDrawer(Bitmap pathBitmap, Map map, int locationID){
		
		this.bitmap = pathBitmap;
		this.map = map;
		this.start = map.getLiftLoc();
		this.dest = getNearestLocation(map.configLocation(locationID));
		//this.coord = coord;
		//this.canvas = new Canvas(bitmap);
		
	}
	
	private Location getNearestLocation(ArrayList<Location> locations){
		
		float shortest_d = Float.MAX_VALUE;
		float cur_d;
		Location currentLoc = map.getLiftLoc();
		Location nearest = null;
		
		for(Location l : locations){
			
			cur_d = currentLoc.getDistanceTo(l);
			
			if(cur_d < shortest_d){
				shortest_d = cur_d;
				nearest = l;	
			}
			System.out.println("cur_nearest: " + l.getCoord().getX() + " cur_d = " + shortest_d);
		}
		System.out.println("nearest: " + nearest.getCoord().getX());
		
		return nearest;
	}
	
	private Coordinate getHallwayCoord(Location l){
		
		Coordinate hallwayCoord;
		
		String hallwayKey = l.getHallway();
		
		System.out.println(l.getLocationID() + "+++++" + hallwayKey);
		
		float coord = map.getHallwayCoord(hallwayKey);
		if(hallwayKey.startsWith("hori")){
			float newY = coord;
			hallwayCoord = new Coordinate(l.getCoord().getX(), newY);
		}
		else{
			float newX = coord;
			hallwayCoord = new Coordinate(newX, l.getCoord().getY());
		}
		
		return hallwayCoord;
	}
	
	public Path findPath(){
		
		String direction;
		
		Coordinate start_hallwayCoord = getHallwayCoord(start);
		Coordinate dest_hallwayCoord = getHallwayCoord(dest);
	    
		Path path = new Path();  
	    path.moveTo(start.getCoord().getX(), start.getCoord().getY());    
	    path.lineTo(start_hallwayCoord.getX(), start_hallwayCoord.getY());
	    
	    //config turning points
	    //test data
	    //Coordinate turning1 = new Coordinate(650/AppConfig.IMAGE_SCALE, 1463/AppConfig.IMAGE_SCALE);
	    //Coordinate turning2 = new Coordinate(650/AppConfig.IMAGE_SCALE, 326/AppConfig.IMAGE_SCALE);
		
	    float xOffset = start_hallwayCoord.getX() - map.getHallwayCoord("vert_left");
		
		if(map.getHallwayCoord("vert_right") - dest_hallwayCoord.getX() >= xOffset){
			direction = "left";
		}
		else{
			direction = "right";
		}
		ArrayList<Coordinate> turnings = map.configTurnings(start.getHallway(), dest.getHallway(), direction);
		
		for(Coordinate t : turnings){
			System.out.println("line to " + t.getX() + "," + t.getY());
			path.lineTo(t.getX(), t.getY());
		}
		
	    //path.lineTo(turning1.getX(), turning1.getY());
	    //path.lineTo(turning2.getX(), turning2.getY());
	    
	    path.lineTo(dest_hallwayCoord.getX(),dest_hallwayCoord.getY());
	    path.lineTo(dest.getCoord().getX(), dest.getCoord().getY());
	    
		return path;
		
	}
	
	public void drawPath(){
		
		Canvas canvas = new Canvas(bitmap);
		//canvas.drawColor(Color.WHITE);
		
		//int canvasWidth = canvas.getWidth();
        //int canvasHeight = canvas.getHeight();
        
        //Coordinate start = new Coordinate(669.333f, 899.333f);
        //Coordinate dest = new Coordinate(475.333f, 189.333f);
		
		//initialise a paint
		Paint p = new Paint();
		p.setColor(Color.RED);
		p.setStrokeWidth(4.0f);
		        
		//draw
		//canvas.drawLine(0, 0, canvasWidth, 0, p);
		//canvas.drawLine(0, 0, 0, canvasHeight, p);
		//canvas.drawLine(0, canvasHeight, canvasWidth, canvasHeight, p);
		//canvas.drawLine(canvasWidth, 0, canvasWidth, canvasHeight, p);
		
		p.setStyle(Paint.Style.STROKE);
		
		Path path  = findPath();

	    canvas.drawPath(path, p);
	    
	}
	
	public Bitmap getBitmap(){
		return bitmap;
	}
	
	
	
}
