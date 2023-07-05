package com.ScheduleMaster.todo.controller;

import com.ScheduleMaster.todo.dto.Liste;
import com.ScheduleMaster.todo.service.ListeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/listes")
public class ListeController {
    @Autowired
    private ListeService listeService;

    @PostMapping
    public Liste ajouterListe(@RequestBody @Valid Liste liste) {
        return listeService.ajouterListe(liste);
    }

    @GetMapping
    public List<Liste> recupererListes() {
        return listeService.recupererListes();
    }
}
