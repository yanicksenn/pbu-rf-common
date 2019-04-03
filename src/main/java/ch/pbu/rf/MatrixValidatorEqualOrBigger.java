package ch.pbu.rf;

import java.math.BigDecimal;

/**
 * Represents the equal-or-bigger matrix-validator.
 * 
 * @author Yanick Senn
 */
public class MatrixValidatorEqualOrBigger extends MatrixValidator {
	private final int width;
	private final int height;
	

	/**
	 * Constructor with length.
	 * 
	 * @param length Length.
	 * 
	 * 
	 * @throws IllegalArgumentException If length is smaller than one.
	 */
	public MatrixValidatorEqualOrBigger(int length) {
		this(length, length);
	}
	
	/**
	 * Constructor with width and height.
	 * 
	 * @param width Width.
	 * @param height Height.
	 * 
	 * @throws IllegalArgumentException If width is smaller than one.
	 * @throws IllegalArgumentException If height is smaller than one.
	 */
	public MatrixValidatorEqualOrBigger(int width, int height) {
		super(MatrixValidatorType.EQUAL_OR_BIGGER);
		
		if (width < 1) {
			throw new IllegalArgumentException("width should not be smaller than 1");
		}
		
		this.width = width;
		
		if (height < 1) {
			throw new IllegalArgumentException("height should not be smaller than 1");
		}
		
		this.height = height;
	}

	@Override
	public boolean validate(BigDecimal[][] matrix) {
		int mHeight = matrix.length;
		if (mHeight == 0) {
			return false;
		}
		
		int mWidth = matrix[0].length;
		if (mWidth == 0) {
			return false;
		}
		
		if (mWidth < width) {
			return false;
		}
		
		if (mHeight < height) {
			return false;
		}
		
		return true;
	}
}
