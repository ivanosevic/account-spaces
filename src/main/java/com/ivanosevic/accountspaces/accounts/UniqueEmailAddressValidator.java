package com.ivanosevic.accountspaces.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailAddressValidator implements ConstraintValidator<UniqueEmailAddress, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(UniqueEmailAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountRepository.findByEmailNoRelations(value).isEmpty();
    }
}
