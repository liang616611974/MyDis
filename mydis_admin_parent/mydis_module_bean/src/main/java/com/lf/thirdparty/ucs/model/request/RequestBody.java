package com.lf.thirdparty.ucs.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* <p>Title: RequestBody.java<／p>
* <p>Description: ucs接口体基类<／p>
* @author Liangfeng
* @date 2016-12-27
* @version 1.0
 */
public class RequestBody implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("InstitutionNo")
	private String institutionNo; //机构号
	
	@JsonProperty("Token")
	private String token;//令牌
	
	public String getInstitutionNo() {
		return institutionNo;
	}

	public void setInstitutionNo(String institutionNo) {
		this.institutionNo = institutionNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
