package com.wyp.mylive.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jutong.live.MainActivity;
import com.slidingmenu.lib.SlidingMenu;
import com.wyp.mylive.BaseActivity;
import com.wyp.mylive.R;
import com.wyp.mylive.adapter.ContentViewPagerAdapter;
import com.wyp.mylive.view.ContentViewPager;

public class HomePageFragment extends Fragment {

	private ContentViewPager vp_content_fragment;
	private BaseActivity activity;
	// ViewPager展示的内�?
	private List<Fragment> homePageVpList;
	private View view;
	private ImageView iv_toutiao_top_toleft;
	@ViewInject(R.id.main_fragment__title_line)
	private View titleLine;
	private int titleLineWidth;
	@ViewInject(R.id.rl_viewpager_option)
	private RelativeLayout rlVpOption;
	@ViewInject(R.id.tv_hot_fragment)
	private TextView tvHotFrament;
	@ViewInject(R.id.tv_concern_fragment)
	private TextView tvConcernFragment;
	@ViewInject(R.id.tv_find_fragment_title)
	private TextView tvtvFindTitle;
	@ViewInject(R.id.rbtn_main_page)
	private RadioButton rbtnMain;
	@ViewInject(R.id.rbtn_find_page)
	private RadioButton rbtnFind;
	@ViewInject(R.id.ibtn_my_live)
	private ImageButton ibtnMyLive;
	@Event(value = { R.id.tv_hot_fragment, R.id.tv_concern_fragment, R.id.rbtn_find_page, R.id.rbtn_main_page,R.id.ibtn_my_live })
	private void doClick(View view) {
		switch (view.getId()) {
		case R.id.tv_hot_fragment:
			vp_content_fragment.setCurrentItem(0);
			break;
		case R.id.tv_concern_fragment:
			vp_content_fragment.setCurrentItem(1);
			break;
		case R.id.rbtn_main_page:
			vp_content_fragment.setCurrentItem(0);
			rbtnFind.setChecked(false);
			rbtnMain.setChecked(true);
			break;
		case R.id.rbtn_find_page:
			vp_content_fragment.setCurrentItem(2);
			rbtnFind.setChecked(true);
			rbtnMain.setChecked(false);
			break;
		case R.id.ibtn_my_live:
			Intent intent = new Intent(getActivity(),MainActivity.class);
			Log.i("wyp", getActivity().getPackageName()+"");
			startActivity(intent);
			break;

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		System.out.println("来了�?------------");
		activity = (BaseActivity) getActivity();
		view = inflater.inflate(R.layout.home_page_fragment, null);
		x.view().inject(this, view);
		// 获取控件 设置监听
		vp_content_fragment = (ContentViewPager) view.findViewById(R.id.vp_content_fragment);
		vp_content_fragment.setOnPageChangeListener(new MainPagerChangeListener());
		vp_content_fragment.setSlidingMenu(activity.getSlidingMenu());
		findView();
		initData();
		addListener();

		return view;
	}

	// 获得控件
	private void findView() {
		iv_toutiao_top_toleft = (ImageView) view.findViewById(R.id.iv_toutiao_top_toleft);
	}

	private void addListener() {
		iv_toutiao_top_toleft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.getSlidingMenu().toggle();
			}
		});
	}

	public View getView() {
		return view;
	}

	// 初始化数�?
	private void initData() {
		homePageVpList = new ArrayList<Fragment>();
		homePageVpList.add(new TouTiaoFragment());
		homePageVpList.add(new TouTiaoFragment());
		homePageVpList.add(new UserCenterFragment());
		vp_content_fragment.setAdapter(new ContentViewPagerAdapter(getFragmentManager(), homePageVpList));
	}

	// 监听ViewPager的页面改�?
	private class MainPagerChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			titleLineWidth = titleLine.getMeasuredWidth();
			Log.i("wyp", titleLineWidth + "宽度");
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				TranslateAnimation tAnimation = new TranslateAnimation(titleLineWidth, 0, 0, 0);
				tAnimation.setDuration(100);
				tAnimation.setFillAfter(true);
				titleLine.startAnimation(tAnimation);
				tvHotFrament.setTextColor(getResources().getColor(R.color.green));
				tvConcernFragment.setTextColor(getResources().getColor(R.color.gray_white));
				rlVpOption.setVisibility(View.VISIBLE);
				tvtvFindTitle.setVisibility(View.GONE);
				// 如果处于�?0�? 设置启动menu模式为FULL 即处于第�?页能够右滑出menu
				activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
				break;
			case 1:
				TranslateAnimation tAnimation1 = new TranslateAnimation(0, titleLineWidth, 0, 0);
				tAnimation1.setDuration(100);
				tAnimation1.setFillAfter(true);
				titleLine.startAnimation(tAnimation1);
				tvHotFrament.setTextColor(getResources().getColor(R.color.gray_white));
				tvConcernFragment.setTextColor(getResources().getColor(R.color.green));
				rlVpOption.setVisibility(View.VISIBLE);
				tvtvFindTitle.setVisibility(View.GONE);
				activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
				break;
			case 2:
				activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
				rlVpOption.setVisibility(View.GONE);
				tvtvFindTitle.setVisibility(View.VISIBLE);
				break;
			default:
				// 否则设置为NONE
				activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				break;
			}
		}

	}
}
