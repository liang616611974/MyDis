package com.lf.web.mvc.interceptor.token.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)   
@Retention(RetentionPolicy.RUNTIME) 
public @interface Token {
	boolean save() default false;
	boolean valid() default false;
}
