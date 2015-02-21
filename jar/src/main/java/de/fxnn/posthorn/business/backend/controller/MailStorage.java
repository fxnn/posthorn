package de.fxnn.posthorn.business.backend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import de.fxnn.posthorn.business.mail.entity.Mail;
import de.fxnn.posthorn.business.mail.entity.MailId;

public interface MailStorage extends MailBackend {

  Optional<Mail> loadMail(MailId mailId);

  Iterable<MailId> findMailIdsNewerThan(LocalDateTime dateTime);

  Iterable<MailId> findAllMailIds();

}
