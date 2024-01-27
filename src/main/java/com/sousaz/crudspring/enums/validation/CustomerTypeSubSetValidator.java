package com.sousaz.crudspring.enums.validation;

import java.util.Arrays;

import com.sousaz.crudspring.enums.Category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerTypeSubSetValidator implements ConstraintValidator<CustomerTypeSubset, Category> {
    private Category[] subset;

    @Override
    public void initialize(CustomerTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Category value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
