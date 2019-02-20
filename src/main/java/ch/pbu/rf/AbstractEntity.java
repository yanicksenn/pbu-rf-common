package ch.pbu.rf;

import java.util.Objects;

import javax.persistence.Id;

/**
 * Represents the Abstract-Entity.
 * 
 * @author Yanick Senn
 *
 * @param <ID>
 */
public abstract class AbstractEntity<ID> {
	
	@Id
	private ID id;
	
	/**
	 * Returns the id.
	 * 
	 * @return Id.
	 */
	public ID getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 * 
	 * @param id Id.
	 */
	protected void setId(ID id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[id: %s]", getClass().getSimpleName(), String.valueOf(id));
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractEntity<ID> other = (AbstractEntity<ID>) obj;
		
		return 
			Objects.equals(this.id, other.id);
	}
}
