package ch.pbu.rf;

import java.math.BigDecimal;

/**
 * Represents the any matrix-validator.
 * 
 * @author Yanick Senn
 */
public class MatrixValidatorAny extends MatrixValidator {
	
	/**
	 * Constructor. If height is smaller than one.
	 */
	public MatrixValidatorAny() {
		super(MatrixValidatorType.ANY);
	}

	@Override
	public boolean validate(BigDecimal[][] matrix) {
		return true;
	}
}
