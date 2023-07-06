package com.ScheduleMaster.todo.orchestrator.impl;

import com.ScheduleMaster.todo.dto.Liste;
import com.ScheduleMaster.todo.dto.Todo;
import com.ScheduleMaster.todo.orchestrator.ListeTodoService;
import com.ScheduleMaster.todo.service.ListeService;
import com.ScheduleMaster.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ListeTodoServiceImpl implements ListeTodoService {
    @Autowired
    private ListeService listeService;
    @Autowired
    private TodoService todoService;

    @Override
    public Todo ajouterTodo(Todo todo) {
        try {
            Liste listeT = listeService.recupererListeParId(todo.getListe_id());
            todo.setListe(listeT);
        } catch (EmptyResultDataAccessException e) {
            // Si la liste n'existe pas, on lance une exception avec le code d'erreur 403
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La liste spécifiée n'existe pas", e);
        }
        return todoService.ajouterUnTodo(todo);
    }
}
