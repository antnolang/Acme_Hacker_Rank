
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;

@Service
@Transactional
public class ApplicationService {

	// Managed repository ---------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;

	// Supporting services -------------------------------------------

	@Autowired
	private PositionService			positionService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private UtilityService			utilityService;


	//Constructor ----------------------------------------------------
	public ApplicationService() {
		super();
	}

	// Protected methods -----------------------------------------------
	protected Collection<Application> findApplicationsByProblemHacker(final int idProblem, final int idHacker) {
		Collection<Application> result;

		result = this.findApplicationsByProblemHacker(idProblem, idHacker);

		return result;
	}

	//Simple CRUD methods -------------------------------------------

	// Other business methods ---------------------------------------

	public Double[] findDataNumberApplicationPerHacker() {
		Double[] result;

		result = this.applicationRepository.findDataNumberApplicationPerHacker();
		Assert.notNull(result);

		return result;
	}

	//	public Application create(final Position position) {
	//		Assert.isTrue(position.getIsFinalMode());
	//		Assert.isTrue(!(position.getIsCancelled()));
	//		//Assert.isTrue(!(this.positionService.problemsWithoutAcceptedApplicationWithoutOwnApplication(position).isEmpty()));
	//		//Assert.isTrue(!(this.hackerService.curriculaOriginal().isEmpty()));
	//		Application result;
	//		Hacker hacker;
	//		Date moment;
	//		final Curriculum curriculum;
	//		final Problem problem;
	//
	//		hacker = this.hackerService.findByPrincipal();
	//		moment = this.utilityService.current_moment();
	//		//curriculum = this.hackerService.curriculaOriginal().randon;
	//		//problem = this.positionService.problemsWithoutAcceptedApplicationWithoutOwnApplication(position).randon;
	//
	//		result = new Application();
	//		result.setHacker(hacker);
	//		result.setCurriculum(curriculum);
	//		result.setProblem(problem);
	//		result.setPosition(position);
	//		result.setStatus("PENDING");
	//		result.setApplicationMoment(moment);
	//
	//		return result;
	//	}
	//
	//	public Application save(final Application application) {
	//		Assert.notNull(application);
	//		final Application result;
	//
	//		if (application.getId() == 0) {
	//			Assert.notNull(application.getHandyWorker().getCurriculum());
	//			result = this.applicationRepository.save(application);
	//			this.fixUpTaskService.addApplication(result.getFixUpTask(), result);
	//		} else {
	//			if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDYWORKER]")) {
	//				Assert.isTrue(this.handyWorkerService.findByPrincipal().equals(application.getHandyWorker()));
	//				this.utilityService.checkActorIsBanned(this.handyWorkerService.findByPrincipal());
	//				this.utilityService.checkIsSpamMarkAsSuspicious(application.getHandyWorkerComments(), this.handyWorkerService.findByPrincipal());
	//				Assert.notNull(application.getHandyWorker().getCurriculum());
	//			}
	//			if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]")) {
	//				this.utilityService.checkActorIsBanned(this.customerService.findByPrincipal());
	//				if (this.utilityService.checkIfCreditCardChanged(application.getCreditCard())) {
	//					//Check that number of accepted application is 0
	//					this.checkAcceptedApplication(application);
	//					Assert.isTrue(this.utilityService.current_moment().before(application.getFixUpTask().getStartDate()));
	//					application.setStatus("ACCEPTED");
	//					this.changeStatus(application);
	//				}
	//				this.utilityService.checkIsSpamMarkAsSuspicious(application.getCustomerComments(), this.customerService.findByPrincipal());
	//			}
	//			result = this.applicationRepository.save(application);
	//		}
	//
	//		return result;
	//	}
	//	private void checkAcceptedApplication(final Application application) {
	//		Application app;
	//
	//		app = this.findAcceptedApplication(application.getFixUpTask().getId());
	//
	//		Assert.isTrue(app == null);
	//	}
	//
	//	public Application findOne(final int applicationId) {
	//		Application result;
	//
	//		result = this.applicationRepository.findOne(applicationId);
	//
	//		return result;
	//	}
	//
	//	public Collection<Application> findAll() {
	//		Collection<Application> result;
	//
	//		result = this.applicationRepository.findAll();
	//
	//		Assert.notNull(result);
	//
	//		return result;
	//	}
	//
	//	//Other business methods-------------------------------------------
	//	protected void addCreditCard(final Application application, final CreditCard creditCard) {
	//		Assert.isTrue(this.utilityService.checkCreditCard(creditCard));
	//		this.utilityService.checkActorIsBanned(this.handyWorkerService.findByPrincipal());
	//		application.setCreditCard(creditCard);
	//	}
	//
	//	protected void checkByPrincipal(final Application application) {
	//		HandyWorker handyWorker;
	//
	//		handyWorker = this.handyWorkerService.findByPrincipal();
	//
	//		Assert.isTrue(handyWorker.equals(application.getHandyWorker()));
	//	}
	//
	//	public Application changeStatus(final Application application) {
	//		Assert.notNull(application);
	//		this.utilityService.checkActorIsBanned(this.customerService.findByPrincipal());
	//
	//		FixUpTask fixUpTask;
	//		final Collection<Application> applications;
	//
	//		this.messageService.messageToStatus(application, application.getStatus());
	//
	//		if (application.getStatus().equals("ACCEPTED")) {
	//			fixUpTask = application.getFixUpTask();
	//			applications = fixUpTask.getApplications();
	//			applications.remove(application);
	//			for (final Application a : applications) {
	//				a.setStatus("REJECTED");
	//				this.changeStatus(a);
	//			}
	//			Assert.isTrue(this.utilityService.checkCreditCard(application.getCreditCard()), "Tarjeta de credito no valida");
	//		} else
	//			application.setStatus("REJECTED");
	//
	//		return application;
	//	}
	//
	//	//Req 12.5.4
	//	public Double[] findDataOfApplicationPerFixUpTask() {
	//		Double[] result;
	//
	//		result = this.applicationRepository.findDataOfApplicationPerFixUpTask();
	//
	//		return result;
	//	}
	//
	//	//Req 12.5.4
	//	public Double[] findDataOfApplicationPrice() {
	//		Double[] result;
	//
	//		result = this.applicationRepository.findDataOfApplicationPrice();
	//
	//		return result;
	//	}
	//
	//	//Req 12.5.5
	//	public Double findRatioPendingApplications() {
	//		Double result;
	//
	//		result = this.applicationRepository.findRatioPendingApplications();
	//
	//		return result;
	//	}
	//
	//	//Req 12.5.6
	//	public Double findRatioAcceptedApplications() {
	//		Double result;
	//
	//		result = this.applicationRepository.findRatioAcceptedApplications();
	//
	//		return result;
	//	}
	//
	//	//Req 12.5.7
	//	public Double findRatioRejectedApplications() {
	//		Double result;
	//
	//		result = this.applicationRepository.findRatioRejectedApplications();
	//
	//		return result;
	//	}
	//
	//	//Req 12.5.8
	//	public Double findRatioPendingApplicationsNotChangeStatus() {
	//		Double result;
	//
	//		result = this.applicationRepository.findRatioPendingApplicationsNotChangeStatus();
	//
	//		return result;
	//	}
	//
	//	public Application findAcceptedApplication(final int fixUpTaskId) {
	//		Application result;
	//
	//		result = this.applicationRepository.findAcceptedApplication(fixUpTaskId);
	//
	//		return result;
	//	}
	//	public Page<Application> findApplicationByHandyWorker(final Pageable pageable) {
	//		Page<Application> applications;
	//		HandyWorker handyWorker;
	//
	//		handyWorker = this.handyWorkerService.findByPrincipal();
	//		applications = this.applicationRepository.findApplicationByHandyWorker(handyWorker.getId(), pageable);
	//
	//		return applications;
	//
	//	}
	//	public Page<Application> findApplicationByFixUpTask(final int fixUpTaskId, final Pageable pageable) {
	//		Page<Application> result;
	//
	//		result = this.applicationRepository.findApplicationByFixUpTask(fixUpTaskId, pageable);
	//
	//		return result;
	//	}
	//
	//	public Collection<Application> findApplicationByHWFixUpTask(final int fixUpTaskId) {
	//		Collection<Application> result;
	//		HandyWorker handyWorker;
	//
	//		handyWorker = this.handyWorkerService.findByPrincipal();
	//		result = this.applicationRepository.findApplicationByHWFixUpTask(handyWorker.getId(), fixUpTaskId);
	//
	//		return result;
	//	}

}
