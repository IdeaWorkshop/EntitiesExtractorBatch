package org.idea.analytics.processor;


import org.idea.analytics.model.Entities;
import org.idea.analytics.model.Url;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.web.client.RestTemplate;

public class EntitiesRequestProcessor implements ItemProcessor<Url,Entities> {

    private RestTemplate restTemplate;
    public EntitiesRequestProcessor() {
        restTemplate = new RestTemplate();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public Entities process(Url url) throws Exception {
        String restApiEndPoint = "http://52CQYX1J:8080/entities/?url="+url.getUrl();

        Entities entities = getRestTemplate().getForObject(restApiEndPoint, Entities.class);
        return entities;
    }
}
