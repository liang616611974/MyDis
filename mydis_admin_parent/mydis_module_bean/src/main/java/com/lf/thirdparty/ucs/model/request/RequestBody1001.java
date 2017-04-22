package com.lf.thirdparty.ucs.model.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
* <p>Title: RequestBody1001.java<／p>
* <p>Description: ucs1001接口体<／p>
* @author Liangfeng
* @date 2016-12-27
* @version 1.0
 */
public class RequestBody1001 extends RequestBody {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Shanghai")
	private Date startTime ; //项目起始投标时间(开始时间)
	@JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Shanghai")
	private Date endTime ; //项目起始投标时间(结束时间)
	@JsonProperty("PrjType")
	private String prjType ; //项目类型
	private String queryMode ; //项目查询模式
	private String sortMode ; //排序模式
	private String prjStatus ; //项目状态
	private Integer startIndex ; //页数
	private Integer qryCount ; //每页条数
	private String mobile ;//手机号
	private String channel ; // 渠道 00  投融资平台 01  直销银行-web 02  直销银行-IOS 03  直销银行-android

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPrjType() {
		return prjType;
	}
	public void setPrjType(String prjType) {
		this.prjType = prjType;
	}
	public String getQueryMode() {
		return queryMode;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	public String getSortMode() {
		return sortMode;
	}
	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}
	public String getPrjStatus() {
		return prjStatus;
	}
	public void setPrjStatus(String prjStatus) {
		this.prjStatus = prjStatus;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}	
    public Integer getQryCount() {
		return qryCount;
	}
	public void setQryCount(Integer qryCount) {
		this.qryCount = qryCount;
	}	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
	@Override
	public String toString() {
		return "RequestBody1001 [startTime=" + startTime + ", endTime=" + endTime + ", prjType=" + prjType + ", queryMode=" + queryMode + ", sortMode=" + sortMode + ", prjStatus=" + prjStatus + ", startIndex=" + startIndex + ", qryCount=" + qryCount + ", mobile=" + mobile + ", channel=" + channel + "]";
	}	
    
    
}
