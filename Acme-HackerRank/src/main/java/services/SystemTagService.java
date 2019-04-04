
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SystemTagRepository;
import domain.Actor;
import domain.Message;
import domain.SystemTag;

@Service
@Transactional
public class SystemTagService {

	// Managed repository ----------------------
	@Autowired
	private SystemTagRepository	systemTagRepository;

	// Other supporting services ---------------
	@Autowired
	private ActorService		actorService;


	// Constructor ------------------------------
	public SystemTagService() {
		super();
	}

	// Simple CRUD methods ----------------------
	public SystemTag createAndSave(final Message message) {
		SystemTag systemTag, result;
		Actor principal;

		principal = this.actorService.findPrincipal();

		systemTag = new SystemTag();
		systemTag.setActor(principal);
		systemTag.setMessage(message);
		systemTag.setText("DELETED");

		result = this.systemTagRepository.save(systemTag);

		return result;
	}

	// Other business methods -------------------
	protected SystemTag findByActorAndMessage(final int actorId, final int messageId) {
		SystemTag result;

		result = this.findByActorAndMessage(actorId, messageId);

		return result;
	}

	protected SystemTag findMessageTaggedAsDELETED(final int actorId, final int messageId) {
		SystemTag result;

		result = this.systemTagRepository.findMessageTaggedAsDELETED(actorId, messageId);

		return result;
	}

	protected SystemTag findMessageTaggedAsHARDDELETED(final int actorId, final int messageId) {
		SystemTag result;

		result = this.systemTagRepository.findMessageTaggedAsHARDDELETED(actorId, messageId);

		return result;
	}

	protected Integer numberOfTimesTaggedAsHARDDELETED(final int messageId) {
		Integer result;

		result = this.systemTagRepository.numberOfTimesTaggedAsHARDDELETED(messageId);

		return result;
	}

}
