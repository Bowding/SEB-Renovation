/**
 * 
 */
package com.test.helloandroid;

/**
 * @author Ning WANG
 *
 */
public class Location {
	
	private Coordinate coord;
	private int locationID;		//actual room num or digit for special locations
	private String hallway;
	
	//special locations
	public static final int TOILET = 0;
	public static final int LIFT = 1;
	public static final int STAIRWAY = 2;
	public static final int VENDING = 3;
	public static final int DEVICE = 4;
	
	//constructor
	public Location(Coordinate coord, int locationID, String hallway){
		this.coord = coord;
		this.locationID = locationID;
		this.hallway = hallway;
	}
	
	//methods
	public Coordinate getCoord() {
		return coord;
	}

	public int getLocationID() {
		return locationID;
	}

	public String getHallway() {
		return hallway;
	}
	
	public float getDistanceTo(Location l){
		
		float d_x = Math.abs(this.coord.getX() - l.getCoord().getX());
		float d_y = Math.abs(this.coord.getY() - l.getCoord().getY());
		
		return (d_x + d_y);
	}
	
}
