package com.wyp.mylive.app;

import org.xutils.x;



import android.app.Application;

public class MyApplication extends Application{
	private static MyApplication app;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.app = this;
		x.Ext.init(this);
	}
	public static MyApplication getContext(){
		return app;
	}
	
}
