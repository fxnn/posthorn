package de.fxnn.posthorn;

import java.util.Collections;

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
    result.setOrder(Integer.MIN_VALUE+1); // andernfalls wird dieser Handler nicht berücksichtigt
    result.setUrlMap(Collections.singletonMap("/js/*", javascriptHandler()));
    return result;
  }

  @Bean
  public ResourceHttpRequestHandler javascriptHandler() {
    ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
    resourceHttpRequestHandler.setLocations(asList(javascriptResource));
    return resourceHttpRequestHandler;
  }

}
