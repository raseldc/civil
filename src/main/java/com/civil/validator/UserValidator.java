package com.civil.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;//User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        //ValidationUtils.rejectIfEmpty(errors, "gender", "gender.required");
        //ValidationUtils.rejectIfEmpty(errors, "country", "country.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aboutYou", "aboutYou.required");
        // User user = (User) target;

    }

}
