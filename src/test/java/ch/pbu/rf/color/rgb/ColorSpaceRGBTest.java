package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.color.space.ColorSpaceType;

/**
 * Test for {@link ColorSpaceRGB}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorSpaceRGB")
class ColorSpaceRGBTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	
	@Test
	@DisplayName(value = "Test: ColorSpaceRGB()")
	void testConstructor() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB();
		Assertions.assertEquals(ColorSpaceType.RGB, colorSpace.getType());
	}
	
	
	@Test
	@DisplayName(value = "Test: getR()")
	void testGetR() {
		ChromaticityCoordinate r = new ChromaticityCoordinate()	
			.setX(new BigDecimal("0.64", MC))
			.setY(new BigDecimal("0.33", MC));
		
		ChromaticityCoordinate g = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.30", MC))
			.setY(new BigDecimal("0.60", MC));
		
		ChromaticityCoordinate b = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.15", MC))
			.setY(new BigDecimal("0.06", MC));
		
		ColorSpaceRGB colorSpace = new ColorSpaceRGB()
			.setR(r)
			.setG(g)
			.setB(b);
		
		Assertions.assertEquals(r, colorSpace.getR());
	}
	
	@Test
	@DisplayName(value = "Test: getR() / setR(BigDecimal)")
	void testGetSetR() {
		ChromaticityCoordinate r = new ChromaticityCoordinate()	
			.setX(new BigDecimal("0.64", MC))
			.setY(new BigDecimal("0.33", MC));
		
		ColorSpaceRGB colorSpace = new ColorSpaceRGB();
		Assertions.assertEquals(null, colorSpace.getR());
		
		colorSpace.setR(r);
		Assertions.assertEquals(r, colorSpace.getR());
	}
	
	@Test
	@DisplayName(value = "Test: getG() / setG(BigDecimal)")
	void testSetG() {
		ChromaticityCoordinate g = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.30", MC))
			.setY(new BigDecimal("0.60", MC));
		
		ColorSpaceRGB colorSpace = new ColorSpaceRGB();
		Assertions.assertEquals(null, colorSpace.getG());
		
		colorSpace.setG(g);
		Assertions.assertEquals(g, colorSpace.getG());
	}
	
	@Test
	@DisplayName(value = "Test: getB() / setB(BigDecimal)")
	void testSetB() {
		ChromaticityCoordinate b = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.15", MC))
			.setY(new BigDecimal("0.06", MC));
		
		ColorSpaceRGB colorSpace = new ColorSpaceRGB();
		Assertions.assertEquals(null, colorSpace.getB());
		
		colorSpace.setB(b);
		Assertions.assertEquals(b, colorSpace.getB());
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ChromaticityCoordinate r1 = new ChromaticityCoordinate()	
			.setX(new BigDecimal("0.64", MC))
			.setY(new BigDecimal("0.33", MC));

		ChromaticityCoordinate g1 = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.30", MC))
			.setY(new BigDecimal("0.60", MC));

		ChromaticityCoordinate b1 = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.15", MC))
			.setY(new BigDecimal("0.06", MC));
		
		ColorSpaceRGB colorSpace1 = new ColorSpaceRGB()
			.setR(r1)
			.setG(g1)
			.setB(b1);
		
		ColorSpaceRGB colorSpace2 = new ColorSpaceRGB()
			.setR(r1)
			.setG(g1)
			.setB(b1);
		
		Assertions.assertEquals(colorSpace1, colorSpace2);
		
		ChromaticityCoordinate g2 = new ChromaticityCoordinate()
			.setX(new BigDecimal("0.15", MC))
			.setY(new BigDecimal("0.06", MC));
		
		colorSpace2.setG(g2);
		Assertions.assertNotEquals(colorSpace1, colorSpace2);
	}
}
