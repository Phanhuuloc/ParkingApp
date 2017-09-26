package com.sungwoo.aps.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "barEntityManagerFactory",
        transactionManagerRef = "barTransactionManager",
        basePackages = {"com.sungwoo.aps.repo.security"}
)
public class SecurityDbConfig {

    @Value("${security.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;

    @Bean(name = "securityDataSource")
    @ConfigurationProperties(prefix = "security.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("securityDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return builder
                .dataSource(dataSource)
                .packages("com.sungwoo.aps.domain.security")
                .persistenceUnit("testparkinglotdb")
                .properties(properties)
                .build();
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }
}
