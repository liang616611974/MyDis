package com.lf.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lf.constant.SystemConstant;

/**
* <p>Title: SystemConfigHelper.java<／p>
* <p>Description: 系统配置帮助类<／p>
* @author Liangfeng
* @date 2017-2-10
* @version 1.0
 */
public class SystemConfigHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigHelper.class);
	//propertie对象
	private static Properties p = null;
	
	/**
	 * 私有化
	 */
	private SystemConfigHelper(){};
	
	/**
	 * 初始化properties文件
	 * @param properties
	 */
	public static void init(String configPath){
		logger.info("=================加载system_config文件============开始===========");		
		StringBuilder sb = null;
		try{
			sb = new StringBuilder();
			//1.判断文件是否存在
			if(StringHelper.isBlank(configPath)){
				return;
			}
			//2.如果存在，先过滤空格和“;”
			configPath = configPath.trim();
			if(configPath.endsWith(";")){
				configPath.substring(0, configPath.length()-1);
			}
			//3.遍历所有配置文件,并且合并
			String[] configPaths = configPath.split(";");
			for(String path : configPaths){
				if(StringHelper.isBlank(path)){
					continue;
				}
				path = path.trim();
				InputStream is = null;
				try{
					//多个配置文件组合成一个配置文件
					is = IOHelper.getInputStream(path);
					sb.append(IOHelper.toString(is, SystemConstant.SYS_ENCODING));
					sb.append(SystemConstant.SYS_LINE_SEPARATOR);
				}catch (Exception e) {
					logger.error("读取配置文件发生异常，path:{}",path,e);
				}finally{
					 if (is != null) {
	                     try {
	                         is.close();
	                     } catch (IOException e) {
	                    	 logger.error("关闭系统配置文件六发生异常",e);
	                     }
	                 }
				}
			}
			//4.初始化propertis;
			p = new Properties();
			p.load(new StringReader(sb.toString()));
		}catch (Exception e) {
			logger.error("初始化系统配置文件发生异常",e);
		}
		
		logger.info("=================加载system_config文件============结束===========");		
		
	}
	
	/**
	 * 获取properties对象
	 * @return
	 */
	public static Properties getProperties() {
		return p;
	}
	
	/**
	 * 获取配置文件字符串值
	 * @param key 配置文件的键
	 * @param defaulVal 默认值
	 * @return
	 */
	public static String getStr(String key,String defaulVal){
		if(StringHelper.isBlank(key)){
			if(defaulVal!=null){
				return defaulVal;
			}
			return "";
		}
		if (p.getProperty(key) == null) {
			if(defaulVal!=null){
				return defaulVal;
			}
			return "";
		} 
		return p.getProperty(key);
	}
	
	/**
	 * 获取配置文件字符串值
	 * @param key 配置文件的键 默认值为""
	 * @return
	 */
	public static String getStr(String key) {
		return getStr(key, null);
	}
	
	/**
	 * 获取配置文件Int值
	 * @param key 配置文件的键
	 * @param defaultVal 默认值
	 * @return
	 */
	public static int getInt(String key,int defaultVal) {
		String val = getStr(key);
		if(StringHelper.isBlank(val)) {
			return defaultVal;
		}
		return Integer.parseInt(val);
	}
	
	/**
	 * 获取配置文件Int值
	 * @param key 配置文件的键 默认值为0
	 * @return
	 */
	public static int getInt(String key){
		return getInt(key,0);
	}

	/**
	 * 获取配置文件boolean值
	 * @param key 配置文件的键
	 * @param defaultVal 默认值
	 * @return
	 */
	public static boolean getBoolean(String key,boolean defaultVal){
		if(StringHelper.isBlank(getStr(key))) {
			return defaultVal;
		}
		return Boolean.parseBoolean(key);
	}
	
	/**
	 * 获取配置文件boolean值
	 * @param key 配置文件的键 默认值为false
	 * @return
	 */
	public static boolean getBoolean(String key){
		return getBoolean(key,false);
	}
	
	
	/**
	 * 从System.getProperty(key)及System.getenv(key)得到值 默认值""
	 * @param key
	 * @return
	 */
	public String getSystemStr(String key) {
		String value = null;
		value = System.getProperty(key);
		if(StringHelper.isBlank(value)) {
			value = System.getenv(key);
		}
		if(StringHelper.isBlank(value)){
			value = "";
		}
		return value;
	}

	

}
