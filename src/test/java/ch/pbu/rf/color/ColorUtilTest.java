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
	private static final double DELTA = 0.01;

	@Test
	void testGetReferenceWhite() {
		ColorXYZ d65_2 = ColorUtil.getReferenceWhite(RF.CIE1931.D50);
		assertEquals( 94.811, d65_2.getX(), DELTA);
		assertEquals(100.000, d65_2.getY(), DELTA);
		assertEquals(107.304, d65_2.getZ(), DELTA);
		
		ColorXYZ d65_10 = ColorUtil.getReferenceWhite(RF.CIE1964.D50);
		assertEquals( 94.811, d65_10.getX(), DELTA);
		assertEquals(100.000, d65_10.getY(), DELTA);
		assertEquals(107.304, d65_10.getZ(), DELTA);
	}
	
	@Test
	void testConvertToLabColor() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ(0.3, 0.7, 0.2), RF.CIE1931.D50);
		assertEquals(  87.000, lab.getL(), DELTA);
		assertEquals(-105.150, lab.getA(), DELTA);
		assertEquals(  52.880, lab.getB(), DELTA);
	}
	
	@Test
	void testCalculateDeltaE2000() {
		ColorLab color1 = new ColorLab(52.10, 42.18, 20.98);
		ColorLab color2 = new ColorLab(52.00, 41.00, 25.00);
		
		double dE = ColorUtil.calculateDeltaE2000(color1, color2);
		System.out.println(dE);
	}
}
