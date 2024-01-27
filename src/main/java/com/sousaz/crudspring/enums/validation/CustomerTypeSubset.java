package com.sousaz.crudspring.enums.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.sousaz.crudspring.enums.Category;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CustomerTypeSubSetValidator.class, StringCustomerTypeSubsetValidator.class})
public @interface CustomerTypeSubset {
    Category[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

