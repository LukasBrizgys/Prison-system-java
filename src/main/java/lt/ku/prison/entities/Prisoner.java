package lt.ku.prison.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

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
	private LocalDate birthDate;
	
	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;
}
