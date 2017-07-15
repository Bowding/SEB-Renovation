/**
 * 
 */
package com.test.helloandroid;

/**
 * @author Ning WANG
 *
 */
public class Map {

	private int level = -1;
	private Coordinate[] coordinates;
	
	public Map(int level){
		
		if(level <= 4){
			this.level = level;
			this.coordinates = null;
		}
		else{
			System.out.println("Err in constructing Map: Invalid level number!");
		}
		
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
