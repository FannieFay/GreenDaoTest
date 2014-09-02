package fanch.style.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtil {
	public static int getAppVersionCode(Context context) {
		int versionName = 0;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}
	public static String getAppVersionName(Context context) {
        String versionName =null;
        try {
        	PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionName="0.0.0";
        }
        return versionName;
    }
	
	
	/**
     * 是否更新APP
     * 
     * @param context
     * @return
     */
    public static boolean isUpdataAPP(String lastVersion,String localVersion)
    {

        String[] v = lastVersion.split("[.]");
        String[] l = localVersion.split("[.]");
        for (int i = 0; i < l.length; i++)
        {
            if (!(Integer.valueOf(v[i]) == Integer.valueOf(l[i])))
            {
                if (Integer.valueOf(v[i]) > Integer.valueOf(l[i]))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
}
