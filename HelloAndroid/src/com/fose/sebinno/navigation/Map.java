/**
 * used to load map data from database
 */
package com.fose.sebinno.navigation;

import java.util.ArrayList;
import java.util.Hashtable;

import com.fose.sebinno.AppConfig;
import com.fose.sebinno.Coordinate;
import com.test.helloandroid.R;

import android.database.Cursor;

/**
 * @author Ning WANG
 *
 */
public class Map {

	private int level = -1;
	private Coordinate[] coordinates;
	private Hashtable<String, Float> hallways;
	
	public Map(int level){
		
		if(level <= 4){
			this.level = level;
			//this.coordinates = null;
			this.hallways = new Hashtable<String, Float>();
			loadHallwaysByLevel(level);
			
		}
		else{
			System.out.println("Err in constructing Map: Invalid level number!");
		}
		
	}
	
	private void loadHallwaysByLevel(int level) {
		
		if(level == 1){
			//load hallways for level 1
			hallways.put("hori_up", 301.3f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_down", 1470.8f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_left", 667.7f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_right", 1705.2f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra1", 451.25f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra2", 766.11f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_extra108", 469.8f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_extra1", 931.6f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_extra2", 1231.4f/AppConfig.IMAGE_SCALE);
		}
		else if(level == 2){
			//load hallways for level 2
			hallways.put("hori_up", 326/AppConfig.IMAGE_SCALE);
			hallways.put("hori_down", 1463/AppConfig.IMAGE_SCALE);
			hallways.put("vert_left", 650/AppConfig.IMAGE_SCALE);
			hallways.put("vert_right", 1661/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra1", 473/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra2", 773/AppConfig.IMAGE_SCALE);
		}
		else if(level == 3){
			//load hallways for level 3
			hallways.put("hori_up", 310.3f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_down", 1545.7f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_left", 625.7f/AppConfig.IMAGE_SCALE);
			hallways.put("vert_right", 1615.2f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra1", 1422.8f/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra2", 1626.7f/AppConfig.IMAGE_SCALE);
		}
		else{
			//load hallways for level 4
			hallways.put("hori_up", 326/AppConfig.IMAGE_SCALE);
			hallways.put("hori_down", 1463/AppConfig.IMAGE_SCALE);
			hallways.put("vert_left", 650/AppConfig.IMAGE_SCALE);
			hallways.put("vert_right", 1661/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra1", 473/AppConfig.IMAGE_SCALE);
			hallways.put("hori_extra2", 773/AppConfig.IMAGE_SCALE);
		}
		
	}
	
	public Location getLiftLoc(){
		//get data from db
		Coordinate coord;
		
		//test data
		if(level == 1){
			coord = new Coordinate(1081.5f/AppConfig.IMAGE_SCALE, 1383.8f/AppConfig.IMAGE_SCALE);
		}
		else if(level == 2){
			coord = new Coordinate(1049/AppConfig.IMAGE_SCALE, 1382/AppConfig.IMAGE_SCALE);
		}
		else if(level == 3){
			coord = new Coordinate(1018.5f/AppConfig.IMAGE_SCALE, 1338.8f/AppConfig.IMAGE_SCALE);
		}
		else{ //???temp use 2 as default
			coord = new Coordinate(1030.5f/AppConfig.IMAGE_SCALE, 1360.3f/AppConfig.IMAGE_SCALE);
		}
		
		int locationID = Location.LIFT;
		String hallway = "hori_down";
		
		Location liftLoc = new Location(coord, locationID, hallway);
		
		return liftLoc;
	}
	
	public ArrayList<Location> configLocation(int locationID){
		
		ArrayList<Location> locations = new ArrayList<Location>();
		Coordinate doorCoord;
		String hallway;
		String sql;
		
		switch(locationID){
		case Location.DEVICE:
			//get locations of devices
			
			//test data
			doorCoord = new Coordinate(1004/AppConfig.IMAGE_SCALE, 1349/AppConfig.IMAGE_SCALE);
			
			break;
		case Location.LIFT:
			//get locations of lifts
			break;
		case Location.STAIRWAY:
			//get locations of stairways
			break;
		case Location.TOILET:
			//get locations of toilets
			break;
		case Location.VENDING:
			//get locations of vending machines
			break;
		default:
			//get locations of room by room number
			//read data from db
			sql = "SELECT * FROM locations WHERE locationID=" + locationID;
			
			System.out.println(sql);
			
			locations = selectFromDB(sql);
			
			/*
			//test data
			int roomID;
			
			if(locationID == 201){
				roomID = 201;
				doorCoord = new Coordinate(713/AppConfig.IMAGE_SCALE, 284/AppConfig.IMAGE_SCALE);
				hallway = "hori_up";
			}
			else{//if(locationID == 317){
				roomID = 317;
				doorCoord = new Coordinate(1597.2f/AppConfig.IMAGE_SCALE, 1680.6f/AppConfig.IMAGE_SCALE);
				hallway = "hori_extra2";
			}
			
			Location location = new Location(doorCoord, roomID, hallway);
			locations.add(location);
			*/
		}
		
		return locations;	
	}
	
	public ArrayList<Coordinate> configTurnings(String startHallwayKey, String destHallwayKey, String direction){
		ArrayList<Coordinate> turnings = new ArrayList<Coordinate>();
		
		//System.out.println(startHallwayKey + "+" + destHallwayKey);
		if(startHallwayKey.equals(destHallwayKey)){
			//num = 0
		}
		else if(startHallwayKey.startsWith("hori") && destHallwayKey.startsWith("vert")){
			//num = 1
			float x = getHallwayCoord(destHallwayKey);
			float y = getHallwayCoord(startHallwayKey);
			
			turnings.add(new Coordinate(x,y));
		}
		else{	//if startHallwayKey.startsWith("hori") && destHallwayKey.startsWith("hori")
			//num = 2
			//?????? special case level = 1
			
			String vertHallwayKey = "vert_" + direction;
			
			//config first turning
			float x = getHallwayCoord(vertHallwayKey);
			float y = getHallwayCoord(startHallwayKey);
			turnings.add(new Coordinate(x,y));
			
			//config second turning
			y = getHallwayCoord(destHallwayKey);
			turnings.add(new Coordinate(x,y));
		}
		
		return turnings;
	}
	
	public int getMapResourceID(){
		
		int id = R.drawable.level1;
		
		switch(level){
		case 1:
			id = R.drawable.level1;
			break;
		case 2:
			id = R.drawable.level2;
			break;
		case 3:
			id = R.drawable.level3;
			break;
		case 4:
			id = R.drawable.level4;
		}
		
		return id;
	}
	
	public float getHallwayCoord(String hallwayKey){
		System.out.println(hallwayKey);
		return hallways.get(hallwayKey);
	}
	
	private ArrayList<Location> selectFromDB(String sql){
		ArrayList<Location> locations = new ArrayList<Location>();
		Cursor c = Navigation.dbh.select(sql);
		
		while (c.moveToNext()) {

			int roomID = c.getInt(c.getColumnIndex("locationID"));
			
			float x = c.getFloat(c.getColumnIndex("x"))/AppConfig.IMAGE_SCALE;
			float y = c.getFloat(c.getColumnIndex("y"))/AppConfig.IMAGE_SCALE;
			Coordinate doorCoord = new Coordinate(x, y);
			
			System.out.println(c.getFloat(c.getColumnIndex("x")) + " + " + c.getFloat(c.getColumnIndex("x")));
			
			String hallway = c.getString(c.getColumnIndex("hallway"));
			String tags = c.getString(c.getColumnIndex("tags"));
			
			Location location = new Location(doorCoord, roomID, hallway, tags);
			locations.add(location);
		}
		c.close();
		return locations;
	}

	public void loadCoordinates(){
		if(this.level == 1){
			//load coordinates for level 1
		}
		else if(this.level == 2){
			//load coordinates for level 2
		}
		else if(this.level == 3){
			//load coordinates for level 3
		}
		else if(this.level == 4){
			//load coordinates for level 4
		}
		else{
			System.out.println("Err in loading coords: Invalid level number!");
		}
	}
	
}
