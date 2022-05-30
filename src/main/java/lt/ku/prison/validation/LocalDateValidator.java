package lt.ku.prison.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocalDateValidator implements ConstraintValidator<LocalDateConstraint, LocalDate>{

	@Override
	public void initialize(LocalDateConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		boolean valid = true;
		if(value == null) valid = false;
		return valid;
	}
	
}
