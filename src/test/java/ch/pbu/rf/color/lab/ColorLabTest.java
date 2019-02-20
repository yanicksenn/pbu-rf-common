package ch.pbu.rf.color.lab;

import org.junit.jupiter.api.Test;

import ch.pbu.rf.AbstractTestCase;

/**
 * Test for {@link ColorLab}.
 * 
 * @author Yanick Senn
 */
class ColorLabTest extends AbstractTestCase {
	
	@Test
	void test() {
		
		// Positive-Tests
		assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MIN, ColorLabValidator.B_MIN));
		assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MIN, ColorLabValidator.B_MIN));
		assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MAX, ColorLabValidator.B_MIN));
		assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MAX, ColorLabValidator.B_MIN));
		assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MIN, ColorLabValidator.B_MAX));
		assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MIN, ColorLabValidator.B_MAX));
		assertValid(new ColorLab(ColorLabValidator.L_MIN, ColorLabValidator.A_MAX, ColorLabValidator.B_MAX));
		assertValid(new ColorLab(ColorLabValidator.L_MAX, ColorLabValidator.A_MAX, ColorLabValidator.B_MAX));

		// Negative-Tests
		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MIN - 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MIN - 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MIN - 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MIN - 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MAX + 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MIN - 1, ColorLabValidator.B_MAX + 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MIN - 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MAX + 1));
		assertInvalid(new ColorLab(ColorLabValidator.L_MAX + 1, ColorLabValidator.A_MAX + 1, ColorLabValidator.B_MAX + 1));
	}
}
