/**
 * 
 */
package com.fose.sebinno.labintro;

import java.util.ArrayList;

import com.fose.sebinno.profintro.Staff;

/**
 * @author Ning WANG
 *
 */
public class LabList {

	private ArrayList<Lab> labs;
	//private Hashtable<String, Staff> staffs;
	
	public LabList(){
		this.labs = new ArrayList<Lab>();
		loadLabsFromDB();
	}
	
	private void loadLabsFromDB(){
		
		String name;
		String roomNum;
		String intro;
		String equiptment;
		
		//get data from db
		//test data
		//0
		name = "Civil Engineering Lab";
		roomNum = "103";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//1
		name = "CEE Lab";
		roomNum = "106";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//2
		name = "Sample Preparation Lab";
		roomNum = "107";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//3
		name = "Chemistry Lab";
		roomNum = "108";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//4
		name = "Project Lab/Analysis Lab";
		roomNum = "109";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//5
		name = "Analysis Lab-SEM";
		roomNum = "110";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//6
		name = "Analysis Lab-SEM";
		roomNum = "111";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//7
		name = "Analysis Lab-XRD LCMS";
		roomNum = "112";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//8
		name = "Material Test Lab";
		roomNum = "115";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//9
		name = "Electrical Machine Drive Lab (3315 Innovation Team)";
		roomNum = "119";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//10
		name = "Research Lab-Particle Imagining Velocimetry";
		roomNum = "121";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
		//11
		name = "Chemical Engineering Lab";
		roomNum = "122";
		intro = "Lab intro here...";
		equiptment = "";
		
		labs.add(new Lab(name, roomNum, intro, equiptment));
		
	}
	
	public ArrayList<Lab> getLabArrayList(){
		return labs;
	}

}
