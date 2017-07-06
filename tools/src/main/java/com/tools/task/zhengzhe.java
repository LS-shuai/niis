
package com.tools.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class zhengzhe {
	public static void main(String[] args) {
		/*
		 * // 要验证的字符串 String str = "http://www.yizucn. com/misc.php?mod=mobile";
		 * 
		 * String regEx = "^(?=.*[a-zA-Z])(?=.*[0-9]).*$"; // 编译正则表达式 Pattern
		 * pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE); Matcher
		 * matcher = pat.matcher(str); // 字符串是否与正则表达式相匹配 boolean rs =
		 * matcher.matches(); System.out.println(rs);
		 */
/*		char numChar = '9';
		int intNum = (int) numChar;
		System.out.println(numChar + ": " + intNum);

		// method 1:
		char[] charArray = {numChar};  
		intNum = Integer.parseInt(new String(charArray));
		System.out.println("method 1: " + numChar + ":" + intNum);

		// method 2:
		System.out.println("method 2: " + numChar + ":"
				+ Character.getNumericValue(numChar));*/
		
        long a = Long.MAX_VALUE;
        long b = Long.MAX_VALUE;
        long sum = a + b;
        System.out.println("a="+a+",b="+b+",sum="+sum);
	}
}