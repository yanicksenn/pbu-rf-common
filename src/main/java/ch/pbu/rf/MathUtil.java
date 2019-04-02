package ch.pbu.rf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import ch.obermuhlner.math.big.BigDecimalMath;

/**
 * Represents the math-util.
 * 
 * @author Yanick Senn
 */
public class MathUtil {
	public static final MathContext MC = RF.MC;
	public static final BigDecimal PI = BigDecimalMath.pi(MC);

	
	/**
	 * Private constructor.
	 */
	private MathUtil() {
		throw new AssertionError();
	}
	
	
	/**
	 * Converts the given <I>degrees</I> to radians.
	 * 
	 * @param angdeg Degrees.
	 * @param mc Math-Context.
	 * 
	 * @return The given <I>degrees</I> to radians.
	 * 
	 * @throws NullPointerException If angdeg is not specified.
	 * @throws NullPointerException If mc is not specified.
	 */
	public static BigDecimal toRadians(BigDecimal angdeg, MathContext mc) throws NullPointerException {
		Objects.requireNonNull(angdeg, "angdeg is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
        return angdeg.divide(bd(180, mc), mc).multiply(PI, mc);
    }
	
	/**
	 * Converts the given <I>radians</I> to degrees.
	 * 
	 * @param angrad Radians.
	 * @param mc Math-Context.
	 * 
	 * @return The given <I>radians</I> to degrees.
	 * 
	 * @throws NullPointerException If angrad is not specified.
	 * @throws NullPointerException If mc is not specified.
	 */
	public static BigDecimal toDegrees(BigDecimal angrad, MathContext mc) {
		Objects.requireNonNull(angrad, "angrad is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
        return angrad.multiply(bd(180, mc), mc).divide(PI, mc);
    }

	/**
	 * Returns a matrix with the given length and filled with the given default value.
	 * 
	 * @param defaultValue Default-value.
	 * @param length Length.
	 * 
	 * @return A matrix with the given length and filled with the given default value.
	 * 
	 * @throws NullPointerException If defaultValue is not specified.
	 * @throws IllegalArgumentException If length is smaller than 1.
	 */
	public static BigDecimal[][] createMatrix(BigDecimal defaultValue, int length) {
		Objects.requireNonNull(defaultValue, "defaultValue is not specified");
		
		return createMatrix(defaultValue, length, length);
	}
	
	/**
	 * Returns a matrix with the given width, height and filled with the given default value.
	 * 
	 * @param defaultValue Default-value.
	 * @param width Width.
	 * @param height Height.
	 * 
	 * @return A matrix with the given width, height and filled with the given default value.
	 * 
	 * @throws NullPointerException If defaultValue is not specified.
	 * @throws IllegalArgumentException If width is smaller than 1.
	 * @throws IllegalArgumentException If height is smaller than 1.
	 */
	public static BigDecimal[][] createMatrix(BigDecimal defaultValue, int width, int height) {
		Objects.requireNonNull(defaultValue, "defaultValue is not specified");
		
		if (width < 1) {
			throw new IllegalArgumentException("width should not be smaller than 1");
		}
		
		if (height < 1) {
			throw new IllegalArgumentException("height should not be smaller than 1");
		}
		
		BigDecimal[][] result = new BigDecimal[height][width];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				result[y][x] = defaultValue;
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the width of the given matrix.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return The width of the given matrix.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static int getWidth(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");

		if (validateMatrix(matrix, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		int result = matrix[0].length;
		return result;
	}
	
	/**
	 * Returns the height of the given matrix.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return The height of the given matrix.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static int getHeight(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");

		if (validateMatrix(matrix, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		int result = matrix.length;
		return result;
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is a row-vector.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix is a row-vector. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static boolean isRowVector(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");

		if (validateMatrix(matrix, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		boolean result = true;
		
		int height = getHeight(matrix);
		if (height != 1) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is a column-vector.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix is a column-vector. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static boolean isColumnVector(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");

		if (validateMatrix(matrix, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		boolean result = true;
		
		int width = getWidth(matrix);
		if (width != 1) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is a column-vector.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix is a column-vector. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */ asdflkajsdflakjsd
	public static boolean isQuadratic(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");

		if (validateMatrix(matrix, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		boolean result = getWidth(matrix) == getHeight(matrix);
		return result;
	}
	
	/**
	 * 
	 * 
	 * @param matrix1
	 * @param matrix2
	 * 
	 * @return
	 */
	public static BigDecimal[][] calculateProduct(BigDecimal[][] matrix1, BigDecimal[][] matrix2, MathContext mc) {
		Objects.requireNonNull(matrix1, "matrix1 is not specified");
		Objects.requireNonNull(matrix2, "matrix2 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		if (validateMatrix(matrix1, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix1 is not valid");
		}
		
		if (validateMatrix(matrix2, new MatrixValidatorEqualOrBigger(1))) {
			throw new IllegalArgumentException("matrix2 is not valid");
		}
		
		int m1Width = getWidth(matrix1);
		int m1Height = getHeight(matrix2);
		
		int m2Width = getWidth(matrix2);
		int m2Height = getHeight(matrix2);
		
		if (m1Height != m2Width) {
			throw new IllegalArgumentException(String.format("width of matrix2 (%d) does not match with the height of matrix1 (%d)", m2Width, m1Height));
		}
		
		BigDecimal[][] result = null;
		
		if (isRowVector(matrix1) && isColumnVector(matrix2) && m1Width == m1Height) {
			// Row-vector and column-vector.
			result = MathUtil.createMatrix(BigDecimal.ZERO, 1, 1);
			
			BigDecimal value = BigDecimal.ZERO;
			
			for (int i = 0; i < m1Width; i++) {
				value = value.add(matrix1[0][i].multiply(matrix2[i][0], mc), mc);
			}
			
			result[0][0] = value;
			
		} else if (isColumnVector(matrix1) && isRowVector(matrix2)) {
			// Column-vector and Row-vector.
			result = MathUtil.createMatrix(BigDecimal.ZERO, m2Width, m1Height);
			
			for (int y = 0; y < m1Height; y++) {
				for (int x = 0; x < m2Width; x++) {
					result[y][x] = matrix1[y][0].multiply(matrix2[0][x], mc);
				}
			}
			
		} else if (isColumnVector(matrix2)) {
			// Matrix and column-vector.
			result = MathUtil.createMatrix(BigDecimal.ZERO, m2Width, m1Height);
			
			for (int y = 0; y < m1Height; y++) {
				BigDecimal value = BigDecimal.ZERO;
				
				for (int x = 0; x < m2Height; x++) {
					value = value.add(matrix1[y][x].multiply(matrix2[x][0], mc), mc);
				}
				
				result[y][0] = value;
			}
			
		} else if (isRowVector(matrix1)) {
			// Row-vector and matrix.
			result = MathUtil.createMatrix(BigDecimal.ZERO, m2Width, m1Height);
			
			for (int y = 0; y < m2Height; y++) {
				BigDecimal value = BigDecimal.ZERO;
				
				for (int x = 0; x < m1Width; x++) {
					value = value.add(matrix1[0][x].multiply(matrix2[y][x], mc), mc);
				}
				
				result[0][y] = value;
			}
		}
		
		return result;

//		BigDecimal[][] result = MathUtil.createMatrix(BigDecimal.ZERO, width, height);
//		int rWidth = result[0].length;
//		int rHeight = result.length;
//		
//		for (int ry = 0; ry < rHeight; ry++) {
//			for (int rx = 0; rx < rWidth; rx++) {
//				
//				
//				
//				for (int x1 = 0; x1 < (useM1Width ? m1Width : m2Width); x1++) {
//					for (int y2 = 0; y2 < (useM1Height ? m1Height : m2Height); y2++) {
//						
//					}
//				}
//			}
//		}
//		
//		return null;
	}
	
	/**
	 * Calculates the inverse of the given matrix.
	 * 
	 * @param matrix Matrix.
	 * @param mc Math-Context.
	 * 
	 * @return The inverse of the given matrix.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws NullPointerException If mc is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static BigDecimal[][] calculate3x3Inverse(BigDecimal[][] matrix, MathContext mc) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		if (!MathUtil.validateMatrix(matrix, new MatrixValidatorExact(3))) {
			throw new IllegalArgumentException("matrix is invalid");
		}

		// If determinat is equal to zero then the matrix is its own inverse
		BigDecimal determinant = calculate3x3Determinant(matrix, mc);
		if (determinant.compareTo(BigDecimal.ZERO) == 0) {
			return matrix;
		}

		BigDecimal xr = matrix[0][0]; 
		BigDecimal yr = matrix[0][1]; 
		BigDecimal zr = matrix[0][2]; 
		
		BigDecimal xg = matrix[1][0]; 
		BigDecimal yg = matrix[1][1]; 
		BigDecimal zg = matrix[1][2]; 
		
		BigDecimal xb = matrix[2][0]; 
		BigDecimal yb = matrix[2][1]; 
		BigDecimal zb = matrix[2][2]; 
		
		// M^(-1)
		
		// xr yr zr
		// xg yg zg
		// xb yb zb
		
		BigDecimal dxr = MathUtil.calculate2x2Determinant(yg, zg, yb, zb, MC);
		BigDecimal dyr = MathUtil.calculate2x2Determinant(xg, zg, xb, zb, MC);
		BigDecimal dzr = MathUtil.calculate2x2Determinant(xg, yg, xb, yb, MC);
		
		BigDecimal dxg = MathUtil.calculate2x2Determinant(yr, zr, yb, zb, MC);
		BigDecimal dyg = MathUtil.calculate2x2Determinant(xr, zr, xb, zb, MC);
		BigDecimal dzg = MathUtil.calculate2x2Determinant(xr, yr, xb, yb, MC);
		
		BigDecimal dxb = MathUtil.calculate2x2Determinant(yr, zr, yg, zg, MC);
		BigDecimal dyb = MathUtil.calculate2x2Determinant(xr, zr, xg, zg, MC);
		BigDecimal dzb = MathUtil.calculate2x2Determinant(xr, yr, xg, yg, MC);
		
		// Multiply
		
		// + - +
		// - + -
		// + - +

		dyr = dyr.negate(MC);
		dxg = dxg.negate(MC);
		dzg = dzg.negate(MC);
		dyb = dyb.negate(MC);
		
		// Transponate & apply factor

		BigDecimal factor = BigDecimal.ONE.divide(determinant, mc);
		
		BigDecimal[][] result = new BigDecimal[][] {
			{ dxr.multiply(factor, mc), dxg.multiply(factor, mc), dxb.multiply(factor, mc) },
			{ dyr.multiply(factor, mc), dyg.multiply(factor, mc), dyb.multiply(factor, mc) },
			{ dzr.multiply(factor, mc), dzg.multiply(factor, mc), dzb.multiply(factor, mc) }
		};
		
		return result;
	}

	/**
	 * Calculates the determinant of a 3x3 matrix.
	 * 
	 * @param matrix Matrix.
	 * @param mc Math-Context.
	 * 
	 * @return The determinant of a 3x3 matrix.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws NullPointerException If mc is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static BigDecimal calculate3x3Determinant(BigDecimal[][] matrix, MathContext mc) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		Objects.requireNonNull(mc, "mc is not specified");

		if (!MathUtil.validateMatrix(matrix, new MatrixValidatorExact(3))) {
			throw new IllegalArgumentException("matrix is invalid");
		}
		
		BigDecimal a = matrix[0][0];
		BigDecimal b = matrix[0][1];
		BigDecimal c = matrix[0][2];

		BigDecimal d = matrix[1][0];
		BigDecimal e = matrix[1][1];
		BigDecimal f = matrix[1][2];

		BigDecimal g = matrix[2][0];
		BigDecimal h = matrix[2][1];
		BigDecimal i = matrix[2][2];
		
		BigDecimal resultStep1 = a.multiply(e, mc).multiply(i, mc);
		BigDecimal resultStep2 = b.multiply(f, mc).multiply(g, mc);
		BigDecimal resultStep3 = c.multiply(d, mc).multiply(h, mc);
		BigDecimal resultStep4 = g.multiply(e, mc).multiply(c, mc);
		BigDecimal resultStep5 = h.multiply(f, mc).multiply(a, mc);
		BigDecimal resultStep6 = i.multiply(d, mc).multiply(b, mc);
		
		BigDecimal result = resultStep1
			.add(resultStep2, mc)
			.add(resultStep3, mc)
			.subtract(resultStep4)
			.subtract(resultStep5)
			.subtract(resultStep6);
			
		return result;
	}
	
	/**
	 * Calculates the determinant of a 2x2 matrix.
	 * 
	 * @param y0x0 Value at x: 0 and y: 0.
	 * @param y0x1 Value at x: 1 and y: 0.
	 * @param y1x0 Value at x: 0 and y: 1.
	 * @param y1x1 Value at x: 1 and y: 1.
	 * 
	 * @return The determinant of a 2x2 matrix.
	 * 
	 * @throws NullPointerException If x0y0 is not specified.
	 * @throws NullPointerException If x1y0 is not specified.
	 * @throws NullPointerException If x0y1 is not specified.
	 * @throws NullPointerException If x1y1 is not specified.
	 * @throws NullPointerException If mc is not specified.
	 */
	public static BigDecimal calculate2x2Determinant(BigDecimal y0x0, BigDecimal y0x1, BigDecimal y1x0, BigDecimal y1x1, MathContext mc) {
		Objects.requireNonNull(y0x0, "y0x0 is not specified");
		Objects.requireNonNull(y0x1, "y0x1 is not specified");
		Objects.requireNonNull(y1x0, "y1x0 is not specified");
		Objects.requireNonNull(y1x1, "y1x1 is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal[][] matrix = new BigDecimal[][] {
			{ y0x0, y0x1 },
			{ y1x0, y1x1 }
		};
		
		return calculate2x2Determinant(matrix, mc);
	}

	/**
	 * Calculates the determinant of a 2x2 matrix.
	 * 
	 * @param matrix Matrix.
	 * @param mc Math-Context.
	 * 
	 * @return The determinant of a 2x2 matrix.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws NullPointerException If mc is not specified.
	 * @throws IllegalArgumentException If matrix is not valid.
	 */
	public static BigDecimal calculate2x2Determinant(BigDecimal[][] matrix, MathContext mc) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		if (!MathUtil.validateMatrix(matrix, new MatrixValidatorExact(2))) {
			throw new IllegalArgumentException("matrix is invalid");
		}
		
		BigDecimal a = matrix[0][0];
		BigDecimal b = matrix[0][1];
		BigDecimal c = matrix[1][0];
		BigDecimal d = matrix[1][1];
		
		BigDecimal result = a.multiply(d, mc).subtract(c.multiply(b, mc), mc);
		return result;
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is valid. 
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix is valid. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 */
	public static boolean validateMatrix(BigDecimal[][] matrix) {
		return validateMatrix(matrix, new MatrixValidatorAny());
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is valid. 
	 * 
	 * @param matrix Matrix.
	 * @param validator Validator.
	 * 
	 * @return <code>true</code> if the given matrix is valid. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 * @throws NullPointerException If validator is not specified.
	 */
	public static boolean validateMatrix(BigDecimal[][] matrix, MatrixValidator validator) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		Objects.requireNonNull(validator, "validator is not specified");
		
		boolean result = true;
		
		int width = -1;
		
		for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
			BigDecimal[] row = matrix[rowIndex];
			
			if (row == null) {
				result = false;
				break;
			}
			
			int mWidth = row.length;
			if (width == -1) {
				width = mWidth;
			}
			
			if (mWidth != width) {
				result = false;
				break;
			}
			
			for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
				BigDecimal cell = row[columnIndex];
				
				if (cell == null) {
					result = false;
					break;
				}
			}
			
			if (result == false) {
				break;
			}
		}
		
		if (result == true) {
			result = validator.validate(matrix);
		}
		
		return result;
	}
	
	/**
	 * If the given number is exactly equal to zero it returns a number that is 
	 * close to zero by taking its math-contexts precision and adding one to the 
	 * last digit.
	 * 
	 * @param val Value.
	 * @param mc Math-Context.
	 * 
	 * @return A number close to zero.
	 * 
	 * @throws NullPointerException If val is not specified.
	 * @throws NullPointerException If mc is not specified.
	 */
	public static BigDecimal replaceZeroWithNearlyZero(BigDecimal val, MathContext mc) {
		Objects.requireNonNull(val, "val is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		BigDecimal result = val;
		
		if (BigDecimal.ZERO.compareTo(val) == 0) {
			int digitsAfterComma = mc.getPrecision();
			result = BigDecimal.ONE.divide(BigDecimal.TEN.pow(digitsAfterComma, mc), mc);
		}
		
		return result;
	}
	
	/**
	 * Creates a {@link BigDecimal}.
	 * 
	 * @param i Value.
	 * @param mc Math-Context.
	 * 
	 * @return {@link BigDecimal}.
	 */
	public static BigDecimal bd(int i, MathContext mc) {
		return new BigDecimal(i, mc);
	}

	/**
	 * Creates a {@link BigDecimal}.
	 * 
	 * @param i Value.
	 * @param mc Math-Context.
	 * 
	 * @return {@link BigDecimal}.
	 */
	public static BigDecimal bd(double i, MathContext mc) {
		return new BigDecimal(i, mc);
	}

	/**
	 * Creates a {@link BigDecimal}.
	 * 
	 * @param i Value.
	 * @param mc Math-Context.
	 * 
	 * @return {@link BigDecimal}.
	 */
	public static BigDecimal bd(String i, MathContext mc) {
		return new BigDecimal(i, mc);
	}
	
}
