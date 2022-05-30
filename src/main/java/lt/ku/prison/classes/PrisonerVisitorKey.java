package lt.ku.prison.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lt.ku.prison.validation.DateTimeConstraint;

@Embeddable
public class PrisonerVisitorKey implements Serializable{
	
	@Column
	@DateTimeConstraint
	private String startDate;
	
	@Column(name="prisoner_id")
	@NotNull(message = "Pasirinkite kalinio id")
	private Integer prisonerId;
	
	@Column(name = "visitor_id")
	@NotNull(message = "Pasirinkite lankytojo id")
	private Integer visitorId;

	public PrisonerVisitorKey() {
		super();
	}

	public PrisonerVisitorKey(String startDate,
			@NotNull(message = "Pasirinkite kalinio id") Integer prisonerId,
			@NotNull(message = "Pasirinkite lankytojo id") Integer visitorId) {
		super();
		this.startDate = startDate;
		this.prisonerId = prisonerId;
		this.visitorId = visitorId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Integer getPrisonerId() {
		return prisonerId;
	}

	public void setPrisonerId(Integer prisonerId) {
		this.prisonerId = prisonerId;
	}

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
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
