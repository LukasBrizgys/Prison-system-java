package lt.ku.prison.entities;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lt.ku.prison.classes.PrisonerCrimeKey;

@Entity
@Table(name = "prisoner_crime")
public class PrisonerCrime {
	
	
	@EmbeddedId
	private PrisonerCrimeKey id;
	
	
	@ManyToOne
	@MapsId("crimeId")
	@JoinColumn(name = "crime_id")
	private Crime crime;
	
	@ManyToOne
	@MapsId("prisonerId")
	@JoinColumn(name = "prisoner_id")
	private Prisoner prisoner;
	
	public PrisonerCrime(Crime crime, Prisoner prisoner) {
		super();
		this.crime = crime;
		this.prisoner = prisoner;
	}

	public PrisonerCrime() {
		super();
	}

	public PrisonerCrimeKey getId() {
		return id;
	}

	public void setId(PrisonerCrimeKey id) {
		this.id = id;
	}

	public Crime getCrime() {
		return crime;
	}

	public void setCrime(Crime crime) {
		this.crime = crime;
	}

	public Prisoner getPrisoner() {
		return prisoner;
	}

	public void setPrisoner(Prisoner prisoner) {
		this.prisoner = prisoner;
	}
	
}
