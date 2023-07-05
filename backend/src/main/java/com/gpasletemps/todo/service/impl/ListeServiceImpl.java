package com.ScheduleMaster.todo.service.impl;

import com.ScheduleMaster.todo.dto.Liste;
import com.ScheduleMaster.todo.repository.ListeRepository;
import com.ScheduleMaster.todo.service.ListeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ListeServiceImpl implements ListeService {
    @Inject
    private ListeRepository listeRepository;

    @Override
    public Liste ajouterListe(Liste liste) {
        Integer newId = listeRepository.ajouterListe(liste);
        liste.setId(newId);
        return liste;
    }

    @Override
    public List<Liste> recupererListes() {
        return listeRepository.recupererListes();
    }

    @Override
    public Liste recupererListeParId(Integer id) {
        return listeRepository.recupererListeParId(id);
    }
}
