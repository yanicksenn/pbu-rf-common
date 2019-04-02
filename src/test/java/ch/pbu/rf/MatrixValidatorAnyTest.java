package ch.pbu.rf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link MatrixValidatorAny}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: MatrixValidatorAny")
class MatrixValidatorAnyTest {
	
	@Test
	@DisplayName(value = "Test: MatrixValidatorAny()")
	void testMatrixValidatorAny() {
		Assertions.assertDoesNotThrow(() -> new MatrixValidatorAny());
	}
	
	@Test
	@DisplayName(value = "Test: validate(BigDecimal[][])")
	void testValidate() {
		MatrixValidatorAny validator = new MatrixValidatorAny();
		
		BigDecimal[][] matrix1 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 2);
		Assertions.assertEquals(true, validator.validate(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 3);
		Assertions.assertEquals(true, validator.validate(matrix2));
		
		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 5, 6);
		Assertions.assertEquals(true, validator.validate(matrix3));
	}
}
