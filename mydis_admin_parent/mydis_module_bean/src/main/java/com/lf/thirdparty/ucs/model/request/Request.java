package com.lf.thirdparty.ucs.model.request;

import java.io.Serializable;


/**
 * <p>
 * Title: Request.java<／p>
 * <p>
 * Description: ucs接口请求基类<／p>
 * 
 * @author Liangfeng
 * @date 2016-12-24
 * @version 1.0
 */
public class Request<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private RequestHead head;// 请求头

	private T body; // 请求体

	public RequestHead getHead() {
		return head;
	}

	public void setHead(RequestHead head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
	
	

}
