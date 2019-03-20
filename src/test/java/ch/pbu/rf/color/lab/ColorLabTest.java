package ch.pbu.rf.color.lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ColorLab}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ColorLab")
class ColorLabTest {
	
	@Test
	@DisplayName(value = "Test: ColorLab(String, String, String)")
	void test() {
		
		// Positive-Tests
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MIN, ColorLabValidator.B_MIN));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MIN, ColorLabValidator.B_MIN));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MAX, ColorLabValidator.B_MIN));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MAX, ColorLabValidator.B_MIN));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MIN, ColorLabValidator.B_MAX));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MIN, ColorLabValidator.B_MAX));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MAX, ColorLabValidator.B_MAX));
		Testing.assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MAX, ColorLabValidator.B_MAX));

		// TODO: Negative-Tests
//		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MIN - 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MIN - 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MIN - 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MIN - 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MAX + 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MAX + 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MAX + 1));
//		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MAX + 1));
	}
}
