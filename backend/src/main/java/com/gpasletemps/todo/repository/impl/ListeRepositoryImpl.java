package com.ScheduleMaster.todo.repository.impl;

import com.ScheduleMaster.todo.dto.Liste;
import com.ScheduleMaster.todo.exception.NotFoundException;
import com.ScheduleMaster.todo.repository.ListeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class ListeRepositoryImpl implements ListeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Inject
    public ListeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Integer ajouterListe(Liste liste) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nom", liste.getNom());
        String sql = "INSERT INTO liste(nom) VALUES(:nom)";
        namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder, new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public List<Liste> recupererListes() {
        return namedParameterJdbcTemplate.query(
                "SELECT id, nom FROM liste",
                new BeanPropertyRowMapper<>(Liste.class));
    }

    @Override
    public Liste recupererListeParId(Integer id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        List<Liste> result = namedParameterJdbcTemplate.query(
                "SELECT id, nom FROM liste WHERE id=:id",
                namedParameters,
                new BeanPropertyRowMapper<>(Liste.class));
        if (result.isEmpty()) {
            throw new NotFoundException("La liste avec l'identifiant " + id + " n'existe pas");
        }
        return result.get(0);
    }
}
