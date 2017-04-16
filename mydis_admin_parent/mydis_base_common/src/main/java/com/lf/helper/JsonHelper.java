package com.lf.helper;


import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
* <p>Title: JsonHelper.java<／p>
* <p>Description: json操作帮助类<／p>
* @author Liangfeng
* @date 2016-12-23
* @version 1.0
 */
@SuppressWarnings("all")
public class JsonHelper {
	
	private static ObjectMapper mapper = null;
	
	static {
		//1.初始化
		mapper = new ObjectMapper();
		//2.设置属性
		//忽略空属性
		//mapper.setSerializationInclusion(Include.NON_EMPTY);  
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 字段和值都加引号
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 数字也加引号
		mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		// 该特性允许parser可以识别"Not-a-Number" (NaN)标识集合作为一个合法的浮点数。
		mapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		// 空值处理为空串
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
				jg.writeString("");
			}
		});
	}
	
	/**
	 * 私有化构造函数
	 */
	private JsonHelper(){};
	
	/**
	 * 对象序列化为json字符串
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object object) throws Exception{
		return mapper.writeValueAsString(object);
	}
	
	/**
	 * json字符串反序列化为对象
	 * @param json
	 * @param objClass 对象类型，例如：Obj.class
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObj(String json, Class<T> objClass) throws Exception {
		return mapper.readValue(json, objClass);
	}
	
	/**
	 * json字符串反序列化为对象
	 * @param json
	 * @param typeReference 对象类型，例如：  typeReference = new TypeReference<AAAA<BBB>>(){};
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObj(String json, TypeReference<T> typeReference) throws Exception {
		return (T) mapper.readValue(json, typeReference);
	}
	
	/**
	 * json Array字符串转换成集合
	 * @param json json Array字符串
	 * @param collectionClass 集合类型，例如：ArrayList.class
	 * @param elementClasses 集合中的对象类型，例如 Obj.class
	 * @return  例如：List<Obj>
	 * @throws Exception
	 */
	public static Object toObjs(String json,Class<?> collectionClass, Class<?>... elementClasses) throws Exception {   
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses); 
        return mapper.readValue(json, javaType); 
    }
	
	/**
	 * Json Map字符串转换成Map集合
	 * @param json jsonMap字符串
	 * @param mapClass map类型，HashMap.class
	 * @param keyClass key类型 key.class
	 * @param valueClass value类型 value.class
	 * @return
	 * @throws Exception
	 */
	public static Object toMap(String json, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) throws Exception{
		JavaType javaType = mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
		return mapper.readValue(json, javaType);
	}
	
	public static void main(String[] args) throws Exception {

	}
	
}
