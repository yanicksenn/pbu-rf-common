package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-RGB.
 * 
 * @author Yanick Senn
 */
@MetaModel
public class ColorRGB extends Color {
	
	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_NULL)
	@DecimalMin(value = ColorRGBValidator.R_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MIN)
	@DecimalMax(value = ColorRGBValidator.R_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_R_MAX)
	private final BigDecimal r;

	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_NULL)
	@DecimalMin(value = ColorRGBValidator.G_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MIN)
	@DecimalMax(value = ColorRGBValidator.G_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_G_MAX)
	private final BigDecimal g;

	@NotNull(message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_NULL)
	@DecimalMin(value = ColorRGBValidator.B_MIN, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MIN)
	@DecimalMax(value = ColorRGBValidator.B_MAX, message = ColorRGBValidator.LABEL_COLOR_RGB_INVALID_B_MAX)
	private final BigDecimal b;
	
	
	/**
	 * Constructor with <I>R</I>, <I>G</I> and <I>B</I> value.
	 * 
	 * @param r R-value.
	 * @param g G-value.
	 * @param b B-value.
	 */
	public ColorRGB(BigDecimal r, BigDecimal g, BigDecimal b) {
		super(ColorType.RGB);
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Constructor with <I>R</I>, <I>G</I> and <I>B</I> value as string.
	 * 
	 * @param r R-value.
	 * @param g G-value.
	 * @param b B-value.
	 */
	public ColorRGB(String r, String g, String b) {
		this(
			new BigDecimal(r), 
			new BigDecimal(g), 
			new BigDecimal(b)
		);
	}
	
	
	/**
	 * Returns the R-value.
	 * 
	 * @return R-value.
	 */
	public BigDecimal getR() {
		return r;
	}
	
	/**
	 * Returns the G-value.
	 * 
	 * @return G-value.
	 */
	public BigDecimal getG() {
		return g;
	}
	
	/**
	 * Returns the B-value.
	 * 
	 * @return B-value.
	 */
	public BigDecimal getB() {
		return b;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, R: %03d, G: %03d, B: %03d]", 
			getClass().getSimpleName(), getType(), r.doubleValue(), g.doubleValue(), b.doubleValue());
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
