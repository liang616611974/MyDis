package com.lf.common;
/**
* @Title: Result.java
* @Description: 公共处理结果响应类
* @author Liangfeng
* @date 2016-9-12
* @version 1.0
 */
public class Result<T> {
	
	private boolean flag; // 结果标识：成功 true;失败 fasle
	private String code; // 结果代码 成功 0000;异常 9999;失败 9998;其他
	private String msg; // 结果消息
	private T data; // 结果返回数据
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
