package de.fxnn.posthorn.business.backend.boundary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.fxnn.posthorn.business.backend.controller.MailStorage;
import de.fxnn.posthorn.business.mail.entity.Mail;
import de.fxnn.posthorn.business.mail.entity.MailId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailStorageService {

  @Autowired
  protected Collection<MailStorage> mailStorages;

  public List<MailId> findAllMailIds() {
    List<MailId> result = new ArrayList<>();

    for (MailStorage mailStorage : mailStorages) {
      mailStorage.findAllMailIds().forEach(result::add);
    }

    return result;
  }

  public Mail getMail(MailId mailId) {
    return findMailByMailId(mailId);
  }

  private Mail findMailByMailId(MailId mailId) {
    for (MailStorage mailStorage : mailStorages) {
      Optional<Mail> mail = mailStorage.loadMail(mailId);
      if (mail.isPresent()) {
        return mail.get();
      }
    }

    throw new IllegalArgumentException("No mail with mailId [" + mailId + "]");
  }

}
