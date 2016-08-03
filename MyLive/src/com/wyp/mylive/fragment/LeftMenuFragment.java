package com.wyp.mylive.fragment;



import com.wyp.mylive.R;
import com.wyp.mylive.ui.MainActivityPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * 左侧menu的fragment 操作menu逻辑以及显示 状�?�等
 * 
 */
public class LeftMenuFragment extends Fragment{

	// 此fragment绑定的Activity
	private MainActivityPager activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			// 得到绑定的Activity 此Activity必须是MainActivity或�?�继承他
			activity = (MainActivityPager) getActivity();

		} catch (ClassCastException e) {
			throw new ClassCastException("");
//			activity is not MainActivity or MainActivity's derivative and must implement MenuIntemClickListener
		}
		return inflater.inflate(R.layout.left_menu_info, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}


	
}
