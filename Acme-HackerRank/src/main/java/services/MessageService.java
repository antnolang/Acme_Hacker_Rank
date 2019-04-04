
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Application;
import domain.Customisation;
import domain.Message;
import domain.SystemTag;

@Service
@Transactional
public class MessageService {

	// Managed repository  ----------------------------------
	@Autowired
	private MessageRepository		messageRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UtilityService			utilityService;

	@Autowired
	private CustomisationService	customisationService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private SystemTagService		systemTagService;


	// Constructors -----------------------------------------
	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------
	public Message findOne(final int messageId) {
		Message result;

		result = this.messageRepository.findOne(messageId);

		return result;
	}

	public Message findOneToDisplay(final int messageId) {
		Message result;
		SystemTag systemTag;
		Actor principal;

		result = this.messageRepository.findOne(messageId);
		Assert.notNull(result);
		this.checkSenderOrRecipient(result);

		// If actor "has removed" the message, then it's not visible to him
		principal = this.actorService.findPrincipal();
		systemTag = this.systemTagService.findMessageTaggedAsHARDDELETED(principal.getId(), result.getId());
		Assert.isNull(systemTag);

		return result;
	}

	public Message create() {
		Message result;
		Actor principal;
		Date current_moment;

		principal = this.actorService.findPrincipal();
		current_moment = this.utilityService.current_moment();

		result = new Message();
		result.setSender(principal);
		result.setRecipients(Collections.<Actor> emptySet());
		result.setSentMoment(current_moment);

		return result;
	}

	public Message createBroadcast() {
		Message result;
		Actor principal;
		Collection<Actor> recipients;

		principal = this.actorService.findPrincipal();
		recipients = this.actorService.findAll();
		recipients.remove(principal);

		result = this.create();
		result.setTags("SYSTEM");
		result.setRecipients(recipients);

		return result;
	}

	public Message send(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() == 0);
		this.checkByPrincipal(message);

		Message result;
		boolean isSpam;

		isSpam = this.messageIsSpam(message);
		message.setIsSpam(isSpam);

		result = this.messageRepository.save(message);

