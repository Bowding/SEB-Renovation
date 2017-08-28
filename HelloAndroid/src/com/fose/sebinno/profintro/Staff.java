/**
 * 
 */
package com.fose.sebinno.profintro;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Ning WANG
 *
 */
public class Staff {

	private String avatarURL;
	private String name;
	private String title;
	private String office;
	private String bio;
	
	public Staff(String name, String title, String office, String bio, String avatarURL) {
		// TODO Auto-generated constructor stub
		this.avatarURL = avatarURL;
		this.name = name;
		this.title = title;
		this.office = office;
		this.bio = bio;
	}
	
	
	public String getAvatarURL() {
		return avatarURL;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getOffice() {
		return office;
	}

	public String getBio() {
		return bio;
	}

}
