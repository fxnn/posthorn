package de.fxnn.posthorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class PosthornApplication {

  public static void main(String[] args) {
    SpringApplication.run(PosthornApplication.class, args);
  }


  //  @Configuration
  //  public static class JavaScriptConfiguration implements ResourceLoaderAware {
  //
  //    private ResourceLoader resourceLoader;
  //
  //    @Bean
  //    public SimpleUrlHandlerMapping faviconHandlerMapping() {
  //      SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
  //      mapping.setOrder(Integer.MIN_VALUE + 1);
  //      mapping.setUrlMap(Collections.singletonMap("**/favicon.ico", faviconRequestHandler()));
  //      return mapping;
  //    }
  //
  //    @Override
  //    public void setResourceLoader(ResourceLoader resourceLoader) {
  //      this.resourceLoader = resourceLoader;
  //    }
  //
  //    @Bean
  //    public ResourceHttpRequestHandler faviconRequestHandler() {
  //      ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
  //      requestHandler.setLocations(getLocations());
  //      return requestHandler;
  //    }
  //
  //    private List<Resource> getLocations() {
  //      List<Resource> locations = new ArrayList<Resource>(
  //          CLASSPATH_RESOURCE_LOCATIONS.length + 1);
  //      for (String location : CLASSPATH_RESOURCE_LOCATIONS) {
  //        locations.add(this.resourceLoader.getResource(location));
  //      }
  //      locations.add(new ClassPathResource("/"));
  //      return Collections.unmodifiableList(locations);
  //    }
  //
  //  }

}
