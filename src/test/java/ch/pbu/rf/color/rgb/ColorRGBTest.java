package ch.pbu.rf.color.rgb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorRGB}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorRGB")
class ColorRGBTest {
	
	@Test
	@DisplayName(value = "Test: ColorRGB(String, String, String)")
	void testConstructor() {
		
		// Positive-Tests
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MIN));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MIN));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MIN));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MIN));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MAX));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MIN, ColorRGBValidator.B_MAX));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MIN, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MAX));
		Testing.assertValid(new ColorRGB(ColorRGBValidator.R_MAX, ColorRGBValidator.G_MAX, ColorRGBValidator.B_MAX));

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
