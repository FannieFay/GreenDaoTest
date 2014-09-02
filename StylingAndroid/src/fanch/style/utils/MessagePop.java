package fanch.style.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class MessagePop {
	/**
	 * 弹出Toast消息
	 * 
	 * @param msg
	 */
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, int msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, String msg, int time) {
		Toast.makeText(cont, msg, time).show();
	}

	public static void ToastMessage(Context cont, int msg, int time) {
		Toast.makeText(cont, msg, time).show();
	}

	public static void ToastMessdageTop(Context cont, String msg) {
		Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 120);
		toast.show();
	}
}
