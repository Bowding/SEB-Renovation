/**
 * Public class for handling user inputs
 */
package com.fose.sebinno;

import java.util.ArrayList;
import android.database.Cursor;
import com.fose.sebinno.main.QuiescentState;
import com.fose.sebinno.navigation.Location;


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
	
	public ArrayList<Integer> handle(String input){
		
		ArrayList<Integer> potentialDests = new ArrayList<Integer>();
		
		int inputInt;
		//boolean isNum = input.matches("[0-9]+");
		if(input == null || input.isEmpty() || input.equals(" ")){
			return potentialDests;
		}
		
		if(input.length() == 3 && input.matches("[0-9]+")){
			inputInt = Integer.parseInt(input); 
			
			if(isValidInt(inputInt)){
				potentialDests.add(inputInt);
			}
		}
		else{
			potentialDests = handleString(input);
		}
		
		return potentialDests;
		/*
		int dest = -1;
		if(input.length() <= 4 ){
			System.out.println("asahdhsahk");
			
			dest = Integer.parseInt(input);
					
		}
		
		System.out.println(dest);
		return dest;
		*/
	}
	
	private boolean isValidInt(int inputInt){
		
		boolean isValid;
		
		if(inputInt >= 101 && inputInt <= 123){
			isValid = true;
		}
		else if(inputInt >= 201 && inputInt <= 218){
			isValid = true;
		}
		else if(inputInt >= 301 && inputInt <= 341){
			isValid = true;
		}
		else if(inputInt >= 401 && inputInt <= 449){
			isValid = true;
		}
		else{
			isValid = false;
		}
		
		return isValid;
	}
	
	private ArrayList<Integer> handleString(String input){
		
		ArrayList<Integer> potentialDests = new ArrayList<Integer>();
		
		String sql = "SELECT * FROM locations WHERE tags LIKE '%"+ input +"%'";
		Cursor c = QuiescentState.dbh.select(sql);
		
		int locID;
		
		while (c.moveToNext()) {
			
			locID = c.getInt(c.getColumnIndex("locationID"));
			if(!potentialDests.contains(locID)){
				System.out.println("add: " + locID);
				potentialDests.add(locID);
			}
			
			
		}
		c.close();
		
		return potentialDests;
	}
	
	public int getLevel(int locID){
		int level;
		
		if(locID > 10000){	//special location ID
			level = locID/10000;
		}
		else if(locID > 1000){	//115a 119a 119b...
			level = locID/1000;
		}
		else if(locID > 100){	//room number
			level = locID/100;
		}
		else{
			//get data from db
			//test data
			level = 2;
			//level = locID/10;
		}
		
		return level;
	}
	
}
