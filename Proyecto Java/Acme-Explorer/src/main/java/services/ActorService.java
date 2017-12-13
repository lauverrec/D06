
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ActorRepository				actorRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Constructors -----------------------------------------------------------
	public ActorService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------
	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		Actor result;

		result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		this.actorRepository.delete(actor);
	}
	// Other business methods -------------------------------------------------

	public Actor findPrincipal() {
		Actor result;
		int userAccountId;
		userAccountId = LoginService.getPrincipal().getId();
		result = this.actorRepository.findActorByUseraccount(userAccountId);
		Assert.notNull(result);

		return result;
	}
	public boolean isAuthenticated() {
		boolean result = false;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		if (userAccount != null)
			result = true;
		return result;
	}
	public Actor findActorByUseraccount(int id) {
		Actor res;
		res = this.findActorByUseraccount(id);
		return res;

	}

	public Boolean isSpam(Collection<String> text) {

		Boolean res = false;
		Collection<String> spamWords;
		spamWords = this.configurationSystemService.spamWord();

		for (String spam : spamWords)
			if (text.contains(spam)) {
				res = true;
				break;
			}

		return res;
	}

	public Boolean actorIsSpam(Actor actor) {
		Boolean result;
		Collection<String> words;
		Collection<String> spamWords;

		spamWords = this.configurationSystemService.spamWord();
		words = new ArrayList<String>();
		result = false;

		words.add(actor.getAddress());
		words.add(actor.getEmail());
		words.add(actor.getName());
		words.add(actor.getSurname());

		for (SocialIdentity social : actor.getSocialIdentities()) {
			words.add(social.getLink());
			words.add(social.getName());
			words.add(social.getNick());
		}

		for (String spam : spamWords)
			if (words.contains(spam)) {
				result = true;
				break;

			}
		return result;
	}
}
