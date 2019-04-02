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
	void testConstructor() {
		ColorRGB color = new ColorRGB();
		Assertions.assertEquals(ColorType.RGB, color.getType());
	}
	
	@Test
	@DisplayName(value = "Test: getR() / setR(BigDecimal)")
	void testGetSetR() {
		ColorRGB color = new ColorRGB();
		Testing.assertEquals(null, color.getR(), MC);
		
		color.setR(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getR(), MC);
	}

	@Test
	@DisplayName(value = "Test: getG() / setG(BigDecimal)")
	void testGetSetG() {
		ColorRGB color = new ColorRGB();
		Testing.assertEquals(null, color.getG(), MC);
		
		color.setG(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getG(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: getB() / setB(BigDecimal)")
	void testGetSetB() {
		ColorRGB color = new ColorRGB();
		Testing.assertEquals(null, color.getB(), MC);
		
		color.setB(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, color.getB(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ColorRGB color1 = new ColorRGB();
		ColorRGB color2 = new ColorRGB();
		
		Assertions.assertEquals(color1, color2);
		
		color2.setG(BigDecimal.ZERO);
		Assertions.assertNotEquals(color1, color2);
	}
}
