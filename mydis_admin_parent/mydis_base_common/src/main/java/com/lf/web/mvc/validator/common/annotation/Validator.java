package com.lf.web.mvc.validator.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lf.web.mvc.validator.common.CommonValidator;


@Target({ElementType.FIELD, ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Constraint(validatedBy = {CommonValidator.class})  
public @interface Validator {
	
	String value() default "";
	
	CheckType checkType();
	
	//默认错误消息
	String message() default "内容格式不正确";   
    
	//分组
    Class<?>[] groups() default {};   
    
    //负载
    Class<? extends Payload>[] payload() default {};
    
    //指定多个时使用
    @Target({ElementType.FIELD, ElementType.METHOD})   
    @Retention(RetentionPolicy.RUNTIME)   
    public @interface List {
    	Validator[] values();
    }
   

}
