/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fxnn.posthorn;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger
public class SwaggerConfig {

  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;

  @Bean
  public SwaggerSpringMvcPlugin customImplementation() {
    return new SwaggerSpringMvcPlugin(this.springSwaggerConfig) //
        .apiInfo(apiInfo()) //
        .includePatterns("/rest/.*");
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Posthorn REST API", "REST API for Posthorn WebMail client", null,
        "dev@fxnn.de", null, null);
  }
}
