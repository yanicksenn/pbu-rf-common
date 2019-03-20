package ch.pbu.rf.color;

import ch.pbu.rf.color.lab.ColorLab;

/**
 * Represents the delta-e2000-calculation-exception.
 * 
 * @author Yanick Senn
 */
public class DeltaE2000CalculationException extends CalculationException {
	private final ColorLab color1;
	private final ColorLab color2;
	
	/**
	 * Constructor with the first and the second color.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2) {
		super();
		this.color1 = color1;
		this.color2 = color2;
	}
	
	/**
	 * Constructor with the first and the second color and the message.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * @param message Message.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2, String message) {
		super(message);
		this.color1 = color1;
		this.color2 = color2;
	}
	
	/**
	 * Constructor with the first and the second color and the cause.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * @param cause Cause.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2, Throwable cause) {
		super(cause);
		this.color1 = color1;
		this.color2 = color2;
	}
	
	/**
	 * Constructor with the first and the second color, the message and the cause.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * @param message Message.
	 * @param cause Cause.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2, String message, Throwable cause) {
		super(message, cause);
		this.color1 = color1;
		this.color2 = color2;
	}
	
	/**
	 * Returns the first color.
	 * 
	 * @return First color.
	 */
	public ColorLab getColor1() {
		return color1;
	}
	
	/**
	 * Returns the second color.
	 * 
	 * @return Second color.
	 */
	public ColorLab getColor2() {
		return color2;
	}
}
