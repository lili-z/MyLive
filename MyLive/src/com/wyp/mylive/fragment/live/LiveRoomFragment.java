package com.wyp.mylive.fragment.live;




import com.wyp.mylive.R;
import com.wyp.mylive.inter.ILiveRoomFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 播放界面
 * @author Administrator
 *
 */
public class LiveRoomFragment extends Fragment implements ILiveRoomFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.kk_fragment_vert_full_bottom_view, null);
		
		return view;
	}
	@Override
	public void showData() {
		
	}

}
