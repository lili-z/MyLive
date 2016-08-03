package com.wyp.mylive.fragment;

import com.wyp.mylive.BaseActivity;
import com.wyp.mylive.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;



public class TouTiaoTopBarFragment extends Fragment {

	private View view;
	private ImageView iv_toutiao_top_toleft;
	private BaseActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//得到绑定的activity
		activity = (BaseActivity) getActivity();
		view = inflater.inflate(R.layout.toutiao_topbar, null);
		findView();
		addListener();
		return null;
	}
	
	//获得控件
	private void findView(){
		iv_toutiao_top_toleft = (ImageView) view.findViewById(R.id.iv_toutiao_top_toleft);
	}
	
	private void addListener(){
		iv_toutiao_top_toleft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.getSlidingMenu().toggle();
			}
		});
	}
}
