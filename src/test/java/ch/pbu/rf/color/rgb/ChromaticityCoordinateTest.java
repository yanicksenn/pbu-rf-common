package ch.pbu.rf.color.rgb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.yanicksenn.testing.Testing;

/**
 * Test for {@link ChromaticityCoordinate}.
 * 
 * @author Yanick Senn
 */
@DisplayName(value = "Test: ChromaticityCoordinate")
public class ChromaticityCoordinateTest {
	private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);

	@Test
	@DisplayName(value = "Test: ChromaticityCoordinate(BigDecimal, BigDecimal)")
	void testConstructor() {
		Assertions.assertThrows(NullPointerException.class, () -> new ChromaticityCoordinate(null, BigDecimal.ZERO));
		Assertions.assertThrows(NullPointerException.class, () -> new ChromaticityCoordinate(BigDecimal.ZERO, null));
		Assertions.assertDoesNotThrow(() -> new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO));
	}
	
	@Test
	@DisplayName(value = "Test: getX()")
	void testGetSetX() {
		ChromaticityCoordinate cc = new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, cc.getX(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: getY()")
	void testGetSetY() {
		ChromaticityCoordinate cc = new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, cc.getY(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ChromaticityCoordinate cc1 = new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO);
		ChromaticityCoordinate cc2 = new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertEquals(cc1, cc2);

		ChromaticityCoordinate cc3 = new ChromaticityCoordinate(BigDecimal.ONE, BigDecimal.ZERO);
		Assertions.assertNotEquals(cc1, cc3);
		
		ChromaticityCoordinate cc4 = new ChromaticityCoordinate(BigDecimal.ZERO, BigDecimal.ONE);
		Assertions.assertNotEquals(cc1, cc4);
		
		ChromaticityCoordinate cc5 = new ChromaticityCoordinate(BigDecimal.ONE, BigDecimal.ONE);
		Assertions.assertNotEquals(cc1, cc5);
	}
}
