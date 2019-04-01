
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	// Constructor

	public Curriculum() {
		super();
	}


	// Attributes

	private boolean	isOriginal;


	public boolean getIsOriginal() {
		return this.isOriginal;
	}

	public void setIsOriginal(final boolean isOriginal) {
		this.isOriginal = isOriginal;
	}


	// Relationships

	private Hacker							hacker;
	private Collection<MiscellaneousData>	miscellaneousDatas;
	private Collection<EducationData>		educationDatas;
	private Collection<PositionData>		positionDatas;
	private PersonalData					personalData;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Hacker getHacker() {
		return this.hacker;
	}

	public void setHacker(final Hacker hacker) {
		this.hacker = hacker;
	}

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<MiscellaneousData> getMiscellaneousDatas() {
		return this.miscellaneousDatas;
	}

	public void setMiscellaneousDatas(final Collection<MiscellaneousData> miscellaneousDatas) {
		this.miscellaneousDatas = miscellaneousDatas;
	}

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EducationData> getEducationDatas() {
		return this.educationDatas;
	}

	public void setEducationDatas(final Collection<EducationData> educationDatas) {
		this.educationDatas = educationDatas;
	}

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<PositionData> getPositionDatas() {
		return this.positionDatas;
	}

	public void setPositionDatas(final Collection<PositionData> positionDatas) {
		this.positionDatas = positionDatas;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

}
