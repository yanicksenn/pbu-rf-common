package ch.pbu.rf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link MatrixValidatorExact}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: MatrixValidatorExact")
class MatrixValidatorExactTest {
	
	@Test
	@DisplayName(value = "Test: MatrixValidatorExact()")
	void testMatrixValidatorExact() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new MatrixValidatorExact(0, 1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new MatrixValidatorExact(1, 0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new MatrixValidatorExact(0));
		
		Assertions.assertDoesNotThrow(() -> new MatrixValidatorExact(1));
	}
	
	@Test
	@DisplayName(value = "Test: validate(BigDecimal[][])")
	void testValidate() {
		MatrixValidatorExact validator = new MatrixValidatorExact(2);
		
		BigDecimal[][] matrix1 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
		Assertions.assertEquals(false, validator.validate(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 2);
		Assertions.assertEquals(false, validator.validate(matrix2));
		
		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 1);
		Assertions.assertEquals(false, validator.validate(matrix3));
		
		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		Assertions.assertEquals(true, validator.validate(matrix4));
		
		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 3);
		Assertions.assertEquals(false, validator.validate(matrix5));
		
		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 2);
		Assertions.assertEquals(false, validator.validate(matrix6));
		
		BigDecimal[][] matrix7 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 3);
		Assertions.assertEquals(false, validator.validate(matrix7));
	}
}
