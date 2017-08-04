/**
 * 
 */
package com.fose.sebinno.profintro;

import java.util.ArrayList;

/**
 * @author Ning WANG
 *
 */
public class Department {

	private String deptName;
	private ArrayList<Integer> staffIDs;
	private boolean display;
	
	public Department(String deptName, ArrayList<Integer> staffIDs) {
		// TODO Auto-generated constructor stub
		this.deptName = deptName;
		this.staffIDs = staffIDs;
		this.display = false;
	}
	

	public String getDeptName() {
		return deptName;
	}

	public ArrayList<Integer> getstaffIDs() {
		return staffIDs;
	}	

	public boolean isDisplay() {
		return display;
	}
	
	public void setDisplay(Boolean display){
		this.display = display;
	}

	public void convertDisplay() {
		this.display = !display;
	}
	
	

}
