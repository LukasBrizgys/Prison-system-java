package lt.ku.prison.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lt.ku.prison.validation.DateConstraint;

@Entity
@Table(name = "visitors")
public class Visitor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Length(min = 3, max = 20, message="Vardą turi sudaryti daugiau nei 3 simboliai ir mažiau nei 20 simbolių")
	private String name;
	
	@Length(min = 3, max = 20, message="Pavardę turi sudaryti daugiau nei 3 simboliai ir mažiau nei 20 simbolių")
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Įveskite datą")
	@DateConstraint
	private String birthDate;
	
	public Visitor() {
		super();
	}

	public Visitor(String name, String surname, String birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	

}
