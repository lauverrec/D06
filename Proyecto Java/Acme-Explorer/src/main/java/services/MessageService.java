
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MessageRepository			messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService				actorService;

	@Autowired
	private MessageFolderService		messageFolderService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Constructors -----------------------------------------------------------
	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		final Actor sender = this.actorService.findPrincipal();
		Message message;
		message = new Message();
		message.setMoment(new Date());
		message.setSender(sender);
		message.setMessageFolder(this.messageFolderService.returnDefaultFolder(sender, "Out box"));
		return message;
	}
	public Message findOne(final int messageId) {
		Assert.isTrue(messageId != 0);

		Message result;

		result = this.messageRepository.findOne(messageId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;
		result = this.messageRepository.findAll();
		return result;

	}

	public Message save(Message message) {
		Assert.notNull(message.getSender());
		Assert.notNull(message.getRecipient());
		Assert.notNull(message);

		Message messageBD;
		Message messageNew;
		Date current;

		messageNew = null;

		if (message.getId() != 0) {
			Assert.notNull(message.getMessageFolder());
			messageBD = this.messageRepository.findOne(message.getId());

			if (message.getMessageFolder() != messageBD.getMessageFolder()) {
				messageNew = this.messageRepository.save(message);
				Assert.notNull(messageNew);
				message = messageNew;

			}
		}
		if (message.getId() == 0) {
			current = new Date(System.currentTimeMillis() - 1000);
			message.setMoment(current);
			Actor sender;
			Actor recipient;

			sender = message.getSender();
			recipient = message.getRecipient();

			MessageFolder messageFolderInboxOfRecipient;
			MessageFolder messageFolderOutboxOfSender;
			MessageFolder messageFolderSpamOfRecipient;

			Message messageNewInbox;
			Message messageNewOutbox;
			Message messageNewSpambox;

			messageFolderInboxOfRecipient = this.messageFolderService.returnDefaultFolder(recipient, "In box");
			messageFolderOutboxOfSender = this.messageFolderService.returnDefaultFolder(sender, "Out box");
			messageFolderSpamOfRecipient = this.messageFolderService.returnDefaultFolder(recipient, "Spam box");

			if (this.MessageisSpam(message)) {

				message.setMessageFolder(messageFolderSpamOfRecipient);
				messageNewSpambox = this.messageRepository.save(message);

				Assert.notNull(messageNewSpambox);
				messageFolderSpamOfRecipient.getMessages().add(messageNewSpambox);

			} else {
				message.setMessageFolder(messageFolderInboxOfRecipient);
				messageNewInbox = this.messageRepository.save(message);

				Assert.notNull(messageNewInbox);
				messageFolderInboxOfRecipient.getMessages().add(messageNewInbox);
			}

			message.setMessageFolder(messageFolderOutboxOfSender);
			messageNewOutbox = this.messageRepository.save(message);

			Assert.notNull(messageNewOutbox);
			messageFolderOutboxOfSender.getMessages().add(messageNewOutbox);

			message = messageNewOutbox;
		}
		return message;

	}
	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Actor actorPrincipal;
		actorPrincipal = this.actorService.findPrincipal();
		Assert.isTrue(actorPrincipal.getMessagesFolders().contains(message.getMessageFolder()));

		this.messageRepository.delete(message);
	}
	// Other business methods -------------------------------------------------
	public boolean MessageisSpam(final Message message) {
		//Comprueba si un mensaje es spam
		boolean bool;
		final Collection<String> words;
		Collection<String> spams;

		bool = false;
		words = new ArrayList<String>();
		words.add(message.getBody());
		words.add(message.getSubject());

		spams = this.SpamWord();

		for (final String w : words)
			for (final String spam : spams) {
				final int i = w.indexOf(spam);
				if (i != -1)
					bool = true;
			}
		return bool;

	}
	public Collection<String> SpamWord() {
		//devuelve la collecion de palabras spam
		Collection<String> res;
		res = this.configurationSystemService.spamWord();
		return res;

	}
	public Collection<Message> recipientAllByActor(final int idActor) {
		//Todos los mensajes recibidos de un actor
		Collection<Message> res;
		res = this.messageRepository.recipientAllByActor(idActor);
		return res;

	}
	public Collection<Message> senderAllByActor(final int idActor) {
		//Todos los mensajes enviados de un actor
		Collection<Message> res;
		res = this.messageRepository.senderAllByActor(idActor);
		return res;

	}

	public void ChangeMessageOfFolder(final Message message, final MessageFolder folder) {

		if (!message.getMessageFolder().equals(folder))
			message.setMessageFolder(folder);
	}

	public String recivemessage(final Message message) {
		//Comprueba si el mensaje es spam y devuelve el nombre de la carpeta a la que debe ir
		String folderName = "in box";
		if (this.MessageisSpam(message) == true)
			folderName = "spam box";

		return folderName;

	}

	public Collection<Message> messagesOfFolder(int messageFolderId) {
		Collection<Message> messages;

		messages = this.messageRepository.messagesOfFolder(messageFolderId);
		return messages;
	}

}
