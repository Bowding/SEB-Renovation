package com.fose.sebinno.profintro;

import com.test.helloandroid.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfessorBar extends FrameLayout {
	
	private ImageView ivAvatar_s;
    private TextView tvName_s;

	public ProfessorBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }
	
	public ProfessorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProfessorBar(Context context) {
        super(context);
        initView(context);
    }
    
    private void initView(Context context) {
        // TODO Auto-generated method stub
        View.inflate(context, R.layout.professor_bar, this);
        ivAvatar_s =  (ImageView) findViewById(R.id.ivAvatar_s);
        tvName_s = (TextView) findViewById(R.id.tvName_s);

    }
    
    public void setAvatar_s(String url){
    	
    	Bitmap bm;
		
		BitmapFactory.Options opts = new BitmapFactory.Options();  
		opts.inSampleSize = 1;
		opts.inPreferredConfig = Config.RGB_565;
		
		bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, opts); 
		ivAvatar_s.setImageBitmap(bm);
    }
    
    public void setName_s(String name){
    	tvName_s.setText(name);
    }

    

}
