package ch.pbu.rf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Matrix2D}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: Matrix2D")
public class Matrix2DTest {
	
	@Test
	@DisplayName(value = "Test: Constructor(int, int, Object)")
	void testConstructor() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Matrix2D<>(-1, 3, null));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Matrix2D<>(3, -1, null));
		
		Matrix2D<BigDecimal> matrix = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(0, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(0, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(0, 2));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(1, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(1, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(1, 2));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(2, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(2, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix.get(2, 2));

		Assertions.assertDoesNotThrow(() -> new Matrix2D<>(0, 0, null));
		Matrix2D<BigDecimal> matrix1 = new Matrix2D<>(0, 0, BigDecimal.ZERO);
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> matrix1.get(0, 0));
		Assertions.assertEquals(0, matrix1.getRows());
		Assertions.assertEquals(0, matrix1.getColumns());
		Assertions.assertEquals(true, matrix1.isEmpty());
		Assertions.assertEquals(0, matrix1.size());
		
		Assertions.assertDoesNotThrow(() -> new Matrix2D<>(3, 3, null));
		Matrix2D<BigDecimal> matrix2 = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(0, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(0, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(0, 2));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(1, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(1, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(1, 2));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(2, 0));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(2, 1));
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(2, 2));
		Assertions.assertEquals(3, matrix2.getRows());
		Assertions.assertEquals(3, matrix2.getColumns());
		Assertions.assertEquals(false, matrix2.isEmpty());
		Assertions.assertEquals(9, matrix2.size());
	}

	@Test
	@DisplayName(value = "Test: getRows()")
	void testGetRows() {
		Matrix2D<BigDecimal> matrix1 = new Matrix2D<>(0, 0, BigDecimal.ZERO);
		Assertions.assertEquals(0, matrix1.getRows());
		
		Matrix2D<BigDecimal> matrix2 = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(3, matrix2.getRows());
	}

	@Test
	@DisplayName(value = "Test: getColumns()")
	void testGetColumns() {
		Matrix2D<BigDecimal> matrix1 = new Matrix2D<>(0, 0, BigDecimal.ZERO);
		Assertions.assertEquals(0, matrix1.getColumns());
		
		Matrix2D<BigDecimal> matrix2 = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(3, matrix2.getColumns());
	}

	@Test
	@DisplayName(value = "Test: get(int, int)")
	void testGet() {
		Matrix2D<BigDecimal> matrix1 = new Matrix2D<>(0, 0, BigDecimal.ZERO);
		Assertions.assertThrows(IllegalArgumentException.class, () -> matrix1.get(-1, 0));
		Assertions.assertThrows(IllegalArgumentException.class, () -> matrix1.get(0, -1));
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> matrix1.get(0, 0));
		
		Matrix2D<BigDecimal> matrix2 = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(BigDecimal.ZERO, matrix2.get(1, 1));
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> matrix2.get(3, 1));
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> matrix2.get(1, 3));
	}

	@Test
	@DisplayName(value = "Test: isEmpty()")
	void testIsEmpty() {
		Matrix2D<BigDecimal> matrix1 = new Matrix2D<>(0, 0, BigDecimal.ZERO);
		Assertions.assertEquals(true, matrix1.isEmpty());
		
		Matrix2D<BigDecimal> matrix2 = new Matrix2D<>(3, 3, BigDecimal.ZERO);
		Assertions.assertEquals(false, matrix2.isEmpty());
	}
	
}
