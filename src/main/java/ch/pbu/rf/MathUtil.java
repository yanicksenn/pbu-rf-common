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
	 * 
	 * @param matrix
	 * @param mc
	 * @return
	 */
	public static BigDecimal[][] calculateInverse(BigDecimal[][] matrix, MathContext mc) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		Objects.requireNonNull(mc, "mc is not specified");
		
		if (!isValidMatrix(matrix)) {
			throw new IllegalArgumentException("matrix is not valid");
		}

		return null;
	}

	/**
	 * Returns an identity matrix by the scale of n<sup>2</sup>.
	 * 
	 * @param n Length and height.
	 * 
	 * @return Identity matrix.
	 * 
	 * @throws IllegalArgumentException If n is negative or zero.
	 */
	public static BigDecimal[][] calculateIdentityMatrix(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("n should not be negative");
		}
		
		BigDecimal[][] result = new BigDecimal[n][n];
		
		int i = 0;
		
		for (BigDecimal[] row : result) {
			for (int j = 0; i < row.length; j++) {
				if (i == j) {
					row[j] = BigDecimal.ONE;
					
				} else {
					row[j] = BigDecimal.ZERO;
				}
			}
			
			i++;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public static int getColumnsOfMatrix(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		
		if (!isValidMatrix(matrix)) {
			throw new IllegalArgumentException("matrix is not valid");
		}
		
		return 0;
	}
	
	/**
	 * Returns <code>true</code> if the given matrix does not contain any 
	 * <code>null</code> values.
	 * 
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix does not contain any 
	 * <code>null</code> values. Returns <code>false</code> otherwise.
	 * 
	 * @throws NullPointerException If matrix is not specified.
	 */
	public static boolean isValidMatrix(BigDecimal[][] matrix) {
		Objects.requireNonNull(matrix, "matrix is not specified");
		
		boolean result = true;
		int rowLength = -1;
		
		for (BigDecimal[] row : matrix) {
			if (row == null) {
				result = false;
				break;
			}
			
			if (rowLength == -1) {
				rowLength = row.length;
				
			} else if (rowLength != row.length) {
				result = false;
				break;
			}
			
			for (BigDecimal cell : row) {
				if (cell == null) {
					result = false;
					break;
				}
			}
			
			if (result == false) {
				break;
			}
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
