package lt.ku.prison.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lt.ku.prison.classes.PrisonerCrimeKey;

@Entity
public class PrisonerCrime {
	@EmbeddedId
	PrisonerCrimeKey id;
	
	@ManyToOne
	@MapsId("crimeId")
	@JoinColumn(name = "crime_id")
	Crime crime;
	
	@ManyToOne
	@MapsId("prisonerId")
	@JoinColumn(name = "prisoner_id")
	Prisoner prisoner;
	
	@Column(nullable = false)
	LocalDate date;
}
