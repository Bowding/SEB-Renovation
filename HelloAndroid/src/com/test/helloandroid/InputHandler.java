/**
 * Public class for handling user inputs
 */
package com.test.helloandroid;

/**
 * @author Ning WANG
 *
 */
public class InputHandler {
	
	//public static final int NUM_STATES = 4;
	public static final int NAVIGATION = 0;
	//public static final int MENU = 1;
	//public static final int PLAY = 2;
	//public static final int GAMEOVER = 3;
	
	private int situation;
	
	public InputHandler(int situation){
		
		this.situation = situation;
	}
	
	public int handle(String input){
		
		int dest = -1;
		if(input.length() == 3){
			System.out.println("asahdhsahk");
			
			dest = Integer.parseInt(input);
					
		}
		
		System.out.println(dest);
		return dest;
	}
	
}
