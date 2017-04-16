package com.lf.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
* @Title: StringHelper.java
* @Description: 字符操作帮助类
* @author Liangfeng
* @date 2016-10-8
* @version 1.0
 */
public class StringHelper {
	
	private StringHelper(){}
	
	/**
	 * 判断是否空字符串，不包括空格字符
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	
	/**
	 * 判断是否非空字符串，不包括空格字符
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	/**
	 * 字符串转换成List集合
	 * @param str	字符串，例如："A,B,C...."
	 * @param regex 正则表达式，例如：","
	 * @return
	 */
	public static List<String> toList(String str,String regex){
		return Arrays.asList(str.split(regex));
	}
	
	/**
	 * 字符串转换成Set集合
	 * @param str 字符串，例如："A,B,C...."
	 * @param regex 正则表达式，例如：","
	 * @return
	 */
	public static Set<String> toSet(String str,String regex){
		return new HashSet<String>(Arrays.asList(str.split(regex)));
	}
	
	/**
	 * 如果字符串为null，则返回“”
	 * @param str
	 * @return
	 */
	public static String defaultString(String str) {
		return StringUtils.defaultString(str);
	}
	
	/**
	 * 如果字符串为Blank，则返回指定defaultStr
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String defaultString(String str, String defaultStr) {
		return StringUtils.defaultIfBlank(str, defaultStr);
	}
	
	/**
	 * 截取字符串
	 * @param str 
	 * @param start 开始截取的位置索引，从0开始，到字符串最后一个字符
	 * @return
	 */
	public static String subString(String str,int start){
		return StringUtils.substring(str, start);
	}
	
	/**
	 * 截取字符串
	 * @param str
	 * @param start 开始位置索引
	 * @param end 结束位置索引
	 * @return
	 */
	public static String substring(String str,int start, int end){
		return StringUtils.substring(str, start, end);
	}
	
	/**
	 * 截取分隔符前的字符串内容
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public static String substringBefore(String str,String separator){
		return StringUtils.substringBefore(str, separator);
	}
	
	/**
	 * 截取最后一个分隔符前的字符串内容
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringBeforeLast(String str,String separator){
		return StringUtils.substringBeforeLast(str, separator);
	}
	
	/**
	 * 截取分隔符后的字符串内容
	 * @param str
	 * @param separator 分隔符 
	 * @return
	 */
	public static String substringAfter(String str,String separator){
		return StringUtils.substringAfter(str, separator);
	}
	
	/**
	 * 截取最后一个分隔符后的字符串内容
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public static String substringAfterLast(String str,String separator){
		return StringUtils.substringAfterLast(str, separator);
	}
	
	/**
	 * 截取开始标志和结束标志中间的字符串内容
	 * @param str 
	 * @param open 开始标志
	 * @param close 结束标志
	 * @return
	 */
	public static String substringBetween(String str,String open,String close){
		return StringUtils.substringBetween(str, open, close);
	}
	
	/**
	 * 替换文本指定的字符串
	 * @param text 文本
	 * @param searchString 被替换的字符串
	 * @param replacement 替换的字符串
	 * @return
	 */
	public static String replace(String text, String searchString, String replacement) {
		return StringUtils.replace(text, searchString, replacement);
	}
	
	/**
	 * 比较字符串不同
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		if(str1==null&&str2==null){
			return false;
		}
		return StringUtils.equals(str1, str2);
	}
	
	/**
	 * 不区分大小写比较字符串不同
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsIgnoreCase(String str1, String str2){
		if(str1==null&&str2==null){
			return false;
		}
		return StringUtils.equalsIgnoreCase(str1, str2);
	}
	
	
	public static void main(String[] args) {
		String str1 = null;
		String str2 = null;
		System.out.println(((str1 == null) ? false : (str2 == null) ? true : str1
				.equals(str2)));
		
	}
	
}
