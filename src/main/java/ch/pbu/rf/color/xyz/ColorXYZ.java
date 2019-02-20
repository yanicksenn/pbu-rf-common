package ch.pbu.rf.color.xyz;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-XYZ.
 * 
 * @author Yanick Senn
 */
@Embeddable
@MetaModel
public class ColorXYZ extends Color {
	
	@Column(nullable = false)
	private final double x;

	@Column(nullable = false)
	private final double y;

	@Column(nullable = false)
	private final double z;
	
	
	/**
	 * Constructor with <I>X</I>, <I>Y</I> and <I>Z</I> value.
	 * 
	 * @param x X-value.
	 * @param y Y-value.
	 * @param z Z-value.
	 */
	public ColorXYZ(double x, double y, double z) {
		super(ColorType.XYZ);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the X-value.
	 * 
	 * @return X-value.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the Y-value.
	 * 
	 * @return Y-value.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the Z-value.
	 * 
	 * @return Z-value.
	 */
	public double getZ() {
		return z;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, X: %03d, Y: %03d, Z: %03d]", getClass().getSimpleName(), getType(), x, y, z);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
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
		
		ColorXYZ other = (ColorXYZ) obj;
		
		return 
			Objects.equals(this.x, other.x) &&
			Objects.equals(this.y, other.y) &&
			Objects.equals(this.z, other.z);
	}
}
