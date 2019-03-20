package ch.pbu.rf.color;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the abstract Color.
 * 
 * @author Yanick Senn
 */
public abstract class Color implements Serializable {
	
	private final ColorType type;
	
	
	/**
	 * Consdtructor with <I>type</I>. 
	 * 
	 * @param type Type.
	 */
	public Color(ColorType type) {
		this.type = Objects.requireNonNull(type, "type is not specified");
	}
	
	
	/**
	 * Returns the type.
	 * 
	 * @return Type.
	 */
	public ColorType getType() {
		return type;
	}
}
