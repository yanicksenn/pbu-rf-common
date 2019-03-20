package ch.pbu.rf.color;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

import ch.pbu.rf.RF;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;
import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorUtil}.
 * 
 * @author Yanick Senn
 */
class ColorUtilTest {
	private static final MathContext MC = ColorUtil.MC;
	private static final BigDecimal DELTA = new BigDecimal("0.002");

	@Test
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
	void testConvertToLabColor() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ("0.300", "0.700", "0.200"), RF.CIE1931.D50);
		Testing.assertEquals(new BigDecimal("86.996"), lab.getL(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("-105.145"), lab.getA(), DELTA, MC);
		Testing.assertEquals(new BigDecimal("52.885"), lab.getB(), DELTA, MC);
	}
	
	@Test
	void testCalculateDeltaE2000() {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab("52.10", "42.18", "20.92");
		color2 = new ColorLab("52.00", "41.00", "25.00");
		Testing.assertEquals(new BigDecimal("2.629"), ColorUtil.calculateDeltaE2000(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("70.23", "-12.36", "-13.86");
		color2 = new ColorLab("68.20", "-13.80", "-14.40");
		Testing.assertEquals(new BigDecimal("1.899"), ColorUtil.calculateDeltaE2000(color1, color2), DELTA, MC);
		
		color1 = new ColorLab("53.13", "-34.86", "13.39");
		color2 = new ColorLab("53.00", "-34.00", "17.00");
		Testing.assertEquals(new BigDecimal("2.108"), ColorUtil.calculateDeltaE2000(color1, color2), DELTA, MC);
	}
}
