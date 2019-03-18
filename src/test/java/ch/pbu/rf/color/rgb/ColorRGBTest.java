package ch.pbu.rf.color.rgb;

import org.junit.jupiter.api.Test;

import ch.pbu.rf.AbstractTestCase;

/**
 * Test for {@link ColorRGB}.
 * 
 * @author Yanick Senn
 */
class ColorRGBTest extends AbstractTestCase {
	
	@Test
	void test() {
		
		// Positive-Tests
		assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MIN));
		assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MIN));
		assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MIN));
		assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MIN));
		assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MAX));
		assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MAX));
		assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MAX));
		assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MAX));

		// TODO: Negative-Tests
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MIN - 1, ColorRGBValidator.G_MIN - 1, ColorRGBValidator.B_MIN - 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MAX + 1, ColorRGBValidator.G_MIN - 1, ColorRGBValidator.B_MIN - 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MIN - 1, ColorRGBValidator.G_MAX + 1, ColorRGBValidator.B_MIN - 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MAX + 1, ColorRGBValidator.G_MAX + 1, ColorRGBValidator.B_MIN - 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MIN - 1, ColorRGBValidator.G_MIN - 1, ColorRGBValidator.B_MAX + 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MAX + 1, ColorRGBValidator.G_MIN - 1, ColorRGBValidator.B_MAX + 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MIN - 1, ColorRGBValidator.G_MAX + 1, ColorRGBValidator.B_MAX + 1));
//		assertInvalid(new ColorRGB(ColorRGBValidator.R_MAX + 1, ColorRGBValidator.G_MAX + 1, ColorRGBValidator.B_MAX + 1));
	}
}
