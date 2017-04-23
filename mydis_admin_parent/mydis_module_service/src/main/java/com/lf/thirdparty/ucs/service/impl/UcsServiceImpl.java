package com.lf.thirdparty.ucs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lf.helper.Base64Helper;
import com.lf.helper.ClassHelper;
import com.lf.helper.HttpClientHelper;
import com.lf.helper.JsonHelper;
import com.lf.helper.PropertiesHelper;
import com.lf.helper.UUIDHelper;
import com.lf.helper.WebHelper;
import com.lf.thirdparty.ucs.model.request.Request;
import com.lf.thirdparty.ucs.model.request.RequestBody;
import com.lf.thirdparty.ucs.model.request.RequestBody1001;
import com.lf.thirdparty.ucs.model.request.RequestHead;
import com.lf.thirdparty.ucs.model.response.Response;
import com.lf.thirdparty.ucs.model.response.ResponseBody;
import com.lf.thirdparty.ucs.model.response.ResponseBody1001;
import com.lf.thirdparty.ucs.model.response.ResponseHead;
import com.lf.thirdparty.ucs.service.UcsService;


@Service
@SuppressWarnings("all")
public class UcsServiceImpl implements UcsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UcsService.class);
	private static final String CHARSET_UTF8 = "UTF-8";

	public <T> Response<T> doService(RequestBody requestBody){
		Response response = null;
		try{
			//1.创建Request
			String requestBodyClassName = ClassHelper.getClassName(requestBody);
			Request request = createRequest(requestBodyClassName);
			request.setBody(requestBody);
			//2.发送请求，获取结果json
			String responseJson = pushData(request);
			//3.返回相应的response
			response = createResponse(requestBodyClassName);
			TypeReference typeReference = createTypeReference(requestBodyClassName);
			response = (Response) JsonHelper.toObj(responseJson, typeReference);
		}catch (Exception e) {
			logger.error("处理ucs接口 异常", e);
		}
		return response;
	}
	
	private String pushData(Request request) throws Exception {
        String orderCenterMerchantUrl = PropertiesHelper.getStr("ucsUrl");
        String data = JsonHelper.toJson(request);
        Map<String, String> param = new HashMap<String, String>();
        data = Base64Helper.encode(data.getBytes(CHARSET_UTF8));
        param.put("data", data);
        param.put("txCode", request.getHead().getTxCode());
        param.put("msgSn", request.getHead().getMessageSn());
        String json = HttpClientHelper.post(orderCenterMerchantUrl, param);
        json = new String(Base64Helper.decoder(json),CHARSET_UTF8);
        return json;
    }

	private Request createRequest(String className){
		Request request = null;
		if(StringUtils.isBlank(className)){
			return null;
		}else if(RequestBody1001.class.getName().equalsIgnoreCase(className)){
			request = new Request<RequestBody1001>();
		}
		request.setHead(createRequestHead());
		request.setBody(createRequestBody(className));
		return request;
	}
	
	private Response createResponse(String className){
		Response response = null;
		if(StringUtils.isBlank(className)){
			return null;
		}else if(RequestBody1001.class.getName().equalsIgnoreCase(className)){
			response = new Response<ResponseBody1001>();
		}
		response.setHead(new ResponseHead());
		response.setBody(createResponseBody(className));
		return response;
	}
	
	 private RequestHead createRequestHead() {
	        RequestHead requestHead = new RequestHead();
	        requestHead.setVersion("100");
	        requestHead.setSysCode((String) WebHelper.getSession("channel"));
	        requestHead.setReqTime(new Date());
	        requestHead.setMessageSn(UUIDHelper.generateUUID());
	        return requestHead;
	 }
	 
	 private RequestBody createRequestBody(String className){
		RequestBody requestBody = null;
		if (StringUtils.isBlank(className)) {
			return null;
		} else if (RequestBody1001.class.getName().equalsIgnoreCase(className)) {
			requestBody = new RequestBody1001();
		}
		return requestBody;
	 }
	 
	private ResponseBody createResponseBody(String className) {
		ResponseBody responseBody = null;
		if (StringUtils.isBlank(className)) {
			return null;
		} else if (RequestBody1001.class.getName().equalsIgnoreCase(className)) {
			responseBody = new ResponseBody1001();
		}
		return responseBody;
	}
	
	private TypeReference createTypeReference(String className){
		TypeReference typeReference = null;
		if (StringUtils.isBlank(className)) {
			return null;
		} else if (RequestBody1001.class.getName().equalsIgnoreCase(className)) {
			typeReference = new TypeReference<Response<ResponseBody1001>>() {
			};
		}
		return typeReference;
	}

}
