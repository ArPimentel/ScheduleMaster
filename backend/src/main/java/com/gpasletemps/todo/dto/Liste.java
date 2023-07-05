package com.ScheduleMaster.todo.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;
@Component
public class Liste {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonProperty
    @NotEmpty
    private String nom;

    public Liste() {
    }


    @JsonCreator
    public Liste(@JsonProperty("id") Integer id, @JsonProperty("nom") String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}



