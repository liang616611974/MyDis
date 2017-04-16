package com.lf.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
* <p>Title: IOHelper.java<／p>
* <p>Description: IO流操作帮助类<／p>
* @author Liangfeng
* @date 2017-2-10
* @version 1.0
 */
public class IOHelper {

	/**
	 * 私有化
	 */
	private IOHelper(){}
	
	/**
	 * 获取文件输入流，支持classpath:表达式
	 * @param filePath
	 * @return
	 */
	public static InputStream getInputStream(String filePath) {
		InputStream is = null;
		try{
			is = new FileInputStream(FileHelper.getFile(filePath));
		}catch (Exception e) {
			return null;
		}
		return is;
	}
	
	/**
	 * 输入流转化成字符串
	 * @param input
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String toString(InputStream input, String encoding) throws IOException{
		return IOUtils.toString(input, encoding);
	}
	
		
}
