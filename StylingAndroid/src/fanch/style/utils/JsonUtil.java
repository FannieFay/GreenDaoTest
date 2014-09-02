package fanch.style.utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	// -----------------下面是一些工具方法--------------------------
	public static String getString(JSONObject jobj, String key) {
		String result = null;
		try {
			result = jobj.getString(key);
		} catch (JSONException e) {
		}
		return result;
	}

	public static int getInt(JSONObject jobj, String key) {
		String result = getString(jobj, key);
		if (result == "null" || result == null)
			return 0;
		return Integer.parseInt(result);
	}

	public static long getLong(JSONObject jobj, String key) {
		String result = getString(jobj, key);
		if (result == "null" || result == null)
			return 0;
		return Long.parseLong(result);
	}

	public static float getFloat(JSONObject jobj, String key) {
		String result = getString(jobj, key);
		if (result == "null" || result == null)
			return 0;
		return Float.parseFloat(result);
	}

	public static Double getDouble(JSONObject jobj, String key) {
		String result = getString(jobj, key);
		if (result == "null" || result == null)
			return (double) 0;
		return Double.parseDouble(result);
	}

	public static ArrayList<String> getStringList(JSONObject jobj, String key) {
		try {
			ArrayList<String> values = new ArrayList<String>();
			JSONArray jsonArray = jobj.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				String item = jsonArray.getString(i);
				values.add(item);
			}
			return values;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Set<String> getStringSet(JSONObject jobj, String key) {
        try {
            Set<String> values = new LinkedHashSet<String>();
            JSONArray jsonArray = jobj.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                String item = jsonArray.getString(i);
                values.add(item);
            }
            return values;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
