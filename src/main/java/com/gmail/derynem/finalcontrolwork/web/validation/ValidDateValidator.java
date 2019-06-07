package com.gmail.derynem.finalcontrolwork.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
    private final static int DAYS = 365;

    public boolean isValid(String date, ConstraintValidatorContext context) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime discountDate = LocalDateTime.parse(date);
        Duration duration = Duration.between(now, discountDate);
        long difference = Math.abs(duration.toDays());
        return difference >= DAYS;
    }
}