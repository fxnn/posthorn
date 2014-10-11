package de.fxnn.silbendrechsler.business.page.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class Page extends ResourceSupport {
    
    private final String name;
    
    private String content;

    @JsonCreator
    public Page(@JsonProperty String name) {
        this.name = name;
    }
    
}
