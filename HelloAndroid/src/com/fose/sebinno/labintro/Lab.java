/**
 * 
 */
package com.fose.sebinno.labintro;

/**
 * @author Ning WANG
 *
 */
public class Lab {

	private String name;
	private String roomNum;
	private String intro;
	private String equiptment;
	
	public Lab(String name, String roomNum, String intro, String equiptment) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.roomNum = roomNum;
		this.intro = intro;
		this.equiptment = equiptment;
	}

	public String getName() {
		return name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public String getIntro() {
		return intro;
	}

	public String getEquiptment() {
		return equiptment;
	}

}
