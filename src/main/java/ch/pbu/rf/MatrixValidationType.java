package ch.pbu.rf;

/**
 * Represents the matrix-validation-type.
 * 
 * @author Yanick Senn
 */
public enum MatrixValidationType {
	
	/**
	 * Represents the exact type.
	 */
	EXACT,

	/**
	 * Represents the equal or smaller type.
	 */
	EQUAL_OR_SMALLER,

	/**
	 * Represents the equal or bigger type.
	 */
	EQUAL_OR_BIGGER,
	
	/**
	 * Represents the any type.
	 */
	ANY,
}