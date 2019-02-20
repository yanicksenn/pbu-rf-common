package ch.pbu.rf.color.rgb;

import ch.yanicksenn.label.Label;

/**
 * Represents the Color-Lab Validator.
 * 
 * @author Yanick Senn
 */
@Label(resourceName = "ch/pbu/rf/color/rgb/Label.properties")
public class ColorRGBValidator {
	@Label public static final String LABEL_COLOR_RGB_INVALID = "color.rgb.invalid";
	@Label public static final String LABEL_COLOR_RGB_INVALID_R_MIN = "color.rgb.invalid.r.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_R_MAX = "color.rgb.invalid.r.max";
	@Label public static final String LABEL_COLOR_RGB_INVALID_G_MIN = "color.rgb.invalid.g.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_G_MAX = "color.rgb.invalid.g.max";
	@Label public static final String LABEL_COLOR_RGB_INVALID_B_MIN = "color.rgb.invalid.b.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_B_MAX = "color.rgb.invalid.b.max";
	
	public static final long R_MIN = 0;
	public static final long R_MAX = 255;
	public static final long G_MIN = 0;
	public static final long G_MAX = 255;
	public static final long B_MIN = 0;
	public static final long B_MAX = 255;
}
