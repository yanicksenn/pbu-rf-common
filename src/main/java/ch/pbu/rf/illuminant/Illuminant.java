
package ch.pbu.rf.illuminant;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * Represents the Illuminant.
 * 
 * @author Yanick Senn
 */
public class Illuminant {
	private final String name;
	private final int year;
	private final int degrees;
	private final BigDecimal x;
	private final BigDecimal y;
	private final MathContext mc;
	private final int cct;
	
	/**
	 * Constructor with <I>Name</I>, <I>Year</I>, <I>Degrees</I>, <I>X</I>, <I>Y</I> and <I>CCT</I>.
	 * 
	 * @param name Name.
	 * @param year Year.
	 * @param degrees Degrees.
	 * @param x X-value.
	 * @param y Y-value.
	 * @param cct CCT.
	 */
	public Illuminant(String name, int year, int degrees, BigDecimal x, BigDecimal y, MathContext mc, int cct) {
		this.name = Objects.requireNonNull(name, "name is not specified");
		this.year = year;
		this.degrees = degrees;
		this.x = Objects.requireNonNull(x, "x is not specified");
		this.y = Objects.requireNonNull(y, "y is not specified");
		this.mc = Objects.requireNonNull(mc, "mc is not specified");
		this.cct = cct;
		
		// https://github.com/anderslanglands/colorspace-rs/blob/master/src/illuminant.rs
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return Name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the year.
	 * 
	 * @return Year.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns the degrees.
	 * 
	 * @return Degrees.
	 */
	public int getDegrees() {
		return degrees;
	}
	
	/**
	 * Returns the x-value.
	 * 
	 * @return X-value.
	 */
	public BigDecimal getX() {
		return x;
	}
	
	/**
	 * Returns the y-value.
	 * 
	 * @return Y-value.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	/**
	 * Returns the z-value.
	 * 
	 * @return Z-value.
	 */
	public BigDecimal getZ() {
		return BigDecimal.ONE.subtract(x, mc).subtract(y, mc);
	}
	
	/**
	 * Returns the math-context.
	 * 
	 * @return Math-context.
	 */
	public MathContext getMathContext() {
		return mc;
	}
	
	/**
	 * Returns the CCT.
	 * 
	 * @return CCT.
	 */
	public int getCCT() {
		return cct;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[name: %s, year: %d, degrees: %d, x: %03d, y: %03d, CCT: %d]", getClass().getSimpleName(), name, year, degrees, x, y, cct);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(name, year, degrees, x, y, mc, cct);
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
		
		Illuminant other = (Illuminant) obj;
		
		return 
			Objects.equals(this.name, other.name) &&
			Objects.equals(this.year, other.year) &&
			Objects.equals(this.degrees, other.degrees) &&
			this.x.compareTo(other.x) == 0 &&
			this.y.compareTo(other.y) == 0 &&
			Objects.equals(this.mc, other.mc) &&
			Objects.equals(this.cct, other.cct);
	}
}
