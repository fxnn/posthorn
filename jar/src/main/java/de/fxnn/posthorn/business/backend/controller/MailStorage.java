package de.fxnn.posthorn.business.backend.controller;

import java.util.Optional;

import de.fxnn.posthorn.business.mail.entity.Mail;
import org.joda.time.DateTime;

public interface MailStorage extends MailBackend {

  /**
   * NOTE, that the {@link Mail#indexMailId} parameter SHOULD not be set on the result, as it isn't the matter of the
   * backend.
   */
  Optional<Mail> loadMail(String backendMailId);

  /**
   * NOTE, that the returned strings are backendMailIds that can be given to {@link #loadMail(String)}.
   */
  Iterable<String> findMailIdsNewerThan(DateTime dateTime);

  /**
   * NOTE, that the returned strings are backendMailIds that can be given to {@link #loadMail(String)}.
   */
  Iterable<String> findAllMailIds();

}
