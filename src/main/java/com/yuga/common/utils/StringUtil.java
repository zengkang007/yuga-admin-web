package com.yuga.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringUtil {

	public static String formatSperatorList(String inStr) {
		if (inStr != null && inStr.endsWith(",")) {
			return inStr.substring(0, inStr.length() - 1);
		}
		return inStr;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	 /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }
    /**
     * 格式化字符串
     * @param msg
     * @return
     */
    public static String formatUTF8(String msg) {
    	if(msg==null) {
    		return "";
    	}
    	try {
    		if(isMessyCode(msg)){
    			return new String(msg.getBytes("iso-8859-1"),"utf-8");
    		}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	return msg;
    }
    
    public static void main(String[] args) {
		System.out.println(isMessyCode("你好"));
	}
	
	@Test
	public void test() {
		System.out.println(formatSperatorList("1,2,3,"));
		System.out.println(formatSperatorList("1,2,3"));
		System.out.println(formatSperatorList(""));
		System.out.println(formatSperatorList(null));
		System.out.println(formatSperatorList("1"));
		System.out.println(formatSperatorList("2,"));
	}
}
