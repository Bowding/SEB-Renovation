/**
 * used to load map data from database
 */
package com.test.helloandroid;

import java.util.ArrayList;
import java.util.Hashtable;

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
		}
		else{
			//load hallways for level 4
		}
		
	}
	
	public Location getThisDeviceLoc(){
		//get data from db
		
		//test data
		Coordinate coord = new Coordinate(1004/AppConfig.IMAGE_SCALE, 1349/AppConfig.IMAGE_SCALE);
		int locationID = Location.DEVICE;
		String hallway = "hori_down";
		
		Location deviceLoc = new Location(coord, locationID, hallway);
		
		return deviceLoc;
	}
	
	public ArrayList<Location> configLocation(int locationID){
		
		ArrayList<Location> locations = new ArrayList<Location>();
		Coordinate doorCoord;
		String hallway;
		
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
			
			//test data
			int roomID = 201;
			doorCoord = new Coordinate(713/AppConfig.IMAGE_SCALE, 284/AppConfig.IMAGE_SCALE);
			hallway = "hori_up";
			
			Location location = new Location(doorCoord, roomID, hallway);
			locations.add(location);
			
		}
		
		return locations;	
	}
	
	public ArrayList<Coordinate> configTurnings(String startHallwayKey, String destHallwayKey, String direction){
		ArrayList<Coordinate> turnings = new ArrayList<Coordinate>();
		
		if(startHallwayKey == destHallwayKey){
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
	
	public float getHallwayCoord(String hallwayKey){
		return hallways.get(hallwayKey);
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
