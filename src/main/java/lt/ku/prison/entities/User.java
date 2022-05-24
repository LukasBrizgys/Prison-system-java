package lt.ku.prison.entities;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails{
	
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
	
	@Column(length = 64)
	@NotEmpty(message = "Adresas privalomas")
	private String address;
	
	@Column(nullable = false)
	@NotEmpty(message = "Slaptažodis privalomas")
	private String password;
	
	@Transient
	private String passwordRepeat;
	
	@Column(nullable = false, length = 64, unique = true)
    @Email(message="Netinkamas El. paštas", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "El-paštas privalomas")
    private String email;
	
	
	@ManyToOne
	@JoinColumn(name = "cityId", nullable = false)
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	private Role role;
	
	
	public User() {
		super();
	}

	public User(Integer id,
			@Length(min = 3, max = 20, message = "Vardas turi būti ilgesnis nei 3 simboliai ir trumpesnis negu 20 simbolių") @NotBlank(message = "Įveskite vardą") String name,
			@Length(min = 3, max = 20, message = "Pavardė turi būti ilgesnė nei 3 simboliai ir trumpesnė nei 20 simbolių") String surname,
			@Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$", message = "Netinkamas telefono numeris") String phone,
			@NotEmpty(message = "Adresas privalomas") String address,
			@NotEmpty(message = "Slaptažodis privalomas") String password,
			@Email(message = "Netinkamas El. paštas", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$") @NotEmpty(message = "El-paštas privalomas") String email,
			City city, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.email = email;
		this.city = city;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<GrantedAuthority> auth = new HashSet<>();
		auth.add(new SimpleGrantedAuthority(role.getRole()));
		
		return auth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
