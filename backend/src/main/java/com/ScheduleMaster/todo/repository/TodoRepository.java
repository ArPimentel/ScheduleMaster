package com.ScheduleMaster.todo.repository;

import com.ScheduleMaster.todo.dto.Todo;

import java.util.List;

public interface TodoRepository {
    Integer ajouterUnTodo(Todo todo);

    Todo modifierUnTodo(Todo todo, Integer id);

    Todo recupererTodoAvecId(Integer id);

    List<Todo> recupererTodos();

    void supprimerTodo(Integer id);
}
