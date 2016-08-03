package com.wyp.mylive.activity;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.wyp.mylive.R;
import com.wyp.mylive.R.drawable;
import com.wyp.mylive.R.id;
import com.wyp.mylive.R.layout;
import com.wyp.mylive.ui.MainActivityPager;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * 欢迎动画界面    集成第三方登录
 * @author Administrator
 *
 */
public class StartLoginActivity extends Activity {
	private RelativeLayout rlLogin;
	private AnimationDrawable animation;
	@ViewInject(R.id.btn_kk_login)
	private Button btn;
	@ViewInject(R.id.rl_login_info)
	private RelativeLayout rlLoginInfo;
	@ViewInject(R.id.btn_xl_login)
	private Button btnXlLogin;
	@ViewInject(R.id.btn_wx_login)
	private Button btnWxLogin;
	@ViewInject(R.id.btn_qq_login)
	private Button btnQQLogin;
	@Event(value = {R.id.btn_kk_login,R.id.btn_xl_login,R.id.btn_wx_login,R.id.btn_qq_login})
	private void onClick(View view){
		switch (view.getId()) {
		case R.id.btn_kk_login:
			Intent intent = new Intent(this,KkLoginActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_xl_login:
			
			break;
		case R.id.btn_wx_login:
			break;
		case R.id.btn_qq_login:
			Intent intent1= new Intent(StartLoginActivity.this,MainActivityPager.class);
			startActivity(intent1);
			finish();
			break;
			
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_login);
		x.view().inject(this);
		rlLogin = (RelativeLayout) findViewById(R.id.rl_main_aw);
		new Handler().postAtTime(new Runnable() {
			
			@Override
			public void run() {
				AlphaAnimation alpha = new AlphaAnimation(0, 1);
				ScaleAnimation scale = new ScaleAnimation( 0, 0,1, 1, rlLoginInfo.getHeight()/2, rlLoginInfo.getWidth()/2);
				alpha.setDuration(1000);
				scale.setDuration(1000);
				rlLoginInfo.setAnimation(alpha);
				rlLoginInfo.setAnimation(scale);
				}
		}, 1000);
	}
	@Override
	protected void onResume() {
		super.onResume();
		showAnimationDrawable();
	}
	@Override
	protected void onPause() {
		super.onPause();
		animation.stop();
	}
	private void showAnimationDrawable() {
		animation = new AnimationDrawable();
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_1), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_2), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_3), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_4), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_5), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_6), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_7), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_8), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_9), 100);
		animation.addFrame(getResources().getDrawable(R.drawable.kk_launcher_loading_10), 100);
		rlLogin.setBackground(animation);
		animation.setOneShot(false);
		animation.start();
	}
	@Override
	protected void onDestroy() {
		animation.stop();
		animation = null;
		super.onDestroy();
	}
}
