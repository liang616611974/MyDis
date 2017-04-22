package com.lf.thirdparty.ucs.model.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* <p>Title: RequestHead.java<／p>
* <p>Description: ucs接口协议头信息<／p>
* @author Liangfeng
* @date 2016-12-26
* @version 1.0
 */
public class RequestHead {

	private String version; // 接口版本
    private String txCode; // 交易编码
    private String messageSn; // 报文流水号
    private String sysCode; // 发送者标识
    @JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    private Date reqTime; // 请求时间
    
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
	@Override
	public String toString() {
		return "RequestHead [version=" + version + ", txCode=" + txCode + ", messageSn=" + messageSn + ", sysCode=" + sysCode + ", reqTime=" + reqTime + "]";
	}
    
	
    
	
}
