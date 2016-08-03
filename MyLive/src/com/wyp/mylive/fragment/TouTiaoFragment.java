package com.wyp.mylive.fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wyp.mylive.R;
import com.wyp.mylive.activity.PlayActivity;
import com.wyp.mylive.entity.PosterHandler;
import com.wyp.mylive.view.PullToRefreshView;
import com.wyp.mylive.view.PullToRefreshView.OnHeaderRefreshListener;
import com.wyp.mylive.view.TouTiaoListView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;



public class TouTiaoFragment extends Fragment {
	public static final String LOG_TAG = "TouTiaoFragment";
	private View headView;
	private List<ImageView> headList;
	private TouTiaoListView lv_toutiao;
	private PullToRefreshView prv_toutiao_refresh;
	public ViewPager headPage;
	public  PosterHandler handler=new PosterHandler(new WeakReference<TouTiaoFragment>(this));
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.toutiao, null);
		prv_toutiao_refresh = (PullToRefreshView) view.findViewById(R.id.prv_toutiao_refresh);
		prv_toutiao_refresh.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
			
			@Override
			public void onHeaderRefresh(PullToRefreshView view) {
				System.out.println("�?要刷�?---------------------");
//				Date date = new Date();
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				System.out.println(dateFormat.format(date));
//				prv_toutiao_refresh.onHeaderRefreshComplete("上次刷新:"+dateFormat.format(date));
			}
		});
		
	
		headView = inflater.inflate(R.layout.toutiao_lv_head, null);
		System.out.println(headView);
//		FragmentTransaction transaction = getFragmentManager()
//				.beginTransaction();
//		transaction.replace(R.id.ll_toutiao_topbar, new TouTiaoTopBarFragment());
//		transaction.commit();
		lv_toutiao = (TouTiaoListView) view.findViewById(R.id.lv_toutiao);
		addHead();
		initData();
		setListener();
		return view;
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	private void setListener() {
		lv_toutiao.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posation, long arg3) {
				Intent intent = new Intent(getActivity(),PlayActivity.class);
				startActivity(intent);
			}
		});
		headPage.setOnPageChangeListener(new OnPageChangeListener() {
			
			//配合Adapter的currentItem字段进行设置。  
            @Override  
            public void onPageSelected(int arg0) {  
                handler.sendMessage(Message.obtain(handler, handler.MSG_PAGE_CHANGED, arg0, 0));  
            }  
               
            @Override  
            public void onPageScrolled(int arg0, float arg1, int arg2) {  
            }  
               
            //覆写该方法实现轮播效果的暂停和恢复  
            @Override  
            public void onPageScrollStateChanged(int arg0) {  
                switch (arg0) {  
                case ViewPager.SCROLL_STATE_DRAGGING:  
                    handler.sendEmptyMessage(handler.MSG_KEEP_SILENT);  
                    break;  
                case ViewPager.SCROLL_STATE_IDLE:  
                    handler.sendEmptyMessageDelayed(handler.MSG_UPDATE_IMAGE, handler.MSG_DELAY);  
                    break;  
                default:  
                    break;  
                }  
            }  
        });  
		
//		headPage.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界     这句没用()
        //开始轮播效果  
		handler.sendEmptyMessageDelayed(handler.MSG_UPDATE_IMAGE, handler.MSG_DELAY); 
		
	}

	//添加头部
	private void addHead(){
		lv_toutiao.addHeaderView(headView);
		headPage = (ViewPager) headView.findViewById(R.id.vp_toutiao_head);
		//头部的list
		headList = new ArrayList<ImageView>();
		ImageView iv_1 = new ImageView(getActivity());
		iv_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "点击了我 是图�?---------", 0).show();
			}
		});
		iv_1.setBackgroundResource(R.drawable.qq20001);
		headList.add(iv_1);
		ImageView iv_2 = new ImageView(getActivity());
		iv_2.setBackgroundResource(R.drawable.qq20002);
		headList.add(iv_2);
		ImageView iv_3 = new ImageView(getActivity());
		iv_3.setBackgroundResource(R.drawable.qq20003);
		headList.add(iv_3);
		ImageView iv_4 = new ImageView(getActivity());
		iv_4.setBackgroundResource(R.drawable.qq20004);
		headList.add(iv_4);
		ImageView iv_5 = new ImageView(getActivity());
		iv_5.setBackgroundResource(R.drawable.qq20005);
		headList.add(iv_5);
		ImageView iv_6 = new ImageView(getActivity());
		iv_6.setBackgroundResource(R.drawable.qq20006);
		headList.add(iv_6);
		ImageView iv_7 = new ImageView(getActivity());
		iv_7.setBackgroundResource(R.drawable.qq20007);
		headList.add(iv_7);
		
		headPage.setAdapter(new PagerAdapter() {
			
	   
	        @Override  
	        public int getCount() {  
	            //设置成最大，使用户看不到边界  
	            return Integer.MAX_VALUE;  
	        }  
	   
	        @Override  
	        public boolean isViewFromObject(View arg0, Object arg1) {  
	            return arg0==arg1;  
	        }  
	         @Override    
	         public void destroyItem(ViewGroup container, int position,    
	                 Object object) {    
	             //Warning：不要在这里调用removeView  
	         }    
	         @Override    
	         public Object instantiateItem(ViewGroup container, int position) {  
	             //对ViewPager页号求模取出View列表中要显示的项  
	             position %= headList.size();  
	             if (position<0){  
	                 position = headList.size()+position;  
	             }  
	             ImageView view = headList.get(position);  
	             //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。  
	             ViewParent vp =view.getParent();  
	             if (vp!=null){  
	                 ViewGroup parent = (ViewGroup)vp;  
	                 parent.removeView(view);  
	             }  
	             container.addView(view);    
	             //add listeners here if necessary  
	             return view;    
	         }    
		});
//		lv_toutiao.setHeadViewPager(headPage);
	}

	//初始化数�?
	private void initData() {
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", i+".");
			data.add(map);
		}
		String[] from = new String[]{"id"};
		int[] to = new int[]{android.R.id.text1};
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data ,android.R.layout.simple_list_item_1, from , to );
		lv_toutiao.setAdapter(adapter);
		
	}
}
