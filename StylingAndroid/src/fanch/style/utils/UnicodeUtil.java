package fanch.style.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtil {

    /**
     * utf-8 转unicode
     * 
     * @param inStr
     * @return String
     */
    public static String utf8ToUnicode(String str) {
	str = (str == null ? "" : str);
	String tmp;
	StringBuffer sb = new StringBuffer(1000);
	char c;
	int i, j;
	sb.setLength(0);
	for (i = 0; i < str.length(); i++) {
	    c = str.charAt(i);
	    sb.append("\\u");
	    j = (c >>> 8); // 取出高8位
	    tmp = Integer.toHexString(j);
	    if (tmp.length() == 1)
		sb.append("0");
	    sb.append(tmp);
	    j = (c & 0xFF); // 取出低8位
	    tmp = Integer.toHexString(j);
	    if (tmp.length() == 1)
		sb.append("0");
	    sb.append(tmp);

	}
	return (new String(sb));
    }

    /**
     * 
     * @param theString
     * @return String
     */
    public static String unicodeToUtf8(String str) {
	Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
	Matcher matcher = pattern.matcher(str);
	char ch;
	while (matcher.find()) {
	    ch = (char) Integer.parseInt(matcher.group(2), 16);
	    str = str.replace(matcher.group(1), ch + "");
	}
	return str;
    }
}
