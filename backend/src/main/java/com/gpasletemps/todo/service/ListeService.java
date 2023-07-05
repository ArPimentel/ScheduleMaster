package com.ScheduleMaster.todo.service;

import com.ScheduleMaster.todo.dto.Liste;

import java.util.List;

public interface ListeService {
    Liste ajouterListe(Liste liste);

    List<Liste> recupererListes();

    Liste recupererListeParId(Integer id);
}
