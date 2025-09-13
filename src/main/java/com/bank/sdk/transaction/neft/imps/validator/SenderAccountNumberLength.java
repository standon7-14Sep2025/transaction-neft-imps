package com.bank.sdk.transaction.neft.imps.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SenderAccountNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
//@interface is one way of creating your own annotation having your custom validation logic
public @interface SenderAccountNumberLength {
    String message() default "Invalid account number length";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

