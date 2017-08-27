package org.idea.analytics.configuration;

import org.idea.analytics.listeners.JobCompletionNotificationListener;
import org.idea.analytics.model.Entities;
import org.idea.analytics.model.Url;
import org.idea.analytics.processor.EntitiesRequestProcessor;
import org.idea.analytics.reader.UrlReader;
import org.idea.analytics.writer.EntitiesWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Import(StandaloneInfrastructureConfiguration.class)
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Bean
    public FlatFileItemReader<Url> reader() {
        return new UrlReader();
    }

    @Bean
    public EntitiesRequestProcessor processor() {
        return new EntitiesRequestProcessor();
    }

    @Bean
    public EntitiesWriter writer() {
        return new EntitiesWriter();
    }

    @Bean
    public Job importUrls(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUrls")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Url, Entities>chunk (1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
