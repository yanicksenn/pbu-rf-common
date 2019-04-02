package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;

import ch.pbu.rf.RF;
import ch.yanicksenn.label.Label;

/**
 * Represents the Color-Lab Validator.
 * 
 * @author Yanick Senn
 */
@Label(resourceName = "ch/pbu/rf/color/rgb/Label.properties")
public class ColorRGBValidator {
	@Label public static final String LABEL_COLOR_RGB_INVALID = "color.rgb.invalid";
	@Label public static final String LABEL_COLOR_RGB_INVALID_R_NULL = "color.rgb.invalid.r.null";
	@Label public static final String LABEL_COLOR_RGB_INVALID_R_MIN = "color.rgb.invalid.r.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_R_MAX = "color.rgb.invalid.r.max";
	@Label public static final String LABEL_COLOR_RGB_INVALID_G_NULL = "color.rgb.invalid.g.null";
	@Label public static final String LABEL_COLOR_RGB_INVALID_G_MIN = "color.rgb.invalid.g.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_G_MAX = "color.rgb.invalid.g.max";
	@Label public static final String LABEL_COLOR_RGB_INVALID_B_NULL = "color.rgb.invalid.b.null";
	@Label public static final String LABEL_COLOR_RGB_INVALID_B_MIN = "color.rgb.invalid.b.min";
	@Label public static final String LABEL_COLOR_RGB_INVALID_B_MAX = "color.rgb.invalid.b.max";
	
	public static final String R_MIN_STRING = "0";
	public static final BigDecimal R_MIN = new BigDecimal(R_MIN_STRING, RF.MC);
	
	public static final String R_MAX_STRING = "255";
	public static final BigDecimal R_MAX = new BigDecimal(R_MAX_STRING, RF.MC);
	
	public static final String G_MIN_STRING = "0";
	public static final BigDecimal G_MIN = new BigDecimal(G_MIN_STRING, RF.MC);
	
	public static final String G_MAX_STRING = "255";
	public static final BigDecimal G_MAX = new BigDecimal(G_MAX_STRING, RF.MC);
	
	public static final String B_MIN_STRING = "0";
	public static final BigDecimal B_MIN = new BigDecimal(B_MIN_STRING, RF.MC);
	
	public static final String B_MAX_STRING = "255";
	public static final BigDecimal B_MAX = new BigDecimal(B_MAX_STRING, RF.MC);
}
