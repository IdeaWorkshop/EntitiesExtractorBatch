package org.idea.analytics.writer;

import org.idea.analytics.model.Entities;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class EntitiesWriter implements ItemWriter<Entities> {
    @Override
    public void write(List<? extends Entities> list) throws Exception {
        list.forEach(entities -> {
            System.out.println(entities);
        });
    }
}
