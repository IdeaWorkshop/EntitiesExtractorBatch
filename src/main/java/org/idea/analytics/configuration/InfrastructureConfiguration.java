package org.idea.analytics.configuration;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public interface InfrastructureConfiguration {
    @Bean
    public abstract DataSource dataSource();

}
