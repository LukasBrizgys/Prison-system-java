package lt.ku.prison.classes;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lt.ku.prison.validation.DateConstraint;
import lt.ku.prison.validation.LocalDateConstraint;

@Embeddable
public class PrisonerCrimeKey implements Serializable{

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Įveskite datą")
	@DateConstraint
	private String date;
	
	@Column(name="prisoner_id")
	@NotNull(message = "Pasirinkite kalinio id")
	Integer prisonerId;
	
	@Column(name = "crime_id")
	@NotNull(message = "Pasirinkite nusikaltimo id")
	Integer crimeId;
	
	
	public PrisonerCrimeKey() {
		super();
	}

	public PrisonerCrimeKey(@NotNull(message = "Įveskite datą") String date,
			@NotNull(message = "Pasirinkite kalinio id") Integer prisonerId,
			@NotNull(message = "Pasirinkite nusikaltimo id") Integer crimeId) {
		super();
		this.date = date;
		this.prisonerId = prisonerId;
		this.crimeId = crimeId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getPrisonerId() {
		return prisonerId;
	}

	public void setPrisonerId(Integer prisonerId) {
		this.prisonerId = prisonerId;
	}

	public Integer getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(Integer crimeId) {
		this.crimeId = crimeId;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}
