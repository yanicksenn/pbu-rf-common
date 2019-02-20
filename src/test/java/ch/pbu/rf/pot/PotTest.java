package ch.pbu.rf.pot;

import org.junit.jupiter.api.Test;

import ch.pbu.rf.AbstractTestCase;
import ch.pbu.rf.color.lab.ColorLab;

/**
 * Test for {@link Pot}.
 * 
 * @author Yanick Senn
 */
class PotTest extends AbstractTestCase {
	
	@Test
	void test() {

		// Positive-Tests
		{
			Pot pot = new Pot();
			pot.setName("name");
			pot.setColor(new ColorLab(1, 0, 0));
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN);
			assertValid(pot);
		}
		
		{
			Pot pot = new Pot();
			pot.setName("name");
			pot.setColor(new ColorLab(1, 0, 0));
			pot.setAmountInMilliliter(Long.MAX_VALUE);
			assertValid(pot);
		}
		

		// Negative-Tests
		{
			Pot pot = new Pot();
			pot.setName(null);
			pot.setColor(new ColorLab(1, 0, 0));
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN);
			assertInvalid(pot);
		}
		
		{
			Pot pot = new Pot();
			pot.setName("");
			pot.setColor(new ColorLab(1, 0, 0));
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN);
			assertInvalid(pot);
		}
		
		{
			Pot pot = new Pot();
			pot.setName("name");
			pot.setColor(null);
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN);
			assertInvalid(pot);
		}
		
		{
			Pot pot = new Pot();
			pot.setName("name");
			pot.setColor(new ColorLab(-1, 0, 0));
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN);
			assertInvalid(pot);
		}

		{
			Pot pot = new Pot();
			pot.setName("name");
			pot.setColor(new ColorLab(1, 0, 0));
			pot.setAmountInMilliliter(PotValidator.AMOUNT_IN_LITER_MIN - 1);
			assertInvalid(pot);
		}
	}
}
