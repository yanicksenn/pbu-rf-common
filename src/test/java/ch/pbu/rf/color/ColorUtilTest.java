package ch.pbu.rf.color;

import static ch.pbu.rf.MathUtil.bd;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.RF;
import ch.pbu.rf.color.deltae.Delta;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.rgb.ColorSpaceRGB;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.pbu.rf.illuminant.Illuminant;
import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorUtil}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorUtil")
class ColorUtilTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	private static final BigDecimal DELTA = bd("0.001", MC);


	@Test
	@DisplayName(value = "Test: calculateReferenceWhite(Illuminant) - Exceptions")
	void testCalculateReferenceWhite_exceptions() {
		Assertions.assertThrows(NullPointerException.class, () -> ColorUtil.calculateReferenceWhite(null));
		Assertions.assertDoesNotThrow(() -> ColorUtil.calculateReferenceWhite(RF.CIE1931.D50));
	}
	
	@Test
	@DisplayName(value = "Test: calculateReferenceWhite(Illuminant) - CIE1931 D50")
	void testCalculateReferenceWhite_cie1931_d50() {
		ColorXYZ d65_2 = ColorUtil.calculateReferenceWhite(RF.CIE1931.D50);
		Testing.assertEquals(bd("0.964", MC), d65_2.getX(), DELTA, MC);
		Testing.assertEquals(bd("1.000", MC), d65_2.getY(), DELTA, MC);
		Testing.assertEquals(bd("0.825", MC), d65_2.getZ(), DELTA, MC);
	}

	@Test
	@DisplayName(value = "Test: calculateReferenceWhite(Illuminant) - CIE1964 D50")
	void testCalculateReferenceWhite_cie1964_d50() {
		ColorXYZ d65_10 = ColorUtil.calculateReferenceWhite(RF.CIE1964.D50);
		Testing.assertEquals(bd("0.967", MC), d65_10.getX(), DELTA, MC);
		Testing.assertEquals(bd("1.000", MC), d65_10.getY(), DELTA, MC);
		Testing.assertEquals(bd("0.814", MC), d65_10.getZ(), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: convertToLab(ColorXYZ, Illuminant) - Exceptions")
	void testConvertToLabColor_exceptions() {
		Assertions.assertThrows(NullPointerException.class, () -> ColorUtil.convertToLab(null, RF.CIE1931.D50));
		Assertions.assertThrows(NullPointerException.class, () -> ColorUtil.convertToLab(new ColorXYZ(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), null));
		Assertions.assertDoesNotThrow(() -> ColorUtil.convertToLab(new ColorXYZ(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO), RF.CIE1931.D50));
	}
	
	@Test
	@DisplayName(value = "Test: convertToLab(ColorXYZ, Illuminant) - CIE1931 D50")
	void testConvertToLabColor_cie1964_d50() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ(bd("0.300", MC), bd("0.700", MC), bd("0.200", MC)), RF.CIE1931.D50);
		Testing.assertEquals(bd("86.996", MC), lab.getL(), DELTA, MC);
		Testing.assertEquals(bd("-105.145", MC), lab.getA(), DELTA, MC);
		Testing.assertEquals(bd("52.884", MC), lab.getB(), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateRGBTransformationMatrix(ColorSpaceRGB) - Exceptions")
	void testCalculateRGBTransformationMatrix_exceptions() {
		Assertions.assertThrows(NullPointerException.class, () -> ColorUtil.calculateRGBTransformationMatrix(RF.RGB.ColorSpace.sRGB));
		Assertions.assertDoesNotThrow(() -> ColorUtil.calculateRGBTransformationMatrix(RF.RGB.ColorSpace.sRGB));
	}
	
	@Test
	@DisplayName(value = "Test: calculateRGBTransformationMatrix(ColorSpaceRGB) - sRGB")
	void testCalculateRGBTransformationMatrix_sRGB_cie1931_d50() {
		BigDecimal[][] matrix = ColorUtil.calculateRGBTransformationMatrix(RF.RGB.ColorSpace.sRGB);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab)")
	void testCalculateDeltaE1976() {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab(bd("52.1", MC), bd("42.18", MC), bd("20.92", MC));
		color2 = new ColorLab(bd("52", MC), bd("41", MC), bd("25", MC));
		Testing.assertEquals(bd("4.248", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("70.23", MC), bd("-12.36", MC), bd("-13.86", MC));
		color2 = new ColorLab(bd("68.2", MC), bd("-13.8", MC), bd("-14.4", MC));
		Testing.assertEquals(bd("2.547", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("53.13", MC), bd("-34.86", MC), bd("13.39", MC));
		color2 = new ColorLab(bd("53", MC), bd("-34", MC), bd("17", MC));
		Testing.assertEquals(bd("3.713", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("65.46", MC), bd("25.91", MC), bd("-0.41", MC));
		color2 = new ColorLab(bd("63.6", MC), bd("26.4", MC), bd("-0.5", MC));
		Testing.assertEquals(bd("1.926", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("40.65", MC), bd("7.39", MC), bd("-21.43", MC));
		color2 = new ColorLab(bd("41", MC), bd("7", MC), bd("-22", MC));
		Testing.assertEquals(bd("0.774", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("80.72", MC), bd("-3.89", MC), bd("36.85", MC));
		color2 = new ColorLab(bd("80.3", MC), bd("-1.8", MC), bd("34.2", MC));
		Testing.assertEquals(bd("3.401", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("36.23", MC), bd("1.41", MC), bd("4.48", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("36.533", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("75.61", MC), bd("0.73", MC), bd("4.84", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("75.768", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("57.02", MC), bd("-22.23", MC), bd("-29.89", MC));
		color2 = new ColorLab(bd("57", MC), bd("-23", MC), bd("-27", MC));
		Testing.assertEquals(bd("2.991", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("62.48", MC), bd("1.56", MC), bd("4.18", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("62.639", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("53.94", MC), bd("45.5", MC), bd("0.53", MC));
		color2 = new ColorLab(bd("54", MC), bd("44", MC), bd("-2", MC));
		Testing.assertEquals(bd("2.942", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("61.31", MC), bd("0.15", MC), bd("3.45", MC));
		color2 = new ColorLab(bd("58.6", MC), bd("0.6", MC), bd("4", MC));
		Testing.assertEquals(bd("2.802", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("79.35", MC), bd("-4.04", MC), bd("61.3", MC));
		color2 = new ColorLab(bd("78", MC), bd("-3", MC), bd("58", MC));
		Testing.assertEquals(bd("3.714", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("51.81", MC), bd("1.71", MC), bd("3.13", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("51.933", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("82.63", MC), bd("-0.58", MC), bd("3.35", MC));
		color2 = new ColorLab(bd("80", MC), bd("0.01", MC), bd("0.01", MC));
		Testing.assertEquals(bd("4.292", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("35.61", MC), bd("1.3", MC), bd("2.35", MC));
		color2 = new ColorLab(bd("38", MC), bd("0.01", MC), bd("0.01", MC));
		Testing.assertEquals(bd("3.585", MC), Delta.E1976.calculate(color1, color2), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab) with neutrality")
	void testCalculateDeltaE1976_neutrality() {
		ColorLab color = new ColorLab(bd("52.10", MC), bd("42.18", MC), bd("20.92", MC));
		
		BigDecimal dE = Delta.E1976.calculate(color, color);
		Testing.assertEquals(BigDecimal.ZERO, dE, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab) with associativity")
	void testCalculateDeltaE1976_associativity() {
		ColorLab color1 = new ColorLab(bd("52.10", MC), bd("42.18", MC), bd("20.92", MC));
		ColorLab color2 = new ColorLab(bd("52.00", MC), bd("41.00", MC), bd("25.00", MC));
		
		BigDecimal dE12 = Delta.E1976.calculate(color1, color2);
		BigDecimal dE21 = Delta.E1976.calculate(color2, color1);
		Testing.assertEquals(dE12, dE21, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab)")
	void testCalculateDeltaE2000() {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab(bd("52.1", MC), bd("42.18", MC), bd("20.92", MC));
		color2 = new ColorLab(bd("52", MC), bd("41", MC), bd("25", MC));
		Testing.assertEquals(bd("2.629", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("70.23", MC), bd("-12.36", MC), bd("-13.86", MC));
		color2 = new ColorLab(bd("68.2", MC), bd("-13.8", MC), bd("-14.4", MC));
		Testing.assertEquals(bd("1.899", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("53.13", MC), bd("-34.86", MC), bd("13.39", MC));
		color2 = new ColorLab(bd("53", MC), bd("-34", MC), bd("17", MC));
		Testing.assertEquals(bd("2.108", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("65.46", MC), bd("25.91", MC), bd("-0.41", MC));
		color2 = new ColorLab(bd("63.6", MC), bd("26.4", MC), bd("-0.5", MC));
		Testing.assertEquals(bd("1.558", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("40.65", MC), bd("7.39", MC), bd("-21.43", MC));
		color2 = new ColorLab(bd("41", MC), bd("7", MC), bd("-22", MC));
		Testing.assertEquals(bd("0.617", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("80.72", MC), bd("-3.89", MC), bd("36.85", MC));
		color2 = new ColorLab(bd("80.3", MC), bd("-1.8", MC), bd("34.2", MC));
		Testing.assertEquals(bd("1.802", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("36.23", MC), bd("1.41", MC), bd("4.48", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("24.986", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("75.61", MC), bd("0.73", MC), bd("4.84", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("64.682", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("57.02", MC), bd("-22.23", MC), bd("-29.89", MC));
		color2 = new ColorLab(bd("57", MC), bd("-23", MC), bd("-27", MC));
		Testing.assertEquals(bd("1.437", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("62.48", MC), bd("1.56", MC), bd("4.18", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("49.243", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("53.94", MC), bd("45.5", MC), bd("0.53", MC));
		color2 = new ColorLab(bd("54", MC), bd("44", MC), bd("-2", MC));
		Testing.assertEquals(bd("1.430", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("61.31", MC), bd("0.15", MC), bd("3.45", MC));
		color2 = new ColorLab(bd("58.6", MC), bd("0.6", MC), bd("4", MC));
		Testing.assertEquals(bd("2.513", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("79.35", MC), bd("-4.04", MC), bd("61.3", MC));
		color2 = new ColorLab(bd("78", MC), bd("-3", MC), bd("58", MC));
		Testing.assertEquals(bd("1.418", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab(bd("51.81", MC), bd("1.71", MC), bd("3.13", MC));
		color2 = new ColorLab(bd("0", MC), bd("0", MC), bd("0", MC));
		Testing.assertEquals(bd("38.405", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("82.63", MC), bd("-0.58", MC), bd("3.35", MC));
		color2 = new ColorLab(bd("80", MC), bd("0.01", MC), bd("0.01", MC));
		Testing.assertEquals(bd("3.674", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab(bd("35.61", MC), bd("1.3", MC), bd("2.35", MC));
		color2 = new ColorLab(bd("38", MC), bd("0.01", MC), bd("0.01", MC));
		Testing.assertEquals(bd("3.481", MC), Delta.E2000.calculate(color1, color2), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab) with neutrality")
	void testCalculateDeltaE2000_neutrality() {
		ColorLab color = new ColorLab(bd("52.10", MC), bd("42.18", MC), bd("20.92", MC));
		
		BigDecimal dE = Delta.E2000.calculate(color, color);
		Testing.assertEquals(BigDecimal.ZERO, dE, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab) with associativity")
	void testCalculateDeltaE2000_associativity() {
		ColorLab color1 = new ColorLab(bd("52.10", MC), bd("42.18", MC), bd("20.92", MC));
		ColorLab color2 = new ColorLab(bd("52.00", MC), bd("41.00", MC), bd("25.00", MC));
		
		BigDecimal dE12 = Delta.E2000.calculate(color1, color2);
		BigDecimal dE21 = Delta.E2000.calculate(color2, color1);
		Testing.assertEquals(dE12, dE21, MC);
	}
}
