package com.wyp.mylive;



import com.wyp.mylive.activity.PlayActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
/**
 * 创建系统快捷图标
 * @author Administrator
 *
 */
public class A {

	public void createShortcut(Context c) {
		
		//获得快捷方式系统表的uri
		Uri uri = null;
		System.out.println(android.os.Build.VERSION.SDK_INT);
		if (android.os.Build.VERSION.SDK_INT < 8) {
			uri = Uri.parse("content://com.android.launcher.settings/favorites?notify=true"); 
		} else {
			uri = Uri.parse("content://com.android.launcher2.settings/favorites?notify=true");
		}
		//查询是否存在本应�?
		Cursor cursor = c.getContentResolver().query(uri,
				new String[] { "title" }, "title = ?",
				new String[] { c.getResources().getString(R.string.app_name) },
				null);
		boolean isExit = false;
		if (null != cursor) {
			isExit = cursor.moveToNext();
			System.out.println(cursor.getColumnIndex("title"));
		}
		System.out.println(cursor+"：空了吗--------------");
		//如果不存�?
		if (!isExit) {
			System.out.println("创建快捷图标-----------");
			//获取应用图标
			Parcelable icon = Intent.ShortcutIconResource.fromContext(c,R.drawable.ic_launcher);
			//创建快捷方式意图
			Intent intent = new Intent(
					"com.android.launcher.action.INSTALL_SHORTCUT");
			intent.putExtra("duplicate", false);
			//快捷�?
			intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, c.getResources().getString(R.string.app_name));
			//快捷图标
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
			//点击快捷方式要执行的意图 
			Intent mIntent = new Intent(Intent.ACTION_MAIN);
			mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			ComponentName component = new ComponentName(c,PlayActivity.class);
			mIntent.setComponent(component);
			intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, mIntent);
			//发�?�广�? 通知系统创建快捷方式
			c.sendBroadcast(intent);
		}
	}
}
