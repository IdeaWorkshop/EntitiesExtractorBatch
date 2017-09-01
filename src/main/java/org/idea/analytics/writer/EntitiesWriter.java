package org.idea.analytics.writer;

import org.idea.analytics.model.Entities;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntitiesWriter implements ItemWriter<Entities> {

    private NamedParameterJdbcTemplate jdbcTemplate;
    public EntitiesWriter(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    private String insertPerson = "INSERT INTO PERSON(NAME) VALUES(:name)";
    private String insertLocation = "INSERT INTO LOCATION(NAME) VALUES(:name)";
    private String insertProduct = "INSERT INTO PRODUCT(NAME) VALUES(:name)";

    @Override
    public void write(List<? extends Entities> list) throws Exception {
        list.forEach(entities -> {
            System.out.println(entities);
            entities.getPersons().forEach((person) -> {
                Map<String, String> map = new HashMap<>();
                map.put("name", person.getMention());
                getJdbcTemplate().update(insertPerson, map);
            });

            entities.getLocations().forEach((location) ->{
                //getJdbcTemplate().update(insertLocation, location);
            });

            entities.getProducts().forEach((product) -> {
                //getJdbcTemplate().update(insertProduct, product);
            });


        });
    }
}
