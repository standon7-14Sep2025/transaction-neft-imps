package com.bank.sdk.transaction.neft.imps.validator;

import com.bank.sdk.transaction.neft.imps.config.AccountSenderConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SenderAccountNumberValidator implements ConstraintValidator<SenderAccountNumberLength, String> {

    @Autowired
    private AccountSenderConfig config;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        int len = value.length();
        return len >= config.getMinLength() && len <= config.getMaxLength();
    }
}
