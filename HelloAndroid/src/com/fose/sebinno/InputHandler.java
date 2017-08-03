/**
 * Public class for handling user inputs
 */
package com.fose.sebinno;

/**
 * @author Ning WANG
 *
 */
public class InputHandler {
	
	//public static final int NUM_STATES = 4;
	public static final int NAVIGATION = 0;
	public static final int STAFF = 1;
	//public static final int PLAY = 2;
	//public static final int GAMEOVER = 3;
	
	private int situation;
	
	public InputHandler(int situation){
		
		this.situation = situation;
	}
	
	public int handle(String input){
		
		int dest = -1;
		if(input.length() <= 4 ){
			System.out.println("asahdhsahk");
			
			dest = Integer.parseInt(input);
					
		}
		
		System.out.println(dest);
		return dest;
	}
	
	public int getLevel(int locID){
		int level;
		
		if(locID > 1000){
			level = locID/1000;
		}
		else if(locID > 100){	//room number
			level = locID/100;
		}
		else{				//special location ID
			//get data from db
			//test data
			level = 2;
		}
		
		return level;
	}
	
}
