package de.fxnn.posthorn.business.backend.boundary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.fxnn.posthorn.business.backend.controller.MailStorage;
import de.fxnn.posthorn.business.mail.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailStorageService {

  @Autowired
  protected Collection<MailStorage> mailStorages;

  public List<String> findAllMailIds() {
    List<String> result = new ArrayList<>();

    for (MailStorage mailStorage : mailStorages) {
      mailStorage.findAllMailIds().forEach(result::add);
    }

    return result;
  }

  public Mail getMail(String indexMailId) {
    return findMailByIndexMailId(indexMailId);
  }

  private Mail findMailByIndexMailId(String indexMailId) {
    for (MailStorage mailStorage : mailStorages) {
      // TODO: Eventually, we need to have our own index of mails, so to give only the backendMailId to backend
      Optional<Mail> mail = mailStorage.loadMail(indexMailId);
      if (mail.isPresent()) {
        mail.get().setIndexMailId(indexMailId);
        return mail.get();
      }
    }

    throw new IllegalArgumentException("No mail with id [" + indexMailId + "]");
  }

}
