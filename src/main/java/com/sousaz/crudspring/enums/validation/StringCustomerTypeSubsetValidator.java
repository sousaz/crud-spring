package com.sousaz.crudspring.enums.validation;

import java.util.Arrays;
import java.util.List;

import com.sousaz.crudspring.enums.Category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringCustomerTypeSubsetValidator implements ConstraintValidator<CustomerTypeSubset, String> {
    private List<String> subset;

    @Override
    public void initialize(CustomerTypeSubset constraint) {
        this.subset = Arrays.asList(constraint.anyOf());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || subset.contains(value);
    }
}

