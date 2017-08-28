package com.fose.sebinno.profintro;

import java.util.ArrayList;
import java.util.Hashtable;

import com.fose.sebinno.DBHelper;
import com.fose.sebinno.InputHandler;
import com.fose.sebinno.main.QuiescentState;
import com.fose.sebinno.navigation.Location;
import com.fose.sebinno.navigation.Navigation;
import com.fose.sebinno.navigation.PathViewer;
import com.test.helloandroid.R;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.ViewGroup;
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
	private TextView tvMessage;
	private EditText tvKeyword = null;
	private ImageView ivSearchButton = null;
	private LinearLayout dept_staff_list, professor_detail_container;
	private FrameLayout pb_00, pb_0, pb_1, pb_2, pb_3, pb_4;
	private ImageView ivDropDown_dean, ivDropDown_director;

	
	private StaffList staffs;
	private DeptList depts;
	private ArrayList<Staff> sal;
	private ArrayList<Department> dal;
	private ArrayList<FrameLayout> pbs;
	private ArrayList<ImageView> dds;
	private ArrayList<Integer> potentialList;
	//private static InputHandler ih;
	//public static DBHelper dbh;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_academic_staff);
		
		ivAvatar = (ImageView) super.findViewById(R.id.ivAvatar);
		tvName = (TextView) super.findViewById(R.id.tvName);
		tvTitle = (TextView) super.findViewById(R.id.tvTitle);
		tvOffice = (TextView) super.findViewById(R.id.tvRoomNum);
		tvBio = (TextView) super.findViewById(R.id.tvIntro);
		tvMessage = (TextView) super.findViewById(R.id.tvMessage);

		professor_detail_container = (LinearLayout) super.findViewById(R.id.professor_detail_container);
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
		
		ivDropDown_dean = (ImageView) super.findViewById(R.id.ivDropDown_dean);
		ivDropDown_director = (ImageView) super.findViewById(R.id.ivDropDown_director);
		
		dds = new ArrayList<ImageView>();
		
		dds.add(ivDropDown_dean);
		dds.add(ivDropDown_director);
		
		//Integer i=1;
		for(ImageView dd : dds){
			
			dd.setOnClickListener(new DDOnClickListener());
			//tvBio.setText(i.toString());
			//i += 1;
		}
		
		//dept_staff_list = (LinearLayout) super.findViewById(R.id.dept_staff_list_dean);
		pb_00 = (FrameLayout) super.findViewById(R.id.pb_00);
		pb_0 = (FrameLayout) super.findViewById(R.id.pb_0);
		pb_1 = (FrameLayout) super.findViewById(R.id.lb_4);
		pb_2 = (FrameLayout) super.findViewById(R.id.lb_5);
		pb_3 = (FrameLayout) super.findViewById(R.id.lb_6);
		pb_4 = (FrameLayout) super.findViewById(R.id.lb_7);
		
		pb_00.setVisibility(View.GONE);
		
		pbs = new ArrayList<FrameLayout>();
		//pbs.put(0, pb_0);
		pbs.add(pb_0);
		pbs.add(pb_1);
		pbs.add(pb_2);
		pbs.add(pb_3);
		pbs.add(pb_4);
		
		for(FrameLayout pb : pbs){
			pb.setOnClickListener(new PBOnClickListener());
			pb.setVisibility(View.GONE);
		}
		
		//dbh = new DBHelper(this);
		//ih = new InputHandler(InputHandler.STAFF);
		
		staffs = new StaffList();
		sal = staffs.getStaffArrayList();
		
		depts = new DeptList();
		dal = depts.getDeptArrayList();
		
		potentialList = new ArrayList<Integer>();
		potentialList.add(0);
		potentialList.add(1);
		potentialList.add(2);
		potentialList.add(3);
		potentialList.add(4);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.academic_staff_lookup, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	AlertDialog.Builder dlg = new AlertDialog.Builder(AcademicStaff.this);
    	        dlg.setTitle("Sign In");
    	        dlg.setMessage("Please touch your university ID card on the card reader to log in.");
    	        dlg.setPositiveButton("OK",null);
    	        dlg.show();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(AcademicStaff.this, QuiescentState.class);
				startActivity(intent);
                break;  
            
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }
	
	
	class EditChangedListener implements TextWatcher {  
	       private CharSequence temp;//监听前的文本  
	       private int editStart;//光标开始位置  
	       private int editEnd;//光标结束位置  
	       private final int charMaxNum = 10;
	       private String inputString;
	       private int PLindex;
	  
	       @Override  
	       public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文本之前的状态");  
	           temp = s;  
	       }  
	  
	       @Override  
	       public void onTextChanged(CharSequence s, int start, int before, int count) {  
	          /* //if (DEBUG)  
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
	    	   }	*/
	  
	       }  
	  
	       @Override  
	       public void afterTextChanged(Editable s) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文字后的状态");  
	           /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
	    	   
	    	   //ProfessorBar pb;
	    	   inputString = tvKeyword.getText().toString();
	    	   //Toast.makeText(getApplicationContext(), inputString, Toast.LENGTH_LONG).show();
	    	   
	    	   for(Staff staff : sal){
	    		   
	    		   int id = sal.indexOf(staff);
	    		   PLindex = potentialList.indexOf(id);
	    		   if(!staff.getName().toUpperCase().contains(inputString.toUpperCase())){
	    			   //pb = new ProfessorBar(AcademicStaff.this);
	    			   //pb.setAvatar_s(staff.getAvatarURL());
	    			   //pb.setName_s(staff.getName());
	    			   
	    			   //dept_staff_list.addView(pb);
	    			   
	    			   pbs.get(id).setVisibility(View.GONE);
	    			   
	    			   if(PLindex != -1){
	    				   potentialList.remove(PLindex);
	    			   }
	    		   }
	    		   else if(staff.getName().toUpperCase().contains(inputString.toUpperCase())){
	    			   if(inputString.isEmpty()){
	    				   pbs.get(id).setVisibility(View.GONE);
	    			   }
	    			   else{
	    				   pbs.get(id).setVisibility(View.VISIBLE);
	    			   }
	    			   
	    			   if(PLindex == -1){
	    				   potentialList.add(id);
	    			   }
	    		   }
	    	   }
	    	   
	    	   if(inputString.isEmpty()){
	    		   for (Department d: dal){
		    		   d.setDisplay(false);
	    		   }
	    	   }
	    	   else{
	    		   for (Department d: dal){
	    		   d.setDisplay(true);
	    		   }
	    	   }
	    	   
	    	   if(potentialList.size() == 0){
	    		   tvMessage.setText(R.string.no_staff_found);
	    		   tvMessage.setVisibility(View.VISIBLE);
	    		   professor_detail_container.setVisibility(View.INVISIBLE);
	    	   }
	    	   else{
	    		   tvMessage.setText(R.string.select_prof_instruc);
	    		   
	    		   if(professor_detail_container.getVisibility() == View.INVISIBLE){
	    			   Resources res = getResources();
		    		   int c_text = res.getColor(color.darker_gray);
		    		   for(FrameLayout pb : pbs){
		    			   pb.setBackgroundColor(Color.WHITE);
		    			   ((TextView) ((ViewGroup) ((ViewGroup) pb).getChildAt(0)).getChildAt(1)).setTextColor(c_text);
		    		   }
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
	
	private  class PBOnClickListener implements OnClickListener{
		Resources res; 
		int c_back;
		int c_text;
		//static View pre_down = null;
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			res = getResources(); 
			
			c_text = res.getColor(color.darker_gray);
			/*
			if(pre_down != null){
				pre_down.setBackgroundColor(Color.WHITE);
				((TextView) ((ViewGroup) ((ViewGroup) pre_down).getChildAt(0)).getChildAt(1)).setTextColor(c_text);
			}
			*/
			for(FrameLayout pb : pbs){
				pb.setBackgroundColor(Color.WHITE);
				((TextView) ((ViewGroup) ((ViewGroup) pb).getChildAt(0)).getChildAt(1)).setTextColor(c_text);
			}
			
			c_back = res.getColor(color.holo_blue_light);
			c_text = res.getColor(color.background_light);
			((TextView) ((ViewGroup) ((ViewGroup) v).getChildAt(0)).getChildAt(1)).setTextColor(c_text);
			v.setBackgroundColor(c_back);
			
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
			tvMessage.setVisibility(View.GONE);
			//String info = "Hint: try to enter a professor's full name or a specific room number";
			//Toast.makeText(getApplicationContext(), s.getName(), Toast.LENGTH_SHORT).show();
			
			//pre_down = v;
		}
		
		
    	
    }
	
	private  class DDOnClickListener implements OnClickListener{
		
		ArrayList<Integer> staffIDs = new ArrayList<Integer>();
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			int id = dds.indexOf(v);
			Department d = dal.get(id);
		
			staffIDs = new ArrayList<Integer>();
			staffIDs = d.getstaffIDs();
			
			if(d.isDisplay()){
				//tvBio.setText("set to gone");
				for(Integer i : staffIDs){
					pbs.get(i).setVisibility(View.GONE);
					//tvBio.setText("set to gone");
				}
			}
			else{
				//tvBio.setText("set to visible");
				for(Integer i : staffIDs){
					
					if(potentialList.contains(i)){
						pbs.get(i).setVisibility(View.VISIBLE);
					}
					//tvBio.setText("set to visible");
				}
			}
			
			d.convertDisplay();

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
