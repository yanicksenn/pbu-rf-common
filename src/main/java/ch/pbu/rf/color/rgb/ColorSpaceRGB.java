package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.util.Objects;

import ch.pbu.rf.color.ColorType;
import ch.pbu.rf.color.space.ColorSpace;
import ch.pbu.rf.illuminant.Illuminant;

/**
 * Represents the color space rgb.
 * 
 * @author Yanick Senn
 */
public class ColorSpaceRGB extends ColorSpace {
	private final Illuminant illuminant;
	private final ChromaticityCoordinate r;
	private final ChromaticityCoordinate g;
	private final ChromaticityCoordinate b;
	

	/**
	 * Constructor with name, illuminant, rx, ry, gx, gy, bx and by.
	 * 
	 * @param name Name.
	 * @param illuminant Illuminant.
	 * @param rx Rx.
	 * @param ry Ry.
	 * @param gx Gx.
	 * @param gy Gy.
	 * @param bx Bx.
	 * @param by By.
	 * 
	 * @throws NullPointerException If name is not specified.
	 * @throws NullPointerException If illuminant is not specified.
	 * @throws NullPointerException If rx is not specified.
	 * @throws NullPointerException If ry is not specified.
	 * @throws NullPointerException If gx is not specified.
	 * @throws NullPointerException If gy is not specified.
	 * @throws NullPointerException If bx is not specified.
	 * @throws NullPointerException If by is not specified.
	 */
	public ColorSpaceRGB(String name, Illuminant illuminant, BigDecimal rx, BigDecimal ry, BigDecimal gx, BigDecimal gy, BigDecimal bx, BigDecimal by) {
		super(name, ColorType.RGB);
		this.illuminant = Objects.requireNonNull(illuminant, "illuminant is not specified");
		
		Objects.requireNonNull(rx, "rx is not specified");
		Objects.requireNonNull(ry, "ry is not specified");
		this.r = new ChromaticityCoordinate(rx, ry);
		
		Objects.requireNonNull(gx, "gx is not specified");
		Objects.requireNonNull(gy, "gy is not specified");
		this.g = new ChromaticityCoordinate(gx, gy);
		
		Objects.requireNonNull(bx, "bx is not specified");
		Objects.requireNonNull(by, "by is not specified");
		this.b = new ChromaticityCoordinate(bx, by);
	}
	
	/**
	 * Constructor with name, r, g and b.
	 * 
	 * @param name Name.
	 * @param r R.
	 * @param g G.
	 * @param b B.
	 * 
	 * @throws NullPointerException If name is not specified.
	 * @throws NullPointerException If r is not specified.
	 * @throws NullPointerException If g is not specified.
	 * @throws NullPointerException If b is not specified.
	 */
	public ColorSpaceRGB(String name, Illuminant illuminant, ChromaticityCoordinate r, ChromaticityCoordinate g, ChromaticityCoordinate b) {
		super(name, ColorType.RGB);
		this.illuminant = Objects.requireNonNull(illuminant, "illuminant is not specified");
		this.r = Objects.requireNonNull(r, "r is not specified");
		this.g = Objects.requireNonNull(g, "g is not specified");
		this.b = Objects.requireNonNull(b, "b is not specified");
	}
	
	
	/**
	 * Returns the illuminant.
	 * 
	 * @return The illuminant.
	 */
	public Illuminant getIlluminant() {
		return illuminant;
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
	 * Returns the g coordinate.
	 * 
	 * @return G coordinate.
	 */
	public ChromaticityCoordinate getG() {
		return g;
	}

	/**
	 * Returns the b coordinate.
	 * 
	 * @return B coordinate.
	 */
	public ChromaticityCoordinate getB() {
		return b;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), r, g, b);
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}

		ColorSpaceRGB other = (ColorSpaceRGB) obj;
		
		return 
			Objects.equals(this.illuminant, other.illuminant) &&
			Objects.equals(this.r, other.r) &&
			Objects.equals(this.g, other.g) &&
			Objects.equals(this.b, other.b);
	}
}
