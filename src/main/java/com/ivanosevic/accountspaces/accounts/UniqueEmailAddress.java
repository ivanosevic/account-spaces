package com.ivanosevic.accountspaces.accounts;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailAddress {
    String message() default "Email Address is already associated with another account. Please, use a different Email Address.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
