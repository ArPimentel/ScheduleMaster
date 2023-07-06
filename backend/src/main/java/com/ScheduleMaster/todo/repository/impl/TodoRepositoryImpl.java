package com.ScheduleMaster.todo.repository.impl;

import com.ScheduleMaster.todo.dto.Todo;
import com.ScheduleMaster.todo.repository.TodoRepository;
import com.ScheduleMaster.todo.repository.rowmapper.TodoRowmapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
@ComponentScan
@Repository
public class TodoRepositoryImpl  implements TodoRepository {
    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer ajouterUnTodo(Todo todo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nom", todo.getNom())
                .addValue("description", todo.getDescription())
                .addValue("termine", todo.getTermine())
                .addValue("date", todo.getDateFin())
                .addValue("idListe", todo.getListe_id());

        String sql = "INSERT INTO todo(nom, description, termine, dateFin, liste_id) "
                + "VALUES(:nom, :description, :termine, :date, :idListe)";

        namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
        }


    @Override
    public Todo modifierUnTodo(Todo todo, Integer id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nom", todo.getNom())
                .addValue("description", todo.getDescription())
                .addValue("termine", todo.getTermine())
                .addValue("date", todo.getDateFin())
                .addValue("idListe", todo.getListe_id())
                .addValue("idTodo", id);
        String sql = "UPDATE todo SET nom=:nom, description=:description, termine=:termine,"
                + "dateFin=:date, liste_id=:idListe WHERE id=:idTodo";

        namedParameterJdbcTemplate.update(sql, namedParameters);
        return todo;
    }


    @Override
    public Todo recupererTodoAvecId(Integer id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        String sql = "SELECT todo.id AS idTodo, todo.nom AS nomTodo, todo.description AS descriptionTodo, "
                + "todo.termine AS termineTodo, todo.dateFin AS dateFinTodo, liste.id AS idListe, "
                + "liste.nom AS nomListe FROM todo INNER JOIN liste ON todo.liste_id = liste.id WHERE todo.id=:id";

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TodoRowmapper());
    }

    @Override
    public List<Todo> recupererTodos() {
        String sql = "SELECT todo.id AS idTodo, todo.nom AS nomTodo, todo.description AS descriptionTodo, "
                + "todo.termine AS termineTodo, todo.dateFin AS dateFinTodo, liste.id AS idListe, "
                + "liste.nom AS nomListe FROM todo INNER JOIN liste ON todo.liste_id = liste.id";

        return namedParameterJdbcTemplate.query(sql, new TodoRowmapper());
    }

    @Override
    public void supprimerTodo(Integer id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM todo where id=:id", namedParameters);
    }
}



