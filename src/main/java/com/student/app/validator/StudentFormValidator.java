package com.student.app.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.model.Student;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentFormValidator implements Validator {

	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return Student.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "country.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "acceptForm", "acceptForm.required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "standard", "standard.required");
		
		
		Student stu = (Student) obj;
		if(stu.getCountry().equals("NONE")){
			errors.rejectValue("country", "country.required");
		}
//		if(stu.getGender()==null){
//			errors.rejectValue("gender", "gender.required");
//		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		
	}
}