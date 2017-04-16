package com.lf.web.mvc.converter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
* <p>Title: LongJsonMapper.java<／p>
* <p>Description: 自定义的jackson mapper类，
* jackson 自定义mapper,处理一些java类型转换成json后到前端js使用的问题，比如long值转换成js数值后会丢失精度 <／p>
* @author Liangfeng
* @date 2017-3-5
* @version 1.0
 */
public class JacksonMapper extends ObjectMapper{
	

	private static final long serialVersionUID = 1L;
	
	public JacksonMapper() {
		/* 序列换成json时,将所有的long变成string
		 *  因为js中得数字类型不能包含所有的java long值 ,这样精度会缺失，所以要转成字符串json格式
		 */
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		super.registerModule(simpleModule);
	}
	
	/*@Override
	public ObjectMapper registerModule(Module module) {
		*//** 序列换成json时,将所有的long变成string
		 *  因为js中得数字类型不能包含所有的java long值 ,因为精度会缺失
		 *//*
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		return super.registerModule(simpleModule);

	}*/
}
