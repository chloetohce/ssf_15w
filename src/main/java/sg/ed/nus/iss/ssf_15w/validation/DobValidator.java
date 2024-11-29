package sg.ed.nus.iss.ssf_15w.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<ValidDob, LocalDate> {

    @Override
    public void initialize(ValidDob constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext constraintValidatorContext) {
        if (dob == null) {
            return false;
        }

        LocalDate now = LocalDate.now();
        Period diff = Period.between(dob, now);

        return diff.getYears() >= 10 && diff.getYears() <=100;
    }
    
}
