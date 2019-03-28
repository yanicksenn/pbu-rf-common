package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the chromaticity-coordinates.
 * 
 * @author Yanick Senn
 */
@MetaModel
public class ChromaticityCoordinate {
	
	@NotNull(message = ChromaticityCoordinateValidator.LABEL_COLOR_RGB_CC_INVALID_X_NULL)
	private BigDecimal x;

	@NotNull(message = ChromaticityCoordinateValidator.LABEL_COLOR_RGB_CC_INVALID_X_NULL)
	private BigDecimal y;
	
	/**
	 * Constructor with <I>X</I> and <I>Y</I>.
	 * 
	 * @param x X value.
	 * @param y Y value.
	 */
	public ChromaticityCoordinate(BigDecimal x, BigDecimal y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor with <I>X</I> and <I>Y</I> value as string.
	 * 
	 * @param x X value.
	 * @param y Y value.
	 */
	public ChromaticityCoordinate(String x, String y) {
		this(
			x != null ? new BigDecimal(x) : null,
			y != null ? new BigDecimal(y) : null
		);
	}

	
	/**
	 * Returns the x value.
	 * 
	 * @return X value.
	 */
	public BigDecimal getX() {
		return x;
	}
	
	/**
	 * Sets the x value.
	 * 
	 * @param x X value.
	 */
	public void setX(BigDecimal x) {
		this.x = x;
	}
	
	/**
	 * Returns the y value.
	 * 
	 * @return Y value.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	/**
	 * Sets the y value.
	 * 
	 * @param y Y value.
	 */
	public void setY(BigDecimal y) {
		this.y = y;
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
			this.x.compareTo(other.x) == 0 &&
			this.y.compareTo(other.y) == 0;
	}
	
}
