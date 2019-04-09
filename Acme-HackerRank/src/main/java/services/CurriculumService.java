
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Application;
import domain.Curriculum;
import domain.EducationData;
import domain.Hacker;
import domain.MiscellaneousData;
import domain.PersonalData;
import domain.PositionData;

@Service
@Transactional
public class CurriculumService {

	// Managed repository ------------------------------------------------

	@Autowired
	private CurriculumRepository		curriculumRepository;

	// Other supporting services -----------------------------------------

	@Autowired
	private PersonalDataService			personalDataService;

	@Autowired
	private PositionDataService			positionDataService;

	@Autowired
	private EducationDataService		educationDataService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;

	@Autowired
	private HackerService				hackerService;


	// Constructors ------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	public Double[] findDataNumberCurriculumPerHacker() {
		Double[] result;

		result = this.curriculumRepository.findDataNumberCurriculumPerHacker();
		Assert.notNull(result);

		return result;
	}

	// Ancillary methods -------------------------------------------------

	protected Curriculum saveCopy(final Curriculum curriculum) {
		Curriculum saved, copy;

		copy = this.copy(curriculum);
		saved = this.curriculumRepository.save(copy);

		return saved;
	}

	protected void deleteCurriculum(final Application application) {
		Curriculum curriculum;

		curriculum = application.getCurriculum();

		this.curriculumRepository.delete(curriculum);
	}

	protected void deleteCurriculums(final Hacker hacker) {
		Collection<Curriculum> curriculums;

		curriculums = this.curriculumRepository.findAllByHacker(hacker.getId());

		this.curriculumRepository.deleteInBatch(curriculums);
	}

	private Curriculum copy(final Curriculum curriculum) {
		Curriculum result;
		PersonalData personalData;
		Collection<EducationData> educationDatas;
		Collection<MiscellaneousData> miscellaneousDatas;
		Collection<PositionData> positionDatas;

		result = new Curriculum();
		personalData = this.personalDataService.copy(curriculum.getPersonalData());
		educationDatas = this.educationDataService.copy(curriculum.getEducationDatas());
		miscellaneousDatas = this.miscellaneousDataService.copy(curriculum.getMiscellaneousDatas());
		positionDatas = this.positionDataService.copy(curriculum.getPositionDatas());

		result.setHacker(curriculum.getHacker());
		result.setIsOriginal(false);
		result.setTitle(curriculum.getTitle() + "copy");
		result.setEducationDatas(educationDatas);
		result.setMiscellaneousDatas(miscellaneousDatas);
		result.setPersonalData(personalData);
		result.setPositionDatas(positionDatas);

		return result;
	}

	public List<Curriculum> originalCurriculaByPrincipal() {
		List<Curriculum> results;
		Hacker principal;

		principal = this.hackerService.findByPrincipal();

		results = new ArrayList<>(this.curriculumRepository.originalCurricula(principal.getId()));

		return results;
	}

	protected List<Curriculum> originalCurricula(final int hackerId) {
		List<Curriculum> results;

		results = new ArrayList<>(this.curriculumRepository.originalCurricula(hackerId));

		return results;
	}

	public Curriculum findOne(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);

		return result;
	}

}
