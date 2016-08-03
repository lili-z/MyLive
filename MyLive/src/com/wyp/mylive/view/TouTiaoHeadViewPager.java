package com.wyp.mylive.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 第一页listView中的HeadView
 */
public class TouTiaoHeadViewPager extends ViewPager {

	//标识
	private int abc = 1;
	private float mLastMotionX;
	private float mLastMotionY;
	private float xDistance,yDistance;

	public TouTiaoHeadViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		final float x = ev.getX();
		final float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = 0;
			yDistance = 0;
			//down事件注册不拦�?
			getParent().requestDisallowInterceptTouchEvent(true);
			abc = 1;
			//down下来的x y坐标
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (abc == 1) {
				//获得滑动的距�? x y
				xDistance += Math.abs(x - mLastMotionX);
				yDistance += Math.abs(y - mLastMotionY);
				//如果X滑动比较�? 认为这个事件是左右滑�?
				if (xDistance > yDistance + 5) {
					//向右滑动 并且当前处于第一�? 允许出现menu也就是取消注册不允许拦截
					if (x - mLastMotionX > 2 && getCurrentItem() == 0) {
						abc = 0;
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					//向左滑动
					if (x - mLastMotionX < -2
							&& getCurrentItem() == getAdapter().getCount() - 1) {
						abc = 0;
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				} else if(yDistance > xDistance + 5) {
					//如果Y滑动比较�? 直接取消注册
					getParent().requestDisallowInterceptTouchEvent(false);
				}
//				if (y - mLastMotionY > 2) {
//					abc = 0;
//					getParent().requestDisallowInterceptTouchEvent(false);
//				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
//			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

}
