package com.manish.sahaj.ticketupgrade.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private StringUtil() {}
	
	public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	
	public static boolean isValidString(String str, Pattern pattern) {
		if (str == null)
            return false;
        Matcher m = pattern.matcher(str);
        return m.matches();
	}
}
