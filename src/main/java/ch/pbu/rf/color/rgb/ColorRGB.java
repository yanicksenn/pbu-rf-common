package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.util.Util;

/**
 * Represents the color rgb.
 * 
 * @author Yanick Senn
 */
public class ColorRGB extends Color {
	private final BigDecimal r;
	private final BigDecimal g;
	private final BigDecimal b;
	
	
	/**
	 * Constructor with r, g and b.
	 * 
	 * @param r R.
	 * @param g G.
	 * @param b B.
	 * 
	 * @throws NullPointerException If r is not specified.
	 * @throws NullPointerException If g is not specified.
	 * @throws NullPointerException If b is not specified.
	 */
	public ColorRGB(BigDecimal r, BigDecimal g, BigDecimal b) {
		super(ColorType.RGB);
		this.r = Objects.requireNonNull(r, "r is not specified");
		this.g = Objects.requireNonNull(g, "g is not specified");
		this.b = Objects.requireNonNull(b, "b is not specified");
	}
	
	
	/**
	 * Returns the r.
	 * 
	 * @return R.
	 */
	public BigDecimal getR() {
		return r;
	}
	
	/**
	 * Returns the g.
	 * 
	 * @return G.
	 */
	public BigDecimal getG() {
		return g;
	}
	
	/**
	 * Returns the b.
	 * 
	 * @return B.
	 */
	public BigDecimal getB() {
		return b;
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
