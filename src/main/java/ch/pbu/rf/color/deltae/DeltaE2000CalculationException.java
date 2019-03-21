package ch.pbu.rf.color.deltae;

import ch.pbu.rf.color.lab.ColorLab;

/**
 * Represents the delta-e2000-calculation-exception.
 * 
 * @author Yanick Senn
 */
public class DeltaE2000CalculationException extends DeltaECalculationException {
	
	/**
	 * Constructor with the first and the second color.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2) {
		super(color1, color2);
	}
	
	/**
	 * Constructor with the first and the second color and the message.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * @param message Message.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2, String message) {
		super(color1, color2, message);
	}
	
	/**
	 * Constructor with the first and the second color and the cause.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * @param cause Cause.
	 */
	public DeltaE2000CalculationException(ColorLab color1, ColorLab color2, Throwable cause) {
		super(color1, color2, cause);
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
		super(color1, color2, message, cause);
	}
}
