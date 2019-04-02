package ch.pbu.rf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link MathUtil}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: MathUtil")
class MathUtilTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	private static final BigDecimal DELTA = new BigDecimal("0.001");

	@Test
	@DisplayName(value = "Test: toRadians(BigDecimal, MathContext)")
	void testToRadians() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.toRadians(null, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.toRadians(DELTA, null));
		
		Testing.assertEquals(new BigDecimal("0.0174533"), MathUtil.toRadians(new BigDecimal("1"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("0.0872665"), MathUtil.toRadians(new BigDecimal("5"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("0.2617993"), MathUtil.toRadians(new BigDecimal("15"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("0.7853981"), MathUtil.toRadians(new BigDecimal("45"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("1.5707963"), MathUtil.toRadians(new BigDecimal("90"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("3.1415926"), MathUtil.toRadians(new BigDecimal("180"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("4.7123889"), MathUtil.toRadians(new BigDecimal("270"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("6.2831853"), MathUtil.toRadians(new BigDecimal("360"), MC), DELTA, MC);
	}
	

	@Test
	@DisplayName(value = "Test: toDegrees(BigDecimal, MathContext)")
	void testToDegrees() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.toDegrees(null, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.toDegrees(DELTA, null));
		
		Testing.assertEquals(new BigDecimal("1"), MathUtil.toDegrees(new BigDecimal("0.0174533"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("5"), MathUtil.toDegrees(new BigDecimal("0.0872665"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("15"), MathUtil.toDegrees(new BigDecimal("0.2617993"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("45"), MathUtil.toDegrees(new BigDecimal("0.7853981"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("90"), MathUtil.toDegrees(new BigDecimal("1.5707963"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("180"), MathUtil.toDegrees(new BigDecimal("3.1415926"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("270"), MathUtil.toDegrees(new BigDecimal("4.7123889"), MC), DELTA, MC);
		Testing.assertEquals(new BigDecimal("360"), MathUtil.toDegrees(new BigDecimal("6.2831853"), MC), DELTA, MC);
	}

	@Test
	@DisplayName(value = "Test: replaceZeroWithNearlyZero(BigDecimal, MathContext)")
	void testReplaceZeroWithNearlyZero() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.replaceZeroWithNearlyZero(null, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.replaceZeroWithNearlyZero(DELTA, null));
		
		BigDecimal value = MathUtil.replaceZeroWithNearlyZero(BigDecimal.ZERO, MC);
		Assertions.assertEquals(1, value.compareTo(BigDecimal.ZERO));
		Assertions.assertEquals(-1, value.compareTo(BigDecimal.ONE));
	}
	

	@Test
	@DisplayName(value = "Test: createMatrix(BigDecimal, int, int)")
	void testCreateMatrix() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.createMatrix(null, 0, 0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.createMatrix(BigDecimal.ZERO, -1, 0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.createMatrix(BigDecimal.ZERO, 0, -1));
		
		BigDecimal[][] matrix1 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
		Assertions.assertEquals(1, matrix1.length);
		Assertions.assertEquals(1, matrix1[0].length);
		Assertions.assertEquals(BigDecimal.ZERO, matrix1[0][0]);
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		Assertions.assertEquals(2, matrix2.length);
		Assertions.assertEquals(2, matrix2[0].length);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2[0][0]);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2[0][1]);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2[1][0]);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2[1][1]);
	}

	@Test
	@DisplayName(value = "Test: calculate3x3Inverse(BigDecimal[][], MathContext)")
	void testCalculate3x3Inverse() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate3x3Inverse(null, MC));
		
		BigDecimal[][] matrix1 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate3x3Inverse(matrix1, null));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 2);
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculate3x3Inverse(matrix2, MC));
		
		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 4);
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculate3x3Inverse(matrix3, MC));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		BigDecimal[][] matrix4Inverse = MathUtil.calculate3x3Inverse(matrix4, MC);
		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[0][0], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[0][1], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[0][2], MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[1][0], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[1][1], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[1][2], MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[2][0], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[2][1], MC);		
		Testing.assertEquals(BigDecimal.ZERO, matrix4Inverse[2][2], MC);
		
		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		matrix5[0][0] = MathUtil.bd("1", MC);
		matrix5[0][1] = MathUtil.bd("1", MC);
		matrix5[0][2] = MathUtil.bd("1", MC);
		matrix5[1][0] = MathUtil.bd("0", MC);
		matrix5[1][1] = MathUtil.bd("1", MC);
		matrix5[1][2] = MathUtil.bd("2", MC);
		matrix5[2][0] = MathUtil.bd("0", MC);
		matrix5[2][1] = MathUtil.bd("1", MC);
		matrix5[2][2] = MathUtil.bd("1", MC);
		
		BigDecimal[][] matrix5Inverse = MathUtil.calculate3x3Inverse(matrix5, MC);
		Testing.assertEquals(MathUtil.bd("1", MC), matrix5Inverse[0][0], MC);		
		Testing.assertEquals(MathUtil.bd("0", MC), matrix5Inverse[0][1], MC);		
		Testing.assertEquals(MathUtil.bd("-1", MC), matrix5Inverse[0][2], MC);
		Testing.assertEquals(MathUtil.bd("0", MC), matrix5Inverse[1][0], MC);		
		Testing.assertEquals(MathUtil.bd("-1", MC), matrix5Inverse[1][1], MC);		
		Testing.assertEquals(MathUtil.bd("2", MC), matrix5Inverse[1][2], MC);
		Testing.assertEquals(MathUtil.bd("0", MC), matrix5Inverse[2][0], MC);		
		Testing.assertEquals(MathUtil.bd("1", MC), matrix5Inverse[2][1], MC);		
		Testing.assertEquals(MathUtil.bd("-1", MC), matrix5Inverse[2][2], MC);

		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		matrix6[0][0] = MathUtil.bd("1", MC);
		matrix6[0][1] = MathUtil.bd("0", MC);
		matrix6[0][2] = MathUtil.bd("5", MC);
		matrix6[1][0] = MathUtil.bd("2", MC);
		matrix6[1][1] = MathUtil.bd("1", MC);
		matrix6[1][2] = MathUtil.bd("6", MC);
		matrix6[2][0] = MathUtil.bd("3", MC);
		matrix6[2][1] = MathUtil.bd("4", MC);
		matrix6[2][2] = MathUtil.bd("0", MC);
		
		BigDecimal[][] matrix6Inverse = MathUtil.calculate3x3Inverse(matrix6, MC);
		Testing.assertEquals(MathUtil.bd("-24", MC), matrix6Inverse[0][0], MC);		
		Testing.assertEquals(MathUtil.bd("20", MC), matrix6Inverse[0][1], MC);		
		Testing.assertEquals(MathUtil.bd("-5", MC), matrix6Inverse[0][2], MC);
		Testing.assertEquals(MathUtil.bd("18", MC), matrix6Inverse[1][0], MC);		
		Testing.assertEquals(MathUtil.bd("-15", MC), matrix6Inverse[1][1], MC);		
		Testing.assertEquals(MathUtil.bd("4", MC), matrix6Inverse[1][2], MC);
		Testing.assertEquals(MathUtil.bd("5", MC), matrix6Inverse[2][0], MC);		
		Testing.assertEquals(MathUtil.bd("-4", MC), matrix6Inverse[2][1], MC);		
		Testing.assertEquals(MathUtil.bd("1", MC), matrix6Inverse[2][2], MC);
		
	}

	@Test
	@DisplayName(value = "Test: calculate3x3Determinant(BigDecimal[][] MathContext)")
	void testCalculate3x3Determinant() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate3x3Determinant(null, MC));

		BigDecimal[][] matrix1 = new BigDecimal[][] {};
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate3x3Determinant(matrix1, null));

		BigDecimal[][] matrix2 = new BigDecimal[][] { 
			{ BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO },
			{ BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO },  
			{ BigDecimal.ZERO, BigDecimal.ZERO } };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculate3x3Determinant(matrix2, MC));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		matrix3[0][0] = MathUtil.bd("1", MC);
		matrix3[0][1] = MathUtil.bd("2", MC);
		matrix3[0][2] = MathUtil.bd("3", MC);
		matrix3[1][0] = MathUtil.bd("4", MC);
		matrix3[1][1] = MathUtil.bd("5", MC);
		matrix3[1][2] = MathUtil.bd("6", MC);
		matrix3[2][0] = MathUtil.bd("7", MC);
		matrix3[2][1] = MathUtil.bd("8", MC);
		matrix3[2][2] = MathUtil.bd("9", MC);
		Testing.assertEquals(MathUtil.bd("0", MC), MathUtil.calculate3x3Determinant(matrix3, MC), MC);
		
		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		matrix4[0][0] = MathUtil.bd("2", MC);
		matrix4[0][1] = MathUtil.bd("5", MC);
		matrix4[0][2] = MathUtil.bd("2", MC);
		matrix4[1][0] = MathUtil.bd("3", MC);
		matrix4[1][1] = MathUtil.bd("-3", MC);
		matrix4[1][2] = MathUtil.bd("1", MC);
		matrix4[2][0] = MathUtil.bd("1", MC);
		matrix4[2][1] = MathUtil.bd("4", MC);
		matrix4[2][2] = MathUtil.bd("-4", MC);
		Testing.assertEquals(MathUtil.bd("111", MC), MathUtil.calculate3x3Determinant(matrix4, MC), MC);
	}

	@Test
	@DisplayName(value = "Test: calculate2x2Determinant(BigDecimal, BigDecimal, BigDecimal, BigDecimal, MathContext)")
	void testCalculate2x2DeterminantValues() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(
			null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, MC));
		
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(
			BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO, MC));
		
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(
			BigDecimal.ZERO, BigDecimal.ZERO, null, BigDecimal.ZERO, MC));
		
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(
			BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null, MC));
		
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(
			BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null));
		
		Testing.assertEquals(MathUtil.bd("0", MC), MathUtil.calculate2x2Determinant(
			MathUtil.bd("2", MC), MathUtil.bd("2", MC), MathUtil.bd("2", MC), MathUtil.bd("2", MC), MC), MC);

		Testing.assertEquals(MathUtil.bd("-13", MC), MathUtil.calculate2x2Determinant(
			MathUtil.bd("3", MC), MathUtil.bd("2", MC), MathUtil.bd("2", MC), MathUtil.bd("-3", MC), MC), MC);

		Testing.assertEquals(MathUtil.bd("-33", MC), MathUtil.calculate2x2Determinant(
			MathUtil.bd("-4", MC), MathUtil.bd("-2", MC), MathUtil.bd("-2.5", MC), MathUtil.bd("7", MC), MC), MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculate2x2Determinant(BigDecimal[][], MathContext)")
	void testCalculate2x2DeterminantMatrix() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(null, MC));

		BigDecimal[][] matrix1 = new BigDecimal[][] {};
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculate2x2Determinant(matrix1, null));

		BigDecimal[][] matrix2 = new BigDecimal[][] { 
			{ BigDecimal.ZERO, BigDecimal.ZERO  }, 
			{ BigDecimal.ZERO } };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculate2x2Determinant(matrix2, MC));
		
		BigDecimal[][] matrix3 = new BigDecimal[][] { 
			{ MathUtil.bd("2", MC), MathUtil.bd("2", MC) }, 
			{ MathUtil.bd("2", MC), MathUtil.bd("2", MC) } };
		Testing.assertEquals(MathUtil.bd("0", MC), MathUtil.calculate2x2Determinant(matrix3, MC), MC);

		BigDecimal[][] matrix4 = new BigDecimal[][] { 
			{ MathUtil.bd("3", MC), MathUtil.bd("2", MC) }, 
			{ MathUtil.bd("2", MC), MathUtil.bd("-3", MC) } };
		Testing.assertEquals(MathUtil.bd("-13", MC), MathUtil.calculate2x2Determinant(matrix4, MC), MC);

		BigDecimal[][] matrix5 = new BigDecimal[][] { 
			{ MathUtil.bd("-4", MC), MathUtil.bd("-2", MC) }, 
			{ MathUtil.bd("-2.5", MC), MathUtil.bd("7", MC) } };
		Testing.assertEquals(MathUtil.bd("-33", MC), MathUtil.calculate2x2Determinant(matrix5, MC), MC);
	}
	
	@Test
	@DisplayName(value = "Test: isValid(BigDecimal[][], int, int)")
	void testIsValidMatrix() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.validateMatrix(null, new MatrixValidatorAny()));

		BigDecimal[][] matrix1 = new BigDecimal[][] { { BigDecimal.ONE } };
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.validateMatrix(matrix1, null));

		BigDecimal[][] matrix2 = new BigDecimal[][] { null };
		Assertions.assertEquals(false, MathUtil.validateMatrix(matrix2, new MatrixValidatorAny()));
		
		BigDecimal[][] matrix3 = new BigDecimal[][] { { null } };
		Assertions.assertEquals(false, MathUtil.validateMatrix(matrix3, new MatrixValidatorAny()));
		
		BigDecimal[][] matrix4 = new BigDecimal[][] { { BigDecimal.ONE } };
		Assertions.assertEquals(true, MathUtil.validateMatrix(matrix4, new MatrixValidatorAny()));
		
		BigDecimal[][] matrix5 = new BigDecimal[][] { { BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE } };
		Assertions.assertEquals(false, MathUtil.validateMatrix(matrix5, new MatrixValidatorAny()));
		
		BigDecimal[][] matrix6 = new BigDecimal[][] { { BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE, BigDecimal.ONE } };
		Assertions.assertEquals(true, MathUtil.validateMatrix(matrix6, new MatrixValidatorAny()));
	}
}
