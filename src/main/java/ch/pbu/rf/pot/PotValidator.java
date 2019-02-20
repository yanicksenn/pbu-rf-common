package ch.pbu.rf.pot;

import ch.yanicksenn.label.Label;

/**
 * Represents the Color-Lab Validator.
 * 
 * @author Yanick Senn
 */
@Label(resourceName = "ch/pbu/rf/pot/Label.properties")
public class PotValidator {
	@Label public static final String LABEL_POT_INVALID = "pot.invalid";
	@Label public static final String LABEL_POT_INVALID_NAME_NULL = "pot.invalid.name.null";
	@Label public static final String LABEL_POT_INVALID_NAME_EMPTY = "pot.invalid.name.empty";
	@Label public static final String LABEL_POT_INVALID_COLOR_NULL = "pot.invalid.color.null";
	@Label public static final String LABEL_POT_INVALID_AMOUNT_IN_LITER_MIN = "pot.invalid.amountInLiter.min";
	
	public static final int AMOUNT_IN_LITER_MIN = 0;
}
