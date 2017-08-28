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
		//0
		avatarURL = null;
		name = "Professor Tao Wu";
		title = "Professor in Chemical Engineering, Fellow of Royal Society of Chemistry , Department of Chemical and Environmental Engineering";
		office = "SEB 418";
		bio = "Professor Tao Wu is an active researcher on clean energy technologies at The University of Nottingham Ningbo China (UNNC). He has over 25 years of experience on the efficient conversion and utilization of fossil fuels and biomass. His research programmes cover a wide range from blue-sky research to proof of concept and patent development leading to commercialization. Professor Wu has completed over 40 research projects funded by both funding agencies and industry.\n\nHe has published over 80 journal papers related to energy conversion technologies and resource efficiency and holds 5 patents. His current interests focus on clean energy conversion technologies, energy saving, wastewater treatment, air pollution control, etc. He serves as committee member on a number of national and regional professional bodies and is currently leading the Municipal Key Laboratory on Clean Energy Conversion Technologies at the UNNC.\n";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
		//1
		avatarURL = "http://www.nottingham.edu.cn/en/Science-Engineering/images-multimedia/StaffPhoto/Binjie-Hu105x149.jpg";
		name = "Dr Binjie Hu";
		title = "Associate Professor in Chemical Engineering, Faculty Director of Marketing and Recruitments , Department of Chemical and Environmental Engineering";
		office = "SEB 436";
		bio = "I am from Dalian, China, where I gained my BEng. I obtained a PhD at the University of Newcastle upon Tyne, UK, working on Crossflow microfiltration of water in oil emulsions. From September 1999, I joined the University of Birmingham as a research fellow, where I extend my research area onto understanding hydrodynamics in different multiphase systems in stirred tanks using video techniques. The research was closely related to industry processes such as food formulation and heterogeneously catalytic reactions. Especially in reaction processes, the most recent developments include hydrodynamics in multiphase reactors, interfacial and transport phenomena and their role in chemical reactions.\n\nI joined the Department of Chemical Engineering and Biotechnology, University of Cambridge as a Teaching Consortium Teaching Fellow in September 2007. I was involved in a wide range of undergraduate teaching, i.e design lecture for 3rd year undergraduate, supervising design project for both 2nd and 3rd year undergraduates, laboratory-based teaching and setting out and supervising exercises (mini projects) which serve as reinforcing the lecture materials, thus cover wide range of chemical engineering subjects.\n\nMy research interest lie on several areas including different multiphase system applications, such as ionic liquids which have direct impact to our living environment.\n";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
		//2
		avatarURL = null;
		name = "Dr Dunant Halim";
		title = "Associate Professor in Mechanical Engineering , Director of Teaching, Faculty of Science and Engineering";
		office = "SEB 442";
		bio = "Dr Dunant Halim is an Associate Professor in Mechanical Engineering and Director of Teaching of Faculty of Science and Engineering at University of Nottingham Ningbo China. He obtained his PhD from University of Newcastle Australia for his work in developing structural vibration control techniques for smart structures. For over 18 years, he has worked extensively on various research and industrial projects related to dynamics (acoustics and vibration), intelligent sensing and control at a number of international institutions: Royal Melbourne Institute of Technology ¨C Australia; Acoustics, Vibration and Control Group - University of Adelaide; Australian Research Council - Centre of Excellence in Autonomous Systems: Australian Centre for Field Robotics - University of Sydney; Department of Mechanical Engineering - Hong Kong Polytechnic University and University of Nottingham Ningbo China.\n\nDr Halim has co-authored a book that introduced a novel optimal spatial control for regulating structural vibration: ¡°Spatial Control of Vibration: Theory and Experiments¡±. To date, he has published more than 80 publications, including 2 book chapters in structural vibration control and flow control, and more than 55 refereed international conference and journal publications.\n";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
		//3
		avatarURL = null;
		name = "Dr Philip Hall";
		title = "Assistant Professor in Chemical and Environmental Engineering , Department of Chemical and Environmental Engineering";
		office = "SEB 437";
		bio = "I have over 20 years¡¯ industrial experience in the areas of hazardous waste management waste recycling and energy from waste. During my time in industry, I involved in the development of various technologies in waste incineration and waste management. I have been involved in the construction and commissioning of many recycling projects in China, Japan, Belgium, the USA and UK. I have extensive knowledge of the application of research and practical engineering. I have, experience in particle separation technologies for WEEE recycling, vibration separation and pneumatic separation. I have a special interest in the use of recycled plastics in manufacturing.\n\nI started my PhD at the University of Nottingham in 2003, Closed Loop Recycling of CRT glass, following this I was made an European Innovation Fellow and was part of an Industrial Innovation Fund to achieve industrialization of this technology. I have continued research in waste recycling since he joined University of Nottingham Ningbo China. I have collaborated with local government and industries in various projects for the development of novel technologies for waste recycling.\n";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
		//4
		avatarURL = "http://www.nottingham.edu.cn/en/Science-Engineering/departments/Computer-Science/images-multimedia/staff/ruibinbai-Cropped-120x150.jpg";
		name = "Professor Ruibin Bai";
		title = "Deputy Head of Computer Science, Faculty Director of Research and Knowledge Exchange , School of Computer Sicence";
		office = "SEB 425";
		bio = "Professor Ruibin Bai holds BSc and MSc degrees from Northwestern Polytechnic University, China and a PhD from University of Nottingham UK. He joined the University of Nottingham Ningbo China (UNNC) in 2007 after spending 2 years as a postdoctoral research fellow at UoN UK. He is now an associate professor in the School of Computer Science. During his time at UNNC, he has been awarded 3 NSFC (National Natural Science Foundation of China) grants, 2 from Zhejiang Province and 3 from Ningbo Sci&Tech. Bureau) as principal investigator, with total fund of RMB 3.25m (approximately GBP33.8K). He also participated as a core member in other major projects (1 NSFC, 1 innovation team project, and 1 key project from Ningbo Sci&Tech. Bureau). His current research interests include computational intelligence, machine learning, operations research, modelling, scheduling and optimisation with a special focus on transportation systems. He has published 20 journals articles (16 of which SCI indexed) and 18 conference papers, including publications in the most influential journals in computational intelligence (e.g. IEEE Transactions on Evolutionary Computation, IF:5.55, ranking:1; INFORMS Journal on Computing, one of TU Dallas 24 selected journals in management), Information Technology and Operations Research (e.g. Information Sciences, IF:3.64, EJOR, IF: 2.35) and their applications in transportation (e.g. Transportation Research Part B, IF:3.89). Two of conference papers won the best paper awards (ACM GEC 2009 & ICOSCM2015) and one was nominated for the best paper award finalist in IEEE SSCI 2013. He serves as a member of Program Committee in several influential international conferences (IEEE CEC, IEEE SSCI, GECCO, MISTA, EvoCOP, ISDA, IEA/AIE, ACM GEC). He is also a regular peer reviewer for top journals (IEEE TEVC, IEEE TSMCA, TRB£¬ESWA, Information Sciences, Annals OR, Journal of Scheduling, Omega, Journal of Heuristics, etc.).";
		
		staffs.add(new Staff(name, title, office, bio, avatarURL));
		
	}
	
	public ArrayList<Staff> getStaffArrayList(){
		return staffs;
	}
	
}
