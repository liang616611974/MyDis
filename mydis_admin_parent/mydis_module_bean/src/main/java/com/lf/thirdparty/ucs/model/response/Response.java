package com.lf.thirdparty.ucs.model.response;


import java.io.Serializable;

/**
 * 
* <p>Title: Response.java<／p>
* <p>Description:ucs接口相应基类 <／p>
* @author Liangfeng
* @date 2016-12-29
* @version 1.0
 */
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ResponseHead head;
	
	private T body;

	public ResponseHead getHead() {
		return head;
	}

	public void setHead(ResponseHead head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	

}
