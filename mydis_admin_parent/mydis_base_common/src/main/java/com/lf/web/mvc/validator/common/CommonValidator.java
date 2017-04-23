package com.lf.web.mvc.validator.common;


import com.lf.helper.ValidatorHelper;
import com.lf.web.mvc.validator.common.annotation.CheckType;
import com.lf.web.mvc.validator.common.annotation.Validator;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@SuppressWarnings("all")
public class CommonValidator implements ConstraintValidator<Validator, String>{

	private String value;
	private CheckType checkType;
	private String message;

	public void initialize(Validator validator) {
		//把注解validator的属性值传递
		this.value = validator.value();
		this.checkType = validator.checkType();
		this.message = validator.message();
		
	}

	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		//值为空则不校验
		if(StringUtils.isBlank(value)){
			return true;
		}
		if(CheckType.EMAIL.value()==checkType.value()){
			return ValidatorHelper.isEmail(value);
		}else if(CheckType.IDCARD.value()==checkType.value()){
			return ValidatorHelper.isIdCard(value);
		}else if(CheckType.MOBILE.value()==checkType.value()){
			return ValidatorHelper.isMobile(value);
		}else if(CheckType.TELEPHONE.value()==checkType.value()){
			return ValidatorHelper.isTelePhone(value);
		}else if(CheckType.POST.value()==checkType.value()){
			return ValidatorHelper.isPost(value);
		}else if(CheckType.INT.value()==checkType.value()){
			return ValidatorHelper.isInt(value);
		}else if(CheckType.FLOAT.value()==checkType.value()){
			return ValidatorHelper.isFloat(value);
		}else if(CheckType.CHINESE.value()==checkType.value()){
			return ValidatorHelper.isChinese(value);
		}else if(CheckType.DATE.value()==checkType.value()){
			return ValidatorHelper.isDate(value);
		}else if(CheckType.URL.value()==checkType.value()){
			return ValidatorHelper.isURL(value);
		}else if(CheckType.IP.value()==checkType.value()){
			return ValidatorHelper.isIp(value);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(CheckType.CHINESE.value());
	}
	
	
}
