package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.RF;
import ch.pbu.rf.color.ColorType;

/**
 * Test for {@link ColorSpaceRGB}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorSpaceRGB")
class ColorSpaceRGBTest {
	
	@Test
	@DisplayName(value = "Test: ColorSpaceRGB()")
	void testConstructor() {
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB(null, RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null));
		Assertions.assertDoesNotThrow(() -> new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(ColorType.RGB, colorSpace.getType());
	}

	@Test
	@DisplayName(value = "Test: getName()")
	void testGetName() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals("Name", colorSpace.getName());
	}

	@Test
	@DisplayName(value = "Test: getIlluminant()")
	void testGetIlluminant() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(RF.CIE1931.D65, colorSpace.getIlluminant());
	}

	@Test
	@DisplayName(value = "Test: getR()")
	void testGetR() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO), colorSpace.getR());
	}

	@Test
	@DisplayName(value = "Test: getG()")
	void testGetG() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO), colorSpace.getG());
	}

	@Test
	@DisplayName(value = "Test: getB()")
	void testGetB() {
		ColorSpaceRGB colorSpace = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO), colorSpace.getB());
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ColorSpaceRGB colorSpace1 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		ColorSpaceRGB colorSpace2 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(colorSpace1, colorSpace2);

		ColorSpaceRGB colorSpace3 = new ColorSpaceRGB("Name1", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace3);

		ColorSpaceRGB colorSpace4 = new ColorSpaceRGB("Name", RF.CIE1931.D50, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace4);

		ColorSpaceRGB colorSpace5 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace5);

		ColorSpaceRGB colorSpace6 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace6);

		ColorSpaceRGB colorSpace7 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace7);

		ColorSpaceRGB colorSpace8 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace8);

		ColorSpaceRGB colorSpace9 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO);
		Assertions.assertNotEquals(colorSpace1, colorSpace9);

		ColorSpaceRGB colorSpace10 = new ColorSpaceRGB("Name", RF.CIE1931.D65, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE);
		Assertions.assertNotEquals(colorSpace1, colorSpace10);
	}
}
