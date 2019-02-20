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

	@Test
	void testGetReferenceWhite() {
		ColorXYZ d65_2 = ColorUtil.getReferenceWhite(RF.CIE1931.D65);
		assertEquals( 94.811, d65_2.getX(), 0.002);
		assertEquals(100.000, d65_2.getY(), 0.002);
		assertEquals(107.304, d65_2.getZ(), 0.002);
		
		ColorXYZ d65_10 = ColorUtil.getReferenceWhite(RF.CIE1964.D65);
		assertEquals( 94.811, d65_10.getX(), 0.002);
		assertEquals(100.000, d65_10.getY(), 0.002);
		assertEquals(107.304, d65_10.getZ(), 0.002);
	}
	
	@Test
	void testConvertToLabColor() {
		ColorLab lab = ColorUtil.convertToLab(new ColorXYZ(0.3, 0.7, 0.2), RF.CIE1931.D50);
		assertEquals(  87.000, lab.getL(), 0.002);
		assertEquals(-105.150, lab.getA(), 0.002);
		assertEquals(  52.880, lab.getB(), 0.002);
	}

}
