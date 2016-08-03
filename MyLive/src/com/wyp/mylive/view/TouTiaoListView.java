package com.wyp.mylive.view;

import com.wyp.mylive.R;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;



public class TouTiaoListView extends ListView {

	private float dimension;
	private ViewPager headView;

	public TouTiaoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		dimension = getResources().getDimension(R.dimen.viewpager_height);
		  
	}

	public void setHeadViewPager(ViewPager headView) {
		this.headView = headView;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		if (ev.getY() <= dimension) {
//			requestDisallowInterceptTouchEvent(true);
//		}
		return super.dispatchTouchEvent(ev);
	}





}
