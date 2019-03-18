package ch.pbu.rf.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch.pbu.rf.RF;
import ch.pbu.rf.color.lab.ColorLab;
import ch.pbu.rf.color.xyz.ColorXYZ;

/**
 * Test for {@link ColorUtil}.
 * 
 * @author Yanick Senn
 */
class ColorUtilTest {
	private static final double DELTA = 0.05;

	@Test
	void testGetReferenceWhite() {
		ColorXYZ d65_2 = ColorUtil.getReferenceWhite(RF.CIE1931.D50);
		assertEquals( 94.811, d65_2.getX().doubleValue(), DELTA);
		assertEquals(100.000, d65_2.getY().doubleValue(), DELTA);
		assertEquals(107.304, d65_2.getZ().doubleValue(), DELTA);
		
		ColorXYZ d65_10 = ColorUtil.getReferenceWhite(RF.CIE1964.D50);
		assertEquals( 94.811, d65_10.getX().doubleValue(), DELTA);
		assertEquals(100.000, d65_10.getY().doubleValue(), DELTA);
		assertEquals(107.304, d65_10.getZ().doubleValue(), DELTA);
	}
	
	@Test
	void testConvertToLabColor() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ("0.3", "0.7", "0.2"), RF.CIE1931.D50);
		assertEquals(  87.000, lab.getL().doubleValue(), DELTA);
		assertEquals(-105.150, lab.getA().doubleValue(), DELTA);
		assertEquals(  52.880, lab.getB().doubleValue(), DELTA);
	}
	
	@Test
	void testCalculateDeltaE2000() {
		ColorLab color1 = null;
		ColorLab color2 = null;
		
		color1 = new ColorLab("52.10", "42.18", "20.92");
		color2 = new ColorLab("52.00", "41.00", "25.00");
		assertEquals(2.629, ColorUtil.calculateDeltaE2000(color1, color2).doubleValue(), DELTA);
		
		color1 = new ColorLab("70.23", "-12.36", "-13.86");
		color2 = new ColorLab("68.20", "-13.80", "-14.40");
		assertEquals(1.899, ColorUtil.calculateDeltaE2000(color1, color2).doubleValue(), DELTA);
		
		color1 = new ColorLab("53.13", "-34.86", "13.39");
		color2 = new ColorLab("53.00", "-34.00", "17.00");
		assertEquals(2.108, ColorUtil.calculateDeltaE2000(color1, color2).doubleValue(), DELTA);
	}
}
