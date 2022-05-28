package lt.ku.prison.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrisonerCrimeKey implements Serializable{
	@Column(name="prisoner_id")
	Integer prisonerId;
	
	@Column(name = "crime_id")
	Integer crimeId;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
}
