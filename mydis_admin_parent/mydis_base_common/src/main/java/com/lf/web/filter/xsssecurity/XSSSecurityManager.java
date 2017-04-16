package com.lf.web.filter.xsssecurity;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lf.helper.StringHelper;

/**
* <p>Title: XSSSecurityManager.java<／p>
* <p>Description: XSSSecurityManager管理类<／p>
* @author Liangfeng
* @date 2017-2-22
* @version 1.0
 */
@SuppressWarnings("all")
public class XSSSecurityManager {
	
	private static final Logger logger = LoggerFactory.getLogger(XSSSecurityManager.class);
	
	//正则表达试
	private static String XSS_REGEX;
	
	//特殊字符匹配
    private static Pattern XSS_PATTERN ;
	
    private XSSSecurityManager(){}
    
    /**
     * 初始化XSSSecurity配置
     * @param xssConfigPath
     * @throws Exception
     */
    public static void init(String xssConfigPath) throws Exception{
		logger.info("=================加载xss_security_config文件============开始===========");
		//1.获取xssXml配置根结点
		Element superElement = new SAXReader().read(xssConfigPath).getRootElement();
		//2.初始化配置属性
		String isCheckHeader = getEleValue(superElement, "isCheckHeader");
		String isCheckParameter = getEleValue(superElement, "isCheckParameter");
		String isLog = getEleValue(superElement, "isLog");
		String isChain = getEleValue(superElement, "isChain");
		String isReplace = getEleValue(superElement, "isReplace");
		if(StringHelper.isNotBlank(isCheckHeader)){
			XSSSecurityConfig.IS_CHECK_HEADER = new Boolean(isCheckHeader);
		}
		if(StringHelper.isNotBlank(isCheckHeader)){
			XSSSecurityConfig.IS_CHECK_PARAMETER = new Boolean(isCheckParameter);
		}
		if(StringHelper.isNotBlank(isLog)){
			XSSSecurityConfig.IS_LOG = new Boolean(isLog);
		}
		if(StringHelper.isNotBlank(isChain)){
			XSSSecurityConfig.IS_CHAIN = new Boolean(isChain);
		}
		if(StringHelper.isNotBlank(isReplace)){
			XSSSecurityConfig.IS_REPLACE = new Boolean(isReplace);
		}
		//3.加载regex表达式集合
		Element regexEle = superElement.element("regexList");
		if(regexEle==null){
			logger.error("安全过滤配置文件中没有设置“{}”属性","regexList");
			return;
		}
		Iterator<Element> regexIt = regexEle.elementIterator();
		StringBuffer sb = new StringBuffer("^");
		//xml的cdata标签传输数据时，会默认在\前加\，需要将\\替换为\
		while(regexIt.hasNext()){
			Element regex = (Element)regexIt.next();
			String tmp = regex.getText();
			tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
        	sb.append(tmp);
        	sb.append("|");
		}
        if(sb.charAt(sb.length()-1)=='|'){
        	XSS_REGEX= sb.substring(0, sb.length()-1)+"$";
        	logger.info("安全匹配规则:{}",XSS_REGEX);
        }else{
        	logger.error("安全过滤配置文件加载失败:正则表达式异常{}",sb.toString());
        }
        //4.生成匹配器
		XSS_PATTERN = Pattern.compile(XSS_REGEX);
		logger.info("=================加载xss_security_config文件============结束===========");
    }
    
    /**
     * 对非法字符进行替换
     * @param text
     * @return
     */
    public static String securityReplace(String text){
    	if(StringHelper.isBlank(text)){
    		return text;
    	}
    	return text.replaceAll(XSS_REGEX, "");
    }
    
    /**
     * 匹配字符是否含特殊字符
     * @param text
     * @return
     */
    public static boolean matches(String text){
    	if(StringHelper.isBlank(text)){
    		return false;
    	}
    	//过滤控制字符
    	text = text.replaceAll("\\p{Cntrl}", "");
    	return XSS_PATTERN.matcher(text).matches();
    }
    
    /**
     * 释放关键信息
     */
	public static void destroy() {
		logger.info("=============XSSSecurityManager释放资源============开始===========");
		XSS_PATTERN = null;
		XSS_REGEX = null;
		logger.info("=============XSSSecurityManager释放资源============结束===========");
	}

    /**
     * 获取xss配置标签值
     */
    private static String getEleValue(Element element, String tagName){
    	String tagVal = element.elementText(tagName);
    	if(StringHelper.isBlank(tagVal)){
    		logger.info("安全过滤配置文件中没有设置“{}”属性",tagName);
    		return null;
    	}
		return tagVal;
	}
}
