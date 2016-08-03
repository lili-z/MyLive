package com.wyp.mylive.ui;


import com.wyp.mylive.A;
import com.wyp.mylive.BaseActivity;
import com.wyp.mylive.R;
import com.wyp.mylive.fragment.HomePageFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;



/**
 * 主题Activity menu的切换在此activity的ViewPager的第�?页中切换TabHost
 * 
 * @author Lx-1019
 * 
 */
public class MainActivityPager extends BaseActivity {
	//是否能够�?�?
	private boolean isBack = false;
	//上次按�??出的时间
	private long downTime;
	//首页
	private HomePageFragment homePager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		homePager = new HomePageFragment();
		transaction.replace(R.id.ll_main, homePager);
		transaction.commit();
		A a = new A();
		a.createShortcut(this);
		

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		int count = event.getPointerCount();
//		for (int i = 0; i < count; i++) {
//			event.getY(i);
//		}
		return super.onTouchEvent(event);
	}
	
	//监听后�??按钮
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//如果当前menu没有显示
			if (!getSlidingMenu().isMenuShowing()) {
				if (!isBack) {
					Toast.makeText(this, "再按�?次�??�?", 0).show();
					downTime = event.getDownTime();
					isBack = true;
					return true;
				} else{
					if (event.getDownTime() - downTime <= 2000) {
						finish();
					} else{
						Toast.makeText(this, "再按�?次�??�?", 0).show();
						downTime = event.getDownTime();
						return true;
					}
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}


}
