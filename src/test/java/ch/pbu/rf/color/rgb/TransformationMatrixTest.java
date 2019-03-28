package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.color.rgb.TransformationMatrix.Location;
import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link TransformationMatrix}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: TransformationMatrix")
public class TransformationMatrixTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	
	@Test
	@DisplayName(value = "Test: TransformationMatrix(BigDecimal)")
	void testConstructor() {
		new TransformationMatrix(null);
		new TransformationMatrix(BigDecimal.ZERO);
	}
	
	@Test
	@DisplayName(value = "Test: getRow(Location.Y)")
	void testGetRow() {
		TransformationMatrix matrix = new TransformationMatrix(BigDecimal.ZERO);
		matrix.set(Location.X0_Y0, BigDecimal.ONE);
		matrix.set(Location.X1_Y1, BigDecimal.ONE);
		matrix.set(Location.X2_Y2, BigDecimal.ONE);
		
		BigDecimal[] row1 = matrix.getRow(Location.Y.Y0);
		Testing.assertEquals(BigDecimal.ONE, row1[0], MC);
		Testing.assertEquals(BigDecimal.ZERO, row1[1], MC);
		Testing.assertEquals(BigDecimal.ZERO, row1[2], MC);
		
		BigDecimal[] row2 = matrix.getRow(Location.Y.Y1);
		Testing.assertEquals(BigDecimal.ZERO, row2[0], MC);
		Testing.assertEquals(BigDecimal.ONE, row2[1], MC);
		Testing.assertEquals(BigDecimal.ZERO, row2[2], MC);
		
		BigDecimal[] row3 = matrix.getRow(Location.Y.Y2);
		Testing.assertEquals(BigDecimal.ZERO, row3[0], MC);
		Testing.assertEquals(BigDecimal.ZERO, row3[1], MC);
		Testing.assertEquals(BigDecimal.ONE, row3[2], MC);
	}
	
	@Test
	@DisplayName(value = "Test: getColumn(Location.X)")
	void testGetColumn() {
		TransformationMatrix matrix = new TransformationMatrix(BigDecimal.ZERO);
		matrix.set(Location.X0_Y0, BigDecimal.ONE);
		matrix.set(Location.X1_Y1, BigDecimal.ONE);
		matrix.set(Location.X2_Y2, BigDecimal.ONE);
		
		BigDecimal[] column1 = matrix.getRow(Location.Y.Y0);
		Testing.assertEquals(BigDecimal.ONE, column1[0], MC);
		Testing.assertEquals(BigDecimal.ZERO, column1[1], MC);
		Testing.assertEquals(BigDecimal.ZERO, column1[2], MC);
		
		BigDecimal[] column2 = matrix.getRow(Location.Y.Y1);
		Testing.assertEquals(BigDecimal.ZERO, column2[0], MC);
		Testing.assertEquals(BigDecimal.ONE, column2[1], MC);
		Testing.assertEquals(BigDecimal.ZERO, column2[2], MC);
		
		BigDecimal[] column3 = matrix.getRow(Location.Y.Y2);
		Testing.assertEquals(BigDecimal.ZERO, column3[0], MC);
		Testing.assertEquals(BigDecimal.ZERO, column3[1], MC);
		Testing.assertEquals(BigDecimal.ONE, column3[2], MC);
	}

	@Test
	@DisplayName(value = "Test: get(Location)")
	void testGet() {
		TransformationMatrix matrix = new TransformationMatrix(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X0_Y0), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X1_Y0), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X2_Y0), MC);

		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X0_Y1), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X1_Y1), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X2_Y1), MC);

		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X0_Y2), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X1_Y2), MC);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X2_Y2), MC);
	}

	@Test
	@DisplayName(value = "Test: set(Location)")
	void testSet() {
		TransformationMatrix matrix = new TransformationMatrix(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, matrix.get(Location.X1_Y1), MC);
		
		matrix.set(Location.X1_Y1, BigDecimal.ONE);
		Testing.assertEquals(BigDecimal.ONE, matrix.get(Location.X1_Y1), MC);
	}

	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testEquals() {
		TransformationMatrix matrix1 = new TransformationMatrix(BigDecimal.ZERO);
		matrix1.set(Location.X0_Y0, BigDecimal.ONE);
		matrix1.set(Location.X1_Y1, BigDecimal.ONE);
		matrix1.set(Location.X2_Y2, BigDecimal.ONE);

		TransformationMatrix matrix2 = new TransformationMatrix(BigDecimal.ZERO);
		matrix2.set(Location.X0_Y0, BigDecimal.ONE);
		matrix2.set(Location.X1_Y1, BigDecimal.ONE);
		matrix2.set(Location.X2_Y2, BigDecimal.ONE);
		
		Assertions.assertEquals(matrix1, matrix2);
		
		matrix2.set(Location.X1_Y1, BigDecimal.ZERO);
		Assertions.assertNotEquals(matrix1, matrix2);
	}
}
