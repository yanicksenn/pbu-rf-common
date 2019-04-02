package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import ch.yanicksenn.metamodel.MetaModel;
import ch.yanicksenn.util.Util;

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
	 * 
	 * @return This instance.
	 */
	public ChromaticityCoordinate setX(BigDecimal x) {
		this.x = x;
		return this;
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
	 * 
	 * @return This instance.
	 */
	public ChromaticityCoordinate setY(BigDecimal y) {
		this.y = y;
		return this;
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
