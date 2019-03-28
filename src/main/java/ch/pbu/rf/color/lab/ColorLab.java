package ch.pbu.rf.color.lab;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-Lab.
 * 
 * @author Yanick Senn
 */
@MetaModel
public class ColorLab extends Color {

	@NotNull(message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_L_NULL)
	@DecimalMin(value = ColorLabValidator.L_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_L_MIN)
	@DecimalMax(value = ColorLabValidator.L_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_L_MAX)
	private BigDecimal l;

	@NotNull(message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_A_NULL)
	@DecimalMin(value = ColorLabValidator.A_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_A_MIN)
	@DecimalMax(value = ColorLabValidator.A_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_A_MAX)
	private BigDecimal a;
	
	@NotNull(message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_B_NULL)
	@DecimalMin(value = ColorLabValidator.B_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_B_MIN)
	@DecimalMax(value = ColorLabValidator.B_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_B_MAX)
	private BigDecimal b;
	
	
	/**
	 * Constructor with <I>L</I>, <I>a</I> and <I>b</I> value.
	 * 
	 * @param l L value.
	 * @param a A value.
	 * @param b B value.
	 */
	public ColorLab(BigDecimal l, BigDecimal a, BigDecimal b) {
		super(ColorType.LAB);
		this.l = l;
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Constructor with <I>L</I>, <I>a</I> and <I>b</I> value as string.
	 * 
	 * @param l L value.
	 * @param a A value.
	 * @param b B value.
	 */
	public ColorLab(String l, String a, String b) {
		this(
			l != null ? new BigDecimal(l) : null, 
			a != null ? new BigDecimal(a) : null, 
			b != null ? new BigDecimal(b) : null
		);
	}
	
	
	/**
	 * Returns the l value.
	 * 
	 * @return L value.
	 */
	public BigDecimal getL() {
		return l;
	}
	
	/**
	 * Sets the l value.
	 * 
	 * @param l L value.
	 */
	public void setL(BigDecimal l) {
		this.l = l;
	}
	
	/**
	 * Returns the a value.
	 * 
	 * @return A value.
	 */
	public BigDecimal getA() {
		return a;
	}
	
	/**
	 * Sets the a value.
	 * 
	 * @param a A value.
	 */
	public void setA(BigDecimal a) {
		this.a = a;
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
	 */
	public void setB(BigDecimal b) {
		this.b = b;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, L: %03d, a: %03d, b: %03d]", 
			getClass().getSimpleName(), getType(), l.doubleValue(), a.doubleValue(), b.doubleValue());
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(l, a, b);
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
		
		ColorLab other = (ColorLab) obj;

		return 
			this.l.compareTo(other.l) == 0 &&
			this.a.compareTo(other.a) == 0 &&
			this.b.compareTo(other.b) == 0;
	}
}
