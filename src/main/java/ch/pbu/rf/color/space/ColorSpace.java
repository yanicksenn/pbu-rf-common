package ch.pbu.rf.color.space;

import java.util.Objects;

/**
 * Represents the colorspace.
 * 
 * @author Yanick Senn
 */
public abstract class ColorSpace {
	private final ColorSpaceType type;
	
	
	/**
	 * Constructor with type.
	 * 
	 * @param type Type.
	 */
	public ColorSpace(ColorSpaceType type) {
		this.type = Objects.requireNonNull(type, "type is not specified");
	}
	
	
	/**
	 * Returns the type.
	 * 
	 * @return The type.
	 */
	public ColorSpaceType getType() {
		return type;
	}
}
