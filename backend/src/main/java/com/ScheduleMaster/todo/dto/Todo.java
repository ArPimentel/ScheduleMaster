package com.ScheduleMaster.todo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@Component
public class Todo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotEmpty
    private String nom;

    private String description;

    private Boolean termine;

    private Date dateFin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer liste_id;

    private Liste liste;

    public Todo() {

    }

    @JsonCreator
    public Todo(@JsonProperty ("id") Integer id, @JsonProperty ("nom") String nom, @JsonProperty ("description") String description, @JsonProperty ("termine") Boolean termine, @JsonProperty ("dateFin") Date dateFin, @JsonProperty ("liste_id") Integer liste_id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.termine = termine;
        this.dateFin = dateFin;
        this.liste_id = liste_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTermine() {
        return termine;
    }

    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getListe_id() {
        return liste_id;
    }

    public void setListe_id(Integer liste_id) {
        this.liste_id = liste_id;
    }

    public Liste getListe() {
        return this.liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }
}


