
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
		MessageFolder folder;
		MessageFolder newFolder;
		folder = this.messageFolderService.create();
		folder.setName("prueba");

		newFolder = this.messageFolderService.save(folder);
		Assert.notNull(newFolder);
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
		Collection<MessageFolder> folders;

		folders = this.messageFolderService.createDefaultFolders();
		Assert.notNull(folders);
		Assert.notEmpty(folders);

	}

	@Test
	public void testFindAllByActorAuthenticate() {
		this.authenticate("administrator1");
		Collection<MessageFolder> messageFolders;

		messageFolders = this.messageFolderService.findAllByActorAutenticate();
		Assert.notNull(messageFolders);
		Assert.notEmpty(messageFolders);

	}
}
