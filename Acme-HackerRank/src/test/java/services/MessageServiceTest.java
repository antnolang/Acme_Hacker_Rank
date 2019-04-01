
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
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

	@Test
	public void createTest() {
		super.authenticate("member1");

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
	 * Test negativo: message is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_sendTest_uno() {
		super.authenticate("member1");

		final Message message = null;
		Message sent;

		sent = this.messageService.send(message);

		Assert.isNull(sent);

		super.unauthenticate();
	}

	/*
	 * Test negativo: se trata de editar un mensaje
	 * de la BD
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_sendTest_dos() {
		super.authenticate("member1");

		final int messageId = super.getEntityId("message1");
		Message message = null, sent;

		message = this.messageService.findOne(messageId);
		message.setBody("Body edited");
		message.setSubject("Subject edited");

		sent = this.messageService.send(message);

		Assert.isNull(sent);

		super.unauthenticate();
	}

	/*
	 * Test negativo: el sender no coincide con el principal
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_sendTest_tres() {
		super.authenticate("member1");

		final List<Actor> recipients = new ArrayList<Actor>();

		final int recipientId = super.getEntityId("administrator1");
		final int actorId = super.getEntityId("member2");
		final Actor sender = this.actorService.findOne(actorId);
		final Actor recipient = this.actorService.findOne(recipientId);

		recipients.add(recipient);

		Message message, sent;

		message = this.messageService.create();
		message.setSender(sender);
		message.setRecipients(recipients);
		message.setSubject("Subject Test");
		message.setBody("Body Test");

		sent = this.messageService.send(message);

		Assert.isNull(sent);

		super.unauthenticate();
	}

	/*
	 * Test negativo: prioridad no valida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_sendTest_cuatro() {
		super.authenticate("member1");

		final List<Actor> recipients = new ArrayList<Actor>();

		final int recipientId = super.getEntityId("administrator1");
		final Actor recipient = this.actorService.findOne(recipientId);
		recipients.add(recipient);

		Message message, sent;

		message = this.messageService.create();
		message.setRecipients(recipients);
		message.setSubject("Subject Test");
		message.setBody("Body Test");

		sent = this.messageService.send(message);

		Assert.isNull(sent);

		super.unauthenticate();
	}

	/*
	 * Test positivo: Se envia correctamente un mensaje. Dicho mensaje
	 * no contiene palabra spam, por tanto, se almacenara en la
	 * bandeja de entrada del receptor.
	 */
	@Test
	public void positive_sendTest_uno() {
		super.authenticate("member1");

		final List<Actor> recipients = new ArrayList<Actor>();
		final int recipientId = super.getEntityId("administrator1");
		final Actor recipient = this.actorService.findOne(recipientId);
		recipients.add(recipient);

		Message message, sent, found;

		message = this.messageService.create();
		message.setRecipients(recipients);
		message.setSubject("Subject Test");
		message.setBody("Body Test");

		sent = this.messageService.send(message);
		found = this.messageService.findOne(sent.getId());

		Assert.notNull(sent);
		Assert.notNull(found);

		super.unauthenticate();
	}

	/*
	 * Test positivo: Se envia correctamente un mensaje. Dicho mensaje
	 * contiene palabra spam, por tanto, se almacenara en la
	 * bandeja de spam de los receptores.
	 */
	@Test
	public void positive_sendTest_dos() {
		super.authenticate("member1");

		final List<Actor> recipients = new ArrayList<Actor>();

		int recipientId = super.getEntityId("administrator1");
		final Actor recipientUno = this.actorService.findOne(recipientId);

		recipientId = super.getEntityId("member2");
		final Actor recipientDos = this.actorService.findOne(recipientId);

		recipients.add(recipientUno);
		recipients.add(recipientDos);

		Message message, sent, found;

		message = this.messageService.create();
		message.setRecipients(recipients);
		message.setSubject("Subject Test-sex");
		message.setBody("Body Test. ViaGra");

		sent = this.messageService.send(message);
		found = this.messageService.findOne(sent.getId());

		Assert.notNull(sent);
		Assert.notNull(found);

		super.unauthenticate();
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
