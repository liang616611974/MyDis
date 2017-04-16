package com.lf.web.mvc.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport{
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    converters.add(converter());
	    addDefaultHttpMessageConverters(converters);
	}
	
	
	/**
	 * 注册 jackson处理java long值 转 string 输出
	 * @return
	 */
	@Bean
	MappingJackson2HttpMessageConverter converter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		ObjectMapper objectMapper = new ObjectMapper();
	    /**
	     * 序列换成json时,将所有的long变成string
	     * 因为js中得数字类型不能包含所有的java long值,会失去精度
	     */
	    SimpleModule simpleModule = new SimpleModule();
	    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
	    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
	    simpleModule.addSerializer(long.class, ToStringSerializer.instance);
	    objectMapper.registerModule(simpleModule);
	    converter.setObjectMapper(objectMapper);
		
		return converter;
		
	}
	
	
}
