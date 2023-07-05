package com.ScheduleMaster.todo.service.impl;

import com.ScheduleMaster.todo.dto.Todo;
import com.ScheduleMaster.todo.repository.TodoRepository;
import com.ScheduleMaster.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo ajouterUnTodo(Todo todo) {
        Integer newId = todoRepository.ajouterUnTodo(todo);
        todo.setId(newId);
        return todo;
    }

    @Override
    public Todo modifierUnTodo(Todo todo, Integer id) {
        try {
            Todo todo1 = todoRepository.recupererTodoAvecId(id);
            System.out.println(todo1);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        Todo todoModifie = todoRepository.modifierUnTodo(todo, id);
        todoModifie.setId(id);
        return todoModifie;
    }

    @Override
    public Todo recupererTodoAvecId(Integer id) {
        try {
            return todoRepository.recupererTodoAvecId(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Todo> recupererTodos() {
        return todoRepository.recupererTodos();
    }

    @Override
    public Integer supprimerTodo(Integer id) {
        try {
            todoRepository.recupererTodoAvecId(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        todoRepository.supprimerTodo(id);
        return id;
    }
}
