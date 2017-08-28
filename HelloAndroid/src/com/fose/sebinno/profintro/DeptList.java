/**
 * 
 */
package com.fose.sebinno.profintro;

import java.util.ArrayList;

/**
 * @author Ning WANG
 *
 */
public class DeptList {

	private ArrayList<Department> depts;
	//private Hashtable<String, Staff> staffs;
	
	public DeptList(){
		this.depts = new ArrayList<Department>();
		loadStaffsFromDB();
	}
	
	private void loadStaffsFromDB(){
		
		String deptName;
		ArrayList<Integer> staffIDs;
		//get data from db
		//test data
		deptName = "Dean";
		staffIDs = new ArrayList<Integer>();
		staffIDs.add(0);
		
		depts.add(new Department(deptName, staffIDs));
		
		staffIDs = new ArrayList<Integer>();
		
		deptName = "Faculty Director";
		staffIDs.add(1);
		staffIDs.add(2);
		staffIDs.add(3);
		staffIDs.add(4);
		
		depts.add(new Department(deptName, staffIDs));
		
		//staffIDs.clear();
	}
	
	public ArrayList<Department> getDeptArrayList(){
		return depts;
	}

}
