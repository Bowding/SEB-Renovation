package com.fose.sebinno.labintro;

import java.util.ArrayList;
import java.util.Locale;

import com.fose.sebinno.BaseActivity;
import com.fose.sebinno.main.QuiescentState;
import com.test.helloandroid.R;
import com.test.helloandroid.R.layout;
import com.test.helloandroid.R.menu;

import android.os.Bundle;
import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Laboratories extends Activity {

	private TextView tvName;
	private TextView tvRoomNum;
	private TextView tvIntro;
	private TextView tvMessage;
	private EditText tvKeyword = null;
	private LinearLayout dept_staff_list, professor_detail_container;
	private FrameLayout lb_0, lb_1, lb_2, lb_3, lb_4, lb_5, lb_6, lb_7, lb_8, lb_9, lb_10, lb_11;

	private LabList labs;
	//private DeptList depts;
	private ArrayList<Lab> lal;
	//private ArrayList<Department> dal;
	private ArrayList<FrameLayout> lbs;
	private ArrayList<ImageView> dds;
	private ArrayList<Integer> potentialList;
	//private static InputHandler ih;
	//public static DBHelper dbh;
	
	private Configuration config;
	private DisplayMetrics dm;
	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_laboratories);
		
		tvName = (TextView) super.findViewById(R.id.tvName);
		tvRoomNum = (TextView) super.findViewById(R.id.tvRoomNum);
		tvIntro = (TextView) super.findViewById(R.id.tvIntro);
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
		
		/*
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
		*/
		
		//dept_staff_list = (LinearLayout) super.findViewById(R.id.dept_staff_list_dean);
		lb_0 = (FrameLayout) super.findViewById(R.id.lb_0);
		lb_1 = (FrameLayout) super.findViewById(R.id.lb_1);
		lb_2 = (FrameLayout) super.findViewById(R.id.lb_2);
		lb_3 = (FrameLayout) super.findViewById(R.id.lb_3);
		lb_4 = (FrameLayout) super.findViewById(R.id.lb_4);
		lb_5 = (FrameLayout) super.findViewById(R.id.lb_5);
		lb_6 = (FrameLayout) super.findViewById(R.id.lb_6);
		lb_7 = (FrameLayout) super.findViewById(R.id.lb_7);
		lb_8 = (FrameLayout) super.findViewById(R.id.lb_8);
		lb_9 = (FrameLayout) super.findViewById(R.id.lb_9);
		lb_10 = (FrameLayout) super.findViewById(R.id.lb_10);
		lb_11 = (FrameLayout) super.findViewById(R.id.lb_11);
				
		lbs = new ArrayList<FrameLayout>();
		//pbs.put(0, pb_0);
		lbs.add(lb_0);
		lbs.add(lb_1);
		lbs.add(lb_2);
		lbs.add(lb_3);
		lbs.add(lb_4);
		lbs.add(lb_5);
		lbs.add(lb_6);
		lbs.add(lb_7);
		lbs.add(lb_8);
		lbs.add(lb_9);
		lbs.add(lb_10);
		lbs.add(lb_11);
		
		for(FrameLayout lb : lbs){
			lb.setOnClickListener(new LBOnClickListener());
			//lb.setVisibility(View.GONE);
		}
		
		//dbh = new DBHelper(this);
		//ih = new InputHandler(InputHandler.STAFF);
		
		labs = new LabList();
		lal = labs.getLabArrayList();
		
		//depts = new DeptList();
		//dal = depts.getDeptArrayList();
		
		potentialList = new ArrayList<Integer>();
		potentialList.add(0);
		potentialList.add(1);
		potentialList.add(2);
		potentialList.add(3);
		potentialList.add(4);
		potentialList.add(5);
		potentialList.add(6);
		potentialList.add(7);
		potentialList.add(8);
		potentialList.add(9);
		potentialList.add(10);
		potentialList.add(11);
		
		resources = getResources();
        config = resources.getConfiguration();
        dm = resources.getDisplayMetrics();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.labortories, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()){  
            case R.id.log_in:  
            	showCustomViewDialog();
                break;  
            case R.id.main_menu:  
            	Intent intent = new Intent(Laboratories.this, QuiescentState.class);
				startActivity(intent);
                break;  
            case R.id.language:
            	if(item.getTitle().equals("English")){
            		config.locale = Locale.UK;
            		resources.updateConfiguration(config, dm);
            		//onCreate(null);
            		recreate();
            	}
            	else{
            		config.locale = Locale.SIMPLIFIED_CHINESE;
            		resources.updateConfiguration(config, dm);
            		//onCreate(null);
            		recreate();
            	}
            	break;	
            default: 
            	return super.onOptionsItemSelected(item);  
        }  
        return true;  
    }
	
	private void showCustomViewDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.log_in);

        /**
         * 设置内容区域为自定义View
         */
        LinearLayout loginDialog= (LinearLayout) getLayoutInflater().inflate(R.layout.login,null);
        builder.setView(loginDialog);
        builder.setPositiveButton(R.string.log_in,null);
        builder.setNegativeButton(R.string.cancel,null);

        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
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
	  
	       }  
	  
	       @Override  
	       public void afterTextChanged(Editable s) {  
	           //if (DEBUG)  
	           //    Log.i(TAG, "输入文字后的状态");  
	           /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
	    	   
	    	   //ProfessorBar pb;
	    	   inputString = tvKeyword.getText().toString();
	    	   //Toast.makeText(getApplicationContext(), inputString, Toast.LENGTH_LONG).show();
	    	   
	    	   for(Lab lab : lal){
	    		   
	    		   int id = lal.indexOf(lab);
	    		   PLindex = potentialList.indexOf(id);
	    		   
	    		   String labStr = lab.getRoomNum().concat(" - ".concat(lab.getName()));
	    		   if(!labStr.toUpperCase().contains(inputString.toUpperCase())){
	    			   //pb = new ProfessorBar(AcademicStaff.this);
	    			   //pb.setAvatar_s(staff.getAvatarURL());
	    			   //pb.setName_s(staff.getName());
	    			   
	    			   //dept_staff_list.addView(pb);
	    			   
	    			   lbs.get(id).setVisibility(View.GONE);
	    			   
	    			   if(PLindex != -1){
	    				   potentialList.remove(PLindex);
	    			   }
	    		   }
	    		   else if(labStr.toUpperCase().contains(inputString.toUpperCase())){
	    				   
	    			   lbs.get(id).setVisibility(View.VISIBLE);
	    			   
	    			   if(PLindex == -1){
	    				   potentialList.add(id);
	    			   }
	    		   }
	    	   }
	    	   /*
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
	    	   */
	    	   
	    	   if(potentialList.size() == 0){
	    		   tvMessage.setText(R.string.no_lab_found);
	    		   tvMessage.setVisibility(View.VISIBLE);
	    		   professor_detail_container.setVisibility(View.INVISIBLE);
	    	   }
	    	   else{
	    		   tvMessage.setText(R.string.select_lab_instruc);
	    		   
	    		   if(professor_detail_container.getVisibility() == View.INVISIBLE){
	    			   Resources res = getResources();
		    		   int c_text = res.getColor(color.darker_gray);
		    		   for(FrameLayout lb : lbs){
		    			   lb.setBackgroundColor(Color.WHITE);
		    			   ((TextView) ((ViewGroup) ((ViewGroup) lb).getChildAt(0)).getChildAt(0)).setTextColor(c_text);
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
	
	private  class LBOnClickListener implements OnClickListener{
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
			for(FrameLayout lb : lbs){
				lb.setBackgroundColor(Color.WHITE);
				((TextView) ((ViewGroup) ((ViewGroup) lb).getChildAt(0)).getChildAt(0)).setTextColor(c_text);
			}
			
			c_back = res.getColor(color.holo_blue_light);
			c_text = res.getColor(color.background_light);
			((TextView) ((ViewGroup) ((ViewGroup) v).getChildAt(0)).getChildAt(0)).setTextColor(c_text);
			v.setBackgroundColor(c_back);
			
			int id = lbs.indexOf(v);
			Lab l = lal.get(id);
		
			//ivAvatar.setVisibility(View.VISIBLE);
			//tvName.setVisibility(View.VISIBLE);
			//tvTitle.setVisibility(View.VISIBLE);
			//tvOffice.setVisibility(View.VISIBLE);
			//tvBio.setVisibility(View.VISIBLE);
			
			//ivAvatar.setImageBitmap(bm);
			tvName.setText(l.getName());
			tvRoomNum.setText(l.getRoomNum());
			tvIntro.setText(l.getIntro());
			
			professor_detail_container.setVisibility(View.VISIBLE);
			tvMessage.setVisibility(View.GONE);
			//String info = "Hint: try to enter a professor's full name or a specific room number";
			//Toast.makeText(getApplicationContext(), s.getName(), Toast.LENGTH_SHORT).show();
			
			//pre_down = v;
		}
		
		
    	
    }
	/*
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
	*/


}
