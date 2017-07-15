/**
 * 
 */
package com.test.helloandroid;

/**
 * @author Ning WANG
 *
 */
public class Coordinate {

	private float x, y;
	private int location;	//actual room num or digit for special locations
	private int type = -1;		//start, destination, or turing points
	
	//special locations
	public static final int TOILET = 0;
	public static final int LIFT = 1;
	public static final int STAIRWAY = 2;
	public static final int VENDING = 3;
	
	//special coordinates types
	public static final int START = 0;
	public static final int DEST = 1;
	public static final int TURNING = 2; 
	
	public Coordinate(float x, float y, int location){
		this.x = x;
		this.y = y;
		this.location = location;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
}
