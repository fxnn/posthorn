package de.fxnn.posthorn.business.backend.controller;

import de.fxnn.posthorn.business.mail.entity.Mail;
import org.joda.time.DateTime;

public interface MailStorage extends MailBackend {

  Mail loadMail(String mailId);

  Iterable<String> findMailIdsOlderThan(DateTime dateTime);

  Iterable<String> findAllMailIds();

}
