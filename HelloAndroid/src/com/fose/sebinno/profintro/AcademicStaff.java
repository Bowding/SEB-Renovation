package com.fose.sebinno.profintro;

import java.util.ArrayList;
import java.util.Hashtable;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.InputHandler;
import com.fose.sebinno.navigation.Location;
import com.fose.sebinno.navigation.MainActivity;
import com.fose.sebinno.navigation.PathViewer;
import com.test.helloandroid.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AcademicStaff extends Activity {
	
	private ImageView ivAvatar;
	private TextView tvName;
	private TextView tvTitle;
	private TextView tvOffice;
	private TextView tvBio;
	private EditText tvKeyword = null;
	private ImageView ivSearchButton = null;
	private LinearLayout dept_staff_list;
	private FrameLayout pb_0, pb_1, pb_2, professor_detail_container;

	
	private StaffList staffs;
	private ArrayList<Staff> sal;
	private ArrayList<FrameLayout> pbs;
	//private static InputHandler ih;
	//public static DBHelper dbh;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_academic_staff);
		
		ivAvatar = (ImageView) super.findViewById(R.id.ivAvatar);
		tvName = (TextView) super.findViewById(R.id.tvName);
		tvTitle = (TextView) super.findViewById(R.id.tvTitle);
		tvOffice = (TextView) super.findViewById(R.id.tvOffice);
		tvBio = (TextView) super.findViewById(R.id.tvBio);

		professor_detail_container = (FrameLayout) super.findViewById(R.id.professor_detail_container);
		professor_detail_container.setVisibility(View.INVISIBLE);
		//ivAvatar.setVisibility(View.GONE);
		//tvName.setVisibility(View.GONE);
		//tvTitle.setVisibility(View.GONE);
		//tvOffice.setVisibility(View.GONE);
		//tvBio.setVisibility(View.GONE);
		
		tvKeyword = (EditText) super.findViewById(R.id.tvKeyword);
		tvKeyword.addTextChangedListener(new EditChangedListener()); 
		
		ivSearchButton = (ImageView) super.findViewById(R.id.ivSearchButton);
		//ivSearchButton.setOnClickListener(new SearchButtonOnClickListener());
		
		dept_staff_list = (LinearLayout) super.findViewById(R.id.dept_staff_list);
		pb_0 = (FrameLayout) super.findViewById(R.id.pb_0);
		pb_1 = (FrameLayout) super.findViewById(R.id.pb_1);
		pb_2 = (FrameLayout) super.findViewById(R.id.pb_2);
		
		pb_0.setVisibility(View.GONE);
		
		pbs = new ArrayList<FrameLayout>();
		//pbs.put(0, pb_0);
		pbs.add(pb_1);
		pbs.add(pb_2);
		
		for(FrameLayout pb : pbs){
			pb.setOnClickListener(new PBOnClickListener());
		}
		
		//dbh = new DBHelper(this);
		//ih = new InputHandler(InputHandler.STAFF);
		
		staffs = new StaffList();
		sal = staffs.getStaffArrayList();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.academic_staff_lookup, menu);
		return true;
	}
	
	
	class EditChangedListener implements TextWatcher {  
	       private CharSequence temp;//监听前的文本  
	       private int editStart;//光标开始位置  
	       private int editEnd;//光标结束位置  
	       private final int charMaxNum = 10;
	       private String inputString;
	  
	       @Override  
	       public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文本之前的状态");  
	           temp = s;  
	       }  
	  
	       @Override  
	       public void onTextChanged(CharSequence s, int start, int before, int count) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文字中的状态，count是一次性输入字符数");
	    	   //tvBio.setText("还能输入" + (charMaxNum - s.length()) + "字符");
	    	   
	    	   inputString = tvKeyword.getText().toString();
	    	   //Toast.makeText(getApplicationContext(), inputString, Toast.LENGTH_LONG).show();
	    	   //tvBio.setText(inputString);
	    	   for(Staff staff : sal){
	    		   
	    		   int id = sal.indexOf(staff);
	    		   if(!staff.getName().contains(inputString)){
	    			   //ProfessorBar pb = new ProfessorBar(AcademicStaff.this);
	    			   //pb.setAvatar_s(staff.getAvatarURL());
	    			   //pb.setName_s(staff.getName());
	    			   
	    			   pbs.get(id).setVisibility(View.GONE);  
	    		   }
	    		   else if(staff.getName().contains(inputString)){
	    			   pbs.get(id).setVisibility(View.VISIBLE);
	    		   }
	    	   }
	  
	       }  
	  
	       @Override  
	       public void afterTextChanged(Editable s) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文字后的状态");  
	           /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
	    	   
	    	   ProfessorBar pb;
	    	   inputString = tvKeyword.getText().toString();
	    	   //Toast.makeText(getApplicationContext(), inputString, Toast.LENGTH_LONG).show();
	    	   
	    	   for(Staff staff : sal){
	    		   
	    		   int id = sal.indexOf(staff);
	    		   if(!staff.getName().contains(inputString)){
	    			   //pb = new ProfessorBar(AcademicStaff.this);
	    			   //pb.setAvatar_s(staff.getAvatarURL());
	    			   //pb.setName_s(staff.getName());
	    			   
	    			   //dept_staff_list.addView(pb);
	    			   
	    			   pbs.get(id).setVisibility(View.GONE); 
	    		   }
	    		   else if(staff.getName().contains(inputString)){
	    			   pbs.get(id).setVisibility(View.VISIBLE);
	    		   }
	    		   
	    	   }
	    	   //addContentView();
	  /*
	           editStart = tvKeyword.getSelectionStart();  
	           editEnd = tvKeyword.getSelectionEnd();  
	           if (temp.length() > charMaxNum) {  
	               Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();  
	               s.delete(editStart - 1, editEnd);  
	               int tempSelection = editStart;  
	               tvKeyword.setText(s);  
	               tvKeyword.setSelection(tempSelection);  
	           }  
	  */
	       }
 
	   }
	
	private class PBOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = pbs.indexOf(v);
			Staff s = sal.get(id);
			
			//ivAvatar.setVisibility(View.VISIBLE);
			//tvName.setVisibility(View.VISIBLE);
			//tvTitle.setVisibility(View.VISIBLE);
			//tvOffice.setVisibility(View.VISIBLE);
			//tvBio.setVisibility(View.VISIBLE);
			
			//ivAvatar.setImageBitmap(bm);
			tvName.setText(s.getName());
			tvTitle.setText(s.getTitle());
			tvOffice.setText(s.getOffice());
			tvBio.setText(s.getBio());
			
			professor_detail_container.setVisibility(View.VISIBLE);
			//String info = "Hint: try to enter a professor's full name or a specific room number";
			Toast.makeText(getApplicationContext(), s.getName(), Toast.LENGTH_SHORT).show();

		}
    	
    }
	/*
	private class SearchButtonOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String keyword = tvKeyword.getText().toString();
			//int destLocID = ih.handle(destination);
			//int result = ih.getLevel(destLocID);

			//String info = "Hint: try to enter a professor's full name or a specific room number";
			Toast.makeText(getApplicationContext(), keyword, Toast.LENGTH_SHORT).show();
			//TextView pb = new TextView(this);
			//Intent intent = new Intent(MainActivity.this, PathViewer.class);
            //intent.putExtra("destLocID", destLocID);
            //intent.putExtra("level", level);
			//startActivity(intent);
		}
    	
    }
	*/
}
