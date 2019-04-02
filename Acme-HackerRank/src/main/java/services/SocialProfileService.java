
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.Actor;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialProfileRepository	socialProfileRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------

	public SocialProfileService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	protected SocialProfile findOne(final int socialProfileId) {
		SocialProfile result;

		result = this.socialProfileRepository.findOne(socialProfileId);
		Assert.notNull(result);

		return result;
	}

	public SocialProfile findOneDisplay(final int socialProfileId) {
		SocialProfile result;
		Actor actor;

		result = this.findOne(socialProfileId);

		actor = result.getActor();
		if (actor.getUserAccount().getAuthorities().toString().equals("[ADMIN]"))
			this.checkByPrincipal(result);

		return result;
	}

	public SocialProfile findOneToEdit(final int socialProfileId) {
		SocialProfile result;

		result = this.findOne(socialProfileId);
		this.checkByPrincipal(result);

		return result;
	}

	protected Collection<SocialProfile> findAll() {
		Collection<SocialProfile> result;

		result = this.socialProfileRepository.findAll();

		return result;
	}

	public SocialProfile create() {
		SocialProfile result;
		final Actor actor;

		actor = this.actorService.findPrincipal();

		result = new SocialProfile();
		result.setActor(actor);

		return result;
	}

	public SocialProfile save(final SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		this.checkByPrincipal(socialProfile);

		SocialProfile result;
		String nick, socialNetwork, linkProfile;

		nick = socialProfile.getNick().trim();
		socialNetwork = socialProfile.getSocialNetwork().trim();
		linkProfile = socialProfile.getLinkProfile().trim();

		socialProfile.setNick(nick);
		socialProfile.setSocialNetwork(socialNetwork);
		socialProfile.setLinkProfile(linkProfile);

		result = this.socialProfileRepository.save(socialProfile);

		return result;
	}

	public void delete(final SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		Assert.isTrue(this.socialProfileRepository.exists(socialProfile.getId()));
		this.checkByPrincipal(socialProfile);

		this.socialProfileRepository.delete(socialProfile);
	}

	// Other business methods -------------------------------------------------
	public Collection<SocialProfile> findSocialProfilesByActor(final int actorId) {
		Collection<SocialProfile> result;

		Actor actor;

		actor = this.actorService.findOne(actorId);

		if (actor.getUserAccount().getAuthorities().toString().equals("[ADMIN]")) {
			final Actor principal;
			principal = this.actorService.findPrincipal();
			Assert.isTrue(principal.getId() == actor.getId());
		}

		result = this.socialProfileRepository.findSocialProfilesByActor(actorId);

		return result;
	}

	// private methods -----------------------------
	private void checkByPrincipal(final SocialProfile socialProfile) {
		final Actor actor;

		actor = this.actorService.findPrincipal();

		Assert.isTrue(socialProfile.getActor().equals(actor));
	}

}
