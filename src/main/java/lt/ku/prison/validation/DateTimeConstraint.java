package lt.ku.prison.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
@Documented
public @interface DateTimeConstraint {
	String message() default "Neteisinga data ir laikas";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
