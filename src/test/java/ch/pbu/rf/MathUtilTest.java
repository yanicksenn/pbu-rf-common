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
	@DisplayName(value = "Test: calculateIdentityMatrix(int)")
	void testCalculateIdentityMatrix() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateIdentityMatrix(-1));
		Assertions.assertThrows(IllegalArgumentException.class, () -> MathUtil.calculateIdentityMatrix(0));
		
		MathUtil.calculateIdentityMatrix(1);
	}

	@Test
	@DisplayName(value = "Test: isValidMatrix(BigDecimal[][])")
	void testIsValidMatrix() {
		Assertions.assertThrows(NullPointerException.class, () -> MathUtil.isValidMatrix(null));
		
		BigDecimal[][] matrix1 = new BigDecimal[][] {
			null, 
			null, 
			null };
		Assertions.assertEquals(false, MathUtil.isValidMatrix(matrix1));
		
		BigDecimal[][] matrix2 = new BigDecimal[][] {
			{ null, null, null }, 
			{ null, null, null }, 
			{ null, null, null } };
		Assertions.assertEquals(false, MathUtil.isValidMatrix(matrix2));
		
		BigDecimal[][] matrix3 = new BigDecimal[][] {
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), null },
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) }, 
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) } };
		Assertions.assertEquals(false, MathUtil.isValidMatrix(matrix3));
		
		BigDecimal[][] matrix4 = new BigDecimal[][] {
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) },
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), null }, 
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) } };
		Assertions.assertEquals(false, MathUtil.isValidMatrix(matrix4));

		BigDecimal[][] matrix5 = new BigDecimal[][] {
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) },
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) }, 
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), null } };
		Assertions.assertEquals(false, MathUtil.isValidMatrix(matrix5));

		BigDecimal[][] matrix6 = new BigDecimal[][] {
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) },
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) }, 
			{ MathUtil.bd(1, MC), MathUtil.bd(1, MC), MathUtil.bd(1, MC) } };
		Assertions.assertEquals(true, MathUtil.isValidMatrix(matrix6));
	}

}
