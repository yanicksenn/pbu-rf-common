package ch.pbu.rf.color.xyz;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Color-XYZ.
 * 
 * @author Yanick Senn
 */
@MetaModel
public class ColorXYZ extends Color {
	
	@NotNull(message = ColorXYZValidator.LABEL_COLOR_XYZ_INVALID_X_NULL)
	private final BigDecimal x;

	@NotNull(message = ColorXYZValidator.LABEL_COLOR_XYZ_INVALID_Y_NULL)
	private final BigDecimal y;

	@NotNull(message = ColorXYZValidator.LABEL_COLOR_XYZ_INVALID_Z_NULL)
	private final BigDecimal z;
	
	
	/**
	 * Constructor with <I>X</I>, <I>Y</I> and <I>Z</I> value.
	 * 
	 * @param x X-value.
	 * @param y Y-value.
	 * @param z Z-value.
	 */
	public ColorXYZ(BigDecimal x, BigDecimal y, BigDecimal z) {
		super(ColorType.XYZ);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor with <I>X</I>, <I>Y</I> and <I>Z</I> value as string.
	 * 
	 * @param x X-value.
	 * @param y Y-value.
	 * @param z Z-value.
	 */
	public ColorXYZ(String x, String y, String z) {
		this(
			new BigDecimal(x), 
			new BigDecimal(y), 
			new BigDecimal(z)
		);
	}
	
	/**
	 * Returns the X-value.
	 * 
	 * @return X-value.
	 */
	public BigDecimal getX() {
		return x;
	}
	
	/**
	 * Returns the Y-value.
	 * 
	 * @return Y-value.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	/**
	 * Returns the Z-value.
	 * 
	 * @return Z-value.
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
