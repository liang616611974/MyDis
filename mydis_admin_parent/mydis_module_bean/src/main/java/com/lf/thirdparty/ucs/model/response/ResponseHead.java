package com.lf.thirdparty.ucs.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* <p>Title: ResponseHead.java<／p>
* <p>Description: ucs接口响应头类<／p>
* @author Liangfeng
* @date 2016-12-29
* @version 1.0
 */
public class ResponseHead {
	
	private String version; // 接口版本
    private String txCode; // 交易编码
    private String messageSn; // 报文流水号
    private String sysCode; // 发送者标识
    @JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "Asia/Shanghai")
    private Date reqTime; // 请求时间
    @JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "Asia/Shanghai")
    private Date respTime; // 应答时间
    private String respCode; // 应答码
    private String respMsg; // 应答信息
    
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTxCode() {
		return txCode;
	}
	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}
	public String getMessageSn() {
		return messageSn;
	}
	public void setMessageSn(String messageSn) {
		this.messageSn = messageSn;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	public Date getRespTime() {
		return respTime;
	}
	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
    
}
