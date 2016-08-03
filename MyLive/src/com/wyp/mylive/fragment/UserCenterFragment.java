package com.wyp.mylive.fragment;



import com.wyp.mylive.R;
import com.wyp.mylive.ui.MainActivityPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class UserCenterFragment extends Fragment {
    View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.user_center_fragment, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	    super.onActivityCreated(savedInstanceState);
	    
	    Button back=(Button)view.findViewById(R.id.button1);
	    
	    back.setOnClickListener(new View.OnClickListener() {
	        
	        @Override
	        public void onClick(View v) {
	    	// TODO Auto-generated method stub
	    	((MainActivityPager) getActivity()).getSlidingMenu().showContent();

	        }
	    });
	
	}
}
