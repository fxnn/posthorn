package de.fxnn.posthorn.business.mail.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class Mail extends ResourceSupport {

  private MailId mailId;

  private List<String> senders = Collections.emptyList();

  private List<String> recipients = Collections.emptyList();

  private List<String> secondaryRecipients = Collections.emptyList();

  private List<String> hiddenRecipients = Collections.emptyList();

  private LocalDateTime dateTimeOfReception;

  private LocalDateTime dateTimeOfSending;

  /**
   * The host who originally sent this mail.
   */
  private String sendingHost;

  /**
   * The hosts who transported the mail, in order.
   */
  private List<String> relayHosts = Collections.emptyList();

  /**
   * The host through which this mail was received.
   * <p>
   * NOTE that while this mail might have multiple recipient, it may only have on receivingHost.
   */
  private String receivingHost;

}
