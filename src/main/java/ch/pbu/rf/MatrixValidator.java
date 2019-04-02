package ch.pbu.rf;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents the matrix-validation.
 * 
 * @author Yanick Senn 
 */
public abstract class MatrixValidator {
	private final MatrixValidationType type;
	
	/**
	 * Constructor with type.
	 * 
	 * @param type Type.
	 * 
	 * @throws NullPointerException If type is not specified.
	 */
	public MatrixValidator(MatrixValidationType type) {
		this.type = Objects.requireNonNull(type, "type is not specified");
	}
	
	/**
	 * Returns <code>true</code> if the given matrix is valid.
	 *
	 * @param matrix Matrix.
	 * 
	 * @return <code>true</code> if the given matrix is valid. Returns <code>false</code> otherwise.
	 */
	public abstract boolean validate(BigDecimal[][] matrix);
	
	/**
	 * Returns the type.
	 * 
	 * @return The type.
	 */
	public MatrixValidationType getType() {
		return type;
	}
}