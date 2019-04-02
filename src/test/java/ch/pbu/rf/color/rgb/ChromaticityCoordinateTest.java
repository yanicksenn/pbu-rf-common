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
	@DisplayName(value = "Test: ChromaticityCoordinate()")
	void testConstructor() {
		new ChromaticityCoordinate();
	}
	
	@Test
	@DisplayName(value = "Test: getX() / setX(BigDecimal)")
	void testGetSetX() {
		ChromaticityCoordinate cc = new ChromaticityCoordinate();
		Testing.assertEquals(null, cc.getX(), MC);
		
		cc.setX(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, cc.getX(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: getY() / setY(BigDecimal)")
	void testGetSetY() {
		ChromaticityCoordinate cc = new ChromaticityCoordinate();
		Testing.assertEquals(null, cc.getY(), MC);
		
		cc.setY(BigDecimal.ZERO);
		Testing.assertEquals(BigDecimal.ZERO, cc.getY(), MC);
	}
	
	@Test
	@DisplayName(value = "Test: equals(Object)")
	void testGetColumn() {
		ChromaticityCoordinate cc1 = new ChromaticityCoordinate();
		ChromaticityCoordinate cc2 = new ChromaticityCoordinate();
		
		Assertions.assertEquals(cc1, cc2);
		
		cc2.setY(BigDecimal.ZERO);
		Assertions.assertNotEquals(cc1, cc2);
	}
}
