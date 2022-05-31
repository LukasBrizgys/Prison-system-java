package lt.ku.prison.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lt.ku.prison.validation.DateConstraint;

@Entity
@Table(name = "prisoners")
public class Prisoner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Length(min = 3, max = 20, message = "Vardas turi būti ilgesnis nei 3 simboliai ir trumpesnis negu 20 simbolių")
	@NotBlank(message = "Įveskite vardą")
	private String name;
	
	@Column(nullable = false)
	@Length(min = 3, max = 20, message="Pavardė turi būti ilgesnė nei 3 simboliai ir trumpesnė nei 20 simbolių")
	private String surname;
	
	@Column(nullable = false)
	@Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$", message = "Netinkamas telefono numeris")
	private String phone;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Įveskite datą")
	@DateConstraint
	private String birthDate;
	
	@Column
	public String fileName;
	
	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;
	
	@OneToMany(mappedBy = "prisoner",  cascade = CascadeType.ALL)
	List<PrisonerCrime> prisonerCrimes;
	
	@OneToMany(mappedBy = "prisoner",  cascade = CascadeType.ALL)
	List<PrisonerVisitor> prisonerVisitors;
	
	public Prisoner() {
		super();
	}
	
	public Prisoner(
			@Length(min = 3, max = 20, message = "Vardas turi būti ilgesnis nei 3 simboliai ir trumpesnis negu 20 simbolių") @NotBlank(message = "Įveskite vardą") String name,
			@Length(min = 3, max = 20, message = "Pavardė turi būti ilgesnė nei 3 simboliai ir trumpesnė nei 20 simbolių") String surname,
			@Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$", message = "Netinkamas telefono numeris") String phone,
			String birthDate, String fileName) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.birthDate = birthDate;
		this.fileName = fileName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
