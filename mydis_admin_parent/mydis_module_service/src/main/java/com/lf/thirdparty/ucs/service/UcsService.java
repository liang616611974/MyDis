package com.lf.thirdparty.ucs.service;

import com.lf.thirdparty.ucs.model.request.RequestBody;
import com.lf.thirdparty.ucs.model.response.Response;




public interface UcsService {
	
	/**
	 * 处理业务入口
	 * @param requestBody
	 * @return
	 */
	<T> Response<T> doService(RequestBody requestBody);
}