		return result;
	}

	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		this.checkSenderOrRecipient(message);

		SystemTag systemTagAsDeleted, systemTagAsHardDeleted;
		Actor principal;
		Integer number;

		principal = this.actorService.findPrincipal();

		systemTagAsDeleted = this.systemTagService.findMessageTaggedAsDELETED(principal.getId(), message.getId());
		systemTagAsHardDeleted = this.systemTagService.findMessageTaggedAsHARDDELETED(principal.getId(), message.getId());

		if (systemTagAsDeleted != null) {
			// If the message has already been tagged as "DELETED", now this message is not visible
			// to the principal
			systemTagAsDeleted.setText("HARDDELETED");

			number = this.systemTagService.numberOfTimesTaggedAsHARDDELETED(message.getId());

			if (number == message.getRecipients().size())
				this.messageRepository.delete(message);

		} else if (systemTagAsDeleted == null && systemTagAsHardDeleted == null)
			// If the message "is deleted" and the message doesn't have tag "DELETED", then
			// it gets tag "DELETED"
			this.systemTagService.createAndSave(message);
	}

	// Other business methods -------------------------------
	public String displayMessageTags(final Message message) {
		String result, tags;
		SystemTag systemTag;
		Actor principal;

		principal = this.actorService.findPrincipal();
		systemTag = this.systemTagService.findMessageTaggedAsDELETED(principal.getId(), message.getId());

		tags = message.getTags();
		if ((tags != null && tags != "") && systemTag != null)
			result = tags + " DELETED";
		else if ((tags != null && tags != "") && systemTag == null)
			result = tags;
		else if ((tags == null || tags == "") && systemTag != null)
			result = "DELETED";
		else
			result = null;

		return result;
	}

	public Message sendBroadcast(final Message message) {
		Assert.isTrue(message.getTags() != null && message.getTags().contains("SYSTEM"));

		Message result;
		boolean isSpam;
		Actor principal;
		Collection<Actor> recipients;

		principal = this.actorService.findPrincipal();

		recipients = this.actorService.findAll();
		recipients.remove(principal);

		isSpam = this.messageIsSpam(message);

		message.setRecipients(recipients);
		message.setIsSpam(isSpam);

		result = this.messageRepository.save(message);

		return result;
	}

	public Collection<Message> findSentMessagesOrderByTags(final int actorId) {
		Collection<Message> results;

		results = this.messageRepository.findMessagesSentByActorOrderByTags(actorId);

		return results;
	}

	public Collection<Message> findReceivedMessagesOrderByTags(final int actorId) {
		Collection<Message> results;

		results = this.messageRepository.findReceivedMessagesOrderByTags(actorId);

		return results;
	}


	@Autowired
	private Validator	validator;


	public Message reconstruct(final Message message, final BindingResult binding) {
		Message result;
		Collection<Actor> recipients;

		result = this.create();
		result.setSubject(message.getSubject());
		result.setBody(message.getBody());
		result.setTags(message.getTags());

		recipients = new ArrayList<>();
		if (message.getRecipients() != null)
			recipients.addAll(message.getRecipients());

		result.setRecipients(recipients);

		this.validator.validate(result, binding);

		return result;
	}

	public Message breachNotification() {
		Message message, result;
		String subject, body;

		subject = "Breach notification / Notificación de brecha de seguridad";
		body = "Dear valued user, we regret to inform you that your data has been exposed. Urge you to remain vigilant /" + "Apreciado usuario, lamentamos informarle de que sus datos han sido expuestos. Le instamos a estar alerta.";

		message = this.createBroadcast();
		message.setSubject(subject);
		message.setBody(body);

		result = this.messageRepository.save(message);

		return result;
	}

	protected Message notification_applicationStatusChanges(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);

		Message notification, result;
		Actor hacker, company;
		List<Actor> recipients;
		String subject, body;

		hacker = application.getHacker();
		company = application.getPosition().getCompany();

		recipients = new ArrayList<Actor>();
		recipients.add(hacker);
		recipients.add(company);

		subject = "Application notification / Notificación de solicitud";
		body = "The application has been " + application.getStatus() + ". / La solicitud has sido " + application.getStatus() + ".";

		notification = this.createNotification(recipients, subject, body);

		result = this.messageRepository.save(notification);

		return result;
	}

	//	protected Message notification_newOfferPublished(Position position) {
	//		Assert.notNull(position);
	//		Assert.isTrue(position.getIsFinalMode() && !position.getIsCancelled());
	//		
	//		Message notification, result;
	//		List<Hacker> all, hackers;
	//		Collection<Position>  returned_positions;
	//		String subject, body;
	//		
	//		all = this.hackerService.findAll();
	//		
	//		for (Hacker h: all) {
	//			thi
	//		}
	//		
	//		notification = this.createNotification(hackers, subject, body);
	//	
	//	
	//	}

	// Protected methods ------------------------------------
	protected Double numberMessagesSentByActor(final int actorId) {
		Double result;

		result = this.messageRepository.numberMessagesSentByActor(actorId);

		return result;
	}

	protected Double numberSpamMessagesSentByActor(final int actorId) {
		Double result;

		result = this.messageRepository.numberSpamMessagesSentByActor(actorId);

		return result;
	}

	// Private methods --------------------------------------
	private Message createNotification(final Collection<Actor> recipients, final String subject, final String body) {
		Message result;
		Date current_moment;
		Actor system;

		current_moment = this.utilityService.current_moment();
		system = this.administratorService.findSystem();

		result = new Message();
		result.setSender(system);
		result.setRecipients(recipients);
		result.setSentMoment(current_moment);
		result.setBody(body);
		result.setSubject(subject);
		result.setTags("SYSTEM");

		return result;
	}

	private void checkByPrincipal(final Message message) {
		Actor principal;

		principal = this.actorService.findPrincipal();

		Assert.notNull(principal);
		Assert.isTrue(message.getSender().equals(principal));
	}

	private void checkSenderOrRecipient(final Message message) {
		Actor principal;

		principal = this.actorService.findPrincipal();

		Assert.isTrue(message.getSender().equals(principal) || message.getRecipients().contains(principal));
	}

	private boolean messageIsSpam(final Message message) {
		String spam_words;
		List<String> spamWords;
		Customisation customisation;
		String text;
		boolean result;

		customisation = this.customisationService.find();

		spam_words = customisation.getSpamWords();
		spamWords = this.utilityService.ListByString(spam_words);

		text = message.getSubject() + " " + message.getBody();

		result = false;
		for (final String s : spamWords)
			if (text.toLowerCase().contains(s.toLowerCase())) {
				result = true;
				break;
			}

		return result;
	}

}
