package com.ScheduleMaster.todo.repository;

import com.ScheduleMaster.todo.dto.Liste;

import java.util.List;

public interface ListeRepository {
    Integer ajouterListe(Liste liste);

    List<Liste> recupererListes();

    Liste recupererListeParId(Integer id);
}
