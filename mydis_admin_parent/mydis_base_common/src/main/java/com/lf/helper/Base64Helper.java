package com.lf.helper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
* <p>Title: Base64Helper.java<／p>
* <p>Description:Base64加密解密帮助类 <／p>
* @author Liangfeng
* @date 2017-1-2
* @version 1.0
 */
public class Base64Helper {
	
	private Base64Helper(){}
	
	/**
	 * Base64加密成字符串
	 * @param b
	 * @return
	 */
	public static String encode(byte[] b) {
		String s = null;
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 * Base64解密字符串
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static byte[] decoder(String s) throws Exception {
		byte[] b = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			b = decoder.decodeBuffer(s);
		}
		return b;
	}
}
