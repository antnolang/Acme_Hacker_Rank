
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Application;
import domain.Curriculum;
import domain.Hacker;

@Service
@Transactional
public class CurriculumService {

	// Managed repository ------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;


	// Other supporting services -----------------------------------------

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

}
