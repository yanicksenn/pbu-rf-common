package ch.pbu.rf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Represents the Abstract-Test-Case.
 * 
 * @author Yanick Senn
 */
public abstract class AbstractTestCase {
	private final Validator validator;
	
	public AbstractTestCase() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	

	public void assertValid(Object object) {
		assertEquals(0, validator.validate(object).size());
	}
	
	public void assertInvalid(Object object) {
		assertNotEquals(0, validator.validate(object).size());
	}
}
