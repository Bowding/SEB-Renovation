package com.fose.sebinno.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.test.helloandroid.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Gradient extends RelativeLayout {
	
	private List<ImageView> imageViews;
	private ArrayList<String> urls;
    private List<Animation> outAnim;//��������
    private List<Animation> inAnim;//���붯��
    private Context mContext;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int couot;
    private int currentIndex;//��ǰ��ҳ��
    private LinearLayout linearLayout;
    private onClickListner listner;
    private long time=1300;//�������ʱ��
    private QuiescentState caller;


    public Gradient(Context context) {
        this(context, null);
    }

    public Gradient(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }
    
    public void setCaller(QuiescentState sender){
    	caller = sender;
    }

    /**
     * ����
     */
    public void cratePoint() {
        if (null != imageViews && imageViews.size() > 0) {
            int size = imageViews.size();

            linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER);
            // ���ͼƬ
            for (int i = 0; i < size; i++) {
                // ����Բ��
                View viewPoint = new View(mContext);
                viewPoint.setBackgroundResource(R.drawable.point_background);
                int weight = dip2px(mContext, 5);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(weight, weight);
                lp.leftMargin = weight;
                viewPoint.setLayoutParams(lp);
                viewPoint.setEnabled(false);
                linearLayout.addView(viewPoint);
            }
            View childAt = linearLayout.getChildAt(0);
            if (null != childAt) {
                childAt.setEnabled(true);
            }
            //��ӵ�ͼƬ���±�
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,-2);
            rlp.bottomMargin = dip2px(mContext, 5);
            rlp.addRule(ALIGN_PARENT_BOTTOM);
            this.addView(linearLayout, rlp);

        }


    }

    /**
     * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * ����ͼƬ
     * @param imageViews
     */
    public void setImageViews(ArrayList<ImageView> imageViews) {
        this.imageViews = imageViews;
        //for (int i = 0; i < imageViews.size(); i++) {
        //    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
        //    addView(imageViews.get(i),layoutParams);
        //}
        setonClick();
        cratePoint();
        createAnim();
        start();

    }
    
    /**
     * set image links
     * @param urls
     */
    public void setImageLinks(ArrayList<String> urls) {
        this.urls = urls;
    }

    /**
     * ��������
     */
    private void start() {
        final int size = imageViews.size();
        handler.post(new Runnable() {
            @Override
            public void run() {
                final int i = couot % size;
                //�������¼��ĳ�ͻ
                for (int j = 0; j < size; j++) {


                    if (j == i) {
                        imageViews.get(i).setClickable(true);


                    } else {
                        imageViews.get(i).setClickable(false);
                    }
                }
                
                if (couot < size) {
                    if (i == size - 1) {

                        ImageView imageView = imageViews.get(i);
                        imageView.startAnimation(outAnim.get(i));
                        
                        ImageView imageView2 = imageViews.get(0);
                        imageView2.startAnimation(inAnim.get(0));


                    }
                    else {
                        //��ǰ�ĵ���,��һ�ŵ���
                    	
                        ImageView imageView = imageViews.get(i);
                        imageView.startAnimation(outAnim.get(i));
                        
                        try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                }
                else {
                    if (i == size - 1) {
                        //����ʾ�����һ�ŵ�ʱ��,Ҫ������һ��
                        ImageView imageView = imageViews.get(i);
                        imageView.startAnimation(outAnim.get(i));

                        ImageView imageView2 = imageViews.get(0);
                        imageView2.startAnimation(inAnim.get(0));


                    } else {
                        //��ǰ�ĵ���,��һ�ŵ���
                        ImageView imageView = imageViews.get(i);
                        imageView.startAnimation(outAnim.get(i));

                        ImageView imageView2 = imageViews.get(i + 1);
                        imageView2.startAnimation(inAnim.get(i + 1));

                    }
                }

                currentIndex = i;
                linearLayout.getChildAt(currentIndex % size).setEnabled(false);
                currentIndex++;
                linearLayout.getChildAt(currentIndex % size).setEnabled(true);
                couot++;
                handler.postDelayed(this, 6000);
            }
        });
    }

    /**
     * ��������
     */
    private void createAnim() {
        outAnim = new ArrayList<Animation>();
        inAnim = new ArrayList<Animation>();
        for (int i = 0; i < imageViews.size(); i++) {
            Animation zoomOutAwayAnim = createZoomOutAwayAnim();
            zoomOutAwayAnim.setFillAfter(true);
            outAnim.add(zoomOutAwayAnim);

            Animation zoomOutNearAnim = createZoomOutNearAnim();
            zoomOutNearAnim.setFillAfter(true);
            inAnim.add(zoomOutNearAnim);

        }
    }

    /**
     * ���õ���¼�
     */
    public void setonClick() {
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if (listner != null) {
                    //    listner.setonClick((currentIndex) % imageViews.size());
                    //}
                	Intent intent = new Intent();
            		intent.setAction("android.intent.action.VIEW");
            		int url_index = (currentIndex) % urls.size();
            		
            		/*
            		if(url_index == 0){
            			url_index =urls.size() - 1;
            		}
            		else{
            			url_index = url_index - 1;
            		}*/
            		
            		Uri link_url = Uri.parse(urls.get((currentIndex) % urls.size()));
            		System.out.println((currentIndex) % urls.size());
            		//caller.btnSignin.setText((currentIndex) % imageViews.size());
            		intent.setData(link_url);
            		caller.startActivity(intent);
                }
            });
        }
    }

    public interface onClickListner{

        void setonClick(int position);
    }

    /**
     * ���ö������ź�handler�ӳ�ʱ��
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
    }

    public void setListner(onClickListner listner) {
        this.listner = listner;
    }

    /** ����һ��������С�Ķ��� */
    public  Animation createZoomOutAwayAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // ����һ�������Ķ���
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(time);
        anim.setInterpolator(new AccelerateInterpolator());
        ret.addAnimation(anim);
        // ����һ����С�Ķ���
        /*anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }
    /** ����һ��������С�Ķ��� */
    public  Animation createZoomOutNearAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // ����һ������Ķ���
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(time);
        anim.setInterpolator(new AccelerateInterpolator());
        ret.addAnimation(anim);
        // ����һ����С�Ķ���
        /*anim = new ScaleAnimation(3, 1, 3, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }

}
