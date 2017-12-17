
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContactEmergencyRepository;
import domain.ContactEmergency;
import domain.Explorer;

@Service
@Transactional
public class ContactEmergencyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ContactEmergencyRepository	contactEmergencyRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ExplorerService				explorerService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Constructors -----------------------------------------------------------

	public ContactEmergencyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ContactEmergency create() {
		ContactEmergency result;

		result = new ContactEmergency();

		return result;
	}

	public Collection<ContactEmergency> findAll() {
		Collection<ContactEmergency> result;

		Assert.notNull(this.contactEmergencyRepository);
		result = this.contactEmergencyRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public ContactEmergency findOne(final int contactEmergencyId) {
		ContactEmergency result;

		result = this.contactEmergencyRepository.findOne(contactEmergencyId);
		Assert.notNull(result);

		return result;
	}

	public ContactEmergency save(final ContactEmergency contactEmergency) {
		Assert.notNull(contactEmergency);

		ContactEmergency result;
		Explorer explorer;
		explorer = this.explorerService.findByPrincipal();
		result = this.contactEmergencyRepository.save(contactEmergency);
		this.contactnotexisting(explorer, result);

		return result;
	}

	public void delete(final ContactEmergency contactEmergency) {
		final Collection<Explorer> explorersWithThisContact;
		Assert.notNull(contactEmergency);
		Assert.isTrue(contactEmergency.getId() != 0);
		Assert.isTrue(this.contactEmergencyRepository.exists(contactEmergency.getId()));
		//De esta manera falla:
		//Collection<ContactEmergency> contactsEmergency;
		//contactsEmergency = this.findContactsEmergencyByContactEmergencyId(contactEmergency.getId());
		//contactsEmergency.removeAll(contactsEmergency);
		explorersWithThisContact = this.explorerService.findExplorersByContactEmergencyId(contactEmergency.getId());
		for (final Explorer e : explorersWithThisContact)
			e.getContactsEmergency().remove(contactEmergency);
		this.contactEmergencyRepository.delete(contactEmergency);
	}
	// Other business methods -------------------------------------------------

	public void contactnotexisting(Explorer explorer, ContactEmergency contactemergency) {
		if (!explorer.getContactsEmergency().contains(contactemergency))
			explorer.getContactsEmergency().add(contactemergency);
	}

	public Boolean contactEmergencyContainsSpam(Explorer explorer) {
		Boolean result;
		Collection<ContactEmergency> contactsEmergency;
		Collection<String> words;
		Collection<String> spamWords;

		result = false;
		words = new ArrayList<String>();
		spamWords = this.configurationSystemService.spamWord();
		contactsEmergency = explorer.getContactsEmergency();

		for (ContactEmergency contact : contactsEmergency) {
			words.add(contact.getEmail());
			words.add(contact.getName());

		}
		for (String word : words)
			for (String spam : spamWords)
				if (word.indexOf(spam) > -1) {
					result = true;
					break;
				}
		return result;
	}
}
