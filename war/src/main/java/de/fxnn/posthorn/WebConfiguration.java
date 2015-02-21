package de.fxnn.posthorn;

import java.time.LocalDateTime;
import java.util.Collections;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import de.fxnn.posthorn.business.mail.entity.MailId;
import de.fxnn.posthorn.technical.Jackson8Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import static java.util.Arrays.asList;

@Configuration
public class WebConfiguration {

  @Value("classpath:/js/")
  Resource javascriptResource;

  @Bean
  public SimpleUrlHandlerMapping javascriptHandlerMapping() {
    SimpleUrlHandlerMapping result = new SimpleUrlHandlerMapping();
    result.setOrder(Integer.MIN_VALUE + 1); // andernfalls wird dieser Handler nicht berücksichtigt
    result.setUrlMap(Collections.singletonMap("/js/*", javascriptHandler()));
    return result;
  }

  @Bean
  public ResourceHttpRequestHandler javascriptHandler() {
    ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
    resourceHttpRequestHandler.setLocations(asList(javascriptResource));
    return resourceHttpRequestHandler;
  }

  @Bean
  public Jackson8Module jacksonCustomClassesModule() {
    Jackson8Module module = new Jackson8Module();

    // NOTE, that we prefer ISO strings, while jackson-datatype-jsr310 would give us arrays
    module.addStringSerializer(LocalDateTime.class, LocalDateTime::toString);

    module.addStringSerializer(MailId.class, MailId::toIdString);

    return module;
  }

  /**
   * (De)Serialization of JDK 8 API Classes
   */
  @Bean
  public Jdk8Module jacksonJdk8Module() {
    return new Jdk8Module();
  }

}
