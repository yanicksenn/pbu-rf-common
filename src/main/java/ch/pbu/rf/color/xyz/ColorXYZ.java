package ch.pbu.rf.color.xyz;

import java.math.BigDecimal;
import java.util.Objects;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;

/**
 * Represents the Color-XYZ.
 * 
 * @author Yanick Senn
 */
public class ColorXYZ extends Color {
	private final BigDecimal x;
	private final BigDecimal y;
	private final BigDecimal z;
	
	
	/**
	 * Constructor with <I>X</I>, <I>Y</I> and <I>Z</I> value.
	 * 
	 * @param x X value.
	 * @param y Y value.
	 * @param z Z value.
	 * 
	 * @throws NullPointerException If x is not specified.
	 * @throws NullPointerException If y is not specified.
	 * @throws NullPointerException If z is not specified.
	 */
	public ColorXYZ(BigDecimal x, BigDecimal y, BigDecimal z) {
		super(ColorType.XYZ);
		this.x = Objects.requireNonNull(x, "x is not specified");
		this.y = Objects.requireNonNull(y, "y is not specified");;
		this.z = Objects.requireNonNull(z, "z is not specified");;
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
	 * Returns the y value.
	 * 
	 * @return Y value.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	/**
	 * Returns the z value.
	 * 
	 * @return Z value.
	 */
	public BigDecimal getZ() {
		return z;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[type: %s, X: %03d, Y: %03d, Z: %03d]", 
			getClass().getSimpleName(), getType(), x.doubleValue(), y.doubleValue(), z.doubleValue());
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
			this.x.compareTo(other.x) == 0 &&
			this.y.compareTo(other.y) == 0 &&
			this.z.compareTo(other.z) == 0;
	}
}
