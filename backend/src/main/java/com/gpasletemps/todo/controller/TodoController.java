
package com.ScheduleMaster.todo.controller;

import com.ScheduleMaster.todo.dto.Todo;
import com.ScheduleMaster.todo.exception.BadRequestException;
import com.ScheduleMaster.todo.orchestrator.ListeTodoService;
import com.ScheduleMaster.todo.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
public class TodoController {

    private static final Integer ALL_TODOS = 0;
    private static final Integer ONLY_TODOS_FINISHED = 1;
    private static final Integer ONLY_TODOS_NOT_FINISHED = 2;

    private final TodoService todoService;
    private final ListeTodoService listeTodoService;

    @Autowired
    public TodoController(TodoService todoService, ListeTodoService listeTodoService) {
        this.todoService = todoService;
        this.listeTodoService = listeTodoService;
    }

    @PostMapping
    public Todo ajouterUnTodo(@Valid @RequestBody Todo todo) throws BadRequestException {
        Todo todoT = listeTodoService.ajouterTodo(todo);
        if (todoT == null) {
            throw new BadRequestException("Erreur dans les paramètres de la requête");
        }
        return todoT;
    }

    @GetMapping("/{id}")
    public Todo recupererTodoAvecId(@PathVariable Integer id) throws BadRequestException {
        Todo todo = todoService.recupererTodoAvecId(id);
        if (todo == null) {
            throw new BadRequestException("Erreur dans les paramètres de la requête");
        }
        System.out.println(todo);
        return todo;
    }

    @GetMapping
    public List<Todo> recupererTodos(@RequestParam(value = "finished", required = false) Integer finished) {
        finished = finished == null || finished < ALL_TODOS || finished > ONLY_TODOS_NOT_FINISHED ? ALL_TODOS : finished;
        List<Todo> todos = todoService.recupererTodos();
        List<Todo> newTodos = new ArrayList<>();
        for (Todo todo : todos) {
            boolean estTermine = todo.getTermine();
            if ((finished.equals(ONLY_TODOS_FINISHED) && estTermine) || (finished.equals(ONLY_TODOS_NOT_FINISHED) && !estTermine) || finished.equals(ALL_TODOS)) {
                newTodos.add(todo);
            }
        }
        return newTodos;
    }

    @PutMapping("/{id}")
    public Todo modifierUnTodo(@Valid @RequestBody Todo todo, @PathVariable @Min(1) Integer id) throws BadRequestException {
        Todo todoModifie = todoService.modifierUnTodo(todo, id);
        if (todoModifie == null) {
            throw new BadRequestException("Erreur dans les paramètres de la requête");
        }
        return todoModifie;
    }

    @DeleteMapping("/{id}")
    public void supprimerTodo(@PathVariable Integer id) throws BadRequestException {
        Integer idTodoSuppr = todoService.supprimerTodo(id);
        if (idTodoSuppr == null) {
            throw new BadRequestException("Erreur dans les paramètres de la requête");
        }
    }
}
