package com.sousaz.crudspring.enums.validation;

import java.util.Arrays;
import java.util.List;

import com.sousaz.crudspring.enums.Category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerTypeSubSetValidator implements ConstraintValidator<CustomerTypeSubset, Object> {
    private List<String> subset;

    @Override
    public void initialize(CustomerTypeSubset constraint) {
        this.subset = Arrays.asList(constraint.anyOf());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }

        if (value instanceof Category) {
            return subset.contains(((Category) value).name());
        } else if (value instanceof String) {
            return subset.contains(value);
        }

        return false; // Incompatible type
    }
}
