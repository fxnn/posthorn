package de.fxnn.silbendrechsler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@ComponentScan
@EnableAutoConfiguration
public class SilbendrechslerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SilbendrechslerApplication.class, args);
    }
    
}
