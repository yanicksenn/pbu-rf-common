
package ch.pbu.rf.color;

import java.util.Objects;

/**
 * Represents the Illuminant.
 * 
 * @author Yanick Senn
 */
public class Illuminant {
	private final Observer observer;
	private final double x;
	private final double y;
	private final int cct;
	
	/**
	 * Constructor with <I>Observer</I>, <I>X</I>, <I>Y</I> and <I>CCT</I>.
	 * 
	 * @param observer Observer.
	 * @param x X-value.
	 * @param y Y-value.
	 * @param cct CCT.
	 */
	public Illuminant(Observer observer, double x, double y, int cct) {
		this.observer = Objects.requireNonNull(observer, "observer is not specified");
		this.x = x;
		this.y = y;
		this.cct = cct;
	}
	
	/**
	 * Returns the observer.
	 * 
	 * @return Observer.
	 */
	public Observer getObserver() {
		return observer;
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
		return String.format("%s[observer: %s, x: %03d, y: %03d, CCT: %d]", getClass().getSimpleName(), observer, x, y, cct);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(observer, x, y, cct);
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
			Objects.equals(this.observer, other.observer) &&
			Objects.equals(this.x, other.x) &&
			Objects.equals(this.y, other.y) &&
			Objects.equals(this.cct, other.cct);
	}
}
