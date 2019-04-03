package ch.pbu.rf.color.space;

import java.util.Objects;

import ch.pbu.rf.color.ColorType;

/**
 * Represents the colorspace.
 * 
 * @author Yanick Senn
 */
public abstract class ColorSpace {
	private final String name;
	private final ColorType type;
	
	
	/**
	 * Constructor with name, illuminant and type.
	 * 
	 * @param name Name.
	 * @param type Type.
	 */
	public ColorSpace(String name, ColorType type) {
		this.name = Objects.requireNonNull(name, "name is not specified");
		this.type = Objects.requireNonNull(type, "type is not specified");
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the type.
	 * 
	 * @return The type.
	 */
	public ColorType getType() {
		return type;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, type);
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
		
		ColorSpace other = (ColorSpace) obj;

		return 
			Objects.equals(this.name, other.name) &&
			Objects.equals(this.type, other.type);
	}
}
