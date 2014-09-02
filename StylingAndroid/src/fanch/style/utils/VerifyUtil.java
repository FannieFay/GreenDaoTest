package fanch.style.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
	/**
	 * 验证手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean verifyMoblile(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^(1[0-9]{10})$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/***
	 * 验证密码
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean verifyPassword(String pwd) {
		if (pwd != null && pwd.length() >= 6) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 验证新旧密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public static boolean verifyOldAndNewPwd(String oldPwd, String newPwd) {
		if (oldPwd == null || newPwd == null)
			return false;
		return oldPwd.equals(newPwd);
	}

	/**
	 * 验证验证码
	 * 
	 * @param code
	 * @return
	 */
	public static boolean verifyIdentifyingCode(String code) {
		if (code == null || code.length() < 6)
			return false;
		return true;
	}
}
