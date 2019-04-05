
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	// Service under testing -----------------------------------
	@Autowired
	private MessageService	messageService;

	// Other services ------------------------------------------
	@Autowired
	private ActorService	actorService;


	// Suite test ---------------------------------------------

	/*
	 * A: level A: requirement 23.2 (An authenticated user can display his or her messages).
	 * B: The business rule that is intended to be broken: This user try to display a "deleted" message.
	 * C: Analysis of sentence coverage: 33/34 -> 97.05% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findOneToDisplay_negativeTest_uno() {
		super.authenticate("hacker2");

		int messageId;
		Message message;

		messageId = super.getEntityId("message1");
		message = this.messageService.findOneToDisplay(messageId);

		Assert.isNull(message);

		super.unauthenticate();
	}

	/*
	 * A: level A: requirement 23.2 (An authenticated user can display his or her messages).
	 * B: The business rule that is intended to be broken: An user try to display a message that he or she hasn't sent or hasn't received.
	 * C: Analysis of sentence coverage: 18/34 -> 52.94% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findOneToDisplay_negativeTest_dos() {
		super.authenticate("company1");

		int messageId;
		Message message;

		messageId = super.getEntityId("message1");
		message = this.messageService.findOneToDisplay(messageId);

		Assert.isNull(message);

		super.unauthenticate();
	}

	/*
	 * A: level A: requirement 23.2 (An authenticated user can display his or her messages).
	 * C: Analysis of sentence coverage: 34/34 -> 100.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test
	public void findOneToDisplay_positiveTest() {
		super.authenticate("hacker1");

		int messageId;
		Message message;

		messageId = super.getEntityId("message1");
		message = this.messageService.findOneToDisplay(messageId);

		Assert.notNull(message);

		super.unauthenticate();
	}

	/*
	 * A: level A: requirement 23.2 (An authenticated user can list his or her messages).
	 * C: Analysis of sentence coverage: 6/6 -> 100.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test
	public void findMessageByActor_test() {
		super.authenticate("hacker1");

		int actorId;
		Collection<Message> sentMessages, receivedMessages;

		actorId = super.getEntityId("hacker1");

		sentMessages = this.messageService.findSentMessagesOrderByTags(actorId);
		receivedMessages = this.messageService.findReceivedMessagesOrderByTags(actorId);

		Assert.notEmpty(sentMessages);
		Assert.notEmpty(receivedMessages);

		super.unauthenticate();
	}

	/*
	 * A: level A: requirement 23.2 (An authenticated user can send a message).
	 * C: Analysis of sentence coverage: 22/22 -> 100.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test
	public void createTest() {
		super.authenticate("hacker1");

		Message message;

		message = this.messageService.create();

		Assert.notNull(message);
		Assert.notNull(message.getSender());
		Assert.notNull(message.getRecipients());
		Assert.notNull(message.getIsSpam());
		Assert.notNull(message.getSentMoment());
		Assert.isNull(message.getBody());
		Assert.isNull(message.getSubject());
		Assert.isNull(message.getTags());

		super.unauthenticate();
	}

	/*
	 * A: level A: requirement 23.2 (An authenticated user can send a message).
	 * B: The business rule that is intended to be broken: An user try to edit a message.
	 * C: Analysis of sentence coverage: 16/53 -> 30.18% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_sendTest_dos() {
		super.authenticate("hacker1");

		final int messageId = super.getEntityId("message1");
		Message message = null, sent;

		message = this.messageService.findOne(messageId);
		message.setBody("Body edited");
		message.setSubject("Subject edited");

		sent = this.messageService.send(message);

		Assert.isNull(sent);

		super.unauthenticate();
	}

	@Test
	public void driverSend() {
		final Object testingData[][] = {
			{
				null, "¿Que tal?", "", ConstraintViolationException.class
			}, {
				"", "¿Que tal?", "", ConstraintViolationException.class
			}, {
				"<script> Alert('HACKED'); </script>", "¿Que tal?", "", ConstraintViolationException.class
			}, {
				"Saludos", null, "", ConstraintViolationException.class
			}, {
				"Saludos", "", "", ConstraintViolationException.class
			}, {
				"saludos", "<script> Alert('HACKED'); </script>", "", ConstraintViolationException.class
			}, {
				"saludos", "¿Que tal?", "<script> Alert('HACKED'); </script>", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSend((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

	}
	protected void templateSend(final String subject, final String body, final String tags, final Class<?> expected) {
		Class<?> caught;
		Message message, saved;
		List<Actor> recipients;
		Actor actor_one, actor_two, actor_three;
		int actor_uno, actor_dos, actor_tres;
		actor_uno = super.getEntityId("company1");
		actor_dos = super.getEntityId("company2");
		actor_tres = super.getEntityId("company3");

		actor_one = this.actorService.findOne(actor_uno);
		actor_two = this.actorService.findOne(actor_dos);
		actor_three = this.actorService.findOne(actor_tres);

		recipients = new ArrayList<Actor>();
		recipients.add(actor_one);
		recipients.add(actor_two);
		recipients.add(actor_three);

		this.startTransaction();

		caught = null;
		try {
			super.authenticate("hacker1");

			message = this.messageService.create();
			message.setSubject(subject);
			message.setBody(body);
			message.setTags(tags);
			message.setRecipients(recipients);

			saved = this.messageService.send(message);
			this.messageService.flush();

			Assert.notNull(saved);
			Assert.isTrue(saved.getId() != 0);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			super.unauthenticate();
		}

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);
	}

	/*
	 * Test negativo: el mensaje no se encuentra en la BD.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_deleteTest_uno() {
		super.authenticate("member1");

		final List<Actor> recipients = new ArrayList<Actor>();
		final int recipientId = super.getEntityId("administrator1");
		final Actor recipient = this.actorService.findOne(recipientId);
		recipients.add(recipient);

		Message message;

		message = this.messageService.create();
		message.setRecipients(recipients);
		message.setSubject("Subject Test");
		message.setBody("Body Test");

		super.unauthenticate();
	}

	/*
	 * Test negativo: la bandeja no existe en la BD.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_deleteTest_dos() {
		super.authenticate("member1");

		final int messageId = super.getEntityId("message8");
		final Message message = this.messageService.findOne(messageId);

		super.unauthenticate();
	}

}
