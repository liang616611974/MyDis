package com.lf.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
* <p>Title: WebHelper.java<／p>
* <p>Description: web操作帮助类<／p>
* @author Liangfeng
* @date 2017-1-2
* @version 1.0
 */
public class WebHelper {
	
	/**
	 * 设置cookie有效期，根据需要自定义
	 */
	private final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;
	
	private WebHelper(){};
	
	/**
	 * 获取HttpServletRequest 对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			ServletRequestAttributes reqAttr = (ServletRequestAttributes) requestAttributes;
			if (null != reqAttr) {
				request = reqAttr.getRequest();
			}
		}
		return request;
	 }
	
	/**
	 * 获取request的属性值
	 * @param name
	 * @return
	 */
	public static String getRequest(String name) {
		String value = "";
		if(StringHelper.isBlank(name)){
			return value;
		}
		HttpServletRequest request = getRequest();
		if (null != request) {
			Object obj = request.getAttribute(name);
			if (null != obj) {
				value = obj.toString();
			}
		}
		return value;
	}
	
	/**
	 * 获取session的属性值
	 * @param name
	 * @return
	 */
	public static <T> T getSession(String name) {
		T value = null;
		if(StringHelper.isBlank(name)){
			return value;
		}
		HttpServletRequest request = getRequest();
		if (null != request) {
			HttpSession session = request.getSession(false);
			if (null != session) {
				Object obj = session.getAttribute(name);
				if (null != obj) {
					value = (T) obj;
				}
			}
		}
		return value;
	}
	
	/**
	 * 获取Cookie 对象
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(String name) {
		if (StringHelper.isBlank(name)) {
			return null;
		}
		HttpServletRequest request = getRequest();
		Cookie cookies[] = request.getCookies();
		if (name.length() == 0)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (!cookies[i].getName().equals(name))
				continue;
			cookie = cookies[i];
			if (request.getServerName().equals(cookie.getDomain())){
				break;
			}
		}

		return cookie;
	}
	
	/**
	 * 获取cookie属性值
	 * @param name
	 * @return
	 */
	public static String getCookieValue(String name) {
		if (StringHelper.isBlank(name)) {
			return null;
		}
        Cookie cookie = getCookie(name);
        if (cookie != null) {
            return cookie.getValue();
        } 
        return null;
    }
	
	/**
	 * 设置cookie	
	 * @param response
	 * @param name key
	 * @param value value
	 * @param maxAge 过期时间
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		if (StringHelper.isBlank(name) || StringHelper.isBlank(value)) {
			return;
		}
		Cookie cookie = new Cookie(name, value);
		if (maxAge != 0) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 设置cookie 默认过期时间一个月
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param cookie
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
	
	/**
	 * 获取请求客户端的ip地址
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        if (StringHelper.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if (StringHelper.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringHelper.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringHelper.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringHelper.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        return ip.split(",")[0];
    }

}
