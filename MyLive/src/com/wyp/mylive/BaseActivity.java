package com.wyp.mylive;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.wyp.mylive.fragment.LeftMenuFragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.animation.Interpolator;
/**
 * 侧滑整个Activity管理
 * @author Administrator
 *
 */
public class BaseActivity extends SlidingFragmentActivity {
	//fragment管理�?
    private FragmentManager manager;
	private FragmentTransaction transaction;
	private CanvasTransformer mTransformer;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAnimation();
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置左侧的menu
        setBehindContentView(R.layout.left_menu);
        //得到fragment管理�?
        manager = getSupportFragmentManager();
        //�?启事�?
        transaction = manager.beginTransaction();
        //menu的fragment
        LeftMenuFragment leftMenu = new LeftMenuFragment();
        //将menu替换成LeftMenuFragment
        transaction.replace(R.id.fl_left_menu, leftMenu);
        //提交事务
        transaction.commit();
        //得到Slidingment组件(侧滑 )
        SlidingMenu sm = getSlidingMenu();
        //设置阴影宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		//设置阴影的效�?
		sm.setShadowDrawable(R.drawable.shadow);
		//设置menu打开的偏�?  越大显示的越�?
		sm.setBehindOffset(130);
//		setRes(R.dimen.slidingmenu_offset);
		//设置menu淡入淡出程度
		sm.setFadeDegree(0.35f);
		//设置menu能够打开的模�?
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//设置动画
//		sm.setBehindCanvasTransformer(mTransformer);
    }
	
	/**
	 * 初始化动画效�?
	 */
	
	//三种打开菜单的动�?
	private void initAnimation(){
		mTransformer = new CanvasTransformer(){
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
			    //1
				canvas.scale(percentOpen, 1, 0, 0);
			    
			    //2
//				canvas.translate(0, canvas.getHeight() * (1 - interp.getInterpolation(percentOpen)));
				
			    //3
//				float scale = (float) (percentOpen*0.25 + 0.75);
//				canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
			}
			
		};
	}
	private static Interpolator interp = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}		
	};
}