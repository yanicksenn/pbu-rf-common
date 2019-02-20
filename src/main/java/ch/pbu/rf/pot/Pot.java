package ch.pbu.rf.pot;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ch.pbu.rf.AbstractEntity;
import ch.pbu.rf.color.Color;
import ch.pbu.rf.color.lab.ColorLab;
import ch.yanicksenn.metamodel.MetaModel;

/**
 * Represents the Pot.
 * 
 * @author Yanick Senn
 */
@Entity
@MetaModel
public class Pot extends AbstractEntity<Long> {
	
	@Column(nullable = false)
	@NotNull(message = PotValidator.LABEL_POT_INVALID_NAME_NULL)
	@NotEmpty(message = PotValidator.LABEL_POT_INVALID_NAME_EMPTY)
	private String name;

	@Embedded
	@NotNull(message = PotValidator.LABEL_POT_INVALID_COLOR_NULL)
	@Valid
	private ColorLab color;
	
	@Column(nullable = false)
	@Min(value = PotValidator.AMOUNT_IN_LITER_MIN, message = PotValidator.LABEL_POT_INVALID_AMOUNT_IN_LITER_MIN)
	private long amountInMilliliter;
	
	
	/**
	 * Represents the name.
	 * 
	 * @return Name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name Name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the color.
	 * 
	 * @return Color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name Name.
	 */
	public void setColor(ColorLab color) {
		this.color = color;
	}
	
	/**
	 * Returns the amount in milliliter.
	 * 
	 * @return Amount in milliliter.
	 */
	public long getAmountInMilliliter() {
		return amountInMilliliter;
	}

	/**
	 * 
	 * Sets the amount in milliliter.
	 * 
	 * @param amountInLiter Amount in milliliter.
	 */
	public void setAmountInMilliliter(long amountInMilliliter) {
		this.amountInMilliliter = amountInMilliliter;
	}
}
