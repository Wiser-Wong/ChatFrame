package com.wiser.chatframe.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;

import com.wiser.chatframe.ChatApplication;

import java.lang.reflect.Field;

/**
 * @author Wiser
 * 
 *         聊天工具类
 */
public class ChatTool {

	/**
	 * 获得软键盘高度
	 *
	 * @param paramActivity
	 * @return
	 */
	public static int getKeyboardHeight(Activity paramActivity) {
		int height = getWindowHeight(paramActivity) - getStatusBarHeight(paramActivity) - getAppHeight(paramActivity);
		if (height == 0) {
			height = ChatApplication.getInstance().getKeyboardHeight();// 787为默认软键盘高度 基本差不离
		} else {
			ChatApplication.getInstance().setKeyboardHeight(787);
		}
		return height;
	}

	/**
	 * 判断软键盘是否显示
	 *
	 * @return
	 */
	public static boolean isKeyboardShown(Activity activity) {
		if (activity == null) return false;
		if (activity.getWindow() == null) return false;
		// 获取当前屏幕内容的高度
		int screenHeight = activity.getWindow().getDecorView().getHeight();
		// 获取View可见区域的bottom
		Rect rect = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		// 计算软件盘的高度
		int softInputHeight = screenHeight - rect.bottom;
		/**
		 * 某些Android版本下，没有显示软键盘时减出来的高度总是144，而不是零， 这是因为高度是包括了虚拟按键栏的(例如华为系列)，所以在API
		 * Level高于20时， 我们需要减去底部虚拟按键栏的高度（如果有的话）
		 */
		if (Build.VERSION.SDK_INT >= 20) {
			softInputHeight = softInputHeight - getSoftButtonsBarHeight(activity);
		}
		// 存一份到本地
		if (softInputHeight > 0) {
			ChatApplication.getInstance().setKeyboardHeight(softInputHeight);
		}
		return softInputHeight != 0;
	}

	/**
	 * 底部虚拟按键栏的高度
	 *
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) public static int getSoftButtonsBarHeight(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		// 这个方法获取可能不是真实屏幕的高度
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int usableHeight = metrics.heightPixels;
		// 获取当前屏幕的真实高度
		activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
		int realHeight = metrics.heightPixels;
		if (realHeight > usableHeight) {
			return realHeight - usableHeight;
		} else {
			return 0;
		}
	}

	/**
	 * 获取屏幕高度
	 *
	 * @return height
	 */
	public static int getWindowHeight(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}

	// 获取手机状态栏高度
	@SuppressLint("PrivateApi") public static int getStatusBarHeight(Context context) {
		Class<?> c;
		Object obj;
		Field field;
		int x, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 可见屏幕高度
	 **/
	public static int getAppHeight(Activity paramActivity) {
		Rect localRect = new Rect();
		paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
		return localRect.height();
	}

}
