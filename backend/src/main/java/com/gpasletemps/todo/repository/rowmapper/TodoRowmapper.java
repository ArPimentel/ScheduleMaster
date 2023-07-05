package com.ScheduleMaster.todo.repository.rowmapper;

import com.ScheduleMaster.todo.dto.Liste;
import com.ScheduleMaster.todo.dto.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowmapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int i) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getInt("idTodo"));
        todo.setNom(rs.getString("nomTodo"));
        todo.setDescription(rs.getString("descriptionTodo"));
        todo.setTermine(rs.getBoolean("termineTodo"));
        todo.setDateFin(rs.getDate("dateFinTodo"));
        Liste liste = new Liste();
        liste.setId(rs.getInt("idListe"));
        liste.setNom(rs.getString("nomListe"));
        todo.setListe_id(liste.getId());
        todo.setListe(liste);
        return todo;
    }
}
