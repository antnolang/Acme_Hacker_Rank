
package controllers.authenticated;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.ActorService;
import services.CompanyService;
import services.CurriculumService;
import services.EducationDataService;
import services.MessageService;
import services.MiscellaneousDataService;
import services.PersonalDataService;
import services.PositionDataService;
import services.PositionService;
import services.ProblemService;
import services.SocialProfileService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import domain.Position;
import domain.Problem;
import domain.SocialProfile;

@Controller
@RequestMapping(value = "/exportData/administrator,company,hacker")
public class ExportDataMultiUserController extends AbstractController {

	@Autowired
	private ActorService				actorService;

	@Autowired
	private SocialProfileService		socialProfileService;

	@Autowired
	private MessageService				messageService;

	@Autowired
	private CompanyService				companyService;

	@Autowired
	private ProblemService				problemService;

	@Autowired
	private PositionService				positionService;

	@Autowired
	private CurriculumService			curriculumService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;

	@Autowired
	private EducationDataService		educationDataService;

	@Autowired
	private PositionDataService			positionDataService;

	@Autowired
	private PersonalDataService			personalDataService;


	// Constructors -----------------------------------------------------------
	public ExportDataMultiUserController() {
		super();
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(final HttpSession session, final HttpServletResponse response) throws IOException {
		Actor actor;
		actor = this.actorService.findPrincipal();

		final Collection<SocialProfile> socialProfiles = this.socialProfileService.findSocialProfilesByActor(actor.getId());
		final Collection<Message> messagesReceived = this.messageService.findReceivedMessagesOrderByTags(actor.getId());
		final Collection<Message> messagesSent = this.messageService.findSentMessagesOrderByTags(actor.getId());
		String data = "Data of your user account:\r\n";
		data += "\r\n";

		data += "Name: " + actor.getName() + " \r\n" + "Surname: " + actor.getSurname() + " \r\n" + "VAT number: " + actor.getVATnumber() + " \r\n" + "Photo: " + actor.getPhoto() + " \r\n" + "Email: " + actor.getEmail() + " \r\n" + "Phone Number: "
			+ actor.getPhoneNumber() + " \r\n" + "Address: " + actor.getAddress() + " \r\n";

		data += "\r\n\r\n";
		data += "-------------------------------------------------------------";
		data += "\r\n\r\n";

		data += "Data of your credit card:\r\n";
		data += "\r\n";

		data += "Holder name: " + actor.getCreditCard().getHolder() + " \r\n" + "Make: " + actor.getCreditCard().getMake() + " \r\n" + "Number: " + actor.getCreditCard().getNumber() + " \r\n" + "Expiration month: "
			+ actor.getCreditCard().getExpirationMonth() + " \r\n" + "Expiration year: " + actor.getCreditCard().getExpirationYear() + " \r\n" + "CVV Code: " + actor.getCreditCard().getCvvCode() + " \r\n";

		data += "\r\n\r\n";
		data += "-------------------------------------------------------------";
		data += "\r\n\r\n";

		data += "Social Profiles:\r\n";
		data += "\r\n";

		for (final SocialProfile socialProfile : socialProfiles)
			data += "Nick: " + socialProfile.getNick() + " \r\n" + "Link profile: " + socialProfile.getLinkProfile() + " \r\n" + "Social Network: " + socialProfile.getSocialNetwork() + "\r\n";

		data += "\r\n\r\n";
		data += "-------------------------------------------------------------";
		data += "\r\n\r\n";

		data += "Sent Messages:\r\n\r\n";
		Integer m = 0;
		for (final Message message : messagesSent) {
			final Collection<Actor> recipients = message.getRecipients();
			data += "Sender: " + message.getSender().getFullname() + " \r\n";
			for (final Actor recipient : recipients)
				data += "Recipients: " + recipient.getFullname() + " \r\n";
			data += "Sent Moment: " + message.getSentMoment() + " \r\n" + "Subject: " + message.getSubject() + " \r\n" + "Body: " + message.getBody() + " \r\n" + "Tags: " + message.getTags() + " \r\n";
			m++;
			if (m < messagesSent.size())
				data += "\r\n" + "......................." + "\r\n\r\n";
		}

		data += "\r\n";
		data += "-------------------------------------------------------------";
		data += "\r\n\r\n";

		data += "Received Messages:\r\n\r\n";
		Integer n = 0;
		for (final Message message : messagesReceived) {
			final Collection<Actor> recipients = message.getRecipients();
			data += "Sender: " + message.getSender().getFullname() + " \r\n";
			for (final Actor recipient : recipients)
				data += "Recipients: " + recipient.getFullname() + " \r\n";
			data += "Sent Moment: " + message.getSentMoment() + " \r\n" + "Subject: " + message.getSubject() + " \r\n" + "Body: " + message.getBody() + " \r\n" + "Tags: " + message.getTags() + " \r\n";
			n++;
			if (n < messagesReceived.size())
				data += "\r\n" + "......................." + "\r\n\r\n";
		}

		if (actor.getUserAccount().getAuthorities().toString().equals("[COMPANY]")) {
			Collection<Position> positions;
			Collection<Problem> problems;

			positions = this.positionService.findPositionByPrincipal();
			problems = this.problemService.findProblemsByPrincipal();

			data += "\r\n";
			data += "-------------------------------------------------------------";
			data += "\r\n\r\n";

			data += "Positions:\r\n\r\n";
			Integer ss = 0;
			for (final Position position : positions) {
				data += "Ticker: " + position.getTicker() + " \r\n" + "Title: " + position.getTitle() + " \r\n" + "Description: " + position.getDescription() + " \r\n" + "Deadline : " + position.getDeadline() + "\r\n" + "Profile: " + position.getProfile()
					+ " \r\n" + "Skills: " + position.getSkills() + " \r\n" + "Technologies: " + position.getTechnologies() + " \r\n" + "Salary: " + position.getSalary() + " \r\n";
				ss++;
				if (ss < positions.size())
					data += "\r\n" + "......................." + "\r\n\r\n";
			}

			data += "\r\n";
			data += "-------------------------------------------------------------";
			data += "\r\n\r\n";

			data += "Problems:\r\n\r\n";
			Integer ss1 = 0;
			for (final Problem problem : problems) {
				data += "Title: " + problem.getTitle() + " \r\n" + "Statement: " + problem.getAttachments() + " \r\n" + "Hint: " + problem.getHint() + " \r\n" + "Attachment : " + problem.getAttachments() + "\r\n";
				ss1++;
				if (ss1 < problems.size())
					data += "\r\n" + "......................." + "\r\n\r\n";

			}

		}

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=data_user_account.txt");
		final ServletOutputStream out = response.getOutputStream();
		out.println(data);
		out.flush();
		out.close();
	}
}
