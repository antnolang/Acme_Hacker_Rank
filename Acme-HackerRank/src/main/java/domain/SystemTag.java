
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class SystemTag extends DomainEntity {

	// Constructor

	public SystemTag() {
		super();
	}


	// Attributes

	private String	title;


	@NotBlank
	@Pattern(regexp = "^DELETE|HARDDELETE$")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}


	// Relationships

	private Actor	actor;
	private Message	message;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(final Message message) {
		this.message = message;
	}

}
