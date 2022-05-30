package lt.ku.prison.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<DateTimeConstraint, String>{

	@Override
	public void initialize(DateTimeConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			LocalDate.parse(value, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
			return true;
		} catch(DateTimeParseException e) {
			return false;
		}
	}
	
}
