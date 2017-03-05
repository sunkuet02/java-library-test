package com.sunkuet02.todoapplication.validators;

import com.sunkuet02.todoapplication.models.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by sun on 3/5/17.
 */
public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "username", "empty");
    }
}
