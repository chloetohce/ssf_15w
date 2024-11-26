package sg.ed.nus.iss.ssf_13w.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD) // Specifies where your annotation can be applied
@Retention(RetentionPolicy.RUNTIME) // Specifies when the custom validation information should be available
@Constraint(validatedBy = DobValidator.class) // Specifies the validator class taht will implement the custom validation logic
public @interface ValidDob {
    String message() default "Age must be at least 10 y/o and not older than 100 y/o.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
