package ch.pbu.rf.color.lab;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-Lab.
 * 
 * @author Yanick Senn
 */
@Embeddable
@MetaModel
public class ColorLab extends Color {
	
	@Column(nullable = false)
	@Min(value = ColorLabValidator.L_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_L_MIN)
	@Max(value = ColorLabValidator.L_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_L_MAX)
	private final double l;

	@Column(nullable = false)
	@Min(value = ColorLabValidator.A_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_A_MIN)
	@Max(value = ColorLabValidator.A_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_A_MAX)
	private final double a;

	@Column(nullable = false)
	@Min(value = ColorLabValidator.B_MIN, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_B_MIN)
	@Max(value = ColorLabValidator.B_MAX, message = ColorLabValidator.LABEL_COLOR_LAB_INVALID_B_MAX)
	private final double b;
	
	
	/**
	 * Constructor with <I>L</I>, <I>a</I> and <I>b</I> value.
	 * 
	 * @param l L-value.
	 * @param a A-value.
	 * @param b B-value.
	 */
	public ColorLab(double l, double a, double b) {
		super(ColorType.LAB);
		this.l = l;
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Returns the L-value.
	 * 
	 * @return L-value.
	 */
	public double getL() {
		return l;
	}
	
	/**
	 * Returns the a-value.
	 * 
	 * @return a-value.
	 */
	public double getA() {
		return a;
	}
	
	/**
	 * Returns the b-value.
	 * 
	 * @return b-value.
	 */
	public double getB() {
		return b;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, L: %03d, a: %03d, b: %03d]", getClass().getSimpleName(), getType(), l, a, b);
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
			Objects.equals(this.l, other.l) &&
			Objects.equals(this.a, other.a) &&
			Objects.equals(this.b, other.b);
	}
}
