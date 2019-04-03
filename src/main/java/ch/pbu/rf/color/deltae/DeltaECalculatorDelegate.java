package ch.pbu.rf.color.deltae;

import java.math.BigDecimal;

import ch.pbu.rf.color.lab.ColorLab;

/**
 * Represents the delta-e-calculation-delegator.
 * 
 * @author Yanick Senn
 */
public interface DeltaECalculatorDelegate {
	
	/**
	 * Calculates the corresponding delta E.
	 * 
	 * @param color1 First color.
	 * @param color2 Second color.
	 * 
	 * @return The corresponding delta E.
	 * 
	 * @throws NullPointerException If color1 is not specified.
	 * @throws NullPointerException If color2 is not specified.
	 */
	BigDecimal calculate(ColorLab color1, ColorLab color2);
}