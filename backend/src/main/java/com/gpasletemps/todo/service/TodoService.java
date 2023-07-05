package com.ScheduleMaster.todo.service;

import com.ScheduleMaster.todo.dto.Todo;

import java.util.List;

public interface TodoService {
    Todo ajouterUnTodo(Todo todo);

    Todo modifierUnTodo(Todo todo, Integer id);

    Todo recupererTodoAvecId(Integer id);

    List<Todo> recupererTodos();

    Integer supprimerTodo(Integer id);
}
