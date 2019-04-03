package ch.pbu.rf.color.deltae;

import java.math.BigDecimal;

import ch.pbu.rf.color.ColorUtil;
import ch.pbu.rf.color.lab.ColorLab;

/**
 * Represents the delta E types.
 * 
 * @author Yanick Senn.
 */
public enum Delta implements DeltaECalculatorDelegate {
	
	/**
	 * Represents the delta E1976.
	 */
	E1976() {
		@Override
		public BigDecimal calculate(ColorLab color1, ColorLab color2) {
			return ColorUtil.calculateDeltaE1976(color1, color2);
		}
		
	},
	
	/**
	 * Represents the delta E2000.
	 */
	E2000() {
		@Override
		public BigDecimal calculate(ColorLab color1, ColorLab color2) {
			return ColorUtil.calculateDeltaE2000(color1, color2);
		}
		
	},
	;
}
