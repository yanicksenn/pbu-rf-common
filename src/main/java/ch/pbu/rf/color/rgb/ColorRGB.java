package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;
import ch.yanicksenn.util.Util;

/**
 * Represents the Color-RGB.
 * 
 * @author Yanick Senn
 */
@MetaModel
public class ColorRGB extends Color {
	
	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_NULL)
	@DecimalMin(value = ColorRGBValidator.R_MIN_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MIN)
	@DecimalMax(value = ColorRGBValidator.R_MAX_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MAX)
	private BigDecimal r;

	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_NULL)
	@DecimalMin(value = ColorRGBValidator.G_MIN_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MIN)
	@DecimalMax(value = ColorRGBValidator.G_MAX_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MAX)
	private BigDecimal g;

	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_NULL)
	@DecimalMin(value = ColorRGBValidator.B_MIN_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MIN)
	@DecimalMax(value = ColorRGBValidator.B_MAX_STRING, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MAX)
	private BigDecimal b;
	
	
	/**
	 * Constructor.
	 */
	public ColorRGB() {
		super(ColorType.RGB);
	}
	
	
	/**
	 * Returns the r value.
	 * 
	 * @return R value.
	 */
	public BigDecimal getR() {
		return r;
	}
	
	/**
	 * Sets the r value.
	 * 
	 * @param r R value.
	 * 
	 * @return This instance.
	 */
	public ColorRGB setR(BigDecimal r) {
		this.r = r;
		return this;
	}
	
	/**
	 * Returns the g value.
	 * 
	 * @return G value.
	 */
	public BigDecimal getG() {
		return g;
	}

	/**
	 * Sets the g value.
	 * 
	 * @param g G value.
	 * 
	 * @return This instance.
	 */
	public ColorRGB setG(BigDecimal g) {
		this.g = g;
		return this;
	}
	
	/**
	 * Returns the b value.
	 * 
	 * @return B value.
	 */
	public BigDecimal getB() {
		return b;
	}
	
	/**
	 * Sets the b value.
	 * 
	 * @param b B value.
	 * 
	 * @return This instance.
	 */
	public ColorRGB setB(BigDecimal b) {
		this.b = b;
		return this;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(r, g, b);
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
		
		ColorRGB other = (ColorRGB) obj;
		
		return 
			Util.compareTo(this.r, other.r) == 0 &&
			Util.compareTo(this.g, other.g) == 0 &&
			Util.compareTo(this.b, other.b) == 0;
	}
}
