package com.lf.web.mvc.interceptor.token;

import com.lf.constant.WebConstant;
import com.lf.helper.SystemConfigHelper;
import com.lf.helper.UUIDHelper;
import com.lf.web.mvc.interceptor.token.annotation.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
* <p>Title: TokenInterceptor.java<／p>
* <p>Description: 防重复提交拦截器:检查表单的token是否存在并正确，从而达到防重复提交<／p>
* @author Liangfeng
* @date 2017-2-8
* @version 1.0
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
	private static final String TOKEN_KEY = "token";
	private static final String ASYNC_TOKEN_HEADER = "asyncToken";
	
	/**
	 * Controller方法调用之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1.获取Token注解
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		Token tokenAnno = method.getAnnotation(Token.class);
		HttpSession session = request.getSession();
		//2.判断token注解是否存在，不存在则放行
		if(tokenAnno==null){
			return true;
		}
		//3.存在token注解
		//3.1新建token，将token返回页面，并保存到session中
		String newToken = UUIDHelper.generateUUID();
		if(tokenAnno.save()){
			request.setAttribute(TOKEN_KEY, newToken);
			session.setAttribute(getSessionTokenKey(request, tokenAnno), newToken);
			return true;
		}
		//3.2校验token
		if(tokenAnno.valid()){
			String requestType = request.getHeader(WebConstant.AJAX_REQUEST_HEADER);
			boolean isAsyncRequest = WebConstant.AJAX_REQUEST_HEADER_VALUE.equalsIgnoreCase(requestType);
			String sessionTokenKey = getSessionTokenKey(request, tokenAnno);
			//3.2.1如果是重复提交
			if(isRepeatSubmit(request, sessionTokenKey)){
				if (isAsyncRequest) {
                    response.setStatus(401);
                    return false;
                } else {
                    throw new RuntimeException("非法请求：token已失效或与session中token不一致");
                }
			}
			//3.2.2如果不是重复提交，更新页面的token
			if (isAsyncRequest) {
                session.setAttribute(sessionTokenKey, newToken);
                response.setHeader(ASYNC_TOKEN_HEADER, newToken);
            }
		}
		return true;
	}
	
	/**
	 * Controller方法调用之后，DispatcherServlet进行视图的渲染之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * Interceptor的preHandle方法的返回值为true时才会执行。
	 *  该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，这个方法的主要作用是用于清理资源的
	*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 获取sessionTokenKey
	 */
	private String getSessionTokenKey(HttpServletRequest request, Token annotation) {
        String reqUri = request.getRequestURI();
        StringBuilder key = new StringBuilder();
        key.append(TOKEN_KEY);
        //1.如果是新建Token
        if (annotation.save()) {
            logger.info("sessionTokenKeyReqUri=========={}",reqUri);
            //去掉项目名称
            reqUri = StringUtils.replace(reqUri, request.getContextPath(), "");
            //替换"/"为"_"
            reqUri = StringUtils.replace(reqUri, "/", "_");
            key.append(reqUri);
            logger.info("sessionTokenKeySave========={}", key.toString());
            return key.toString();
        } 
    
        //2.如果是验证Token
        String serverPrefix = SystemConfigHelper.getStr("system_url");
        String referer = request.getHeader(WebConstant.REFERER_REQUEST_HEADER);
        logger.info("sessionTokenKeyReferer================{}",referer);
        //2.1如果refer为空者返回key
        if(StringUtils.isBlank(referer)){
        	return key.toString();
        }
        //2.2如果不为空
        String refererUri = StringUtils.replace(referer, serverPrefix, "");
        refererUri = StringUtils.replace(refererUri, "/", "_");
        refererUri = StringUtils.substringBefore(refererUri, "?");
        key.append(refererUri);
        logger.info("sessionTokenKeyValid========={}",key.toString());
        return key.toString();
        
    }
	
	/**
	 * 是否重复提交
	 */
	private boolean isRepeatSubmit(HttpServletRequest request, String sessionTokenKey) {
		boolean isRepeatSubmit = true;
		HttpSession session = request.getSession(false);
		if (null == session) {
			return true;
		}
		Object serverToken = session.getAttribute(sessionTokenKey);//session的token
		String clientToken = request.getParameter(TOKEN_KEY);//客户端的token
		session.removeAttribute(sessionTokenKey);//校验完作废session的token
		if(StringUtils.equals(String.valueOf(serverToken), clientToken)){
			isRepeatSubmit = false;
		}
		return isRepeatSubmit;
	}
    
	
}
