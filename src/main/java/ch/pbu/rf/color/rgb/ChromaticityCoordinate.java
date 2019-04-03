package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import ch.yanicksenn.util.Util;

/**
 * Represents the chromaticity-coordinates.
 * 
 * @author Yanick Senn
 */
public class ChromaticityCoordinate {
	private final BigDecimal x;
	private final BigDecimal y;
	
	/**
	 * Constructor with x and y.
	 * 
	 * @param x X.
	 * @param y Y.
	 * 
	 * @throws NullPointerException If x is not specified.
	 * @throws NullPointerException If y is not specified.
	 */
	public ChromaticityCoordinate(BigDecimal x, BigDecimal y) {
		this.x = Objects.requireNonNull(x, "x is not specified");
		this.y = Objects.requireNonNull(y, "y is not specified");
	}
	
	/**
	 * Returns the x.
	 * 
	 * @return X.
	 */
	public BigDecimal getX() {
		return x;
	}
	
	/**
	 * Returns the y.
	 * 
	 * @return Y.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[x: %03d, y: %03d]", 
			getClass().getSimpleName(), x.doubleValue(), y.doubleValue());
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		ChromaticityCoordinate other = (ChromaticityCoordinate) obj;

		return 
			Util.compareTo(this.x, other.x) == 0 &&
			Util.compareTo(this.y, other.y) == 0;
	}
	
}
