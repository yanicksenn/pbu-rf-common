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
	@DisplayName(value = "Test: createMatrix(BigDecimal, int, int)")
	void testCreateMatrix() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.createMatrix(null, 1, 1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.createMatrix(BigDecimal.ZERO, 0, 1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.createMatrix(BigDecimal.ZERO, 1, 0));
		
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
	@DisplayName(value = "Test: calculateWidth(BigDecimal[][])")
	void testCalculateWidth() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculateWidth(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateWidth(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1);
		Assertions.assertEquals(1, MathUtil.calculateWidth(matrix2));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 2);
		Assertions.assertEquals(2, MathUtil.calculateWidth(matrix3));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		Assertions.assertEquals(3, MathUtil.calculateWidth(matrix4));
	}
	
	@Test
	@DisplayName(value = "Test: calculateHeight(BigDecimal[][])")
	void testCalculateHeight() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculateHeight(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateHeight(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1);
		Assertions.assertEquals(1, MathUtil.calculateHeight(matrix2));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 2);
		Assertions.assertEquals(2, MathUtil.calculateHeight(matrix3));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3);
		Assertions.assertEquals(3, MathUtil.calculateHeight(matrix4));
	}
	
	@Test
	@DisplayName(value = "Test: isRowVector(BigDecimal[][])")
	void testIsRowVector() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.isRowVector(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.isRowVector(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
		Assertions.assertEquals(true, MathUtil.isRowVector(matrix2));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 1);
		Assertions.assertEquals(true, MathUtil.isRowVector(matrix3));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 1);
		Assertions.assertEquals(true, MathUtil.isRowVector(matrix4));
		
		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 2);
		Assertions.assertEquals(false, MathUtil.isRowVector(matrix5));

		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		Assertions.assertEquals(false, MathUtil.isRowVector(matrix6));
	}
	
	@Test
	@DisplayName(value = "Test: isColumnVector(BigDecimal[][])")
	void testIsColumnVector() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.isColumnVector(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.isColumnVector(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
		Assertions.assertEquals(true, MathUtil.isColumnVector(matrix2));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 2);
		Assertions.assertEquals(true, MathUtil.isColumnVector(matrix3));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 3);
		Assertions.assertEquals(true, MathUtil.isColumnVector(matrix4));
		
		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 1);
		Assertions.assertEquals(false, MathUtil.isColumnVector(matrix5));

		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		Assertions.assertEquals(false, MathUtil.isColumnVector(matrix6));
	}
	
	@Test
	@DisplayName(value = "Test: isQuadratic(BigDecimal[][])")
	void testIsQuadratic() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.isQuadratic(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.isQuadratic(matrix1));
		
		BigDecimal[][] matrix2 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
		Assertions.assertEquals(true, MathUtil.isQuadratic(matrix2));

		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 2);
		Assertions.assertEquals(false, MathUtil.isQuadratic(matrix3));

		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 3);
		Assertions.assertEquals(false, MathUtil.isQuadratic(matrix4));

		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 1);
		Assertions.assertEquals(false, MathUtil.isQuadratic(matrix5));

		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 1);
		Assertions.assertEquals(false, MathUtil.isQuadratic(matrix6));
		
		BigDecimal[][] matrix7 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		Assertions.assertEquals(true, MathUtil.isQuadratic(matrix7));
		
		BigDecimal[][] matrix8 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 3);
		Assertions.assertEquals(true, MathUtil.isQuadratic(matrix8));
	}
	
	@Test
	@DisplayName(value = "Test: calculateProduct(BigDecimal[][], BigDecimal[][], MathContext)")
	void testCalculateProduct() {
		BigDecimal[][] matrix1 = MathUtil.createMatrix(BigDecimal.ZERO, 1);
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculateProduct(null, matrix1, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculateProduct(matrix1, null, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.calculateProduct(matrix1, matrix1, null));

		BigDecimal[][] matrix2 = new BigDecimal[][] { null };
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateProduct(matrix2, matrix1, MC));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateProduct(matrix1, matrix2, MC));
		
		BigDecimal[][] matrix3 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 2);
		BigDecimal[][] matrix4 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 3);
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateProduct(matrix3, matrix4, MC));
		
		BigDecimal[][] matrix5 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 4);
		BigDecimal[][] matrix6 = MathUtil.createMatrix(BigDecimal.ZERO, 4, 3);
		Assertions.assertDoesNotThrow(() -> MathUtil.calculateProduct(matrix5, matrix6, MC));

		BigDecimal[][] matrix7 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 3);
		BigDecimal[][] matrix8 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 2);
		BigDecimal[][] matrix9 = MathUtil.calculateProduct(matrix7, matrix8, MC);
		Assertions.assertEquals(3, MathUtil.calculateWidth(matrix9));
		Assertions.assertEquals(3, MathUtil.calculateHeight(matrix9));

		BigDecimal[][] matrix10 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 5);
		BigDecimal[][] matrix11 = MathUtil.createMatrix(BigDecimal.ZERO, 4, 3);
		BigDecimal[][] matrix12 = MathUtil.calculateProduct(matrix10, matrix11, MC);
		Assertions.assertEquals(4, MathUtil.calculateWidth(matrix12));
		Assertions.assertEquals(5, MathUtil.calculateHeight(matrix12));

		BigDecimal[][] matrix13 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 2);
		matrix13[0][0] = MathUtil.bd("4", MC);
		matrix13[0][1] = MathUtil.bd("2", MC);
		matrix13[0][2] = MathUtil.bd("1", MC);
		matrix13[1][0] = MathUtil.bd("0", MC);
		matrix13[1][1] = MathUtil.bd("-2", MC);
		matrix13[1][2] = MathUtil.bd("4", MC);
		
		BigDecimal[][] matrix14 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 3);
		matrix14[0][0] = MathUtil.bd("1", MC);
		matrix14[0][1] = MathUtil.bd("2", MC);
		matrix14[0][2] = MathUtil.bd("3", MC);
		matrix14[1][0] = MathUtil.bd("0", MC);
		matrix14[1][1] = MathUtil.bd("4", MC);
		matrix14[1][2] = MathUtil.bd("6", MC);
		matrix14[2][0] = MathUtil.bd("2", MC);
		matrix14[2][1] = MathUtil.bd("-1", MC);
		matrix14[2][2] = MathUtil.bd("8", MC);
		
		BigDecimal[][] matrix15 = MathUtil.calculateProduct(matrix13, matrix14, MC);
		Testing.assertEquals(MathUtil.bd("6", MC), matrix15[0][0], MC);
		Testing.assertEquals(MathUtil.bd("15", MC), matrix15[0][1], MC);
		Testing.assertEquals(MathUtil.bd("32", MC), matrix15[0][2], MC);
		Testing.assertEquals(MathUtil.bd("8", MC), matrix15[1][0], MC);
		Testing.assertEquals(MathUtil.bd("-12", MC), matrix15[1][1], MC);
		Testing.assertEquals(MathUtil.bd("20", MC), matrix15[1][2], MC);

		BigDecimal[][] matrix16 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 2);
		matrix16[0][0] = MathUtil.bd("1", MC);
		matrix16[0][1] = MathUtil.bd("2", MC);
		matrix16[0][2] = MathUtil.bd("3", MC);
		matrix16[1][0] = MathUtil.bd("4", MC);
		matrix16[1][1] = MathUtil.bd("5", MC);
		matrix16[1][2] = MathUtil.bd("6", MC);
		
		BigDecimal[][] matrix17 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 3);
		matrix17[0][0] = MathUtil.bd("7", MC);
		matrix17[1][0] = MathUtil.bd("8", MC);
		matrix17[2][0] = MathUtil.bd("9", MC);
		
		BigDecimal[][] matrix18 = MathUtil.calculateProduct(matrix16, matrix17, MC);
		Testing.assertEquals(MathUtil.bd("50", MC), matrix18[0][0], MC);
		Testing.assertEquals(MathUtil.bd("122", MC), matrix18[1][0], MC);

		BigDecimal[][] matrix19 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 1);
		matrix19[0][0] = MathUtil.bd("7", MC);
		matrix19[0][1] = MathUtil.bd("8", MC);
		matrix19[0][2] = MathUtil.bd("9", MC);
		
		BigDecimal[][] matrix20 = MathUtil.createMatrix(BigDecimal.ZERO, 2, 3);
		matrix20[0][0] = MathUtil.bd("1", MC);
		matrix20[0][1] = MathUtil.bd("2", MC);
		matrix20[1][0] = MathUtil.bd("3", MC);
		matrix20[1][1] = MathUtil.bd("4", MC);
		matrix20[2][0] = MathUtil.bd("5", MC);
		matrix20[2][1] = MathUtil.bd("6", MC);
		
		BigDecimal[][] matrix21 = MathUtil.calculateProduct(matrix19, matrix20, MC);
		Testing.assertEquals(MathUtil.bd("76", MC), matrix21[0][0], MC);
		Testing.assertEquals(MathUtil.bd("100", MC), matrix21[0][1], MC);

		BigDecimal[][] matrix22 = MathUtil.createMatrix(BigDecimal.ZERO, 3, 1);
		matrix22[0][0] = MathUtil.bd("1", MC);
		matrix22[0][1] = MathUtil.bd("2", MC);
		matrix22[0][2] = MathUtil.bd("3", MC);
		
		BigDecimal[][] matrix23 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 3);
		matrix23[0][0] = MathUtil.bd("4", MC);
		matrix23[1][0] = MathUtil.bd("5", MC);
		matrix23[2][0] = MathUtil.bd("6", MC);
		
		BigDecimal[][] matrix24 = MathUtil.calculateProduct(matrix22, matrix23, MC);
		Testing.assertEquals(MathUtil.bd("32", MC), matrix24[0][0], MC);

		BigDecimal[][] matrix25 = MathUtil.createMatrix(BigDecimal.ZERO, 1, 3);
		matrix25[0][0] = MathUtil.bd("1", MC);
		matrix25[1][0] = MathUtil.bd("2", MC);
		matrix25[2][0] = MathUtil.bd("3", MC);
		
		BigDecimal[][] matrix26 = MathUtil.createMatrix(BigDecimal.ZERO, 4, 1);
		matrix26[0][0] = MathUtil.bd("4", MC);
		matrix26[0][1] = MathUtil.bd("5", MC);
		matrix26[0][2] = MathUtil.bd("6", MC);
		matrix26[0][3] = MathUtil.bd("7", MC);
		
		BigDecimal[][] matrix27 = MathUtil.calculateProduct(matrix25, matrix26, MC);
		Testing.assertEquals(MathUtil.bd("4", MC), matrix27[0][0], MC);
		Testing.assertEquals(MathUtil.bd("5", MC), matrix27[0][1], MC);
		Testing.assertEquals(MathUtil.bd("6", MC), matrix27[0][2], MC);
		Testing.assertEquals(MathUtil.bd("7", MC), matrix27[0][3], MC);
		Testing.assertEquals(MathUtil.bd("8", MC), matrix27[1][0], MC);
		Testing.assertEquals(MathUtil.bd("10", MC), matrix27[1][1], MC);
		Testing.assertEquals(MathUtil.bd("12", MC), matrix27[1][2], MC);
		Testing.assertEquals(MathUtil.bd("14", MC), matrix27[1][3], MC);
		Testing.assertEquals(MathUtil.bd("15", MC), matrix27[2][0], MC);
		Testing.assertEquals(MathUtil.bd("18", MC), matrix27[2][1], MC);
		Testing.assertEquals(MathUtil.bd("21", MC), matrix27[2][2], MC);
		Testing.assertEquals(MathUtil.bd("24", MC), matrix27[2][3], MC);
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
	@DisplayName(value = "Test: validateMatrix(BigDecimal[][], MatrixValidator)")
	void testValidateMatrix() {
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

	@Test
	@DisplayName(value = "Test: replaceZeroWithNearlyZero(BigDecimal, MathContext)")
	void testReplaceZeroWithNearlyZero() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.replaceZeroWithNearlyZero(null, MC));
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.replaceZeroWithNearlyZero(DELTA, null));
		
		BigDecimal value1 = MathUtil.replaceZeroWithNearlyZero(BigDecimal.ZERO, MC);
		Assertions.assertEquals(1, value1.compareTo(BigDecimal.ZERO));
		Assertions.assertEquals(-1, value1.compareTo(BigDecimal.ONE));

		BigDecimal value2 = MathUtil.replaceZeroWithNearlyZero(BigDecimal.ONE, MC);
		Assertions.assertEquals(0, value2.compareTo(BigDecimal.ONE));
	}
}
