
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.MessageFolder;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageFolderServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------
	@Autowired
	private MessageFolderService	messageFolderService;
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SponsorService			sponsorService;


	// Supporting services ----------------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("explorer5");
		MessageFolder messageFolder;
		messageFolder = this.messageFolderService.create();
		Assert.notNull(messageFolder);
		this.unauthenticate();
	}

	@Test
	public void testSave() {
		this.authenticate("administrator1");
		final MessageFolder messageFolder = this.messageFolderService.create();
		messageFolder.setModifiable(true);
		messageFolder.setName("Coordinador");
		this.messageFolderService.save(messageFolder);

		this.unauthenticate();
	}

	@Test
	public void testDelete() {
		this.authenticate("administrator1");
		Administrator administratorPrincipal;
		MessageFolder messageFolder;
		administratorPrincipal = this.administratorService.findByPrincipal();
		messageFolder = administratorPrincipal.getMessagesFolders().iterator().next();
		messageFolder.setModifiable(true);
		this.messageFolderService.delete(messageFolder);

		this.unauthenticate();
	}

	@Test
	public void testCreateDefaultChapter() {
		this.authenticate("sponsor1");
		Sponsor sponsor;
		sponsor = this.sponsorService.findByPrincipal();
		this.messageFolderService.createDefaultFolders();
		Assert.isTrue(sponsor.getMessagesFolders().size() >= 2);

	}
	@Test
	public void testFindAllByActorAuthenticate() {
		this.authenticate("sponsor1");
		Collection<MessageFolder> messageFolders;

		messageFolders = this.messageFolderService.findAllByActorAutenticate();
		Assert.notNull(messageFolders);
		Assert.notEmpty(messageFolders);
		for (MessageFolder me : messageFolders)
			System.out.println(me.getName());

	}

	@Test
	public void testUpdate() {
		this.authenticate("sponsor1");
		MessageFolder folderModify;
		folderModify = this.messageFolderService.findOne(131072);
		folderModify.setName("asf");

	}
}
