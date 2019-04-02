package ch.pbu.rf.color.rgb;

import java.util.Objects;

import ch.pbu.rf.color.space.ColorSpace;
import ch.pbu.rf.color.space.ColorSpaceType;

/**
 * Represents the RGB-color-space.
 * 
 * @author Yanick Senn
 */
public class ColorSpaceRGB extends ColorSpace {
	private ChromaticityCoordinate r;
	private ChromaticityCoordinate g;
	private ChromaticityCoordinate b;
	
	
	/**
	 * Constructor.
	 */
	public ColorSpaceRGB() {
		super(ColorSpaceType.RGB);
	}
	
	
	/**
	 * Returns the r coordinate.
	 * 
	 * @return R coordinate.
	 */
	public ChromaticityCoordinate getR() {
		return r;
	}
	
	/**
	 * Sets the r coordinate.
	 * 
	 * @param r R coordinate.
	 * 
	 * @return This instance.
	 */
	public ColorSpaceRGB setR(ChromaticityCoordinate r) {
		this.r = r;
		return this;
	}

	/**
	 * Returns the g coordinate.
	 * 
	 * @return G coordinate.
	 */
	public ChromaticityCoordinate getG() {
		return g;
	}
	
	/**
	 * Sets the g coordinate.
	 * 
	 * @param g G coordinate.
	 * 
	 * @return This instance.
	 */
	public ColorSpaceRGB setG(ChromaticityCoordinate g) {
		this.g = g;
		return this;
	}

	/**
	 * Returns the b coordinate.
	 * 
	 * @return B coordinate.
	 */
	public ChromaticityCoordinate getB() {
		return b;
	}

	/**
	 * Sets the b coordinate.
	 * 
	 * @param b B coordinate.
	 * 
	 * @return This instance.
	 */
	public ColorSpaceRGB setB(ChromaticityCoordinate b) {
		this.b = b;
		return this;
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
		
		ColorSpaceRGB other = (ColorSpaceRGB) obj;

		return 
			Objects.equals(this.r, other.r) &&
			Objects.equals(this.g, other.g) &&
			Objects.equals(this.b, other.b);
	}
}
