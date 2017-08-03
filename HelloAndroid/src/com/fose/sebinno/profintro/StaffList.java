/**
 * 
 */
package com.fose.sebinno.profintro;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Ning WANG
 *
 */
public class StaffList {

	private ArrayList<Staff> staffs;
	//private Hashtable<String, Staff> staffs;
	
	public StaffList(){
		this.staffs = new ArrayList<Staff>();
		loadStaffsFromDB();
	}
	
	private void loadStaffsFromDB(){
		
		String avatarURL;
		String name;
		String title;
		String office;
		String bio;
		
		//get data from db
		//test data
		avatarURL = null;
		name = "Professor Tao Wu";
		title = "Professor in Chemical Engineering, Fellow of Royal Society of Chemistry , Department of Chemical and Environmental Engineering";
		office = "SEB 418";
		bio = "Professor Tao Wu is an active researcher on clean energy technologies at The University of Nottingham Ningbo China (UNNC). He has over 25 years of experience on the efficient conversion and utilization of fossil fuels and biomass. His research programmes cover a wide range from blue-sky research to proof of concept and patent development leading to commercialization. Professor Wu has completed over 40 research projects funded by both funding agencies and industry.\n\nHe has published over 80 journal papers related to energy conversion technologies and resource efficiency and holds 5 patents. His current interests focus on clean energy conversion technologies, energy saving, wastewater treatment, air pollution control, etc. He serves as committee member on a number of national and regional professional bodies and is currently leading the Municipal Key Laboratory on Clean Energy Conversion Technologies at the UNNC.\n";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
		avatarURL = "http://www.nottingham.edu.cn/en/Science-Engineering/images-multimedia/StaffPhoto/Binjie-Hu105x149.jpg";
		name = "Dr Binjie Hu";
		title = "Associate Professor in Chemical Engineering, Faculty Director of Marketing and Recruitments , Department of Chemical and Environmental Engineering";
		office = "SEB 418";
		bio = "I am from Dalian, China, where I gained my BEng. I obtained a PhD at the University of Newcastle upon Tyne, UK, working on Crossflow microfiltration of water in oil emulsions. From September 1999, I joined the University of Birmingham as a research fellow, where I extend my research area onto understanding hydrodynamics in different multiphase systems in stirred tanks using video techniques. The research was closely related to industry processes such as food formulation and heterogeneously catalytic reactions. Especially in reaction processes, the most recent developments include hydrodynamics in multiphase reactors, interfacial and transport phenomena and their role in chemical reactions.\n\nI joined the Department of Chemical Engineering and Biotechnology, University of Cambridge as a Teaching Consortium Teaching Fellow in September 2007. I was involved in a wide range of undergraduate teaching, i.e design lecture for 3rd year undergraduate, supervising design project for both 2nd and 3rd year undergraduates, laboratory-based teaching and setting out and supervising exercises (mini projects) which serve as reinforcing the lecture materials, thus cover wide range of chemical engineering subjects.\n\nMy research interest lie on several areas including different multiphase system applications, such as ionic liquids which have direct impact to our living environment.";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
	}
	
	public ArrayList<Staff> getStaffArrayList(){
		return staffs;
	}
	
}
