package com.sunkuet02.todoapplication.validators;

import com.sunkuet02.todoapplication.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by sun on 3/5/17.
 */
public class UserValidators implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "Error:1", "Empty Field");
    }
}
