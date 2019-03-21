package ch.pbu.rf.color;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.RF;
import ch.pbu.rf.color.deltae.Delta;
import ch.pbu.rf.color.deltae.DeltaE1976CalculationException;
import ch.pbu.rf.color.deltae.DeltaE2000CalculationException;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorUtil}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorUtil")
class ColorUtilTest {
	private static final MathContext MC = ColorUtil.MC;
	private static final BigDecimal DELTA = new BigDecimal("0.001");

	@Test
	@DisplayName(value = "Test: getReferenceWhite(Illuminant)")
	void testGetReferenceWhite() {
		ColorXYZ d65_2 = ColorUtil.getReferenceWhite(RF.CIE1931.D50);
		Testing.assertEquals(new BigDecimal("0.964"), d65_2.getX(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("1.000"), d65_2.getY(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("0.825"), d65_2.getZ(), DELTA, MC);
		
		ColorXYZ d65_10 = ColorUtil.getReferenceWhite(RF.CIE1964.D50);
		Testing.assertEquals(new BigDecimal("0.967"), d65_10.getX(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("1.000"), d65_10.getY(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("0.814"), d65_10.getZ(), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: convertToLab(ColorXYZ, Illuminant)")
	void testConvertToLabColor() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ("0.300", "0.700", "0.200"), RF.CIE1931.D50);
		Testing.assertEquals(new BigDecimal("86.996"), lab.getL(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("-105.145"), lab.getA(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("52.884"), lab.getB(), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab)")
	void testCalculateDeltaE1976() throws DeltaE1976CalculationException {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab("52.1", "42.18", "20.92");
		color2 = new ColorLab("52", "41", "25");
		Testing.assertEquals(new BigDecimal("4.248"), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("70.23", "-12.36", "-13.86");
		color2 = new ColorLab("68.2", "-13.8", "-14.4");
		Testing.assertEquals(new BigDecimal("2.547"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("53.13", "-34.86", "13.39");
		color2 = new ColorLab("53", "-34", "17");
		Testing.assertEquals(new BigDecimal("3.713"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("65.46", "25.91", "-0.41");
		color2 = new ColorLab("63.6", "26.4", "-0.5");
		Testing.assertEquals(new BigDecimal("1.926"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("40.65", "7.39", "-21.43");
		color2 = new ColorLab("41", "7", "-22");
		Testing.assertEquals(new BigDecimal("0.774"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("80.72", "-3.89", "36.85");
		color2 = new ColorLab("80.3", "-1.8", "34.2");
		Testing.assertEquals(new BigDecimal("3.401"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("36.23", "1.41", "4.48");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("36.533"), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("75.61", "0.73", "4.84");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("75.768"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("57.02", "-22.23", "-29.89");
		color2 = new ColorLab("57", "-23", "-27");
		Testing.assertEquals(new BigDecimal("2.991"), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("62.48", "1.56", "4.18");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("62.639"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("53.94", "45.5", "0.53");
		color2 = new ColorLab("54", "44", "-2");
		Testing.assertEquals(new BigDecimal("2.942"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("61.31", "0.15", "3.45");
		color2 = new ColorLab("58.6", "0.6", "4");
		Testing.assertEquals(new BigDecimal("2.802"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("79.35", "-4.04", "61.3");
		color2 = new ColorLab("78", "-3", "58");
		Testing.assertEquals(new BigDecimal("3.714"), Delta.E1976.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("51.81", "1.71", "3.13");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("51.933"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("82.63", "-0.58", "3.35");
		color2 = new ColorLab("80", "0.01", "0.01");
		Testing.assertEquals(new BigDecimal("4.292"), Delta.E1976.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("35.61", "1.3", "2.35");
		color2 = new ColorLab("38", "0.01", "0.01");
		Testing.assertEquals(new BigDecimal("3.585"), Delta.E1976.calculate(color1, color2), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab) with neutrality")
	void testCalculateDeltaE1976_neutrality() throws DeltaE2000CalculationException {
		ColorLab color = new ColorLab("52.10", "42.18", "20.92");
		
		BigDecimal dE = Delta.E1976.calculate(color, color);
		Testing.assertEquals(BigDecimal.ZERO, dE, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE1976(ColorLab, ColorLab) with associativity")
	void testCalculateDeltaE1976_associativity() throws DeltaE2000CalculationException {
		ColorLab color1 = new ColorLab("52.10", "42.18", "20.92");
		ColorLab color2 = new ColorLab("52.00", "41.00", "25.00");
		
		BigDecimal dE12 = Delta.E1976.calculate(color1, color2);
		BigDecimal dE21 = Delta.E1976.calculate(color2, color1);
		Testing.assertEquals(dE12, dE21, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab)")
	void testCalculateDeltaE2000() throws DeltaE2000CalculationException {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab("52.1", "42.18", "20.92");
		color2 = new ColorLab("52", "41", "25");
		Testing.assertEquals(new BigDecimal("2.629"), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("70.23", "-12.36", "-13.86");
		color2 = new ColorLab("68.2", "-13.8", "-14.4");
		Testing.assertEquals(new BigDecimal("1.899"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("53.13", "-34.86", "13.39");
		color2 = new ColorLab("53", "-34", "17");
		Testing.assertEquals(new BigDecimal("2.108"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("65.46", "25.91", "-0.41");
		color2 = new ColorLab("63.6", "26.4", "-0.5");
		Testing.assertEquals(new BigDecimal("1.558"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("40.65", "7.39", "-21.43");
		color2 = new ColorLab("41", "7", "-22");
		Testing.assertEquals(new BigDecimal("0.617"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("80.72", "-3.89", "36.85");
		color2 = new ColorLab("80.3", "-1.8", "34.2");
		Testing.assertEquals(new BigDecimal("1.802"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("36.23", "1.41", "4.48");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("24.986"), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("75.61", "0.73", "4.84");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("64.682"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("57.02", "-22.23", "-29.89");
		color2 = new ColorLab("57", "-23", "-27");
		Testing.assertEquals(new BigDecimal("1.437"), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("62.48", "1.56", "4.18");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("49.243"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("53.94", "45.5", "0.53");
		color2 = new ColorLab("54", "44", "-2");
		Testing.assertEquals(new BigDecimal("1.430"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("61.31", "0.15", "3.45");
		color2 = new ColorLab("58.6", "0.6", "4");
		Testing.assertEquals(new BigDecimal("2.513"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("79.35", "-4.04", "61.3");
		color2 = new ColorLab("78", "-3", "58");
		Testing.assertEquals(new BigDecimal("1.418"), Delta.E2000.calculate(color1, color2), DELTA, MC);

		color1 = new ColorLab("51.81", "1.71", "3.13");
		color2 = new ColorLab("0", "0", "0");
		Testing.assertEquals(new BigDecimal("38.405"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("82.63", "-0.58", "3.35");
		color2 = new ColorLab("80", "0.01", "0.01");
		Testing.assertEquals(new BigDecimal("3.674"), Delta.E2000.calculate(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("35.61", "1.3", "2.35");
		color2 = new ColorLab("38", "0.01", "0.01");
		Testing.assertEquals(new BigDecimal("3.481"), Delta.E2000.calculate(color1, color2), DELTA, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab) with neutrality")
	void testCalculateDeltaE2000_neutrality() throws DeltaE2000CalculationException {
		ColorLab color = new ColorLab("52.10", "42.18", "20.92");
		
		BigDecimal dE = Delta.E2000.calculate(color, color);
		Testing.assertEquals(BigDecimal.ZERO, dE, MC);
	}
	
	@Test
	@DisplayName(value = "Test: calculateDeltaE2000(ColorLab, ColorLab) with associativity")
	void testCalculateDeltaE2000_associativity() throws DeltaE2000CalculationException {
		ColorLab color1 = new ColorLab("52.10", "42.18", "20.92");
		ColorLab color2 = new ColorLab("52.00", "41.00", "25.00");
		
		BigDecimal dE12 = Delta.E2000.calculate(color1, color2);
		BigDecimal dE21 = Delta.E2000.calculate(color2, color1);
		Testing.assertEquals(dE12, dE21, MC);
	}
}
