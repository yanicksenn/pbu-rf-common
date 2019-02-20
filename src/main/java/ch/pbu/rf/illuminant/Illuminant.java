
package ch.pbu.rf.illuminant;

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
	private final double x;
	private final double y;
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
	public Illuminant(String name, int year, int degrees, double x, double y, int cct) {
		this.name = Objects.requireNonNull(name, "name is not specified");
		this.year = year;
		this.degrees = degrees;
		this.x = x;
		this.y = y;
		this.cct = cct;
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
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the y-value.
	 * 
	 * @return Y-value.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the z-value.
	 * 
	 * @return Z-value.
	 */
	public double getZ() {
		return 1 - x - y;
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
		return Objects.hash(name, year, degrees, x, y, cct);
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
			Objects.equals(this.x, other.x) &&
			Objects.equals(this.y, other.y) &&
			Objects.equals(this.cct, other.cct);
	}
}
