package ch.pbu.rf.color.rgb;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-RGB.
 * 
 * @author Yanick Senn
 */
@Embeddable
@MetaModel
public class ColorRGB extends Color {
	
	@Column(nullable = false)
	@Min(value = ColorRGBValidator.R_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MIN)
	@Max(value = ColorRGBValidator.R_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MAX)
	private final double r;

	@Column(nullable = false)
	@Min(value = ColorRGBValidator.G_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MIN)
	@Max(value = ColorRGBValidator.G_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MAX)
	private final double g;

	@Column(nullable = false)
	@Min(value = ColorRGBValidator.B_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MIN)
	@Max(value = ColorRGBValidator.B_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MAX)
	private final double b;
	
	
	/**
	 * Constructor with <I>R</I>, <I>G</I> and <I>B</I> value.
	 * 
	 * @param r R-value.
	 * @param g G-value.
	 * @param b B-value.
	 */
	public ColorRGB(double r, double g, double b) {
		super(ColorType.RGB);
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Returns the R-value.
	 * 
	 * @return R-value.
	 */
	public double getR() {
		return r;
	}
	
	/**
	 * Returns the G-value.
	 * 
	 * @return G-value.
	 */
	public double getG() {
		return g;
	}
	
	/**
	 * Returns the B-value.
	 * 
	 * @return B-value.
	 */
	public double getB() {
		return b;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, R: %03d, G: %03d, B: %03d]", getClass().getSimpleName(), getType(), r, g, b);
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
			Objects.equals(this.r, other.r) &&
			Objects.equals(this.g, other.g) &&
			Objects.equals(this.b, other.b);
	}
}
