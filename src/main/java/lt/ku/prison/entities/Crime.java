package lt.ku.prison.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "crimes")
public class Crime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "Įveskite nusikaltimo pavadinimą")
	private String name;
	
	public Crime() {
		super();
	}
	@OneToMany(mappedBy = "crime",  cascade = CascadeType.ALL)
	List<PrisonerCrime> prisonerCrimes;

	


	public Crime(@NotBlank(message = "Įveskite nusikaltimo pavadinimą") String name, LocalDate date) {
		super();
		this.name = name;
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
	
	
	
	
}
