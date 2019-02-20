package ch.pbu.rf.color;

import java.util.Objects;

/**
 * Represents the Standard-Observer.
 * 
 * @author Yanick Senn
 */
public class Observer {
	private final int year;
	private final int degrees;
	
	/**
	 * Constructor with <I>year</I> and <I>degrees</I>.
	 * 
	 * @param year Year.
	 * @param degrees Degrees.
	 */
	public Observer(int year, int degrees) {
		this.year = year;
		this.degrees = degrees;
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
	
	
	@Override
	public String toString() {
		return String.format("%s[year: %s, degrees: %d]", getClass().getSimpleName(), year, degrees);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(year, degrees);
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
		
		Observer other = (Observer) obj;
		
		return 
			Objects.equals(this.year, other.year) &&
			Objects.equals(this.degrees, other.degrees);
	}
}
