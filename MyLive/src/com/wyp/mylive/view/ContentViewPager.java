package com.wyp.mylive.view;


import com.slidingmenu.lib.SlidingMenu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ContentViewPager extends ViewPager {

	private SlidingMenu sm;

	public ContentViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setSlidingMenu(SlidingMenu sm){
		this.sm = sm;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (getCurrentItem() == 0) {
			View view = getChildAt(0);
//			view.find
		}
		return super.onTouchEvent(ev);
	}

}
