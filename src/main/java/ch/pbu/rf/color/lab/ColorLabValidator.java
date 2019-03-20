package ch.pbu.rf.color.lab;

import ch.yanicksenn.label.Label;

/**
 * Represents the Color-Lab Validator.
 * 
 * @author Yanick Senn
 */
@Label(resourceName = "ch/pbu/rf/color/lab/Label.properties")
public class ColorLabValidator {
	@Label public static final String LABEL_COLOR_LAB_INVALID = "color.lab.invalid";
	@Label public static final String LABEL_COLOR_LAB_INVALID_L_MIN = "color.lab.invalid.l.min";
	@Label public static final String LABEL_COLOR_LAB_INVALID_L_MAX = "color.lab.invalid.l.max";
	@Label public static final String LABEL_COLOR_LAB_INVALID_A_MIN = "color.lab.invalid.a.min";
	@Label public static final String LABEL_COLOR_LAB_INVALID_A_MAX = "color.lab.invalid.a.max";
	@Label public static final String LABEL_COLOR_LAB_INVALID_B_MIN = "color.lab.invalid.b.min";
	@Label public static final String LABEL_COLOR_LAB_INVALID_B_MAX = "color.lab.invalid.b.max";
	
	public static final String L_MIN = "0";
	public static final String L_MAX = "100";
	public static final String A_MIN = "-170";
	public static final String A_MAX = "100";
	public static final String B_MIN = "-100";
	public static final String B_MAX = "150";
}
