package lt.ku.prison.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

import lt.ku.prison.classes.PrisonerVisitorKey;
import lt.ku.prison.validation.DateConstraint;
import lt.ku.prison.validation.DateTimeConstraint;

@Entity
public class PrisonerVisitor {
	@EmbeddedId
	private PrisonerVisitorKey id;
	
	@ManyToOne
	@MapsId("visitorId")
	@JoinColumn(name = "visitor_id")
	private Visitor visitor;
	
	@ManyToOne
	@MapsId("prisonerId")
	@JoinColumn(name = "prisoner_id")
	private Prisoner prisoner;
	
	@Column
	@DateTimeConstraint
	@NotNull(message = "Įveskite datą")
	private String finishDate;

	public PrisonerVisitor() {
		super();
	}

	public PrisonerVisitor(Visitor visitor, Prisoner prisoner, @NotNull(message = "Įveskite datą") String finishDate) {
		super();
		this.visitor = visitor;
		this.prisoner = prisoner;
		this.finishDate = finishDate;
	}

	public PrisonerVisitorKey getId() {
		return id;
	}

	public void setId(PrisonerVisitorKey id) {
		this.id = id;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Prisoner getPrisoner() {
		return prisoner;
	}

	public void setPrisoner(Prisoner prisoner) {
		this.prisoner = prisoner;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	
}
