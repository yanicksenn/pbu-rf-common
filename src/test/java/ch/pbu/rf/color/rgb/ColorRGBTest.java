package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.pbu.rf.color.ColorType;
import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorRGB}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorRGB")
class ColorRGBTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);
	
	@Test
	@DisplayName(value = "Test: ColorRGB()")
	void testColorRGB() {
		Assertions.assertThrows(NullPointerException.class, () -> new ColorRGB(null, BigDecimal.ZERO, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorRGB(BigDecimal.ZERO, null, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, null));
		Assertions.assertDoesNotThrow(() -> new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

		ColorRGB color = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(ColorType.RGB, color.getType());
	}
	
	@Test
	@DisplayName(value = "Test: getR()")
	void testGetSetR() {
		ColorRGB color = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getR(), MC);
	}

	@Test
	@DisplayName(value = "Test: getG()")
	void testGetSetG() {
		ColorRGB color = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getG(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: getB()")
	void testGetSetB() {
		ColorRGB color = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getB(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ColorRGB color1 = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		ColorRGB color2 = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(color1, color2);

		ColorRGB color3 = new ColorRGB(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotEquals(color1, color3);
		
		ColorRGB color4 = new ColorRGB(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO);
		Assertions.assertNotEquals(color1, color4);
		
		ColorRGB color5 = new ColorRGB(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE);
		Assertions.assertNotEquals(color1, color5);
		
		ColorRGB color6 = new ColorRGB(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
		Assertions.assertNotEquals(color1, color6);
	}
}
